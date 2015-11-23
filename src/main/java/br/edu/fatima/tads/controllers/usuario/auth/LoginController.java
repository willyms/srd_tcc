package br.edu.fatima.tads.controllers.usuario.auth;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.edu.fatima.entities.arquivo.Arquivo;
import br.edu.fatima.entities.repositories.arquivo.ReposArquivo;
import br.edu.fatima.entities.repositories.usuario.ReposUsuario;
import br.edu.fatima.entities.usuario.Usuario;
import br.edu.fatima.entities.utils.interfac.LoginAvailable;
import br.edu.fatima.tads.controllers.HomeController;
import br.edu.fatima.tads.controllers.acesso.AcessoController;

@Controller
@Path(value = {"/login"})
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
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
		if(usuarioNoBanco.findbyName("ROOT"))
			result.include("sucesso", "usuario Root criado com sucesso");
	}
	
	@Post
	@Path(value ={"/"})
	public void login(@LoginAvailable Usuario usuario){
		
		logger.debug("login");
		
		validator.onErrorRedirectTo(this).form();
		
		usuario = usuarioNoBanco.findbyUsername(usuario.getUsername());
	
		if(!usuario.isAtivo()){
			validator.add(new SimpleMessage("usuario", "Este Usuario esta desativado !")).onErrorRedirectTo(this).form();	
		}
		
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
	
	@Get
	@Path("/qrcode/{id}")
	public Download qrcode(Long id){
		String  codigo = ""+id;
		ByteArrayOutputStream out = QRCode.from(codigo).withCharset("UTF-8").to(ImageType.PNG).withSize(450, 450).stream();		
		return new ByteArrayDownload(out.toByteArray(), "image/png", "qrcode");
	}
	
	@Get
	@Path(value={"/500/","/500"})
	@Admin
	public void error500() {
		result.use(Results.http()).sendError(500, "Teste de pagina 500, esta e uma descrição da pagina.");
	}
	
	@Get
	@Path(value={"/404/","/404"})
	@Admin
	public void error404() {
		result.use(Results.http()).sendError(404);
	}
	
	@Get
	@Path(value={"/401/","/401"})
	@Admin
	public void error401() {
		result.use(Results.http()).sendError(401, "você não tem, permissão de acesso!");
	}
}