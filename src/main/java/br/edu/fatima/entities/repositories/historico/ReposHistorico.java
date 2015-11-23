package br.edu.fatima.entities.repositories.historico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.fatima.entities.acesso.Acesso;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.historico.Historico;
import br.edu.fatima.entities.historico.Historico_;
import br.edu.fatima.entities.repositories.Repository;
import br.edu.fatima.entities.repositories.func.ReposFuncionario;
import br.edu.fatima.entities.sector.Setor;
import br.edu.fatima.entities.utils.SrdUtils;

public class ReposHistorico extends Repository<Historico> {
	
	Logger logger = LoggerFactory.getLogger(ReposHistorico.class);
	private static final Integer TAMANHODALISTAGEMPAGINACAO = 50;
	
	@Inject private ReposFuncionario funcNoBanco;			
			
	public Boolean verificaqrcode(Long funcionario, Long setor) {
		try {
			TypedQuery<Acesso> query = em.createQuery("select a from Acesso a where a.func.id=? and a.setor.id=?", Acesso.class)
											.setParameter(1, funcionario)
											.setParameter(2, setor)
											.setHint("javax.persistence.cache.storeMode", "REFRESH");
			Acesso acesso = query.getSingleResult();
			return acesso != null;
		} catch (NoResultException e) {
			return false;
		}
	}

	public Historico funcioarioEntrou(Funcionario funcionario, Setor setor){
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Historico> cq = cb.createQuery(Historico.class);
			Root<Historico> from = cq.from(Historico.class);			
			cq.where(cb.and(cb.equal(from.get(Historico_.funcionario), funcionario),
					 cb.equal(from.get(Historico_.setor), setor)),
					 cb.and(cb.equal(from.get(Historico_.dataentrada), LocalDate.now()),
							 cb.isNull(from.get(Historico_.datasaida)))
					);
			Historico h = em.createQuery(cq).getSingleResult();
			return h;
		} catch (NoResultException e) {
			logger.info(" resultado nao encontrado");
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
		try {
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
				typeQuery.setFirstResult((pageNumber - 1) * TAMANHODALISTAGEMPAGINACAO);
				typeQuery.setMaxResults(TAMANHODALISTAGEMPAGINACAO);
				return typeQuery.getResultList();
			}
			return new ArrayList<Historico>();
		} catch (Exception e) {
			return null;
		}
		
	} 
	
	public List<Historico> filter (Integer pageNumber, LocalDate date, String nome){
		List<Historico> lista_filtrada = new ArrayList<Historico>();	
		
		if(paginator(pageNumber, date, nome) == null){
			return null;
		}
		
		
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
	
	public List<LocalDate> listadata(List<Historico> lista_dias){
		List<LocalDate> dias = new ArrayList<LocalDate>(31);
		if(lista_dias.isEmpty() || lista_dias == null){
			return dias;
		}
		
		for (int i = 0; i < lista_dias.size(); i++) {		
			LocalDate date = lista_dias.get(i).getDataentrada();
			if (dias.isEmpty()) {
				dias.add(date);
			}		
			if (!dias.contains(date)) {
				dias.add(date);
			}
		}			
		return dias;
	}
	
	public Long totalnumber2(LocalDate dataentrada){
		Long count = 1L;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
			Root<Historico> root = countQuery.from(Historico.class);
			countQuery.select(cb.count(root.get(Historico_.funcionario)));
			countQuery.where(cb.equal(root.get(Historico_.dataentrada), dataentrada));
			
			/*if(!SrdUtils.isEmpty(nome)){
				countQuery.where(cb.and(cb.equal(root.get(Historico_.dataentrada), dataentrada),
										root.get(Historico_.funcionario).in(funcNoBanco.buscaPorNome(nome))));
			}	*/		
			count = em.createQuery(countQuery).getSingleResult();
			
			do {				
				count++;
			} while (!(count%TAMANHODALISTAGEMPAGINACAO == 0));
							
			Long numeror = ((count - TAMANHODALISTAGEMPAGINACAO)/ TAMANHODALISTAGEMPAGINACAO);
			return (numeror > 0 ? numeror : 1L);
		} catch (NoResultException e) {
			return count;
		}	
	}
	
	public Long totalnumber(LocalDate dataentrada, String nome){
		Long count = 1L;
		Optional<LocalDate> OptionalLocalDate = Optional.ofNullable(dataentrada);		
		
		if (!OptionalLocalDate.isPresent() && funcNoBanco.buscaPorNome(nome).isEmpty()) {
			return count;
		}
		
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
			Root<Historico> root = countQuery.from(Historico.class);
			countQuery.select(cb.count(root.get(Historico_.funcionario)));
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			if(OptionalLocalDate.isPresent()){
				Predicate ParamData = cb.equal(root.get(Historico_.dataentrada), dataentrada);
				predicates.add(ParamData);
			}
			
			if(!SrdUtils.isEmpty(nome)){
				Predicate ParamNome = root.get(Historico_.funcionario).in(funcNoBanco.buscaPorNome(nome));
				predicates.add(ParamNome);
			}
			
			countQuery.where(predicates.toArray(new Predicate[]{}));
			
			//Optional<Funcionario> optionFunc = Optional.ofNullable(funcNoBanco.buscaPorNome(nome).get(0));
			
			/*if(!SrdUtils.isEmpty(nome)){
				countQuery.where(cb.and(cb.equal(root.get(Historico_.dataentrada), dataentrada),
										root.get(Historico_.funcionario).in(funcNoBanco.buscaPorNome(nome))));
			}	*/
			
			count = em.createQuery(countQuery).getSingleResult();			
			do {	
				count++;
			} while (!(count%TAMANHODALISTAGEMPAGINACAO == 0));
							
			Long numeror = ((count)/ TAMANHODALISTAGEMPAGINACAO);
			return (numeror > 0 ? numeror : 1L);
		} catch (NoResultException e) {
			return count;
		}		
		
	}
}
