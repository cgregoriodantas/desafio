package br.com.gregorio.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gregorio.SpringBootInterviewApplication;
import br.com.gregorio.entity.Estado;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootInterviewApplication.class)
@ActiveProfiles("test")
public class EstadoRepositoryTest {

	@Autowired
	EstadoRepository estadoRepository;


	@Test
	public void consultarEstado() {		

		Estado estado = estadoRepository.findByUf("DF");
		
		assertNotNull(estado);		
	}
	
	
}
