package br.com.gregorio.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gregorio.SpringBootInterviewApplication;
import br.com.gregorio.entity.Cidade;
import br.com.gregorio.entity.Estado;
import br.com.gregorio.util.CidadeBuilder;
import br.com.gregorio.util.EstadoBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootInterviewApplication.class)
@ActiveProfiles("test")
public class CidadeRepositoryTest {

	@Autowired
    CidadeRepository cidadeRepository;
	
	
	
	@Test
	public void salvarCidade() {
		Estado estado = new EstadoBuilder().setId(1L).setUf("DF").build();
		Cidade cidade = new CidadeBuilder().setId(1L).setNome("Brasília").setEstado(estado).build();
		cidadeRepository.save(cidade);

		List<Cidade> cidadeSalvo = cidadeRepository.findByNomeContaining("Brasília");
		
		assertNotNull(cidadeSalvo);
		assertEquals(cidadeSalvo.get(0).getNome(), cidade.getNome());        
	}
	
	
	
	@Test
	public void alterarCidade() {
		Estado estado = new EstadoBuilder().setId(1L).setUf("DF").build();
		Cidade cidade = new CidadeBuilder().setId(1L).setNome("Brasília").setEstado(estado).build();
		cidadeRepository.save(cidade);
		
		Cidade cidadeAlterar = new CidadeBuilder().setId(1L).setNome("Brasília-Aterado").setEstado(estado).build();
		cidadeRepository.save(cidadeAlterar);

		List<Cidade> cidadeSalvo = cidadeRepository.findByNomeContaining("Brasília-Aterado");
		
		assertNotNull(cidadeSalvo);
		assertEquals(cidadeSalvo.get(0).getNome(), cidadeAlterar.getNome());        
	}
	
	
	
	@Test
	public void excluirCidade() {
		Estado estado = new EstadoBuilder().setId(1L).setUf("DF").build();
		Cidade cidade = new CidadeBuilder().setId(1L).setNome("Brasília").setEstado(estado).build();
		cidadeRepository.save(cidade);
		
		cidadeRepository.deleteById(cidade.getId());

		List<Cidade> cidadeConsultada = cidadeRepository.findByNomeContaining("Brasília");
				
		assertTrue(cidadeConsultada.isEmpty());
	}
}
