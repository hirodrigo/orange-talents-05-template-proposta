package br.com.zupacademy.rodrigo.proposta.carteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCarteira tipoCarteira;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nCarteira;

	@NotNull
	@ManyToOne
	private Cartao cartao;

	/**
	 * No argument constructor for Hibernate, should not be used.
	 */
	@Deprecated
	public Carteira() {
	}

	public Carteira(@NotNull TipoCarteira tipoCarteira, @NotBlank @Email String email, @NotBlank String nCarteira,
			@NotNull Cartao cartao) {
		this.tipoCarteira = tipoCarteira;
		this.email = email;
		this.nCarteira = nCarteira;
		this.cartao = cartao;
	}
	
	public TipoCarteira getTipoCarteira() {
		return tipoCarteira;
	}

}
