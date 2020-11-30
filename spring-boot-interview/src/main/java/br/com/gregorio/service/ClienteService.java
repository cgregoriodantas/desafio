package br.com.gregorio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.gregorio.entity.Cliente;
import br.com.gregorio.exception.NegocioException;
import br.com.gregorio.repository.CidadeRepository;
import br.com.gregorio.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	
	
	public Iterable<Cliente> clientes() {		
		return clienteRepository.findAll();
	}
	
	
	
	public List<Cliente> clientesPorNome(String nome) {		
		return clienteRepository.findByNomeContaining(StringUtils.capitalize(nome));
	}
	
	
	
	public Optional<Cliente> clientesPorId(Long id) {		
		return clienteRepository.findById(id);
	}
	
	
	
	public Cliente salvar(Cliente cliente) {		
		cliente.setNome(StringUtils.capitalize(cliente.getNome()));
		
		if(cliente.getCidade() != null) {
			cidadeRepository.findById(cliente.getCidade().getId())
					.orElseThrow(() -> new NegocioException(
							String.format("O código da cidade informado para este cliente não existe, código %d", cliente.getCidade().getId())));
		}
		else {
			throw new NegocioException("É obrigatório informar a cidade do cliente");
		}	
		
		return clienteRepository.save(cliente);		
	} 
	
	
	
	public Cliente alterar(Long id, Cliente cliente) {
		cliente.setNome(StringUtils.capitalize(cliente.getNome()));
		Optional<Cliente> clienteOriginal = clienteRepository.findById(id);
		
		if(clienteOriginal.isPresent()) {
			BeanUtils.copyProperties(cliente, clienteOriginal);
		}
		else {
			throw new NegocioException(String.format("Não existe cadastro de cliente com código %d", id));
		}	
		
		if(cliente.getCidade() != null) {
			cidadeRepository.findById(cliente.getCidade().getId())
					.orElseThrow(() -> new NegocioException(
							String.format("O código da cidade informado para este cliente não existe, código %d", cliente.getCidade().getId())));
		}
		else {
			throw new NegocioException("É obrigatório informar a cidade do cliente");
		}	
		
		return clienteRepository.save(cliente);		
	} 
	
	
	
	public void deletarPorId(Long id) {
		
		clienteRepository.findById(id)
				.orElseThrow(() -> new NegocioException(
						String.format("Não existe cadastro de cliente com código %d", id)));
		
		clienteRepository.deleteById(id);	
	}
}
