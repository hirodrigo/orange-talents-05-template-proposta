package br.com.zupacademy.rodrigo.proposta.cartao;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
	
	@PostMapping("/{uuid}/bloquear")
	private ResponseEntity<?> blockCartao(@PathVariable String uuid, HttpServletRequest request) {
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

}
