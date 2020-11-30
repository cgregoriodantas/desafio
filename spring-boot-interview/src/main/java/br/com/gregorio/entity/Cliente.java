package br.com.gregorio.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Cliente")
public class Cliente {
	
	@Id
	@EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)	
    private Long id;

	@NotBlank
	@Column(name = "none")	
    private String nome;    

    @Column(name = "data_nascimento", columnDefinition = "DATE")
    @NotNull
    private LocalDate dataNascimento;
    
    @Column(name = "idade")
    private Integer idade;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;
   
    @ManyToOne
	@JoinColumn(name = "cidade_id")
    @NotNull
	private Cidade cidade;
   
}
