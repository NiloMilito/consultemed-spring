package br.com.interfaces;

import br.com.model.Paciente;

public interface IPacienteService extends IGenericService<Paciente, Long>{
	
	public Paciente buscarPorNome(String nome);
	
	public Paciente buscarPorCpf(String cpf);

}
