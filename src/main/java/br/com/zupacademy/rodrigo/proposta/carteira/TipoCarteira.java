package br.com.zupacademy.rodrigo.proposta.carteira;

public enum TipoCarteira {

	PayPal(1);

	Integer limiteCarteira;

	TipoCarteira(Integer limiteCarteira) {
		if (limiteCarteira == null) {
			this.limiteCarteira = Integer.MAX_VALUE;
		}
		this.limiteCarteira = limiteCarteira;
	}

	public Integer getLimiteCarteira() {
		return limiteCarteira;
	}

}
