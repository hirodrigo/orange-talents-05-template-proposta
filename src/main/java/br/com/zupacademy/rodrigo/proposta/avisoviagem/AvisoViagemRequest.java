package br.com.zupacademy.rodrigo.proposta.avisoviagem;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;

public class AvisoViagemRequest {

	@NotBlank
	private String destino;

	@NotNull
	@FutureOrPresent
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataTermino;

	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public AvisoViagem toModel(String ipAddress, String userAgent, Cartao cartao) {
		return new AvisoViagem(this.destino, this.dataTermino, ipAddress, userAgent, cartao);
	}

	public AvisarViagemRequest toAPIRequest() {
		return new AvisarViagemRequest(this.destino, this.dataTermino);
	}

}
