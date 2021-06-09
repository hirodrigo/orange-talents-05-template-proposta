package br.com.zupacademy.rodrigo.proposta.carteira;

public enum TipoCarteira {

	PayPal(1, "paypal"), SamsungPay(Integer.MAX_VALUE, "samsungpay");

	private Integer limiteCarteira;
	private String uri;

	TipoCarteira(Integer limiteCarteira, String uri) {
		if (limiteCarteira == null) {
			limiteCarteira = Integer.MAX_VALUE;
		}
		this.limiteCarteira = limiteCarteira;
		this.uri = uri;
	}

	public Integer getLimiteCarteira() {
		return limiteCarteira;
	}
	
	public String getUri() {
		return uri;
	}

}
