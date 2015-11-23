package br.edu.fatima.entities.repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public abstract class Repository<T> {

	protected Class<T> tipo = retornaTipo();

	@Inject
	protected EntityManager em;

	public void novo(T entidade) {
		em.persist(entidade);
		em.flush();
	}

	public void remover(T entidade) {
		em.remove(entidade);
	}

	@SuppressWarnings("unchecked")
	public List<T> todos() {
		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(tipo));
		return (List<T>) em.createQuery(cq).getResultList();
	}

	public T comId(long id) {
		return em.find(tipo, id);
	}

	public T atualizar(T entidade) {
		entidade = em.merge(entidade);
		em.flush();
		return entidade;
	}

	/**
	 * @author Pedro Hos<br>
	 *
	 *         Utilizando Exemplo de Eduardo Guerra!
	 *         https://groups.google.com/forum/#!topic/projeto-oo-guiado-por-
	 *         padroes/pOIiOD9cifs
	 *
	 *         Este método retorna o tipo da Classe, dessa maneira não é
	 *         necessário cada Service expor seu tipo!!!!
	 *
	 * @return Class<T>
	 */
	@SuppressWarnings({ "unchecked" })
	private Class<T> retornaTipo() {
		Class<?> clazz = this.getClass();

		while (!clazz.getSuperclass().equals(Repository.class)) {
			clazz = clazz.getSuperclass();
		}

		ParameterizedType tipoGenerico = (ParameterizedType) clazz
				.getGenericSuperclass();
		return (Class<T>) tipoGenerico.getActualTypeArguments()[0];
	}
}
