package br.edu.ifrs.petbemestar.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Tutor;
import jakarta.validation.Valid;

@Controller
public class TutorController {
	private final TutorDAO dao;
	
	public TutorController(TutorDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping("/paginas/tutores")
	public String listar(Model model) {
		model.addAttribute("tutores", dao.listarTodos());
		return "tutores/lista";
	}
	
	@GetMapping("/paginas/tutores/nova")
	public String formulario(Model model) {
		model.addAttribute("tutor", new Tutor());
		return "tutores/formulario";
	}
	
	@PostMapping("/paginas/tutores/nova")
	public String cadastrar(@Valid @ModelAttribute Tutor tutor, BindingResult resultado) {
		if(resultado.hasErrors()) {
			return "tutores/formulario";
		}
		dao.salvar(tutor);
		return "redirect:/paginas/tutores";
	}
	
	@GetMapping("/paginas/tutores/{id}/editar")
	public String edicao(@PathVariable Long id, Model model) {
		model.addAttribute("tutor", dao.buscarPorId(id));
		return "tutores/formulario";
	}
	
	
	@PostMapping("/paginas/tutores/editar")
	public String atuallizar(@ModelAttribute Tutor tutor) {
		dao.atualizar(tutor);
		return "redirect:/paginas/tutores";
	}
	
	@PostMapping("/paginas/tutores/{id}/remover")
	public String remover(@PathVariable Long id) {
		dao.remover(id);
		return "redirect:/paginas/tutores";
	}

}
