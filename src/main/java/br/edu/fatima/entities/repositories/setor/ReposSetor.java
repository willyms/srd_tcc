package br.edu.fatima.entities.repositories.setor;

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

import br.edu.fatima.entities.acesso.Acesso;
import br.edu.fatima.entities.repositories.Repository;
import br.edu.fatima.entities.repositories.usuario.ReposUsuario;
import br.edu.fatima.entities.sector.Setor;
import br.edu.fatima.entities.sector.Setor_;
import br.edu.fatima.entities.usuario.Usuario_;

@Stateless
public class ReposSetor extends Repository<Setor> {

	Logger logger = LoggerFactory.getLogger(ReposSetor.class);
	private static final Integer TAMANHODALISTAGEMPAGINACAO = 10;
	@Inject
	ReposUsuario usuarioNobanco;

	public List<Setor> retornaTodoSetorNaoCadastrado() {
		List<Setor> setores = new ArrayList<>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Setor> cq = cb.createQuery(Setor.class);
			Root<Setor> form = cq.from(Setor.class);

			if (!usuarioNobanco.listaTodoUsuarioCadastrado().isEmpty())
				cq.where(
						cb.not(form.get(Setor_.nome).in(
								usuarioNobanco.listaTodoUsuarioCadastrado())))
						.orderBy(cb.asc(form.get(Setor_.nome)));
			else
				cq.select(form).orderBy(cb.asc(form.get(Setor_.nome)));
			return em.createQuery(cq).getResultList();
		} catch (NoResultException e) {
			return setores;
		}

	}

	public List<Setor> retornaSetorUnico() {
		List<Setor> novalista = new ArrayList<>();
		for (Setor setor1 : todos()) {
			if (!novalista.contains(setor1))
				novalista.add(setor1);
		}
		return novalista;
	}

	public List<Setor> bustaTodoscomId(Long id) {
		List<Setor> setores = new ArrayList<>();
		TypedQuery<Acesso> query = em.createQuery(
				"select a from Acesso a where a.func.id = ?", Acesso.class);
		query.setParameter(1, id);
		List<Acesso> acessos = query.getResultList();
		for (Acesso acesso : acessos) {
			setores.add(acesso.getSetor());
		}
		return setores;
	}

	public boolean duplicado(Setor setor) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Setor> cq = cb.createQuery(Setor.class);
		Root<Setor> form = cq.from(Setor.class);

		cq.select(form).where(
				cb.and(cb.equal(form.get(Setor_.nome), setor.getNome()),
						cb.equal(form.get(Setor_.ativo), setor.getAcesso())));
		try {
			return em.createQuery(cq).getSingleResult() != null;
		} catch (NoResultException e) {
			return false;
		}
	}

	public List<Setor> paginator(Integer pageNumber) {
		Integer pageSize = TAMANHODALISTAGEMPAGINACAO;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		countQuery.select(cb.count(countQuery.from(Setor.class)));
		Long count = em.createQuery(countQuery).getSingleResult();
		CriteriaQuery<Setor> criteriaQuery = cb.createQuery(Setor.class);
		Root<Setor> form = criteriaQuery.from(Setor.class);
		CriteriaQuery<Setor> select = criteriaQuery.select(form);
		select.orderBy(cb.asc(form.get("nome")));
		TypedQuery<Setor> typeQuery = em.createQuery(select);
		if (pageNumber <= count.longValue()) {
			typeQuery.setFirstResult((pageNumber - 1) * pageSize);
			typeQuery.setMaxResults(pageSize);
			return typeQuery.getResultList();
		}
		return new ArrayList<Setor>();
	}

	public List<Setor> filterName(String nome) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Setor> cq = cb.createQuery(Setor.class);
			Root<Setor> form = cq.from(Setor.class);

			cq.select(form);
			String param = "%" + nome.toUpperCase() + "%";
			cq.where(cb.like(cb.upper(form.get(Setor_.nome)), param));

			return em.createQuery(cq).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Setor>();
		}
		
		
	}
	
	public Long totalnumber(){
		Long count = 1L;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
			countQuery.select(cb.count(countQuery.from(Setor.class)));
			count = em.createQuery(countQuery).getSingleResult();

			do {
				count++;
			} while (!(count % TAMANHODALISTAGEMPAGINACAO == 0));

			Long numeror = ((count - TAMANHODALISTAGEMPAGINACAO) / TAMANHODALISTAGEMPAGINACAO);
			return (numeror > 0 ? numeror : 1L);
		} catch (NoResultException e) {
			return count;
		}

		// return em.createQuery(countQuery).getSingleResult() != null ?
		// em.createQuery(countQuery).getSingleResult() : 1L ;
	}

	public void desativarComId(Long id) {
		desativarOuAtivar(id, false);
	}

	public void ativarComId(Long id) {
		desativarOuAtivar(id, true);
	}

	
	protected void desativarOuAtivar(Long id, boolean ativar) {
		CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		
		CriteriaUpdate<Setor> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Setor.class);
		
		Root<Setor> update = criteriaUpdate.from(Setor.class);
		
		criteriaUpdate.set(Usuario_.ativo, ativar);
		
		criteriaUpdate.where(criteriaBuilder.equal(update.get(Usuario_.id), id));
		
		this.em.createQuery(criteriaUpdate).executeUpdate();
	}
}
