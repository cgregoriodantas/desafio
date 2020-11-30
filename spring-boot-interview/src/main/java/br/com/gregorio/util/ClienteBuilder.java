package br.com.gregorio.util;

import java.time.LocalDate;

import br.com.gregorio.entity.Cidade;
import br.com.gregorio.entity.Cliente;
import br.com.gregorio.entity.SexoCliente;

public class ClienteBuilder {
	
	private Long id;
    private String nome = "";
    private SexoCliente sexo = null;
    private LocalDate dataNascimento;
    private Integer idade;
    private Cidade cidade;


    public ClienteBuilder setId(Long id) {
        this.id = id;
        return this;
    }
    
    public ClienteBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteBuilder setSexo(SexoCliente sexo) {
        this.sexo = sexo;
        return this;
    }

    public ClienteBuilder setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }
    
    public ClienteBuilder setIdade(Integer idade) {
        this.idade = idade;
        return this;
    }

    public ClienteBuilder setCidade(Cidade cidade) {
        this.cidade = cidade;
        return this;
    }

    public Cliente build() {//id, nome, sexo, this.dataNascimento, this.idade, this.cidade
        return new Cliente(id,nome,dataNascimento,idade,sexo,cidade);
    }

}
