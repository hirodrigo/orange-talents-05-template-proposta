package br.com.zupacademy.rodrigo.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoPropostaDetalhesResponse {
	
	private String nCartao;
	
	private LocalDateTime emitidoEm;
	
	private String titular;
	
	private BigDecimal limite;
	
	public CartaoPropostaDetalhesResponse(Cartao cartao) {
		this.nCartao = cartao.getId();
		this.emitidoEm = cartao.getEmitidoEm();
		this.titular = cartao.getTitular();
		this.limite = cartao.getLimite();
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

}
