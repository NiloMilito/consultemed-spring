package br.com.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import br.com.controller.interfaces.IController;
import br.com.interfaces.IPacienteService;
import br.com.model.Paciente;
@Controller
public class PacienteController implements IController<Paciente, Serializable> {
	private static final String PAGES_PACIENTES_NOVO_PACIENTE = "pages/pacientes/novo_paciente";
	private static final String PAGES_PACIENTES_PACIENTES = "pages/pacientes/pacientes";
	
	@Autowired
	private IPacienteService service;

	@Override
	public ModelAndView salvar(Paciente paciente, BindingResult result, Model model, 
							   RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/pacientes");
		
		if (result.hasErrors()) {
			return novo(paciente);
		}
		attributes.addFlashAttribute("mensagem", "Médico salvo com sucesso");
		this.service.salvar(paciente);
		return mv;
	}

	@Override
	public ModelAndView novo(Paciente novo) {
		ModelAndView mv = new ModelAndView(PAGES_PACIENTES_NOVO_PACIENTE);
		mv.addObject("pacientes", novo);
		return mv;
	}

	@Override
	public ModelAndView edit(Long id) {
		Paciente paciente = this.service.buscar(id); 
		return novo(paciente);
	}

	@Override
	public ModelAndView excluir(Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/pacientes");
		this.service.remover(id);
		attributes.addFlashAttribute("removido", "Paciente removido com sucesso!");
		return mv;
	}

	@Override
	public ModelAndView listar(Paciente filtro) {
		ModelAndView mv = new ModelAndView(PAGES_PACIENTES_PACIENTES);		
		
		if(!StringUtils.isEmpty(filtro.getNome())) {
			List<Paciente> pacientes = this.service.listar(filtro);
			mv.addObject("pacientes", pacientes);			
		}else {
			mv.addObject("pacientes", service.listar());
		}		
		return mv;
	}
}
