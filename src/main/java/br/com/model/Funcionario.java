package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Table(name="tb_funcionario")
@Entity
@Getter
@Setter
public class Funcionario extends Pessoa{
	
	@Column(name="matricula")
	private String matricula;

}
