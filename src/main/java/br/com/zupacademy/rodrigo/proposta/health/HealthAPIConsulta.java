package br.com.zupacademy.rodrigo.proposta.health;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "healthCheckAPIConsulta", url = "${consulta.uri}")
public interface HealthAPIConsulta {

	@GetMapping("actuator/health")
	ResponseEntity<Void> healthCheck(); 

}
