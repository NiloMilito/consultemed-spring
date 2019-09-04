package br.com.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Table(name="tb_paciente")
@Entity
@Getter
@Setter
public class Paciente extends Pessoa {	
	
	@Embedded
	private Endereco endereco;
	
	public Paciente() {
		this.endereco = new Endereco();
	}
	

}
