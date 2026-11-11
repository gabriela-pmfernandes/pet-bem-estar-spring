package br.edu.ifrs.petbemestar.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifrs.petbemestar.dominio.Animal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class AnimalDAOJPA implements AnimalDAO{
	@PersistenceContext
	private EntityManager em;
	@Override
	public List<Animal> listarTodos() {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT a FROM Animal a ORDER BY  a.nome", Animal.class).getResultList();
	}

	@Override
	public Animal buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return em.find(Animal.class, id);
	}

	@Override
	public void salvar(Animal animal) {
		// TODO Auto-generated method stub
		em.persist(animal);
		
	}

	@Override
	public void atualizar(Animal animal) {
		// TODO Auto-generated method stub
		em.merge(animal);
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub
		Animal animal = buscarPorId(id);
		if(animal != null) {
			em.remove(animal);
			
		}
	}

}
