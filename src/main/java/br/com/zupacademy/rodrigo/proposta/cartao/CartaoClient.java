package br.com.zupacademy.rodrigo.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "CartaoClient", url = "${accounts.uri}")
public interface CartaoClient {

	@PostMapping("/api/cartoes")
	ResponseEntity<AssociaCartaoResponse> associarCartao(AssociaCartaoRequest request);

}
