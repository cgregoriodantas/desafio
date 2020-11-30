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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gregorio.entity.Cidade;
import br.com.gregorio.entity.Estado;
import br.com.gregorio.service.CidadeService;
import br.com.gregorio.util.CidadeBuilder;
import br.com.gregorio.util.EstadoBuilder;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CidadeController.class)
@ActiveProfiles("test")
public class CidadeControllerTest {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;

	@MockBean
	CidadeService service;

	
	
	@Test	
	public void consultarCidade() throws Exception {

		Estado estado = new EstadoBuilder().setId(1L).setUf("DF").build();
		Cidade cidade = new CidadeBuilder().setId(1L).setNome("Brasília").setEstado(estado).build();
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/cidades").contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON).content(objectMapper.writeValueAsString(cidade));
		
		mockMvc.perform(request).andExpect(status().isCreated());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/cidades/555").accept(APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
	
		
	@Test	
	public void iserirCidade() throws Exception {

		Estado estado = new EstadoBuilder().setId(1L).setUf("DF").build();
		Cidade cidade = new CidadeBuilder().setId(1L).setNome("Brasília").setEstado(estado).build();
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/cidades").contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON).content(objectMapper.writeValueAsString(cidade));
		
		mockMvc.perform(request).andExpect(status().isCreated());
	}
	
}
