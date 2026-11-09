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
import jakarta.validation.Valid;

@Controller
public class AnimalPaginaController {
	private final AnimalDAO dao;
	private final TutorDAO tutorDAO;
	
	public AnimalPaginaController(AnimalDAO dao, TutorDAO tutorDAO) {
		this.dao = dao;
		this.tutorDAO = tutorDAO;
	}
	

	@GetMapping("/paginas/animais")
	public String listar(Model model) {
		model.addAttribute("animais", dao.listarTodos());
		return "animais/lista";
	}
	
	@GetMapping("/paginas/animais/nova")
	public String formulario(Model model) {
		model.addAttribute("animal", new Animal());
		model.addAttribute("tutores", tutorDAO.listarTodos());
		model.addAttribute("tipos", Tipo.values());
		model.addAttribute("portes", Porte.values());
		return "animais/formulario";
	}
	
	@PostMapping("/paginas/animais/nova")
	public String cadastrar(@Valid @ModelAttribute Animal animal, BindingResult resultado, Model model) {
		
		if(resultado.hasErrors()) {
			model.addAttribute("tutores", tutorDAO.listarTodos());
			model.addAttribute("tipos", Tipo.values());
			model.addAttribute("portes", Porte.values());
			return "animais/formulario";
		}
		Tutor completo = tutorDAO.buscarPorId(animal.getTutor().getId());
		animal.setTutor(completo);
		System.out.println("Nome do tutor do animal cadastrado: " + animal.getTutor().getNome());
		dao.salvar(animal);
		return "redirect:/paginas/animais";
	}
	
	@GetMapping("/paginas/animais/{id}/editar")
	public String edicao(@PathVariable Long id, Model model) {
	    model.addAttribute("animal", dao.buscarPorId(id));
	    model.addAttribute("tutores", tutorDAO.listarTodos());
	    model.addAttribute("tipos", Tipo.values());
	    model.addAttribute("portes", Porte.values());
	    return "animais/edicao";
	}
	
	@PostMapping("/paginas/animais/editar")
	public String atualizar(@Valid @ModelAttribute Animal animal, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("tutores", tutorDAO.listarTodos());
			model.addAttribute("tipos", Tipo.values());
			model.addAttribute("portes", Porte.values());
			return "animais/edicao";
		}
	    dao.atualizar(animal);
	    return "redirect:/paginas/animais";
	}
	
	@PostMapping("/paginas/animais/{id}/remover")
	public String remover(@PathVariable Long id) {
	    dao.remover(id);
	    return "redirect:/paginas/animais";
	}

}
