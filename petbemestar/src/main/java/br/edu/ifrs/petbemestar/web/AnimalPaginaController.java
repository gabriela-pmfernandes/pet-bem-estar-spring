package br.edu.ifrs.petbemestar.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifrs.petbemestar.dao.AnimalDAO;
import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Animal;
import br.edu.ifrs.petbemestar.dominio.Porte;
import br.edu.ifrs.petbemestar.dominio.Tipo;
import br.edu.ifrs.petbemestar.dominio.Tutor;
import br.edu.ifrs.petbemestar.servico.AnimalService;
import br.edu.ifrs.petbemestar.servico.TutorService;
import jakarta.validation.Valid;

@Controller
public class AnimalPaginaController {
	private final AnimalService service;
	private final TutorService tutorService;
	
	public AnimalPaginaController(AnimalService service, TutorService tutorService) {
		this.service = service;
		this.tutorService = tutorService;
	}
	

	@GetMapping("/paginas/animais")
	public String listar(Model model) {
		model.addAttribute("animais", service.listarTodos());
		return "animais/lista";
	}
	
	@GetMapping("/paginas/animais/nova")
	public String formulario(Model model) {
		model.addAttribute("animal", new Animal());
		model.addAttribute("tutores", tutorService.listarTodos());
		model.addAttribute("tipos", Tipo.values());
		model.addAttribute("portes", Porte.values());
		return "animais/formulario";
	}
	
	@PostMapping("/paginas/animais/nova")
	public String cadastrar(@Valid @ModelAttribute Animal animal, BindingResult resultado, Model model) {
		
		if(resultado.hasErrors()) {
			model.addAttribute("tutores", tutorService.listarTodos());
			model.addAttribute("tipos", Tipo.values());
			model.addAttribute("portes", Porte.values());
			return "animais/formulario";
		}
		service.salvar(animal);
		return "redirect:/paginas/animais";
	}
	
	@GetMapping("/paginas/animais/{id}/editar")
	public String edicao(@PathVariable Long id, Model model) {
	    model.addAttribute("animal", service.buscarPorId(id));
	    model.addAttribute("tutores", tutorService.listarTodos());
	    model.addAttribute("tipos", Tipo.values());
	    model.addAttribute("portes", Porte.values());
	    return "animais/edicao";
	}
	
	@PostMapping("/paginas/animais/editar")
	public String atualizar(@Valid @ModelAttribute Animal animal, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("tutores", tutorService.listarTodos());
			model.addAttribute("tipos", Tipo.values());
			model.addAttribute("portes", Porte.values());
			return "animais/edicao";
		}
	    service.atualizar(animal);
	    return "redirect:/paginas/animais";
	}
	
	@PostMapping("/paginas/animais/{id}/remover")
	public String remover(@PathVariable Long id) {
	    service.remover(id);
	    return "redirect:/paginas/animais";
	}

}
