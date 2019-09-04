package br.com.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.enus.StatusConsulta;
import br.com.model.Consulta;
import br.com.model.Medico;
import br.com.model.Paciente;
@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
	
	@Query(value="SELECT COUNT(c) FROM Consulta c WHERE c.dataConsulta = :date AND c.medico = :medico")
	public int existsDataConsultaAndMedico(Date date, Medico medico);
	
	List<Consulta> findByDataConsultaBetween(Date inicio, Date fim);

	List<Consulta> findByPaciente(Paciente paciente);

	List<Consulta> findByStatusConsulta(StatusConsulta cancelada);

}
