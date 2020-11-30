package br.com.gregorio.util;

import br.com.gregorio.entity.Cidade;
import br.com.gregorio.entity.Estado;

public class CidadeBuilder {
	
	private Long id;
	private String nome;	
	private Estado estado;
	
	public CidadeBuilder setId(Long id) {
        this.id = id;
        return this;
    }
	
	public CidadeBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public CidadeBuilder setEstado(Estado estado) {
        this.estado = estado;
        return this;
    }
    
    public Cidade build() {
        return new Cidade(id,nome, this.estado);
    }

}
