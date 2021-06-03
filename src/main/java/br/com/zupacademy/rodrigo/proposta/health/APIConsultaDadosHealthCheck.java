package br.com.zupacademy.rodrigo.proposta.health;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.zupacademy.rodrigo.proposta.proposta.consultadadossolicitante.ConsultaClient;

@Component
public class APIConsultaDadosHealthCheck implements HealthIndicator {

	@Autowired
	private ConsultaClient client;

	@Override
	public Health health() {
		Map<String, Object> details = new HashMap<>();

		try {
			ResponseEntity<Void> healthCheck = client.healthCheck();
			details.put("descrição", "Comunicação com o endpoint da API realizada com sucesso.");
			details.put("status", healthCheck.getStatusCodeValue());
		} catch (Exception e) {
			details.put("descrição", e.getMessage());
			return Health.status(Status.DOWN).withDetails(details).build();
		}
		return Health.status(Status.UP).withDetails(details).build();

	}

}
