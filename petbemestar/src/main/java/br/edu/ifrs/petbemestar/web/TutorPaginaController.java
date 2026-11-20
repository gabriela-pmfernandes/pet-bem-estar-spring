package br.edu.ifrs.petbemestar.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Tutor;
import br.edu.ifrs.petbemestar.servico.RegraDeNegocioException;
import br.edu.ifrs.petbemestar.servico.TutorService;
import jakarta.validation.Valid;

@Controller
public class TutorPaginaController {
	
	private final TutorService service;
	
	public TutorPaginaController(TutorService service) {
		this.service = service;
	}
	

	@GetMapping("/paginas/tutores")
	public String listar(Model model) {
		model.addAttribute("tutores", service.listarTodos());
		return "tutores/lista";
	}
	
	@GetMapping("/paginas/tutores/nova")
	public String formulario(Model model) {
		model.addAttribute("tutor", new Tutor());
		return "tutores/formulario";
	}
	
	@PostMapping("/paginas/tutores/nova")
	public String cadastrar(@Valid @ModelAttribute Tutor tutor, BindingResult resultado, Model model) {
		
		if(resultado.hasErrors()) {
			return "tutores/formulario";
			
		}
		try {
			service.salvar(tutor);
			return "redirect:/paginas/tutores";
			
		}catch(RegraDeNegocioException e) {
			model.addAttribute("erro", e.getMessage());
			return "tutores/formulario";
		}
	
		
		
	}
	
	@GetMapping("/paginas/tutores/{id}/editar")
	public String edicao(@PathVariable Long id, Model model) {
	    model.addAttribute("tutor", service.buscarPorId(id));
	    return "tutores/edicao";
	}
	
	@PostMapping("/paginas/tutores/editar")
	public String atualizar(@Valid @ModelAttribute Tutor tutor, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			return "tutores/edicao";
		}
		try {
			service.atualizar(tutor);
			return "redirect:/paginas/tutores";
		}catch(RegraDeNegocioException e) {
			model.addAttribute("erro", e.getMessage());
			return "tutores/edicao";
		}
	    
	    
	}
	
	@GetMapping("/paginas/tutores/{id}")
	public String detalhe(@PathVariable Long id, Model model) {
		model.addAttribute("tutor", service.buscarPorId(id));
		model.addAttribute("animais", service.listarPorTutor(id));
		
		return"tutores/detalhe";
	}
	
	@PostMapping("/paginas/tutores/{id}/remover")
	public String remover(@PathVariable Long id, Model model) {
		try {
			service.remover(id);
			return "redirect:/paginas/tutores";
		}catch(RegraDeNegocioException e) {
			model.addAttribute("tutores", service.listarTodos());
			model.addAttribute("erro", e.getMessage());
			return "tutores/lista";
		}
	    
	    
	}
}
