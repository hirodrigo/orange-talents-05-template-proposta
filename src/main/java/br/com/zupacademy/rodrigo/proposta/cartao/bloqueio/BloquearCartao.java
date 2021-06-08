package br.com.zupacademy.rodrigo.proposta.cartao.bloqueio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;
import br.com.zupacademy.rodrigo.proposta.cartao.CartaoClient;
import feign.FeignException;

@Component
public class BloquearCartao {

	
	@Autowired
	private CartaoClient cartaoClient;
	
	public void bloquear(Cartao cartao, HttpServletRequest request) throws FeignException {
		cartaoClient.bloquearCartao(cartao.getnCartao(), new BloquearCartaoRequest());
	}

}
