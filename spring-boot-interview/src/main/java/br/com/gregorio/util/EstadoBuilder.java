package br.com.gregorio.util;

import br.com.gregorio.entity.Estado;

public class EstadoBuilder {
	
	private Long id;
	private String uf;	
		
	
	public EstadoBuilder setId(Long id) {
        this.id = id;
        return this;
    }
	
	public EstadoBuilder setUf(String uf) {
        this.uf = uf;
        return this;
    }

       
    public Estado build() {
        return new Estado(id,uf);
    }

}
