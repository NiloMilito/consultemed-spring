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
import br.com.interfaces.IMedicoService;
import br.com.model.Medico;

@Controller
@RequestMapping("/medicos")
public class MedicoController implements IController<Medico, Serializable>{
	
	private static final String PAGES_MEDICO_NOVO_MEDICO = "pages/medicos/novo_medico";
	private static final String PAGES_MEDICO_MEDICOS = "pages/medicos/medicos";
	
	@Autowired
	private IMedicoService service;

	@Override
	@PostMapping("/save")	
	public ModelAndView salvar(Medico medico, BindingResult result, Model model, 
							   RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/medicos");
		
		if (result.hasErrors()) {
			return novo(medico);
		}
		attributes.addFlashAttribute("mensagem", "Médico salvo com sucesso");
		this.service.salvar(medico);
		return mv;
	}

	@Override
	@GetMapping("/novo")
	public ModelAndView novo(Medico novo) {
		ModelAndView mv = new ModelAndView(PAGES_MEDICO_NOVO_MEDICO);
		mv.addObject("medicos", novo);
		return mv;
	}

	@Override
	@GetMapping("/edit/{id}")
	public ModelAndView edit(Long id) {
		Medico medico = this.service.buscar(id); 
		return novo(medico);
	}

	@Override
	@GetMapping("/delete/{id}")
	public ModelAndView excluir(Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/medicos");
		this.service.remover(id);
		attributes.addFlashAttribute("removido", "Medico removido com sucesso!");
		return mv;
	}

	@Override
	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") Medico filtro) {
		ModelAndView mv = new ModelAndView(PAGES_MEDICO_MEDICOS);		
		
		if(!StringUtils.isEmpty(filtro.getNome())) {
			List<Medico> medicos = this.service.listar(filtro);
			mv.addObject("medicos", medicos);			
		}else {
			mv.addObject("medicos", service.listar());
		}		
		return mv;
	}

}
