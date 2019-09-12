package br.com.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@MappedSuperclass
@Getter
@Setter
public class Pessoa extends AbstractEntity<Long> {
	
	@Column(name="nome")
	@NotEmpty(message="Nome é obrigatório")
	private String nome;
	
	@Column(name="email")
	@Email(message="Endereço de e-mail enviado em um formato inválido.")
	private String email;
	
	@Column(name="cpf", unique=true)
	@NotEmpty(message="CPF é obrigatório")
	private String cpf;
	
	public Pessoa() {
		
	}

	public Pessoa(Long id, String nome, String cpf) {		
		super.setId(id);
		this.nome = nome;
		this.cpf = cpf;
	}
	
}
