package br.com.resources.pacientes;

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

import br.com.interfaces.IGenericResource;
import br.com.model.Paciente;
import br.com.service.PacienteService;

@RestController
@RequestMapping("/api")
public class PacienteResource implements IGenericResource<Paciente>{
	
	@Autowired
	private PacienteService service;

	@Override
	@PostMapping("/pacientes/add")
	public ResponseEntity<Paciente> add(@Valid @RequestBody Paciente paciente, HttpServletResponse response) {
		this.service.salvar(paciente);
		return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
	}

	@Override
	@DeleteMapping("/pacientes/delete/{id}")
	public ResponseEntity<Paciente> remove(@PathVariable Long id) {
		Paciente paciente = this.service.buscar(id);
		if (paciente != null) {
			this.service.remover(id);
			return ResponseEntity.ok(paciente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PutMapping("/pacientes/edit/{id}")
	public ResponseEntity<Paciente> edit(@Valid @RequestBody Paciente paciente, HttpServletResponse response) {
		this.service.alterar(paciente);	
		return ResponseEntity.status(HttpStatus.CREATED).body(paciente);	
	}

	@Override
	@GetMapping("/pacientes")
	public ResponseEntity<List<Paciente>> list() {
		List<Paciente> pacientes = this.service.listar();
		return !pacientes.isEmpty() ? ResponseEntity.ok(pacientes) : ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping("/pacientes/{id}")
	public Paciente get(@PathVariable Long id) {
		return this.service.buscar(id);	
	}

}
