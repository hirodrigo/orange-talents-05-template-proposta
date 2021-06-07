package br.com.zupacademy.rodrigo.proposta.cartao.bloqueio;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String uuid = UUID.randomUUID().toString();

	@NotNull
	private LocalDateTime instante = LocalDateTime.now();

	@NotNull
	private String ipAddress;

	@NotNull
	private String userAgent;

	@OneToOne
	@NotNull
	private Cartao cartao;
	
	@Deprecated
	/**
	 * No argument constructor for Hibernate, should not be used.
	 */
	public Bloqueio() {
	}

	public Bloqueio(@NotNull String ipAddress, @NotNull String userAgent, @NotNull Cartao cartao) {
		this.ipAddress = ipAddress;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

}
