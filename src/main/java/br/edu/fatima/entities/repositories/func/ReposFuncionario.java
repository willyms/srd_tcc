package br.edu.fatima.entities.repositories.func;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.funcionario.Funcionario_;
import br.edu.fatima.entities.repositories.Repository;
import br.edu.fatima.entities.repositories.usuario.ReposUsuario;

@Stateless
public class ReposFuncionario extends Repository<Funcionario> {
	
	Logger logger = LoggerFactory.getLogger(ReposFuncionario.class);
	
	@Inject ReposUsuario usuarioNoBanco;
	private static final Integer TAMANHODALISTAGEMPAGINACAO = 10;
	
	public void desativarComId(Long id) {
		desativarOuAtivar(id, false);
	}

	public void ativarComId(Long id) {
		desativarOuAtivar(id, true);
	}


	public List<Funcionario> paginator(Integer pageNumber) {
		Integer pageSize = TAMANHODALISTAGEMPAGINACAO;
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		
		countQuery.select(criteriaBuilder.count(countQuery.from(Funcionario.class)));
		
		Long count = em.createQuery(countQuery).getSingleResult();

		CriteriaQuery<Funcionario> criteriaQuery = criteriaBuilder
				.createQuery(Funcionario.class);
		Root<Funcionario> form = criteriaQuery.from(Funcionario.class);
		
		CriteriaQuery<Funcionario> select = criteriaQuery.select(form);
		
		select.orderBy(criteriaBuilder.asc(form.get("nome")));
		
		TypedQuery<Funcionario> typedQuery = em.createQuery(select);
						
		if (pageNumber <= count.longValue()) {
			typedQuery.setFirstResult((pageNumber - 1) * pageSize);
			typedQuery.setMaxResults(pageSize);
			return typedQuery.getResultList();
		}
		return new ArrayList<Funcionario>();
	}

	
	public Long totalnumber() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(Funcionario.class)));		
		return em.createQuery(countQuery).getSingleResult() != null ? em.createQuery(countQuery).getSingleResult() : 1L;
	}

	protected void desativarOuAtivar(Long id, boolean ativar) {
		CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		
		CriteriaUpdate<Funcionario> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Funcionario.class);
		
		Root<Funcionario> update = criteriaUpdate.from(Funcionario.class);
		
		criteriaUpdate.set("ativo", ativar);
		
		criteriaUpdate.where(criteriaBuilder.equal(update.get("id"), id));
		
		this.em.createQuery(criteriaUpdate).executeUpdate();
	}
	
	public List<Funcionario> buscaPorNome(String nome){
		List<Funcionario> lista = new ArrayList<Funcionario>();
		try {
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Funcionario> cq = cb.createQuery(Funcionario.class);
			Root<Funcionario> form = cq.from(Funcionario.class);
			String p = "%"+nome.toUpperCase()+"%";
			cq.select(form).where(cb.like(cb.upper(form.get(Funcionario_.nome)), p));
						
			return em.createQuery(cq).getResultList();
		} catch (NoResultException e) {
			return lista;
		}
	}
	
}
