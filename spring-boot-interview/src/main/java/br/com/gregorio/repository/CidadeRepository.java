package br.com.gregorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.gregorio.entity.Cidade;

@Repository
public interface CidadeRepository extends CrudRepository<Cidade, Long>{

	List<Cidade> findByNomeContaining(String nome);	
		
	@Query("select c from Cidade c inner join c.estado e where e.uf = :uf")
	List<Cidade> buscarCidadesPorEstado(String uf);
	
}
