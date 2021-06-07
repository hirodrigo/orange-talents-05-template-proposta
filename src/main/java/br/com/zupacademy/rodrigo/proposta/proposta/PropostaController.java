package br.com.zupacademy.rodrigo.proposta.proposta;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private List<EventoNovaProposta> eventosNovaProposta;

	@PostMapping
	@Transactional
	private ResponseEntity<?> createProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder ucb) {
		Optional<Proposta> possivelProposta = propostaRepository.findByDocumento(request.getDocumento());
		if (possivelProposta.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"O documento informado já foi cadastrado anteriormente em uma outra proposta.");
		}

		Proposta proposta = request.toModel();
		propostaRepository.save(proposta);

		for (EventoNovaProposta evento : eventosNovaProposta) {
			evento.executar(proposta);
		}

		URI uri = ucb.path("api/propostas/{id}").buildAndExpand(proposta.getUuid()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{uuid}")
	private ResponseEntity<?> showProposta(@PathVariable String uuid) {
		Optional<Proposta> possivelProposta = propostaRepository.findByUuid(uuid);
		if (possivelProposta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Proposta proposta = possivelProposta.get();
		PropostaDetalhesResponse response = new PropostaDetalhesResponse(proposta);
		return ResponseEntity.ok(response);
	}

}
