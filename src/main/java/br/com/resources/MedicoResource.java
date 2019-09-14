package br.com.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.interfaces.IMedicoResource;
import br.com.model.Medico;
import br.com.service.MedicoService;
@RestController
@RequestMapping("/api")
public class MedicoResource implements IMedicoResource<Medico>{

	@Autowired
	private MedicoService service;

	@Override
	@PostMapping("/medicos/add")
	public ResponseEntity<Medico> add(@Valid @RequestBody Medico medico, HttpServletResponse response) {		
		this.service.salvar(medico);
		return ResponseEntity.status(HttpStatus.CREATED).body(medico);
	}

	@Override
	@DeleteMapping("/medicos/delete/{id}")
	public ResponseEntity<Medico> remove(@PathVariable Long id) {
		Medico medico = this.service.buscar(id);
		if (medico != null) {
			this.service.remover(id);
			return ResponseEntity.ok(medico);
		} else {
			return ResponseEntity.notFound().build();
		}		
	}

	@Override
	@PutMapping("/medicos/edit/{id}")
	public ResponseEntity<Medico> edit(@Valid @RequestBody Medico medico, HttpServletResponse response) {
		this.service.alterar(medico);	
		return ResponseEntity.status(HttpStatus.CREATED).body(medico);	
	}

	@Override
	@GetMapping("/medicos")
	public ResponseEntity<List<Medico>> list() {
		List<Medico> medicos = this.service.listar();
		return !medicos.isEmpty() ? ResponseEntity.ok(medicos) : ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping("/medicos/{id}")
	public Medico get(@PathVariable Long id) {
		return this.service.buscar(id);		
	}
}
