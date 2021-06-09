package br.com.zupacademy.rodrigo.proposta.cartao;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.rodrigo.proposta.avisoviagem.AvisarViagemRequest;
import br.com.zupacademy.rodrigo.proposta.avisoviagem.AvisoViagem;
import br.com.zupacademy.rodrigo.proposta.avisoviagem.AvisoViagemRequest;
import br.com.zupacademy.rodrigo.proposta.cartao.bloqueio.BloquearCartao;
import br.com.zupacademy.rodrigo.proposta.cartao.bloqueio.Bloqueio;
import feign.FeignException;

@RestController
@RequestMapping("/api/cartoes")
public class CartaoController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private BloquearCartao bloquearCartao;

	@Autowired
	private CartaoClient cartaoClient;

	@PostMapping("/{uuid}/bloquear")
	private ResponseEntity<?> createBloqueioCartao(@PathVariable String uuid, HttpServletRequest request) {
		Optional<Cartao> possivelCartao = cartaoRepository.findByUuid(uuid);
		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Cartao cartao = possivelCartao.get();

		if (cartao.estaBloqueado()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Este cartão já foi bloqueado anteriormente.");
		}

		try {
			bloquearCartao.bloquear(cartao, request);
		} catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Não foi possível bloquear o cartão agora, tente novamente mais tarde.");
		}
		String ipAddress = request.getRemoteAddr();
		String userAgent = request.getHeader("User-Agent");

		cartao.bloquearCartao(new Bloqueio(ipAddress, userAgent, cartao));
		cartaoRepository.save(cartao);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/{uuid}/avisoviagens")
	private ResponseEntity<?> createAvisoViagem(@PathVariable String uuid,
			@Valid @RequestBody AvisoViagemRequest request, HttpServletRequest hsr) {
		Optional<Cartao> possivelCartao = cartaoRepository.findByUuid(uuid);
		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Cartao cartao = possivelCartao.get();

		AvisarViagemRequest avisarViagemRequest = request.toAPIRequest();
		try {
			cartaoClient.avisarViagem(cartao.getnCartao(), avisarViagemRequest);
		} catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Não foi possível criar o aviso de viagem, tente novamente mais tarde.");
		}

		String ipAddress = hsr.getRemoteAddr();
		String userAgent = hsr.getHeader("User-Agent");

		AvisoViagem avisoViagem = request.toModel(ipAddress, userAgent, cartao);
		cartao.adicionarAvisoViagem(avisoViagem);
		cartaoRepository.save(cartao);

		return ResponseEntity.ok().build();
	}

}
