package br.com.zupacademy.rodrigo.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.lang.Nullable;

import br.com.zupacademy.rodrigo.proposta.avisoviagem.AvisoViagem;
import br.com.zupacademy.rodrigo.proposta.biometria.Biometria;
import br.com.zupacademy.rodrigo.proposta.cartao.bloqueio.Bloqueio;
import br.com.zupacademy.rodrigo.proposta.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String uuid = UUID.randomUUID().toString();

	@NotNull
	private String nCartao;

	@NotNull
	private LocalDateTime emitidoEm;

	@NotNull
	private String titular;

	@PositiveOrZero
	private BigDecimal limite;

	@OneToOne
	private Proposta proposta;

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
	private Set<Biometria> biometrias;
	
	@OneToOne(mappedBy = "cartao", cascade = CascadeType.PERSIST)
	@Nullable
	private Bloqueio bloqueio;
	
	@Enumerated(EnumType.STRING)
	private StatusCartao status = StatusCartao.DISPONIVEL;
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	@Nullable
	private Set<AvisoViagem> avisosViagens;

	public Cartao(String nCartao, @NotNull LocalDateTime emitidoEm, @NotNull String titular, BigDecimal limite,
			Proposta proposta) {
		this.nCartao = nCartao;
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
	
	public String getUuid() {
		return uuid;
	}

	public String getnCartao() {
		return nCartao;
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
	
	public void adicionarBiometria(Biometria biometria) {
		this.biometrias.add(biometria);
	}
	
	public void bloquearCartao(Bloqueio bloqueio) {
		this.bloqueio = bloqueio;
		this.status = StatusCartao.BLOQUEADO;
	}
	
	public boolean estaBloqueado() {
		return this.status == StatusCartao.BLOQUEADO;
	}
	
	public void adicionarAvisoViagem(AvisoViagem avisoViagem) {
		this.avisosViagens.add(avisoViagem);
	}

}
