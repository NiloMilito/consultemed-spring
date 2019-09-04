package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Table(name="tb_dias_atendimento")
@Entity
@Getter
@Setter
public class DiasAtendimento extends AbstractEntity<Long> {

	@Column(name="segunda")
	private Boolean segunda;
	
	@Column(name="terca")
	private Boolean terca;
	
	@Column(name="quarta")
	private Boolean quarta;
	
	@Column(name="quinta")
	private Boolean quinta;
	
	@Column(name="sexta")
	private Boolean sexta;
	
	@Column(name="sabado")
	private Boolean sabado;
	
	@Column(name="domingo")
	private Boolean domingo;
	
	public DiasAtendimento() {
		
	}

	public DiasAtendimento(Boolean segunda, Boolean terca, Boolean quarta, Boolean quinta,
			Boolean sexta, Boolean sabado, Boolean domingo) {
		super();			
		this.segunda = segunda;
		this.terca = terca;
		this.quarta = quarta;
		this.quinta = quinta;
		this.sexta = sexta;
		this.sabado = sabado;
		this.domingo = domingo;
	}
	
	
}
