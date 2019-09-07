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

import br.com.controller.interfaces.IController;
import br.com.interfaces.IConsultaService;
import br.com.model.Consulta;

@Controller
@RequestMapping("/consultas")
public class ConsultaController implements IController<Consulta, Serializable>{
	
	private static final String PAGES_CONSULTAS_NOVA_CONSULTAS = "pages/consultas/nova_consultas";
	private static final String PAGES_CONSULTAS_CONSULTAS = "pages/consultas/consultas";
	
	@Autowired
	private IConsultaService service;

	@Override
	@PostMapping("/save")	
	public ModelAndView salvar(Consulta consulta, BindingResult result, Model model, 
							   RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/consultas");
		
		if (result.hasErrors()) {
			return novo(consulta);
		}
		attributes.addFlashAttribute("mensagem", "Consulta salva com sucesso");
		this.service.salvar(consulta);
		return mv;
	}

	@Override
	@GetMapping("/novo")
	public ModelAndView novo(Consulta novo) {
		ModelAndView mv = new ModelAndView(PAGES_CONSULTAS_NOVA_CONSULTAS);
		mv.addObject("consultas", novo);
		return mv;
	}

	@Override
	@GetMapping("/edit/{id}")
	public ModelAndView edit(Long id) {
		Consulta consulta = this.service.buscar(id); 
		return novo(consulta);
	}

	@Override
	@GetMapping("/delete/{id}")
	public ModelAndView excluir(Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/consultas");
		this.service.remover(id);
		attributes.addFlashAttribute("removido", "Consulta removida com sucesso!");
		return mv;
	}

	@Override
	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") Consulta filtro) {
		ModelAndView mv = new ModelAndView(PAGES_CONSULTAS_CONSULTAS);		
		
		if(filtro != null) {
			List<Consulta> consultas = this.service.listar(filtro);
			mv.addObject("pacientes", consultas);			
		}else {
			mv.addObject("pacientes", service.listar());
		}		
		return mv;
	}

}
