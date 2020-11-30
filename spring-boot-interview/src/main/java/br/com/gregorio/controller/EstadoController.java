package br.com.gregorio.controller;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gregorio.entity.Cidade;
import br.com.gregorio.entity.Estado;
import br.com.gregorio.service.EstadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/estados")
@Api(tags = "Estados", value = "Estado", description = "Busca Estado clientes")
public class EstadoController {
	
	
	@Autowired
	private EstadoService estadoService;
	
	
	
	@GetMapping
	@ApiOperation(value = "Consulta todos os estados")
	public ResponseEntity<?> buscar() {
		try {
			Iterable<Estado> estados = estadoService.estados();
			
			if (IterableUtils.size(estados) > 0) {
				return ResponseEntity.ok(estados);
			}
		}
		catch (Exception e) {
			log.error("Classe EstadoController >>> Método buscar >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar sua consulta");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrado");
	}	 
	
	
		
	@GetMapping("/{uf}")
	@ApiOperation(value = "Consulta estado por uf")
	public ResponseEntity<?> buscarPorUf(@PathVariable String uf) {
		try {
			Estado estado = estadoService.estadosPorUf(uf.toUpperCase());
			
			if (estado != null) {
				return ResponseEntity.ok(estado);
			}
		}
		catch (Exception e) {
			log.error("Classe EstadoController >>> Método buscarPorUf >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar sua consulta");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrado");
	}	 
	
	
	
	@GetMapping("/{uf}/cidades")
	@ApiOperation(value = "Consulta cidades por uf")
	public ResponseEntity<?> buscarCidadePorEstado(@PathVariable String uf) {
		try {
			List<Cidade> cidades = estadoService.buscarCidadesPorEstado(uf.toUpperCase());
			
			if (!cidades.isEmpty()) {
				return ResponseEntity.ok(cidades);
			}
		}
		catch (Exception e) {
			log.error("Classe EstadoController >>> Método buscarCidadePorEstado >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar sua consulta");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há cidades cadastradas para teste estado.");
	}	 
}


