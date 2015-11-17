package br.edu.fatima.tads.controllers.usuario.auth;

import javax.inject.Inject;
import javax.validation.constraints.Size;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.fatima.entities.arquivo.Arquivo;
import br.edu.fatima.entities.repositories.arquivo.ReposArquivo;
import br.edu.fatima.entities.repositories.usuario.ReposUsuario;
import br.edu.fatima.entities.usuario.Usuario;
import br.edu.fatima.tads.controllers.HomeController;
import br.edu.fatima.tads.controllers.acesso.AcessoController;

@Controller
@Path(value = {"/login"})
public class LoginController {
	
	private UsuarioLogado logado;
	private Result result;
	private Validator validator;
	private ReposUsuario usuarioNoBanco;
	private ReposArquivo arquivoNobanco;
	
	@Inject
	public LoginController(ReposArquivo arquivoNobanco, UsuarioLogado logado, Result result, Validator validator, ReposUsuario usuarioNoBanco) {
		this.arquivoNobanco = arquivoNobanco;
		this.logado = logado;
		this.result = result;
		this.validator = validator;
		this.usuarioNoBanco = usuarioNoBanco;
	}
	/**
	 * @deprecated
	 */
	public LoginController() {
		this(null,null,null,null,null);
	}
	
	@Get
	@Path(value = {"","/"})
	public void form() {
		
	}
	
	@Post
	@Path(value ={"/"})
	public void login(String v_login, @Size(min=6, max=50, message="{validator.usuario.password.tamanho}") String v_senha){
		Usuario usuario = usuarioNoBanco.comLoginESenha(v_login, v_senha);
		
		validator.ensure(usuario != null, new I18nMessage("usuario", "login.ousenha.invalidos"))		
		.onErrorRedirectTo(this).form();
		
		logado.loga(usuario);
		
		if(logado.isAdmin())
			result.permanentlyRedirectTo(HomeController.class).index();
		else
			result.permanentlyRedirectTo(AcessoController.class).index();
	}
	
	@Get
	@Path(value ={"/logout"})
	public void logout() {
		logado.desloga();
		result.redirectTo(this).form();
	}
	
	@Get
	@Path(value = "/imagem/{chave}/perfil")
	public Download image(Long chave) {

		Arquivo perfil = arquivoNobanco.comId(chave);

		if (perfil == null) {
			result.notFound();
			return null;
		}

		return new ByteArrayDownload(perfil.getConteudo(), perfil.getContentType(), perfil.getNome());
	}
}