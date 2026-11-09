package br.edu.ifrs.petbemestar.web;

import java.util.List;

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

import br.edu.ifrs.petbemestar.dao.AnimalDAO;
import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Animal;
import br.edu.ifrs.petbemestar.dominio.Porte;
import br.edu.ifrs.petbemestar.dominio.Tipo;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;



@RestController
public class AnimalController {

	private final AnimalDAO dao;
	
	
	public AnimalController(AnimalDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping("/animais")
	public List<Animal> listar() {
		return dao.listarTodos();
	}
	
	
	@PostMapping("/animais")
	@ResponseStatus(HttpStatus.CREATED)
	public Animal cadastrar(@RequestBody Animal animal) {
		dao.salvar(animal);
		return animal;
	}
	
	
	@GetMapping("/animais/{id}")
	public Animal buscarPorId(@PathVariable Long id) {
	    return dao.buscarPorId(id);
	}
	
	@PutMapping("/animais/{id}")
	public Animal atualizar(@PathVariable Long id, @RequestBody Animal dados) {
	    Animal animal = dao.buscarPorId(id);
	    animal.setNome(dados.getNome());
	    animal.setTipo(dados.getTipo());
	    animal.setPorte(dados.getPorte());
	    animal.setTutor(dados.getTutor());
	    dao.atualizar(animal);
	    return animal;
	}
	
	@DeleteMapping("/animais/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
	    dao.remover(id);
	}
}
