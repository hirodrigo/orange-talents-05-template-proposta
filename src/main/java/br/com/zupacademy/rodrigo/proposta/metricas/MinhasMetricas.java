package br.com.zupacademy.rodrigo.proposta.metricas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class MinhasMetricas {

	private final MeterRegistry meterRegistry;

	private Collection<String> strings = new ArrayList<>();

	public MinhasMetricas(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
        criarGauge();
	}

	public void adicionarAoContador(String key) {
		Counter contadorDePropostasCriadas = this.meterRegistry.counter(key);
		contadorDePropostasCriadas.increment();
	}

	public void criarGauge() {
		this.meterRegistry.gauge("meu_gauge", strings, Collection::size);
	}

	@Scheduled(fixedDelay = 10000)
	public void simulandoGauge() {
		Random random = new Random();
		double randomNumber = random.nextInt();
		if (randomNumber % 2 == 0) {
			addString();
		} else {
			removeString();
		}
	}

	public void removeString() {
		strings.removeIf(Objects::nonNull);
	}

	public void addString() {
		strings.add(UUID.randomUUID().toString());
	}

}
