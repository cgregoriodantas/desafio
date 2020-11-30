package br.com.gregorio.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gregorio.SpringBootInterviewApplication;
import br.com.gregorio.entity.Cidade;
import br.com.gregorio.entity.Cliente;
import br.com.gregorio.entity.Estado;
import br.com.gregorio.entity.SexoCliente;
import br.com.gregorio.util.CidadeBuilder;
import br.com.gregorio.util.ClienteBuilder;
import br.com.gregorio.util.EstadoBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootInterviewApplication.class)
@ActiveProfiles("test")
public class ClienteRepositoryTest {

	@Autowired
    ClienteRepository clienteRepository;
	
	@Autowired
    CidadeRepository cidadeRepository;
	
	
	
	@Before
	public void inicio() {
		salvarCidade();
	}
	
	
			
	@Test
	public void salvarCliente() {		
		Cidade cidade = consultarCidade();
		Cliente cliente = new ClienteBuilder().setNome("Fulano")
								.setDataNascimento(LocalDate.now())
								.setIdade(15)
								.setSexo(SexoCliente.MASCULINO)
								.setCidade(cidade).build();
		
		clienteRepository.save(cliente);

		List<Cliente> clienteSalvo = clienteRepository.findByNomeContaining("Fulano");
		
		assertNotNull(clienteSalvo);
		assertEquals(clienteSalvo.get(0).getNome(), cliente.getNome());        
	}
	
	
	
	@Test
	public void alterarCliente() {		
		Cidade cidade = consultarCidade();
		Cliente cliente = new ClienteBuilder().setNome("Fulano")
				.setDataNascimento(LocalDate.now())
				.setIdade(15)
				.setSexo(SexoCliente.MASCULINO)
				.setCidade(cidade).build();
		
		clienteRepository.save(cliente);
		
		Cliente clienteAlterar =  new ClienteBuilder().setNome("Ciclano")
										.setDataNascimento(LocalDate.now())
										.setIdade(15)
										.setSexo(SexoCliente.MASCULINO)
										.setCidade(cidade).build();
		
		clienteRepository.save(clienteAlterar);

		List<Cliente> clienteSalvo = clienteRepository.findByNomeContaining("Ciclano");
		
		assertNotNull(clienteSalvo);
		assertEquals(clienteSalvo.get(0).getNome(), clienteAlterar.getNome());        
	}
	
	
	
	public void salvarCidade() {
		List<Cidade> cidadesConsultada = cidadeRepository.findByNomeContaining("Taguatinga");
		if(cidadesConsultada.isEmpty()) {		
			Estado estado = new EstadoBuilder().setId(1L).setUf("DF").build();
			Cidade cidade = new CidadeBuilder().setNome("Taguatinga").setEstado(estado).build();
			cidadeRepository.save(cidade);
		}		        
	}
	
	
	
	public Cidade consultarCidade() {
		List<Cidade> cidadesConsultada = cidadeRepository.findByNomeContaining("Taguatinga");
		return cidadesConsultada.get(0);  
	}
	
}
