package br.edu.fatima.tads.controllers.acesso;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.view.Results;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.historico.Historico;
import br.edu.fatima.entities.repositories.func.ReposFuncionario;
import br.edu.fatima.entities.repositories.historico.ReposHistorico;
import br.edu.fatima.entities.repositories.setor.ReposSetor;
import br.edu.fatima.entities.sector.Setor;
import br.edu.fatima.tads.controllers.usuario.auth.Guest;
import br.edu.fatima.tads.controllers.usuario.auth.UsuarioLogado;

@Controller
@Path(value = { "/acesso" })
public class AcessoController {

	Logger logger = LoggerFactory.getLogger(AcessoController.class);
	private static final Boolean B_ACESSO_AUTORIZADO = true;
	private static final Boolean B_ACESSO_NAO_AUTORIZADO = false;
	
	private ReposFuncionario funcNoBanco;
	private ReposSetor setorNobanco;
	private Result result;	
	private ReposHistorico historicoNobanco;
	private UsuarioLogado logado;	
	
	

	@Inject
	public AcessoController(ReposFuncionario funcNoBanco, ReposSetor setorNobanco, UsuarioLogado logado, Result result, ReposHistorico historicoNobanco) {
		this.funcNoBanco = funcNoBanco;
		this.setorNobanco = setorNobanco;
		this.logado = logado;
		this.result = result;		
		this.historicoNobanco = historicoNobanco;		
	}

	@Deprecated
	public AcessoController() {
	}

	@Guest
	@Get
	@Path(value = { "/", "" })
	public void index() {  }

	@Guest
	@Get
	@Path("/verificarqrcode/{params}")
	@Consumes(value = "application/json")
	@IncludeParameters
	public void verificarAcesso(Long params) {		
		Boolean acesso = historicoNobanco.verificaqrcode(params, logado.getUsuario().getSetor().getId());
		if(acesso){	
			result.use(Results.json()).from(B_ACESSO_AUTORIZADO).serialize();
			verificaEntrada(params, logado.retornaSetor());			
		}else
			result.use(Results.json()).from(B_ACESSO_NAO_AUTORIZADO).serialize();	
	}
	
	protected void  verificaEntrada(Long funcionario, Long setor) {
		logger.debug("inicianado method verificaentrada ...");
		 Historico historico = historicoNobanco.funcioarioEntrou(funcionario, setor);
		 Funcionario f = funcNoBanco.comId(funcionario);
		 Setor s = setorNobanco.comId(setor);
		 if(historico == null){
			 logger.debug("registrando acesso entrada ....");
			 historicoNobanco.novo(new Historico().registaEntrada(f, s));
		 }else{
			 logger.debug("registrando acesso saida");
			 historico.setDatasaida(LocalDate.now());
		 	 historico.setHorasaida(LocalTime.now());
			 historicoNobanco.atualizar(historico); 
		 }
	}

}
