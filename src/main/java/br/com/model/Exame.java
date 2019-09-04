package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Table(name="tb_exame")
@Entity
@Getter
@Setter
public class Exame extends AbstractEntity<Long> {	
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="diagnostico")
	private String diagnostico;
	
	@Column(name="laudo")
	private String laudo;
	
	@Column(name="material_coletavel")
	private String materialColetavel;
	
	@ManyToOne
	@JoinColumn(name = "exames")
	private Consulta consulta;


}
