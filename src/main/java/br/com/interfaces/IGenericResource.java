package br.com.interfaces;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

public interface IGenericResource <T>{
	
	public ResponseEntity<T> add(T object, HttpServletResponse response);
	
	public ResponseEntity<T> remove(Long id);
	
	public ResponseEntity<T> edit(T object, HttpServletResponse response);
	
	public ResponseEntity<List<T>> list();
	
	public T get(Long id);

}
