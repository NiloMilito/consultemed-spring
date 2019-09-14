package br.com.resources.agendamentos;

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

import br.com.interfaces.IAgendamentoService;
import br.com.interfaces.IGenericResource;
import br.com.model.Agendamento;

@RestController
@RequestMapping("/api")
public class AgendamentoResource implements IGenericResource<Agendamento>{
	
	@Autowired
	private IAgendamentoService service;

	@Override
	@PostMapping("/agendamentos/add")
	public ResponseEntity<Agendamento> add(@Valid @RequestBody Agendamento agendamento, HttpServletResponse response) {
		this.service.salvar(agendamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
	}

	@Override
	@DeleteMapping("/agendamentos/delete/{id}")
	public ResponseEntity<Agendamento> remove(@PathVariable Long id) {
		Agendamento agendamento = this.service.buscar(id);
		if (agendamento != null) {
			this.service.remover(id);
			return ResponseEntity.ok(agendamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PutMapping("/agendamentos/edit/{id}")
	public ResponseEntity<Agendamento> edit(@Valid @RequestBody Agendamento agendamento, HttpServletResponse response) {
		this.service.alterar(agendamento);	
		return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
	}

	@Override
	@GetMapping("/agendamentos")
	public ResponseEntity<List<Agendamento>> list() {
		List<Agendamento> agendamentos = this.service.listar();
		return !agendamentos.isEmpty() ? ResponseEntity.ok(agendamentos) : ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping("/agendamentos/{id}")
	public Agendamento get(@PathVariable Long id) {
		return this.service.buscar(id);	
	}
	

}
