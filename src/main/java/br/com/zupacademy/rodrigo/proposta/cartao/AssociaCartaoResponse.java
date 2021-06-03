package br.com.zupacademy.rodrigo.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.rodrigo.proposta.proposta.Proposta;

public class AssociaCartaoResponse {
	
	@NotNull
	private String id;
	
	@NotNull
	private LocalDateTime emitidoEm;
	
	@NotNull
	private String titular;
	
	@PositiveOrZero
	private BigDecimal limite;

	public AssociaCartaoResponse(@NotNull String id, @NotNull LocalDateTime emitidoEm, @NotNull String titular,
			@PositiveOrZero BigDecimal limite) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
	}
	
	public Cartao toModel(Proposta proposta){
		return new Cartao(id, emitidoEm, titular, limite, proposta);
	}

}
