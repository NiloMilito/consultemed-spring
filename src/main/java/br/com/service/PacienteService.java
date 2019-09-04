package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.interfaces.IPacienteService;
import br.com.model.Paciente;
import br.com.repository.PacienteRepository;

@Service
public class PacienteService implements IPacienteService{
	@Autowired
	private PacienteRepository repository;

	@Override
	@Transactional
	public void salvar(Paciente paciente){
		this.repository.save(paciente);
	}

	@Override
	@Transactional
	public void alterar(Paciente paciente){
		this.repository.save(paciente);
	}

	@Override
	@Transactional
	public void remover(Long id){
		this.repository.delete(id);
	}

	@Override
	public Paciente buscar(Long id){		
		return this.repository.findOne(id);
	}

	@Override
	public List<Paciente> listar(){		
		return this.repository.findAll();
	}

	@Override
	public Paciente buscarPorNome(String nome) {
		return this.repository.findByNome(nome);
	}

	@Override
	public Paciente buscarPorCpf(String cpf) {	
		return this.repository.findByCpf(cpf);
	}

}
