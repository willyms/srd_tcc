package br.edu.fatima.entities.repositories.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.repositories.Repository;
import br.edu.fatima.entities.usuario.Perfil;
import br.edu.fatima.entities.usuario.Usuario;
import br.edu.fatima.entities.usuario.Usuario_;
import br.edu.fatima.entities.utils.CriptografiaUtil;
import br.edu.fatima.entities.utils.interfac.LoginAvailable;

@Stateless
public class ReposUsuario extends Repository<Usuario> implements
		ConstraintValidator<LoginAvailable, Usuario> {

	Logger logger = LoggerFactory.getLogger(ReposUsuario.class);
	private static final Integer TAMANHODALISTAGEMPAGINACAO = 10;

	public List<String> listaTodoUsuarioCadastrado() {
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

	public List<Usuario> paginator(Integer pageNumber) {
		Integer pageSize = TAMANHODALISTAGEMPAGINACAO;
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
		return em.createQuery(countQuery).getSingleResult() != null ? em
				.createQuery(countQuery).getSingleResult() : 1L;
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
			cq.where(
					cb.and(cb.equal(form.get(Usuario_.username), v_login),
							cb.equal(form.get(Usuario_.password), v_senha)),
					cb.equal(form.get(Usuario_.ativo), true));

			return em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public Usuario verificarFuncionario(Funcionario funcionario) {
		logger.debug("verificar se o funcionario é administrador do sistema!");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> form = cq.from(Usuario.class);

		cq.where(cb.equal(form.get(Usuario_.funcionario), funcionario));

		try {
			Usuario u = em.createQuery(cq).getSingleResult();
			logger.info("usuario encontrando : " + (u != null));
			return u;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario findbyUsername(String username) {
		logger.info("verifica usuario root ");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> form = cq.from(Usuario.class);

		cq.where(cb.equal(form.get(Usuario_.username), username));
		try {
			return em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Boolean findbyName(String username) {
		logger.info("verifica usuario root ");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> form = cq.from(Usuario.class);

		cq.where(cb.equal(form.get(Usuario_.username), username));
		try {
			Usuario user = em.createQuery(cq).getSingleResult();
			return user != null;
		} catch (NoResultException e) {
			logger.info("adiciona usuario root ");
			Usuario usuario = new Usuario();
			usuario.setAtivo(true);
			usuario.setPerfil(Perfil.ADMIN);
			usuario.setUsername(username);
			usuario.setPassword(CriptografiaUtil
					.criptografarString("nuncavaodescobrir"));
			usuario.setPassVerify(CriptografiaUtil
					.criptografarString("nuncavaodescobrir"));
			novo(usuario);
			return true;
		}
	}

	@Override
	public void initialize(LoginAvailable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(Usuario usuario, ConstraintValidatorContext context) {
		return comLoginESenha(usuario.getUsername(),
				CriptografiaUtil.criptografarString(usuario.getPassword())) != null;
	}

	public void desativarComId(Long id) {
		desativarOuAtivar(id, false);
	}

	public void ativarComId(Long id) {
		desativarOuAtivar(id, true);
	}

	protected void desativarOuAtivar(Long id, boolean ativar) {
		CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();		
		CriteriaUpdate<Usuario> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Usuario.class);		
		Root<Usuario> update = criteriaUpdate.from(Usuario.class);		
		criteriaUpdate.set(Usuario_.ativo, ativar);		
		criteriaUpdate.where(criteriaBuilder.equal(update.get(Usuario_.id), id));		
		this.em.createQuery(criteriaUpdate).executeUpdate();
	}
}
