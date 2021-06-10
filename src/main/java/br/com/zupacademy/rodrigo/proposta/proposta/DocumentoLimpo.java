package br.com.zupacademy.rodrigo.proposta.proposta;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.encrypt.Encryptors;

import br.com.zupacademy.rodrigo.proposta.validation.CPFOrCNPJ;

public class DocumentoLimpo {

	@NotBlank
	@CPFOrCNPJ
	private String documento;

	final static private String key = "4F81FCA99D2FDCCDB6A2BD7C31BA02A5";
	final static private String salt = "A9FBD420F97303";

	/**
	 * @param documento Precisa ser uma CPF ou CNPJ limpo, sem criptografia.
	 */
	public DocumentoLimpo(String documento) {
		this.documento = documento;
	}

	/**
	 * @return Retorna um documento já criptografado.
	 */
	public String encrypt() {
		return Encryptors.text(key, salt).encrypt(documento);
	}

	/**
	 * @return Retorna um documento de proposta descriptografado.
	 */
	static public String decrypt(String documentoCriptografado) {
		return Encryptors.text(key, salt).decrypt(documentoCriptografado);
	}

}
