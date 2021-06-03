package br.com.zupacademy.rodrigo.proposta.cartao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.rodrigo.proposta.proposta.Proposta;
import br.com.zupacademy.rodrigo.proposta.proposta.PropostaRepository;
import br.com.zupacademy.rodrigo.proposta.proposta.StatusProposta;
import feign.FeignException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AssociaCartao {

	@Autowired
	CartaoClient cartaoClient;

	@Autowired
	PropostaRepository propostaRepository;
	
	Logger logger = LoggerFactory.getLogger(AssociaCartao.class);

	@Scheduled(fixedDelay = 10000)
	public void executar() {
		Set<Proposta> propostasElegiveis = propostaRepository.findByCartaoIsNullAndStatus(StatusProposta.ELEGIVEL);
		propostasElegiveis.forEach(proposta -> associarCartao(proposta));
	}

	public void associarCartao(Proposta proposta) {
		try {
			AssociaCartaoRequest request = new AssociaCartaoRequest(proposta);
			ResponseEntity<AssociaCartaoResponse> responseEntity = cartaoClient.associarCartao(request);
			AssociaCartaoResponse response = responseEntity.getBody();
			Cartao cartao = response.toModel(proposta);
			proposta.setCartao(cartao);
			propostaRepository.save(proposta);
		} catch (FeignException e) {
			logger.warn(e.contentUTF8().toString());
		}
	}

}
