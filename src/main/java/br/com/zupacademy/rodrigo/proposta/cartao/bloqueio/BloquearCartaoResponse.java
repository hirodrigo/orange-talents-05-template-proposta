package br.com.zupacademy.rodrigo.proposta.cartao.bloqueio;

import javax.validation.constraints.NotNull;

public class BloquearCartaoResponse {

	@NotNull
	private String resultado;

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}

}
