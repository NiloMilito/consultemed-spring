/**
 * 
 */
package br.com.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private ID id;

	@Override
	public String toString() {
		return  this.id+"";
	}
	
	
}
