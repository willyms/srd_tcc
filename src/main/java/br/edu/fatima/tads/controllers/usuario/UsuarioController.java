package br.edu.fatima.tads.controllers.usuario;

import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.repositories.func.ReposFuncionario;
import br.edu.fatima.entities.repositories.setor.ReposSetor;
import br.edu.fatima.entities.repositories.usuario.ReposUsuario;
import br.edu.fatima.entities.sector.Setor;
import br.edu.fatima.entities.usuario.Perfil;
import br.edu.fatima.entities.usuario.Usuario;
import br.edu.fatima.entities.utils.CategoriaMessage;
import br.edu.fatima.entities.utils.CriptografiaUtil;
import br.edu.fatima.entities.utils.SrdUtils;

@Controller
@Path(value = {"/usuario"})
public class UsuarioController extends CategoriaMessage {

	Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	
	private Validator validator;
	private Result result;
	private ReposUsuario usuarioNoBanco;
	private ReposSetor setorNoBanco;
	private ReposFuncionario funcNabanco;
	
	
	@Inject
	public UsuarioController( Validator validator, Result result,
			ReposUsuario usuarioNoBanco,ReposSetor setorNoBanco, ReposFuncionario funcNabanco) {		
		this.validator = validator;
		this.result = result;
		this.usuarioNoBanco = usuarioNoBanco;
		this.setorNoBanco = setorNoBanco;
		this.funcNabanco = funcNabanco;
	}


	/**
	 * @deprecated
	 */
	public UsuarioController() {}

	@Get
	@Path(value = {"/"})
	public void form(Usuario usuario, Funcionario func){
		result.include("lista_setor", setorNoBanco.retornaTodoSetorNaoCadastrado())
		.include("u", usuario)
		.include("f", func);
	}
	
	@Post("/lista/filter")
	@IncludeParameters
	public void filterLista(String nome){
		if(nome == null || nome == "") result.notFound();
		result.forwardTo(this).lista(1, nome);
	}
	
	@Get
	@Path(value = {"/tornar/{id}"})
	@IncludeParameters
	public void tornarAdmin(Long id){
		if(id == null){
			result.notFound();
		}
		Funcionario f = funcNabanco.comId(id);
		result.redirectTo(this).form(null, f);
	}
	
	@Get
	@Path(value = {"/lista/{page}"})
	@IncludeParameters
	public void lista(Integer page, String filter) {
		if(SrdUtils.isEmpty(filter)){
			result.include("totalpagina", usuarioNoBanco.totalnumber().intValue() -12 <= 0 ? 1 : usuarioNoBanco.totalnumber().intValue() -12);
			result.include("lista_usuario", usuarioNoBanco.paginator(page));
		}
		if(!SrdUtils.isNullOrBlank(filter)){
			result.include("totalpagina", 1)
			.include("lista_usuario", usuarioNoBanco.filter(filter))
			.include("filter", filter).include(WARNING, "Não foram encontrado, nenhum resgistro");
		}
	}
	
	@Get
	@Path(value = {"/alterar/{id}"})
	public void formedit(Long id){
		if(id == null) result.notFound();
		if(usuarioNoBanco.comId(id) == null) result.notFound();
		result.redirectTo(this).form(usuarioNoBanco.comId(id), null);
	}
	
