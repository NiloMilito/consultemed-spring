package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Funcionario;
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	Funcionario findByEmail(String email);

}
