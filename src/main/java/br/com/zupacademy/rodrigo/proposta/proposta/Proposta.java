package br.com.zupacademy.rodrigo.proposta.proposta;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.lang.Nullable;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;
import br.com.zupacademy.rodrigo.proposta.validation.CPFOrCNPJ;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String uuid = UUID.randomUUID().toString();

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

	@Enumerated(EnumType.STRING)
	private StatusProposta status;
	
	@Nullable
	@OneToOne(mappedBy = "proposta", cascade = CascadeType.MERGE)
	private Cartao cartao;

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

	public void setStatus(StatusProposta status) {
		this.status = status;
	}
	
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public String getUuid() {
		return uuid;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}
	
	public StatusProposta getStatus() {
		return status;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
