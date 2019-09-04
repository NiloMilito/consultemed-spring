package br.com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.enus.StatusConsulta;
import br.com.interfaces.IConsultaService;
import br.com.model.Consulta;
import br.com.model.Medico;
import br.com.model.Paciente;
import br.com.repository.ConsultaRepository;
@Service
public class ConsultaService implements IConsultaService{
	
	@Autowired
	private ConsultaRepository repository;

	@Override
	@Transactional
	public void salvar(Consulta consulta) {
		this.repository.save(consulta);
	}

	@Override
	@Transactional
	public void alterar(Consulta consulta) {
		this.repository.save(consulta);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		this.repository.delete(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Consulta buscar(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Consulta> listar() {
		return this.repository.findAll();
	}

	@Override
	public boolean podeFazerAgendamento(Date data, Medico medico) {
		return this.repository.existsDataConsultaAndMedico(data, medico) > 0;
	}

	@Override
	public List<Consulta> buscarPorPeriodo(Date inicio, Date fim) {
		return this.repository.findByDataConsultaBetween(inicio, fim);
	}

	@Override
	@Transactional
	public void cancelarConsulta(Consulta consulta) {
		consulta.setStatusConsulta(StatusConsulta.CANCELADA);
		this.alterar(consulta);
	}

	@Override
	public List<Consulta> buscaConsultasNoMes(int mes) {
		return null;
	}

	@Override
	public List<Consulta> buscaConsultasPorPaciente(Paciente paciente) {
		return this.repository.findByPaciente(paciente);
	}

	@Override
	public Paciente maisCancelouConsulta() {
		List<Consulta> canceladas = this.repository.findByStatusConsulta(StatusConsulta.CANCELADA);
		Paciente cancelador = new Paciente();
		int cont = 0;
		for(Consulta c : canceladas) {
			int qtde = retornaQtdeCanceladas(c.getPaciente(), canceladas);			
				
			if (qtde > cont) {
				cancelador = c.getPaciente();
			}
			cont = qtde;
		}
		
		return cancelador;
	}

	private int retornaQtdeCanceladas(Paciente paciente, List<Consulta> canceladas) {
		int qtde = 0;
		for(Consulta c : canceladas) {
			if(paciente.equals(c)) {
				qtde ++;
			}									
		}
		return qtde;
	}

}
