package br.com.interfaces;

import br.com.model.Funcionario;

public interface IFuncionarioService extends IGenericService<Funcionario, Long>{
	
	public Funcionario buscarFuncionarioPorEmail(String email);

}
