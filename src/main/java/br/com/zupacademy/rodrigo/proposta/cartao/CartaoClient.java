package br.com.zupacademy.rodrigo.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zupacademy.rodrigo.proposta.avisoviagem.AvisarViagemRequest;
import br.com.zupacademy.rodrigo.proposta.avisoviagem.AvisarViagemResponse;
import br.com.zupacademy.rodrigo.proposta.cartao.bloqueio.BloquearCartaoRequest;
import br.com.zupacademy.rodrigo.proposta.cartao.bloqueio.BloquearCartaoResponse;

@FeignClient(value = "CartaoClient", url = "${accounts.uri}")
public interface CartaoClient {

	@PostMapping("/api/cartoes")
	ResponseEntity<AssociaCartaoResponse> associarCartao(AssociaCartaoRequest request);

	@PostMapping("/api/cartoes/{nCartao}/bloqueios")
	ResponseEntity<BloquearCartaoResponse> bloquearCartao(@PathVariable String nCartao, BloquearCartaoRequest request);
	
	@PostMapping("/api/cartoes/{nCartao}/avisos")
	ResponseEntity<AvisarViagemResponse> avisarViagem(@PathVariable String nCartao, AvisarViagemRequest request);
	
	@GetMapping("/actuator/health")
	ResponseEntity<Void> healthCheck(); 

}
