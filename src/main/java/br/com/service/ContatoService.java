/**
 * 
 */
package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Contato;
import br.com.model.dto.ContatoPesquisaDTO;
import br.com.repository.ContatosRepository;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Service
public class ContatoService {

	@Autowired
	private ContatosRepository repository;
	
	@Transactional(readOnly=true)
	public List<Contato> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void save(Contato contato) {
		this.repository.save(contato);
	}
	
	@Transactional
	public void edit(Contato contato) {
		this.repository.saveAndFlush(contato);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Contato getById(Long id) {
		return this.repository.findOne(id);
	}
	
	public List<Contato> filtrar(ContatoPesquisaDTO contato) {
		String nome = contato.getNome() == null ? "%" : contato.getNome()+"%";
		return repository.findByNomeContaining(nome);
	}


	@Transactional
	public boolean ativarDesativar(Long id) {		
		boolean ativou = false;
		
		Contato contato = this.repository.getOne(id);
		// if(contato.getSituacao().getValor() == 0) {
		//	contato.setSituacao(StatusUsuario.INATIVO);
		//	return ativou;
		//}else {
		//	contato.setSituacao(StatusUsuario.ATIVO);
		//	ativou = true;
		// }
		return ativou;
	}
	
	public boolean ativaDesativarContato(Contato contato) {
		//if (contato.getSituacao().getValor() == 0) {
			//ativaDesativaUsuario(contato);
		//} else {
			//ativaDesativaUsuario(contato);
		//}
		return false;
	}
	
	@Transactional
	private void ativaDesativaUsuario(Contato contato) {

		//if (contato.getSituacao().getValor() == 0) {
			//contato.setSituacao(StatusUsuario.INATIVO);
		//} else {
			//contato.setSituacao(StatusUsuario.ATIVO);
		//}

		this.repository.saveAndFlush(contato);
	}

}
