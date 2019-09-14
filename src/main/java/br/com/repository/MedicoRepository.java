package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Medico;
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

	List<Medico> findByNomeContaining(String nome);

}
