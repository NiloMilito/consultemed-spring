package br.com.resources.contatos;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.interfaces.IGenericResource;
import br.com.model.Contato;
import br.com.service.ContatoService;

@RestController
@RequestMapping("/api")
public class ContatoResource implements IGenericResource<Contato>{
	
	@Autowired
	private ContatoService service;

	@Override
	@PostMapping("/contatos/add")
	public ResponseEntity<Contato> add(Contato contato, HttpServletResponse response) {
		this.service.save(contato);
		return ResponseEntity.status(HttpStatus.CREATED).body(contato);
	}

	@Override
	@DeleteMapping("/contatos/delete/{id}")
	public ResponseEntity<Contato> remove(Long id) {
		Contato contato = this.service.getById(id);
		if (contato != null) {
			this.service.remove(id);
			return ResponseEntity.ok(contato);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PutMapping("/contatos/edit/{id}")
	public ResponseEntity<Contato> edit(Contato agendamento, HttpServletResponse response) {
		this.service.edit(agendamento);	
		return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
	}

	@Override
	@GetMapping("/contatos")
	public ResponseEntity<List<Contato>> list() {
		List<Contato> contatos = this.service.list();
		return !contatos.isEmpty() ? ResponseEntity.ok(contatos) : ResponseEntity.noContent().build();	
	}

	@Override
	@GetMapping("/contatos/{id}")
	public Contato get(Long id) {
		return this.service.getById(id);	
	}

}
