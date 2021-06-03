package br.com.zupacademy.rodrigo.proposta.proposta.consultadadossolicitante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.zupacademy.rodrigo.proposta.proposta.EventoNovaProposta;
import br.com.zupacademy.rodrigo.proposta.proposta.Proposta;
import br.com.zupacademy.rodrigo.proposta.proposta.PropostaRepository;
import br.com.zupacademy.rodrigo.proposta.proposta.StatusProposta;
import feign.FeignException;

@Component
public class ConsultaDadosSolicitante implements EventoNovaProposta {

	@Autowired
	private IntegracaoAPIConsulta integracao;

	@Autowired
	private PropostaRepository propostaRepository;

	@Override
	public void executar(Proposta proposta) {
		ConsultaRequest request = new ConsultaRequest(proposta);
		try {
			ResponseEntity<ConsultaResponse> responseEntity = integracao.consultar(request);
			ConsultaResponse response = responseEntity.getBody();
			proposta.setStatus(response.getResultadoSolicitacao());
		} catch (FeignException e) {
			proposta.setStatus(StatusProposta.NAO_ELEGIVEL);
		}

		propostaRepository.save(proposta);
	}

}
