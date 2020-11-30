package br.com.gregorio.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.gregorio.service.EstadoService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EstadoController.class)
@ActiveProfiles("test")
public class EstadoControllerTest {

		
	@Autowired
	MockMvc mockMvc;

	@MockBean
	EstadoService service;

	
	
	@Test	
	public void consultarEstado() throws Exception {				
		
		mockMvc.perform(MockMvcRequestBuilders.get("/estados/55").accept(APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
}
