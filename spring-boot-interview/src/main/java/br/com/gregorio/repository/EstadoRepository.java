package br.com.gregorio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.gregorio.entity.Estado;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Long>{

	Estado findByUf(String uf);	
	
}