package br.com.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Contato;
@Repository
public interface ContatosRepository extends JpaRepository<Contato, Long> {

	
	public List<Contato> findByNomeContaining(String nome);
}
