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
import br.com.interfaces.IAgendamentoService;
import br.com.model.Agendamento;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController implements IController<Agendamento, Serializable>{
	
	private static final String PAGES_AGENDAMENTOS_NOVO_AGENDAMENTO = "pages/agendamentos/novo_agendamento";
	private static final String PAGES_AGENDAMENTOS_AGENDAMENTOS = "pages/agendamentos/agendamentos";
	
	@Autowired
	private IAgendamentoService service;
	
	@Override
	@PostMapping("/save")
	public ModelAndView salvar(Agendamento agendamento, BindingResult result, Model model, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/agendamentos");
		
		if (result.hasErrors()) {
			return novo(agendamento);
		}
		attributes.addFlashAttribute("mensagem", "Agendamento salvo com sucesso");
		this.service.salvar(agendamento);
		return mv;
	}

	@Override
	@GetMapping("/novo")
	public ModelAndView novo(Agendamento novo) {
		ModelAndView mv = new ModelAndView(PAGES_AGENDAMENTOS_NOVO_AGENDAMENTO);
		mv.addObject("agendamentos", novo);
		return mv;
	}

	@Override
	@GetMapping("/edit/{id}")
	public ModelAndView edit(Long id) {
		Agendamento agendamento = this.service.buscar(id); 
		return novo(agendamento);
	}

	@Override
	@GetMapping("/delete/{id}")
	public ModelAndView excluir(Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/agendamentos	");
		this.service.remover(id);
		attributes.addFlashAttribute("removido", "Agendamento removido com sucesso!");
		return mv;
	}

	@Override
	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") Agendamento filtro) {
		ModelAndView mv = new ModelAndView(PAGES_AGENDAMENTOS_AGENDAMENTOS);		
		
		if(filtro != null) {
			List<Agendamento> agendamentos = this.service.listar(filtro);
			mv.addObject("pacientes", agendamentos);			
		}else {
			mv.addObject("pacientes", service.listar());
		}		
		return mv;
	}

}
