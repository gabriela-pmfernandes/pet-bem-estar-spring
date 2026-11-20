package br.edu.ifrs.petbemestar.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifrs.petbemestar.dao.AnimalDAO;
import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Animal;
import br.edu.ifrs.petbemestar.dominio.Tutor;

@Service
public class AnimalService {
	private final AnimalDAO dao;
	private final TutorDAO tutorDAO;
	
	public AnimalService(AnimalDAO dao, TutorDAO tutorDAO) {
		this.dao = dao;
		this.tutorDAO = tutorDAO;
	}
	
	public List<Animal> listarTodos(){
		return dao.listarTodos();
	}
	
	public Animal buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}
	
	public void salvar(Animal animal) {
		if(animal.getTutor() != null && animal.getTutor().getId() != null) {
			Tutor completo = tutorDAO.buscarPorId(animal.getTutor().getId());
			animal.setTutor(completo);
		}
		dao.salvar(animal);
	}
	
	public void atualizar(Animal animal) {
		if(animal.getTutor() != null && animal.getTutor().getId() != null) {
			Tutor tutor = tutorDAO.buscarPorId(animal.getTutor().getId());
		animal.setTutor(tutor);
		}
		dao.atualizar(animal);
	}
	
	public void remover(Long id) {
		dao.remover(id);
	}

}
