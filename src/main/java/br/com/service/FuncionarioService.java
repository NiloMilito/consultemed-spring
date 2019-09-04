package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.interfaces.IFuncionarioService;
import br.com.model.Funcionario;
import br.com.repository.FuncionarioRepository;
@Service
public class FuncionarioService implements IFuncionarioService{
	@Autowired
	private FuncionarioRepository repository;

	@Override
	@Transactional
	public void salvar(Funcionario funcionario) {
		this.repository.save(funcionario);
	}

	@Override
	@Transactional
	public void alterar(Funcionario funcionario) {
		this.repository.save(funcionario);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		this.repository.delete(id);
	}

	@Override
	public Funcionario buscar(Long id) {
		return this.repository.findOne(id);
	}

	@Override
	public List<Funcionario> listar() {
		return this.repository.findAll();
	}

	@Override
	public Funcionario buscarFuncionarioPorEmail(String email) {
		return this.repository.findByEmail(email);
	}

}
