package br.com.zupacademy.rodrigo.proposta.proposta;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class PropostaControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper jsonMapper;

	@Autowired
	PropostaRepository propostaRepository;

	@Test
	void deveCadastrarUmNovoAutor() throws Exception {
		PropostaRequest request = new PropostaRequest("21522724044", "teste@email.com", "Nome na Proposta Teste",
				"Endereço Proposta Teste", BigDecimal.valueOf(1234.56));
		String json = json(request);

		mockMvc.perform(post("/api/propostas").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());

		Optional<Proposta> possivelProposta = propostaRepository.findByDocumento(json);
		
		Assert.notNull(possivelProposta.get(), "");
	}


	private String json(Object request) throws JsonProcessingException {
		return jsonMapper.writeValueAsString(request);
	}

}
