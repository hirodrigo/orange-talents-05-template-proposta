package br.com.zupacademy.rodrigo.proposta.proposta.consultadadossolicitante;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "ConsultaClient", url = "${consulta.uri}")
public interface ConsultaClient {

	@PostMapping("/api/solicitacao")
	ResponseEntity<ConsultaDadosResponse> consultar(ConsultaDadosRequest request);

	@GetMapping("/actuator/health")
	ResponseEntity<Void> healthCheck(); 

}
