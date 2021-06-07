package br.com.zupacademy.rodrigo.proposta.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;
import br.com.zupacademy.rodrigo.proposta.validation.Base64Value;

@Entity
public class Biometria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Base64Value
	private String fingerprint;
	
	@NotNull
	private LocalDateTime registradoEm = LocalDateTime.now();
	
	@NotNull
	@ManyToOne
	private Cartao cartao;
	
	/**
	 * No argument constructor for Hibernate, should not be used.
	 */
	@Deprecated
	public Biometria() {
	}

	public Biometria(@NotBlank String fingerprint, @NotNull Cartao cartao) {
		this.fingerprint = fingerprint;
		this.cartao = cartao;
	}
	
	public Long getId() {
		return id;
	}
	
	

}
