package br.com.zupacademy.rodrigo.proposta.cartao.bloqueio;

import org.springframework.beans.factory.annotation.Value;

public class BloquearCartaoRequest {
	
	@Value("${spring.application.name}")
	private String sistemaResponsavel;
	
	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

}
