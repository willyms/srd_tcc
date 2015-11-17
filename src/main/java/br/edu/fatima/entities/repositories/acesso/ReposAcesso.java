package br.edu.fatima.entities.repositories.acesso;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.edu.fatima.entities.acesso.Acesso;
import br.edu.fatima.entities.repositories.Repository;

@Stateless
public class ReposAcesso extends Repository<Acesso> {

	public List<Acesso> listarPorId(Long id) {
		TypedQuery<Acesso> query = em.createQuery("select a from Acesso a where a.func.id = ? ", Acesso.class);
		query.setParameter(1, id);		
		return query.getResultList();
	}

}
