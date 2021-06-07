package br.com.zupacademy.rodrigo.proposta.cartao;

import br.com.zupacademy.rodrigo.proposta.proposta.Proposta;

public class AssociaCartaoRequest {

	private String idProposta;
	private String documento;
	private String nome;

	public AssociaCartaoRequest(Proposta proposta) {
		this.idProposta = proposta.getUuid();
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
	}

	public String getIdProposta() {
		return idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

}
