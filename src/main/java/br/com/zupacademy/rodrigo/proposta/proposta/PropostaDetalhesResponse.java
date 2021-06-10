package br.com.zupacademy.rodrigo.proposta.proposta;

import java.math.BigDecimal;

import br.com.zupacademy.rodrigo.proposta.cartao.CartaoPropostaDetalhesResponse;

public class PropostaDetalhesResponse {

	private String documento;

	private String email;

	private String nome;

	private String endereco;

	private BigDecimal salario;

	private StatusProposta status;

	private CartaoPropostaDetalhesResponse cartao;

	public PropostaDetalhesResponse(Proposta proposta) {
		this.documento = DocumentoLimpo.decrypt(proposta.getDocumento());
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.status = proposta.getStatus();
		if (proposta.getCartao() != null) {
			this.cartao = new CartaoPropostaDetalhesResponse(proposta.getCartao());
		}
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public StatusProposta getStatus() {
		return status;
	}

	public CartaoPropostaDetalhesResponse getCartao() {
		return cartao;
	}

}