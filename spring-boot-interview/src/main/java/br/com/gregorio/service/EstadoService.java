package br.com.gregorio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gregorio.entity.Cidade;
import br.com.gregorio.entity.Estado;
import br.com.gregorio.repository.CidadeRepository;
import br.com.gregorio.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	EstadoRepository estadoRepository; 
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	
	
	public Iterable<Estado> estados() {
		return estadoRepository.findAll();
	}
	
	
	
	public Estado estadosPorUf(String uf) {
		return estadoRepository.findByUf(uf);
	}
	
	
	
	public List<Cidade> buscarCidadesPorEstado(String uf) {
		return cidadeRepository.buscarCidadesPorEstado(uf);
	}
}
