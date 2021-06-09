package br.com.zupacademy.rodrigo.proposta.carteira;

import java.net.URI;
import java.util.Optional;

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
import br.com.zupacademy.rodrigo.proposta.cartao.CartaoClient;
import br.com.zupacademy.rodrigo.proposta.cartao.CartaoRepository;
import br.com.zupacademy.rodrigo.proposta.carteira.paypal.CarteiraPayPalRequest;

@RestController
@RequestMapping("/api/cartoes")
public class CarteiraController {

	@Autowired
	private CarteiraRepository carteiraRepository;

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CartaoClient cartaoClient;

	@PostMapping("/{uuid}/carteiras/paypal")
	private ResponseEntity<?> createCarteiraPayPal(@PathVariable String uuid,
			@Valid @RequestBody CarteiraPayPalRequest request, UriComponentsBuilder ucb) {
		return createCarteira(uuid, request, ucb, TipoCarteira.PayPal);
	}

	private ResponseEntity<?> createCarteira(@PathVariable String uuid, @Valid @RequestBody CarteiraRequest request,
			UriComponentsBuilder ucb, TipoCarteira tipoCarteira) {
		Optional<Cartao> possivelCartao = cartaoRepository.findByUuid(uuid);
		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Cartao cartao = possivelCartao.get();

		Integer count = carteiraRepository.countByCartaoAndTipoCarteira(cartao, tipoCarteira);
		if (count >= tipoCarteira.getLimiteCarteira()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"O limite de associações para carteiras do tipo " + tipoCarteira.toString() + " foi atingido.");
		}

		AssociarCarteiraRequest apiRequest = request.toApiRequest(cartao.getnCartao());
		ResponseEntity<AssociarCarteiraResponse> response;
		try {
			response = cartaoClient.associarCarteira(cartao.getnCartao(), apiRequest);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Não foi possível associar a carteira neste momento, tente novamente mais tarde.");
		}

		Carteira carteira = request.toModel(response.getBody().getnCarteira(), cartao);
		cartao.adicionarCarteira(carteira);
		cartaoRepository.save(cartao);

		URI uri = ucb.path("api/carteiras/paypal/{nCarteira}").buildAndExpand(response.getBody().getnCarteira())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
