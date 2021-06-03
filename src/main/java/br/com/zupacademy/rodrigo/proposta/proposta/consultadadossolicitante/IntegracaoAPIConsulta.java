package br.com.zupacademy.rodrigo.proposta.proposta.consultadadossolicitante;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "SolicitacaoAPIConsulta", url = "${consulta.uri}")
public interface IntegracaoAPIConsulta {

	@PostMapping("/api/solicitacao")
	ResponseEntity<ConsultaResponse> consultar(ConsultaRequest request);

}
