package br.com.zupacademy.rodrigo.proposta.proposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.rodrigo.proposta.validation.CPFOrCNPJ;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CPFOrCNPJ
	@NotBlank
	private String documento;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private BigDecimal salario;

	/**
	 * No argument constructor for Hibernate, should not be used.
	 */
	@Deprecated
	public Proposta() {
	}

	public Proposta(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @PositiveOrZero BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Long getId() {
		return id;
	}
}
