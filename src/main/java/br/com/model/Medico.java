/**
 * 
 */
package br.com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */

@SuppressWarnings("serial")
@NoArgsConstructor
@Entity
@Table(name = "TB_MEDICOS")
@Getter
@Setter
public class Medico extends Pessoa{
		
	@Column(name = "CRM", unique = true)
	@NotEmpty(message="CRM é obrigatório")
	private String crm;

	@Column(name = "TELEFONE")
	private String telefone;

	@OneToOne(cascade = CascadeType.ALL)
	private DiasAtendimento diasAtendimento;

		
}
