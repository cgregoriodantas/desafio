package br.com.gregorio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.gregorio.entity.Cidade;
import br.com.gregorio.entity.Estado;
import br.com.gregorio.exception.NegocioException;
import br.com.gregorio.repository.CidadeRepository;
import br.com.gregorio.repository.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EstadoRepository estadoRepository; 
	
	
	
	public Iterable<Cidade> cidades() {		
		return cidadeRepository.findAll();
	}
	
	
	
	public List<Cidade> cidadesPorNome(String nome) {		
		return cidadeRepository.findByNomeContaining(StringUtils.capitalize(nome));
	}
	

	
	public Cidade salvar (Cidade cidade) {
		cidade.setNome(StringUtils.capitalize(cidade.getNome()));
		if(cidade.getEstado() != null) {
			Estado estado = estadoRepository.findById(cidade.getEstado().getId())
				.orElseThrow(() -> new NegocioException(
						String.format("O código do estado informado para esta cidade não existe, código %d", cidade.getEstado().getId())));
			
			cidade.setEstado(estado);
		}
		else {
			throw new NegocioException("É obrigatório informar o estado da cidade");
		}		
		
		return cidadeRepository.save(cidade);		
	} 
}
