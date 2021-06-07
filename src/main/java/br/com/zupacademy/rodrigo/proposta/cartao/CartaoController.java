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

import br.com.zupacademy.rodrigo.proposta.cartao.bloqueio.Bloqueio;

@RestController
@RequestMapping("/api/cartoes")
public class CartaoController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
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

		String ipAddress = request.getRemoteAddr();
		String userAgent = request.getHeader("User-Agent");

		cartao.bloquearCartao(new Bloqueio(ipAddress, userAgent, cartao));
		
		cartaoRepository.save(cartao);
		
		return ResponseEntity.ok().build();
	}

}
