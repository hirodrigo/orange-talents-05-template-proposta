package br.com.zupacademy.rodrigo.proposta.carteira;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.rodrigo.proposta.cartao.Cartao;

public interface CarteiraRepository extends CrudRepository<Carteira, Long> {
	
	Integer countByCartaoAndTipoCarteira(Cartao cartao, TipoCarteira tipoCarteira);

}
