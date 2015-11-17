package br.edu.fatima.entities.repositories.usuario;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.repositories.Repository;
import br.edu.fatima.entities.usuario.Usuario;
import br.edu.fatima.entities.usuario.Usuario_;

@Stateless
public class ReposUsuario extends Repository<Usuario> {
	
	Logger logger = LoggerFactory.getLogger(ReposUsuario.class);
	
	public List<String> listaTodoUsuarioCadastrado(){
		List<String> nomeSetores = new ArrayList<>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
			Root<Usuario> from = cq.from(Usuario.class);
			cq.where(from.get(Usuario_.setor).isNotNull());
			for (Usuario setor : em.createQuery(cq).getResultList()) {
				nomeSetores.add(setor.getSetor().getNome());				
			}			
			return nomeSetores;
		} catch (NoResultException e) {
			return nomeSetores;
		}
	}

	public List<Usuario> paginator(Integer pageNumber, Integer pageSize) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		countQuery.select(cb.count(countQuery.from(Usuario.class)));
		Long count = em.createQuery(countQuery).getSingleResult();
		CriteriaQuery<Usuario> criteriaQuery = cb.createQuery(Usuario.class);
		Root<Usuario> form = criteriaQuery.from(Usuario.class);
		CriteriaQuery<Usuario> select = criteriaQuery.select(form);
		select.orderBy(cb.asc(form.get(Usuario_.username)));
		TypedQuery<Usuario> typeQuery = em.createQuery(select);
		if (pageNumber <= count.longValue()) {
			typeQuery.setFirstResult((pageNumber - 1) * pageSize);
			typeQuery.setMaxResults(pageSize);
			return typeQuery.getResultList();
		}
		return new ArrayList<Usuario>();
	}

	public Long totalnumber() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		countQuery.select(cb.count(countQuery.from(Usuario.class)));
		return em.createQuery(countQuery).getSingleResult() != null ? em.createQuery(countQuery).getSingleResult() : 1L;
	}

	public List<Usuario> filter(String nome) {

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
			Root<Usuario> form = cq.from(Usuario.class);

			cq.select(form);
			String param = "%" + nome.toUpperCase() + "%";
			cq.where(cb.like(cb.upper(form.get(Usuario_.username)), param));
			cq.orderBy(cb.asc(form.get(Usuario_.username)));
			return em.createQuery(cq).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Usuario>();
		}

	}

	public Usuario comLoginESenha(String v_login, String v_senha) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
			Root<Usuario> form = cq.from(Usuario.class);
			cq.where(cb.and(cb.equal(form.get(Usuario_.username), v_login),
					cb.equal(form.get(Usuario_.password), v_senha)), cb.equal(form.get(Usuario_.ativo), true));

			return em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}

	public void criptografandoPassword() {
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			System.out.println("random = "+random);
			
			byte[] byteSalt = new byte[8];			
			System.out.println("byteSalt ="+byteSalt);
			
			random.nextBytes(byteSalt);
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			messageDigest.reset();
			messageDigest.update(byteSalt);
			byte byteHash[] = messageDigest.digest("passwd".getBytes("UTF-8"));

			StringBuilder hexHash = new StringBuilder();
			for (byte b : byteHash) {
				hexHash.append(String.format("%02X", 0xFF & b));
			}
			System.out.println("hexHash ="+hexHash.toString());
			
			StringBuilder hexSalt = new StringBuilder();
			for (byte b : byteSalt) {
				hexSalt.append(String.format("%02X", 0xFF & b));
			}
			System.out.println("hexSalt ="+hexSalt.toString());
			
			String hash = hexHash.toString();
			String salt = hexSalt.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
	
	public Usuario verificarFuncionario(Funcionario funcionario){	
		logger.debug("verificar se o funcionario é administrador do sistema!");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> form =  cq.from(Usuario.class);
		
		cq.where(cb.equal(form.get(Usuario_.funcionario), funcionario));
		
		try {	
			Usuario u = em.createQuery(cq).getSingleResult();
			logger.info("usuario encontrando : "+ (u != null));
			return u;
		} catch (NoResultException e) {
			return null;
		}		
	}
}
