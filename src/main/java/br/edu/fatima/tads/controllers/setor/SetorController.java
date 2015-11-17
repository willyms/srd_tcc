package br.edu.fatima.tads.controllers.setor;

import static br.com.caelum.vraptor.view.Results.json;
import static br.com.caelum.vraptor.view.Results.status;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.fatima.entities.repositories.setor.ReposSetor;
import br.edu.fatima.entities.sector.Setor;

@Controller
@Path(value = "/setor")
public class SetorController {

	Logger logger = LoggerFactory.getLogger(SetorController.class);
	private ReposSetor setorNobanco;
	private Result result;
	private Validator validator;

	@Inject
	public SetorController(ReposSetor setorNobanco, Result result, Validator validator) {
		this.setorNobanco = setorNobanco;
		this.result = result;
		this.validator = validator;
	}

	/**
	 * @deprecated
	 */
	public SetorController() {
		this(null, null, null);
	}
	
		
	@Get
	@Path(value = { "/", "" })
	public void todos() {
		result.use(json()).withoutRoot().from(setorNobanco.retornaSetorUnico()).serialize();
	}

	@Get
	@Path(value = "/{id}")
	@Consumes(value = "application/json", options = WithoutRoot.class)
	public void buscaid(Long id) {
		result.use(json()).withoutRoot().from(setorNobanco.bustaTodoscomId(id)).serialize();
	}

	@Get
	@Path("/alterar/{id}/setor")
	public void form(Long id) {
		result.include("setor", setorNobanco.comId(id));
	}

	@Get("/lista/{page}")
	@IncludeParameters
	public void lista(Integer page, String filter) {
		if(filter == null || filter == ""){
			result.include("totalpagina", setorNobanco.totalnumber().intValue() -12 <= 0 ? 1 : setorNobanco.totalnumber().intValue() -12);
			result.include("lista_setores", setorNobanco.paginator(page))
			.include("mensagem_resultado", "Lista de Setores esta vazio");
		}
		if(filter != null){
			result.include("totalpagina", 1)
			.include("lista_setores", setorNobanco.filterName(filter))
			.include("filter", filter).include("mensagem_resultado", "Não foram encontrado, nenhum resgistro");
		}
	}
	
	@Post("/lista/filter")
	@IncludeParameters
	public void filterLista(String nome){
		if(nome == null || nome == "") result.notFound();
		result.forwardTo(this).lista(1, nome);
	}
	
	@Post
	@Path("/alterar/")
	public void alterar(@Valid Setor setor) {
		logger.debug("atualizando setor no banco de dados ...");
		
		if (setorNobanco.duplicado(setor)) {
			validator.add(new SimpleMessage("setor.duplicado", "setor já cadastrado no banco de dados."));
		}
		
		validator.onErrorRedirectTo(this).form(setor.getId());
		
		setorNobanco.atualizar(setor);
		
		result.include("sucesso", "Alterações realizada com sucesso");
		
		result.redirectTo(this).lista(1, null);
	}

	@Post
	@Path(value = { "/", "" })
	@Consumes(value = "application/json", options = WithoutRoot.class)
	public void novo(Setor setor) {
		try {
			setorNobanco.novo(setor);
			result.use(status()).created();
		} catch (Exception e) {
			e.printStackTrace();
			result.use(status()).notFound();
		}
	}

	@Put
	@Path(value = "/{id}")
	@Consumes(value = "application/json", options = WithoutRoot.class)
	public void editar(Setor setor, Long id) {
		setor.setId(id);
		setorNobanco.atualizar(setor);
		result.use(status()).ok();
	}
}
