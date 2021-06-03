package br.com.zupacademy.rodrigo.proposta.proposta.consultadadossolicitante;

import br.com.zupacademy.rodrigo.proposta.proposta.Proposta;

public class ConsultaDadosRequest {

	private Long idProposta;
	private String nome;
	private String documento;

	public ConsultaDadosRequest(Proposta proposta) {
		this.idProposta = proposta.getId();
		this.nome = proposta.getNome();
		this.documento = proposta.getDocumento();
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

}
