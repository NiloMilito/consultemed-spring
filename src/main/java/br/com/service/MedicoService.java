package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.interfaces.IMedicoService;
import br.com.model.Medico;
import br.com.repository.MedicoRepository;

@Service
public class MedicoService implements IMedicoService{
	
	@Autowired
	private MedicoRepository repository;

	@Override
	@Transactional
	public void salvar(Medico medico) {
		this.repository.save(medico);
	}

	@Override
	@Transactional
	public void alterar(Medico medico) {
		this.repository.saveAndFlush(medico);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		this.repository.delete(id);
	}

	@Override
	public Medico buscar(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public List<Medico> listar(Medico filtro) {			
		return this.repository.findByNomeContaining(filtro.getNome());
	}
	
	public List<Medico> listar() {	
		return this.repository.findAll();
	}

}
