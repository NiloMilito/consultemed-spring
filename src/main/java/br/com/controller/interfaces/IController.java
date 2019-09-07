package br.com.controller.interfaces;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public interface IController <T, ID extends Serializable>{
	
	public ModelAndView salvar(@Valid T object, BindingResult result, Model model, RedirectAttributes attributes);
	
	public ModelAndView novo(T novo);
	
	public ModelAndView edit(@PathVariable("id") Long id);	
	
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes);
	
	public ModelAndView listar(@ModelAttribute("filtro") T filtro);
	
	

}
