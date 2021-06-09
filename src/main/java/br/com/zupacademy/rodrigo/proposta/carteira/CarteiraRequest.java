package br.com.zupacademy.rodrigo.proposta.carteira;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;

public interface CarteiraRequest {
	
	Carteira toModel(String nCarteira, Cartao cartao);
	
	AssociarCarteiraRequest toApiRequest(String ncartao);

}
