package br.com.zupacademy.rodrigo.proposta.biometria;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;
import br.com.zupacademy.rodrigo.proposta.cartao.CartaoRepository;

@RestController
@RequestMapping("/api/cartoes/{uuidCartao}/biometrias")
public class BiometriaController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private BiometriaRepository biometriaRepository;

	@PostMapping
	@Transactional
	private ResponseEntity<?> createBiometria(@PathVariable String uuidCartao,
			@RequestBody @Valid BiometriaRequest request, UriComponentsBuilder ucb) {
		Optional<Cartao> possivelCartao = cartaoRepository.findByUuid(uuidCartao);
		if (possivelCartao.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O cartão informado não existe.");
		}

		Cartao cartao = possivelCartao.get();

		Biometria biometria = request.toModel(cartao);

		cartao.adicionarBiometria(biometria);

		biometriaRepository.save(biometria);
		cartaoRepository.save(cartao);

		URI uri = ucb.path("/api/cartoes/{uuidCartao}/biometrias/{uuidBiometria}")
				.buildAndExpand(uuidCartao, biometria.getUuid()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
