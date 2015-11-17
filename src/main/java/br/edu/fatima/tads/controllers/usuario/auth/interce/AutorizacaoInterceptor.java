package br.edu.fatima.tads.controllers.usuario.auth.interce;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.view.Results;
import br.edu.fatima.tads.controllers.usuario.auth.Admin;
import br.edu.fatima.tads.controllers.usuario.auth.Guest;
import br.edu.fatima.tads.controllers.usuario.auth.LoginController;
import br.edu.fatima.tads.controllers.usuario.auth.UsuarioLogado;


@Intercepts(after=AutenticacaoInterceptor.class)
public class AutorizacaoInterceptor {
	
	private UsuarioLogado logado;
	private Result result;
	
	@Inject
	public AutorizacaoInterceptor(UsuarioLogado logado, Result result) {
		this.logado = logado;
		this.result = result;
	}
	
	
	@Deprecated public AutorizacaoInterceptor(){}
	
	@Accepts
	 public boolean ehRestrito(ControllerMethod method) {
		return !method.getController().getType().equals(LoginController.class);
	}
	
	
	@AroundCall
	public void autentica(SimpleInterceptorStack stack, ControllerMethod method){
			
		if(podeAcessar(method))
			stack.next();
		else
			result.use(Results.http()).sendError(401, "Você não está autorizado");
			//result.redirectTo(LoginController.class).form();	
	}

	public boolean podeAcessar(ControllerMethod method) {				
		return (method.containsAnnotation(Guest.class) &&  !logado.isAdmin()) || logado.isAdmin();
	}
	
}