	@Put
	@Path(value = {"/update/"})
	public void editar(@Valid Usuario usuario){
		logger.debug("alteraçao o usuario no banco de dados ....");	
		validator.onErrorForwardTo(this).form(usuario, null);
		Optional<Funcionario> funcOptional = Optional.ofNullable(usuario.getFuncionario());
		Optional<Setor> setorOptional = Optional.ofNullable(usuario.getSetor());
		
				
		if(setorOptional.isPresent())
		{
			logger.info("E um setor ...");
			Boolean status = setorNoBanco.comId(setorOptional.get().getId()).isAtivo();
			if(!status){ 
				validator.add(
						new SimpleMessage("setor.nome", "{validator.usuario.setor.desativo}", "setor.nome"))
						.onErrorRedirectTo(this)
						.form(usuario, null);}
			usuario.setPerfil(Perfil.USER);
		}
		
		if(funcOptional.isPresent())
		{
			logger.info("E um funcionario ...");
			Boolean status = funcNabanco.comId(funcOptional.get().getId()).isAtivo();
			if(!status){ validator.add(new SimpleMessage("funcionario.ativo", "{validator.usuario.funcionario.desativo}", "funcionario.ativo")).onErrorRedirectTo(this).form(usuario, usuario.getFuncionario());}	
			usuario.setPerfil(Perfil.ADMIN);
		}
		
		String p1 = CriptografiaUtil.criptografarString(usuario.getPassword());
		String p2 = CriptografiaUtil.criptografarString(usuario.getPassVerify());
		
	    usuario.setPassword(p1);
		usuario.setPassVerify(p2);
		
		validator.validate(usuario).onErrorForwardTo(this).form(usuario, usuario.getFuncionario());
		usuarioNoBanco.atualizar(usuario);
		result.include(INFOR, "Usuário alterando com sucesso !");		
		result.redirectTo(this).lista(1, null);
		
	}
	
	@Post
	@Path(value = {"/"})
	public void gravar(@Valid Usuario usuario){
		logger.debug("gravando o usuario no banco de dados ....");	
		validator.onErrorForwardTo(this).form(usuario, new Funcionario());
		Optional<Funcionario> funcOptional = Optional.ofNullable(usuario.getFuncionario());
		Optional<Setor> setorOptional = Optional.ofNullable(usuario.getSetor());
		
				
		if(setorOptional.isPresent())
		{
			Boolean status = setorNoBanco.comId(setorOptional.get().getId()).isAtivo();
			if(!status){ 
				validator.add(
						new SimpleMessage("setor.nome", "{validator.usuario.setor.desativo}", "setor.nome"))
						.onErrorRedirectTo(this)
						.form(usuario, null);}
			usuario.setPerfil(Perfil.USER);
		}
		
		if(funcOptional.isPresent())
		{
			Boolean status = funcNabanco.comId(funcOptional.get().getId()).isAtivo();
			if(!status){ validator.add(new SimpleMessage("funcionario.ativo", "{validator.usuario.funcionario.desativo}", "funcionario.ativo")).onErrorRedirectTo(this).form(usuario, usuario.getFuncionario());}	
			usuario.setPerfil(Perfil.ADMIN);
		}		
		
	    usuario.setPassword(CriptografiaUtil.criptografarString(usuario.getPassword()));
		usuario.setPassVerify(CriptografiaUtil.criptografarString(usuario.getPassVerify()));
		usuarioNoBanco.novo(usuario);
		result.include(SUCESSO, "Usuario cadastro com sucesso !");
		
		result.redirectTo(this).lista(1, null);
	}
	
	@Get
	@Path(value = "/ativar/{id}")
	public void ativar(Long id){
		if(usuarioNoBanco.comId(id) == null){
			result.notFound();
		}
		Optional<Setor> setorOptional = Optional.ofNullable(usuarioNoBanco.comId(id).getSetor());
		if(setorOptional.isPresent())
		{
			setorNoBanco.ativarComId(setorOptional.get().getId());
		}
		usuarioNoBanco.ativarComId(id);		
		result.include(WARNING, "Usuario ativo com sucesso");
		result.redirectTo(this).lista(1, null);
	}
	
	@Get
	@Path(value = "/desativar/{id}")
	public void desativar(Long id){
		if(usuarioNoBanco.comId(id) == null){
			result.notFound();
		}
		
		Optional<Setor> setorOptional = Optional.ofNullable(usuarioNoBanco.comId(id).getSetor());
		if(setorOptional.isPresent())
		{
			setorNoBanco.desativarComId(setorOptional.get().getId());
		}
		usuarioNoBanco.desativarComId(id);			
		result.include(WARNING, "Usuário desativado com sucesso");
		result.redirectTo(this).lista(1, null);
	}
}
