package br.com.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import br.com.controller.interfaces.IController;
import br.com.interfaces.IFuncionarioService;
import br.com.model.Funcionario;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController implements IController<Funcionario, Serializable>{
	
	private static final String PAGES_FUNCIONARIOS_NOVO_FUNCIONARIOS = "pages/funcionarios/novo_funcionario";
	private static final String PAGES_FUNCIONARIOS_FUNCIONARIOS = "pages/funcionarios/funcionarios";
	
	@Autowired
	private IFuncionarioService service;

	@Override
	@PostMapping("/save")	
	public ModelAndView salvar(Funcionario funcionario, BindingResult result, Model model, 
							   RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/funcionarios");
		
		if (result.hasErrors()) {
			return novo(funcionario);
		}
		attributes.addFlashAttribute("mensagem", "Funcionário salvo com sucesso");
		this.service.salvar(funcionario);
		return mv;
	}

	@Override
	@GetMapping("/novo")
	public ModelAndView novo(Funcionario novo) {
		ModelAndView mv = new ModelAndView(PAGES_FUNCIONARIOS_NOVO_FUNCIONARIOS);
		mv.addObject("funcionarios", novo);
		return mv;
	}

	@Override
	@GetMapping("/edit/{id}")
	public ModelAndView edit(Long id) {
		Funcionario funcionario = this.service.buscar(id); 
		return novo(funcionario);
	}

	@Override
	@GetMapping("/delete/{id}")
	public ModelAndView excluir(Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/pacientes");
		this.service.remover(id);
		attributes.addFlashAttribute("removido", "Paciente removido com sucesso!");
		return mv;
	}

	@Override
	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") Funcionario filtro) {
		ModelAndView mv = new ModelAndView(PAGES_FUNCIONARIOS_FUNCIONARIOS);		
		
		if(!StringUtils.isEmpty(filtro.getNome())) {
			List<Funcionario> funcionarios = this.service.listar(filtro);
			mv.addObject("pacientes", funcionarios);			
		}else {
			mv.addObject("pacientes", service.listar());
		}		
		return mv;
	}


}
