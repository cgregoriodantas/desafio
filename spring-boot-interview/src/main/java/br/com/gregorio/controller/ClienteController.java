package br.com.gregorio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gregorio.entity.Cliente;
import br.com.gregorio.exception.NegocioException;
import br.com.gregorio.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/clientes")
@Api(tags = "Clientes", value = "Cliente", description = "Manutenção de clientes")
public class ClienteController {
	
	
	@Autowired
	private ClienteService clienteService;
	
	
	
	@GetMapping("/todos")
	@ApiOperation(value = "Consulta todos os clientes")
	public ResponseEntity<?> buscar() {
		try {
			Iterable<Cliente> clientes = clienteService.clientes();
			
			if (IterableUtils.size(clientes) > 0) {
				return ResponseEntity.ok(clientes);
			}
		}
		catch (Exception e) {
			log.error("Classe ClienteController >>> Método buscar >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar sua consulta");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há clientes cadastrados no banco");
	}	
	
		
	
	@GetMapping
	@ApiOperation(value = "Consulta cliente pelo nome passando nome como parâmetro da url")
	public ResponseEntity<?> buscarPorNome(@RequestParam(name = "nome", required = true) String nome) {
		try {
			List<Cliente> clientes = clienteService.clientesPorNome(nome);
			
			if (!clientes.isEmpty()) {
				return ResponseEntity.ok(clientes);
			}
		}
		catch (Exception e) {
			log.error("Classe ClienteController >>> Método buscarPorNome >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar sua consulta");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
	}	
	
	
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Consulta cliente pelo id")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		try {
			Optional<Cliente> clientes = clienteService.clientesPorId(id);
			
			if (clientes.isPresent()) {
				return ResponseEntity.ok(clientes);
			}
		}
		catch (Exception e) {
			log.error("Classe ClienteController >>> Método buscarPorId >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar sua consulta");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
	}	
	
	
	
	@PostMapping
	@ApiOperation(value = "Cria um novo cliente")
	public ResponseEntity<String> adicionar(@RequestBody @Valid Cliente cliente) {
		try {
						
			clienteService.salvar(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso!");
			
		} catch (NegocioException e) {
			log.error("Classe ClienteController >>> Método adicionar >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		catch (Exception e) {
			log.error("Classe ClienteController >>> Método adicionar >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar o seu cadastro");
		}
	}
	
	
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Altera um cliente")
	public ResponseEntity<String> alterar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
		try {
			
			clienteService.alterar(id,cliente);			
			return ResponseEntity.status(HttpStatus.CREATED).body("Ateração realizada com sucesso!");
			
		} catch (NegocioException e) {
			log.error("Classe ClienteController >>> Método alterar >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		catch (Exception e) {
			log.error("Classe ClienteController >>> Método alterar >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar a alteração");
		}
	}
	
	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui um cliente")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		try {
			
			clienteService.deletarPorId(id);
			return ResponseEntity.ok("Cliente excluído com sucesso!");
		}
		catch (NegocioException e) {
			log.error("Classe ClienteController >>> Método deletar >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		catch (Exception e) {
			log.error("Classe ClienteController >>> Método deletar >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar a alteração");
		}
	}
}
