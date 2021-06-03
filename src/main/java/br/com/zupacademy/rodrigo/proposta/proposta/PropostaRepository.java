package br.com.zupacademy.rodrigo.proposta.proposta;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {

	Optional<Proposta> findByDocumento(String documento);
	
	Set<Proposta> findByCartaoIsNullAndStatus(StatusProposta status);

}
