package br.com.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericService <T, ID extends Serializable>{
	
	public void salvar(T object);
	
	public void alterar(T object);
	
	public void remover (Long id);
	
	public T buscar(Long id);
	
	public List <T> listar();
	
}
