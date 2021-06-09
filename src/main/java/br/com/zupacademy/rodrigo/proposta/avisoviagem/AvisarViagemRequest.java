package br.com.zupacademy.rodrigo.proposta.avisoviagem;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AvisarViagemRequest {
	
	@NotBlank
	private String destino;
	
	@NotNull
	private LocalDate dataTermino;

	public AvisarViagemRequest(@NotBlank String destino, @NotNull LocalDate dataTermino) {
		this.destino = destino;
		this.dataTermino = dataTermino;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	@Override
	public String toString() {
		return "AvisarViagemRequest [destino=" + destino + ", dataTermino=" + dataTermino + "]";
	}
	
}
