package br.com.gregorio.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gregorio.entity.Cidade;
import br.com.gregorio.exception.NegocioException;
import br.com.gregorio.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/cidades")
@Api(tags = "Cidades", value = "Cidade", description = "Manutenção de cidades")
public class CidadeController {
		
	@Autowired
	private CidadeService cidadeService;	
	
	
	@GetMapping()
	@ApiOperation(value = "Consulta todas as cidades")
	public ResponseEntity<?> buscar() {
		try {
			Iterable<Cidade> cidades = cidadeService.cidades();
			
			if (IterableUtils.size(cidades) > 0) {
				return ResponseEntity.ok(cidades);
			}
		}
		catch (Exception e) {
			log.error("Classe CidadeController >>> Método buscar >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar sua consulta");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há cidades cadastradas no banco");
	}
	
	
	
	@GetMapping("/{nome}")
	@ApiOperation(value = "Consulta cidade pelo nome")
	public ResponseEntity<?> buscarPorNome(@PathVariable String nome) {
		try {
			List<Cidade> cidades = cidadeService.cidadesPorNome(nome);
			
			if (!cidades.isEmpty()) {
				return ResponseEntity.ok(cidades);
			}
		}
		catch (Exception e) {
			log.error("Classe CidadeController >>> Método buscarPorNome >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar sua consulta");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade não encontrada");
	}
	
	
	
	@PostMapping
	@ApiOperation(value = "Cria uma nova cidade")
	public ResponseEntity<String> adicionar(@RequestBody @Valid Cidade cidade) {
		try {
			cidadeService.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso!");
			
		} catch (NegocioException e) {
			log.error("Classe CidadeController >>> Método adicionar >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		catch (Exception e) {
			log.error("Classe CidadeController >>> Método adicionar >>> Erro >>>",e.getMessage(),e);
			return ResponseEntity.badRequest().body("Desculpe não foi possivel realizar o seu cadastro");
		}
	}
	
}