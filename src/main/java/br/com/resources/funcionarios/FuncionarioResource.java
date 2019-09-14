package br.com.resources.funcionarios;

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

import br.com.interfaces.IFuncionarioService;
import br.com.interfaces.IGenericResource;
import br.com.model.Funcionario;

@RestController
@RequestMapping("/api")
public class FuncionarioResource implements IGenericResource<Funcionario>{

	@Autowired
	private IFuncionarioService service;
	
	@Override
	@PostMapping("/funcionarios/add")
	public ResponseEntity<Funcionario> add(@Valid @RequestBody Funcionario funcionariocontato, HttpServletResponse response) {
		this.service.salvar(funcionariocontato);
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionariocontato);
	}

	@Override
	@DeleteMapping("/funcionarios/delete/{id}")
	public ResponseEntity<Funcionario> remove(@PathVariable Long id) {
		Funcionario funcionario = this.service.buscar(id);
		if (funcionario != null) {
			this.service.remover(id);
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PutMapping("/funcionarios/edit/{id}")
	public ResponseEntity<Funcionario> edit(@Valid @RequestBody Funcionario funcionario, HttpServletResponse response) {
		this.service.alterar(funcionario);	
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
	}

	@Override
	@GetMapping("/funcionarios")
	public ResponseEntity<List<Funcionario>> list() {
		List<Funcionario> funcionarios = this.service.listar();
		return !funcionarios.isEmpty() ? ResponseEntity.ok(funcionarios) : ResponseEntity.noContent().build();	
	}

	@Override
	@GetMapping("/funcionarios/{id}")
	public Funcionario get(@PathVariable Long id) {
		return this.service.buscar(id);
	}

}
