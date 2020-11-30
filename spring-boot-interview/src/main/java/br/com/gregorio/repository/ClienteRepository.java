package br.com.gregorio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.gregorio.entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	List<Cliente> findByNomeContaining(String nome);
	
	Optional<Cliente> findById(Long id);
		
}
