package br.com.zupacademy.rodrigo.proposta.biometria;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;
import br.com.zupacademy.rodrigo.proposta.validation.Base64Value;

public class BiometriaRequest {
	
	@NotBlank
	@Base64Value
	private String fingerprint;
	
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public Biometria toModel(Cartao cartao) {
		return new Biometria(this.fingerprint, cartao);
	}

}
