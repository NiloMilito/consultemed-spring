package br.com.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Table(name="tb_prontuario")
@Entity
@Getter
@Setter
public class Prontuario extends AbstractEntity<Long>{
	
	@OneToOne
	private Paciente paciente;

}
