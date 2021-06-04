package br.com.zupacademy.rodrigo.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.rodrigo.proposta.proposta.Proposta;

@Entity
public class Cartao {
	
	@Id
	private String id;
	
	@NotNull
	private LocalDateTime emitidoEm;
	
	@NotNull
	private String titular;
	
	@PositiveOrZero
	private BigDecimal limite;
	
	@OneToOne
	Proposta proposta;

	public Cartao(String id, @NotNull LocalDateTime emitidoEm, @NotNull String titular, BigDecimal limite,
			Proposta proposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
	}
	
	/**
	 * No argument constructor for Hibernate, should not be used.
	 */
	@Deprecated
	public Cartao() {
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public Proposta getProposta() {
		return proposta;
	}
	
}
