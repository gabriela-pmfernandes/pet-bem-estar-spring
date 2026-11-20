package br.edu.ifrs.petbemestar.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifrs.petbemestar.dao.AnimalDAO;
import br.edu.ifrs.petbemestar.dao.TutorDAO;
import br.edu.ifrs.petbemestar.dominio.Tutor;

@Service
public class TutorService {
	private final TutorDAO dao;
	private final AnimalDAO animalDAO;
	
	public TutorService(TutorDAO dao, AnimalDAO animalDAO) {
		this.dao = dao;
		this.animalDAO = animalDAO;
	}
	
	public List<Tutor> listarTodos(){
		return dao.listarTodos();
	}
	
	public Tutor buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}
	
	public void salvar(Tutor tutor) {
		dao.salvar(tutor);
	}
	
	public void atualizar(Tutor tutor) {
		dao.atualizar(tutor);
	}
	
	public void remover(Long id) {
		Tutor tutor = dao.buscarPorId(id);
		
		if(!animalDAO.listarPorTutor(id).isEmpty()) {
			throw new RegraDeNegocioException("Não é possível remover " + tutor.getNome()+" porque ainda há animais cadastrados para este cliente");
		}
		dao.remover(id);
	}

}
