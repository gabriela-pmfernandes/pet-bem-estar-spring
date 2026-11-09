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
import jakarta.validation.Valid;

@Controller
public class AnimalController {
	private final AnimalDAO animalDao;
	private final TutorDAO tutorDao;
	
	public AnimalController(AnimalDAO animalDao, TutorDAO tutorDao) {
		this.animalDao = animalDao;
		this.tutorDao = tutorDao;
	}
	
	@GetMapping("/paginas/animais")
	public String listar(Model model) {
		model.addAttribute("animais", animalDao.listarTodos());
		return "animais/lista";
	}
	
	@GetMapping("/paginas/animais/nova")
	public String formulario(Model model) {
		model.addAttribute("animal", new Animal());
		model.addAttribute("tutores", tutorDao.listarTodos());
		model.addAttribute("tipos", Tipo.values());
		model.addAttribute("portes", Porte.values());
		return "animais/formulario";
	}
	
	@PostMapping("/paginas/animais/nova")
	public String cadastrar(@Valid @ModelAttribute Animal animal, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("tutores", tutorDao.listarTodos());
			model.addAttribute("tipos", Tipo.values());
			model.addAttribute("portes", Porte.values());
			return "animais/formulario";
		}
		animalDao.salvar(animal);
		return "redirect:/paginas/animais";
	}
	
	@GetMapping("/paginas/animais/{id}/editar")
	public String edicao(@PathVariable Long id, Model model) {
		model.addAttribute("animal", animalDao.buscarPorId(id));
		model.addAttribute("tutores", tutorDao.listarTodos());
		model.addAttribute("tipos", Tipo.values());
		model.addAttribute("portes", Porte.values());
		return "animais/formulario";
	}
	
	@PostMapping("/paginas/animais/editar")
	public String atualizar(@Valid @ModelAttribute Animal animal, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("tutores", tutorDao.listarTodos());
			model.addAttribute("tipos", Tipo.values());
			model.addAttribute("portes", Porte.values());
			return "animais/formulario";
		}
		animalDao.atualizar(animal);
		return "redirect:/paginas/animais";
	}
	
	@PostMapping("/paginas/aniamis/{id}/remover")
	public String remover(@PathVariable Long id) {
		animalDao.remover(id);
		return "redirect:/paginas/aninais";
	}

}
