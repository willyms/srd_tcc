package br.edu.fatima.tads.controllers.usuario.auth.interce;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.edu.fatima.tads.controllers.usuario.auth.LoginController;
import br.edu.fatima.tads.controllers.usuario.auth.UsuarioLogado;

@Intercepts
public class AutenticacaoInterceptor {
		
	 private UsuarioLogado logado;
	 private Result result;
	 
	@Inject
	public AutenticacaoInterceptor(UsuarioLogado logado, Result result) {
		this.logado = logado;
		this.result = result;
	}
	@Deprecated public AutenticacaoInterceptor() {}
	 
	
	
	@AroundCall
	public void autentica(SimpleInterceptorStack stack){
			
		if(logado.isLogado()){
			stack.next();
		}else{			
			result.redirectTo(LoginController.class).form();	
		}
	}
	
	@Accepts
	 public boolean ehRestrito(ControllerMethod method) {
		return !method.getController().getType().equals(LoginController.class);
	}
		
}
