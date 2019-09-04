package br.com.interfaces;

import java.util.Date;
import java.util.List;

import br.com.model.Consulta;
import br.com.model.Medico;
import br.com.model.Paciente;

public interface IConsultaService extends IGenericService<Consulta, Long>{
	
	public boolean podeFazerAgendamento(Date date, Medico medico);

	public List<Consulta> buscarPorPeriodo(Date inicio, Date fim);

	public void cancelarConsulta(Consulta consulta);
	
	public List<Consulta> buscaConsultasNoMes(int mes);
	
	public List<Consulta> buscaConsultasPorPaciente(Paciente paciente);
	
	public Paciente maisCancelouConsulta();

}
