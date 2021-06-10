 package br.com.zupacademy.rodrigo.proposta.carteira.samsungpay;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;
import br.com.zupacademy.rodrigo.proposta.carteira.AssociarCarteiraRequest;
import br.com.zupacademy.rodrigo.proposta.carteira.Carteira;
import br.com.zupacademy.rodrigo.proposta.carteira.CarteiraRequest;
import br.com.zupacademy.rodrigo.proposta.carteira.TipoCarteira;

public class CarteiraSamsungPayRequest implements CarteiraRequest {

	@NotBlank
	@Email
	private String email;

	private final TipoCarteira tipoCarteira = TipoCarteira.SamsungPay;

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public TipoCarteira getTipoCarteira() {
		return this.tipoCarteira;
	}

	@Override
	public Carteira toModel(String nCarteira, Cartao cartao) {
		return new Carteira(this.tipoCarteira, this.email, nCarteira, cartao);
	}

	@Override
	public AssociarCarteiraRequest toApiRequest(String nCartao) {
		return new AssociarCarteiraRequest(nCartao, this.tipoCarteira);
		
	}

}
