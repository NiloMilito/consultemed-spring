/**
 * 
 */
package br.com.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.enus.StatusUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */

@NoArgsConstructor
@Entity
@Table(name = "TB_USUARIOS")
@Getter
@Setter
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusUsuario situacao;
	
	@Column(name = "LOGIN")
	private String login;
	
	@Column(name = "SENHA")
	private String senha;
	
}
