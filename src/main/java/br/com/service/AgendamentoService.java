package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.interfaces.IAgendamentoService;
import br.com.model.Agendamento;
import br.com.repository.AgendamentoRepository;
@Service
public class AgendamentoService implements IAgendamentoService{
	
	@Autowired
	private AgendamentoRepository repository;

	@Override
	public void salvar(Agendamento object) {
		this.repository.save(object);
	}

	@Override
	public void alterar(Agendamento object) {
		this.repository.save(object);
	}

	@Override
	public void remover(Long id) {
		this.repository.delete(id);
	}

	@Override
	public Agendamento buscar(Long id) {		
		return this.repository.findOne(id);
	}

	@Override
	public List<Agendamento> listar(Agendamento filtro) {		
		return null;
	}

	@Override
	public List<Agendamento> listar() {	
		return this.repository.findAll();
	}

}
