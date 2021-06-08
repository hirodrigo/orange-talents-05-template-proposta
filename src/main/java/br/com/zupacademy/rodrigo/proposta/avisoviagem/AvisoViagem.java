package br.com.zupacademy.rodrigo.proposta.avisoviagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String destino;

	@NotNull
	@FutureOrPresent
	private LocalDate dataTermino;

	@NotNull
	private LocalDateTime instante = LocalDateTime.now();

	@NotNull
	private String ipAddress;

	@NotNull
	private String userAgent;

	@ManyToOne
	@NotNull
	private Cartao cartao;

	/**
	 * No argument constructor for Hibernate, should not be used.
	 */
	@Deprecated
	public AvisoViagem() {
	}

	public AvisoViagem(@NotBlank String destino, @NotNull @FutureOrPresent LocalDate dataTermino,
			@NotNull String ipAddress, @NotNull String userAgent, @NotNull Cartao cartao) {
		this.destino = destino;
		this.dataTermino = dataTermino;
		this.ipAddress = ipAddress;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

}
