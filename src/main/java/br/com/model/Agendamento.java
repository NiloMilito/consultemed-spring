package br.com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("serial")
@Table(name="tb_agendamento")
@Entity
@Getter
@Setter
public class Agendamento extends AbstractEntity<Long> {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_agendamento")
	private Date dataAgendamento;
	
	@OneToOne
	private Consulta consulta;
			
}
