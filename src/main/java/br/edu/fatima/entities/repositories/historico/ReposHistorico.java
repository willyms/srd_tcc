package br.edu.fatima.entities.repositories.historico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.fatima.entities.acesso.Acesso;
import br.edu.fatima.entities.historico.Historico;
import br.edu.fatima.entities.historico.Historico_;
import br.edu.fatima.entities.repositories.Repository;
import br.edu.fatima.entities.repositories.func.ReposFuncionario;
import br.edu.fatima.entities.repositories.setor.ReposSetor;
import br.edu.fatima.entities.utils.SrdUtils;

public class ReposHistorico extends Repository<Historico> {
	
	@Inject private ReposFuncionario funcNoBanco;
	@Inject private ReposSetor setorNobanco;
			private static final Integer TOTALRESULTADO = 50;
			
	public Boolean verificaqrcode(Long params, Long setor) {
		try {
			TypedQuery<Acesso> query = em.createQuery("select a from Acesso a where a.func.id=? and a.setor.id=?", Acesso.class)
											.setParameter(1, params)
											.setParameter(2, setor)
											.setHint("javax.persistence.cache.storeMode", "REFRESH");
			Acesso acesso = query.getSingleResult();
			return acesso != null;
		} catch (NoResultException e) {
			return false;
		}
	}

	public Historico funcioarioEntrou(Long funcionario, Long setor){
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Historico> cq = cb.createQuery(Historico.class);
			Root<Historico> from = cq.from(Historico.class);
			
			cq.where(cb.and(cb.equal(from.get(Historico_.funcionario), funcNoBanco.comId(funcionario)),
					 cb.equal(from.get(Historico_.setor), setorNobanco.comId(setor))),
					 cb.and(cb.equal(from.get(Historico_.dataentrada), LocalDate.now()))
					);
			return em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Long quantidadeFunc2(LocalDate date) {
		Long quantidade = 2L;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);
			Root<Historico> from = cq.from(Historico.class);
			cq.select(cb.count(from.get(Historico_.funcionario)));
			cq.where(cb.equal(from.get(Historico_.dataentrada), date));
			quantidade = em.createQuery(cq).getSingleResult();
			return quantidade + 1;
		} catch (NoResultException e) {
			return quantidade;
		}
	}
	
	public Long quantidadeFunc(LocalDate date,int page, String func) {
		Long quantidade = 2L;
		List<Historico> lista = paginator(page, date, func);
		for (int i = 0; i < lista.size();i++) {
			if(lista.get(i).getDataentrada().equals(date)){
				quantidade ++;
			}
		}		
		return quantidade;
	}

	public List<Historico> paginator(Integer pageNumber, LocalDate date, String nome) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		countQuery.select(cb.count(countQuery.from(Historico.class)));
		Long count = em.createQuery(countQuery).getSingleResult();
		
		CriteriaQuery<Historico> criteriaQuery = cb.createQuery(Historico.class);
		Root<Historico> form = criteriaQuery.from(Historico.class);
		CriteriaQuery<Historico> select = criteriaQuery.select(form).where(cb.equal(form.get(Historico_.dataentrada), date));
				
		if(!SrdUtils.isEmpty(nome)){
			 select = criteriaQuery.select(form).where(cb.and(cb.equal(form.get(Historico_.dataentrada), date),
					 form.get(Historico_.funcionario).in(funcNoBanco.buscaPorNome(nome))));
		}
					
		TypedQuery<Historico> typeQuery = em.createQuery(select);
		if (pageNumber <= count.longValue()) {
			typeQuery.setFirstResult((pageNumber - 1) * TOTALRESULTADO);
			typeQuery.setMaxResults(TOTALRESULTADO);
			return typeQuery.getResultList();
		}
		return new ArrayList<Historico>();
	} 
	
	public List<Historico> filter (Integer pageNumber, LocalDate date, String nome){
		List<Historico> lista_filtrada = new ArrayList<Historico>();		
		try {
			for (Historico h : paginator(pageNumber, date, nome)) {
				if(lista_filtrada.isEmpty()){
					lista_filtrada.add(h);
				}
				if(!lista_filtrada.contains(h)){
					lista_filtrada.add(h);
				}
			}				
			return lista_filtrada;
		} catch (Exception e) {
			return lista_filtrada;
		}
	}
	
	public Long totalnumber(LocalDate dataentrada, String nome){
		Long count = 1L;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
			Root<Historico> root = countQuery.from(Historico.class);
			countQuery.select(cb.count(root.get(Historico_.funcionario)));
			countQuery.where(cb.equal(root.get(Historico_.dataentrada), dataentrada));
			
			if(!SrdUtils.isEmpty(nome)){
				countQuery.where(cb.and(cb.equal(root.get(Historico_.dataentrada), dataentrada),
										root.get(Historico_.funcionario).in(funcNoBanco.buscaPorNome(nome))));
			}			
			count = em.createQuery(countQuery).getSingleResult();
			return ((count - TOTALRESULTADO) > 0 ? (count -TOTALRESULTADO) : 1L);
		} catch (NoResultException e) {
			return count;
		}		
		
	}
}
