package br.com.zupacademy.rodrigo.proposta.avisoviagem;

import javax.validation.constraints.NotNull;

public class AvisarViagemResponse {
	
	@NotNull
	private String resultado;
	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public String getResultado() {
		return resultado;
	}

}
