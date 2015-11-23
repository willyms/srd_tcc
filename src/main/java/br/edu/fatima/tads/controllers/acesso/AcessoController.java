package br.edu.fatima.tads.controllers.acesso;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
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
	private static LocalTime horario_de_agora = LocalTime.now();
	@SessionScoped
	private static LocalTime horario_do_registro;	
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
		horario_de_agora = LocalTime.now();
		
		Optional<LocalTime> OptionalTime = Optional.ofNullable(horario_do_registro);
		Boolean acesso = historicoNobanco.verificaqrcode(params, logado.getUsuario().getSetor().getId());
		
		if(OptionalTime.isPresent()){
			logger.info("Horario de agora : "+horario_de_agora.toString()+" horario de registro : "+OptionalTime.get().toString());
			if(horario_de_agora.isAfter(OptionalTime.get())){
				logger.info("---> "+horario_de_agora.isAfter(OptionalTime.get()));
				if(acesso){	
					horario_do_registro = horario_de_agora.plusMinutes(1);
					result.use(Results.json()).from(B_ACESSO_AUTORIZADO).serialize();
					verificaEntrada(params, logado.retornaSetor());			
				}else{
					horario_do_registro = horario_de_agora.plusMinutes(1);
					registraTentativa(params, logado.getUsuario().getSetor().getId());
					result.use(Results.json()).from(B_ACESSO_NAO_AUTORIZADO).serialize();	
				}
			}else{
				result.use(Results.json()).from(B_ACESSO_NAO_AUTORIZADO).serialize();	
			}
		}
		
		
		if(!OptionalTime.isPresent()){
			if(acesso){	
				horario_do_registro = horario_de_agora.plusMinutes(1);
				result.use(Results.json()).from(B_ACESSO_AUTORIZADO).serialize();
				verificaEntrada(params, logado.retornaSetor());			
			}else{
				horario_do_registro = horario_de_agora.plusMinutes(1);
				registraTentativa(params, logado.getUsuario().getSetor().getId());
				result.use(Results.json()).from(B_ACESSO_NAO_AUTORIZADO).serialize();	
			}
		}
	}
	
	protected void  verificaEntrada(Long funcionario, Long setor) {
		logger.debug("inicianado method verificaentrada ...");
		Funcionario f = funcNoBanco.comId(funcionario);
		Setor s = setorNobanco.comId(setor);
		
		 Optional<Historico> histOptional = Optional.ofNullable(historicoNobanco.funcioarioEntrou(f, s));
		 		 
		 if(histOptional.isPresent()){
			 logger.info("registrando saida do setor");
			 Historico h = histOptional.get();
			 h.setDatasaida(LocalDate.now());
		 	 h.setHorasaida(LocalTime.now());
			 historicoNobanco.atualizar(h); 
		 }else{
			 logger.info("registrando acesso entrada ....");
			 historicoNobanco.novo(new Historico().registaEntrada(f, s));
		 }		 
	}
	
	protected void registraTentativa(Long funcionario, Long setor) {
		logger.debug("registrando tentativa de acesso");
		Funcionario f = funcNoBanco.comId(funcionario);
		Setor s = setorNobanco.comId(setor);
		Historico h = new Historico();
		h.setLiberado(false);
		h.setFuncionario(f);
		h.setSetor(s);
		historicoNobanco.atualizar(h); 
	}

}
