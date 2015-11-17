package br.edu.fatima.tads.controllers.funcionario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.edu.fatima.entities.acesso.Acesso;
import br.edu.fatima.entities.arquivo.Arquivo;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.repositories.acesso.ReposAcesso;
import br.edu.fatima.entities.repositories.arquivo.ReposArquivo;
import br.edu.fatima.entities.repositories.func.ReposFuncionario;
import br.edu.fatima.entities.repositories.setor.ReposSetor;
import br.edu.fatima.entities.sector.Setor;

import com.google.common.io.ByteStreams;

@Controller
@Path(value = "/funcionario")
public class FuncionarioController {
	Logger logger = LoggerFactory.getLogger(FuncionarioController.class);

	private  ReposSetor setorNoBanco;
	private ReposAcesso acessoNobanco;
	private ReposArquivo arquivoNobanco;
	private ReposFuncionario funcNobanco;
	private Validator validator;
	private Result result;

	@Inject
	public FuncionarioController(ReposSetor setorNobanco, ReposAcesso acessoNobanco, ReposFuncionario funcNobanco,
			Result result, ReposArquivo arquivoNobanco, Validator validator) {
		this.setorNoBanco = setorNobanco;
		this.acessoNobanco = acessoNobanco;
		this.funcNobanco = funcNobanco;
		this.result = result;
		this.validator = validator;
		this.arquivoNobanco = arquivoNobanco;
	}

	/**
	 * @deprecated
	 */
	public FuncionarioController() {
		this(null, null, null, null, null, null);
	}

	@Get
	@Path(value = "/lista/{pageNumber}")
	@IncludeParameters
	public void lista(Integer pageNumber, String filter) {
		result.include("totalpagina", funcNobanco.totalnumber().intValue() - 12 <= 0 ? 1 : funcNobanco.totalnumber().intValue() - 12 )
		.include("s", setorNoBanco.todos())
		.include("lista_funcionario", funcNobanco.paginator(pageNumber))
		.include("mensagem_resultado", "Lista de Funcionario esta vazio");
	}

	@Get
	@Path(value = "/todos/{pageNumber}")
	public void todos(Integer pageNumber) {
		result.use(Results.json()).withoutRoot().from(funcNobanco.paginator(pageNumber)).serialize();

	}

	@Get
	@Path(value = "/")
	public void form(Funcionario funcionario) {
		result.include("f", funcionario);
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

	@Post
	@Path(value = { "/", "" })
	@UploadSizeLimit(sizeLimit = 40 * 1024 * 1024, fileSizeLimit = 100 * 1024 * 1024)
	public void novo(@Valid Funcionario funcionario, @NotNull UploadedFile imagem) {
		try {
			logger.debug("funcionario controller novo");
			Arquivo arquivo = upload(imagem);		
			validator.validate(arquivo);
			validator.onErrorRedirectTo(this).form(funcionario);

			// salva imagem no banco de dados
			arquivoNobanco.novo(arquivo);

			// passa id do arquivo para funcionario
			funcionario.setArquivo(arquivo);

			// salvando os setores no banco e pegando retorno do setores com id
			if (!setorNoBanco.todos().isEmpty()) {
				funcionario.setAcesso(listaAcessocomIdsetor((List<Acesso>) funcionario.getAcesso(), funcionario.getId()));
			} else {
				for (Acesso acesso : funcionario.getAcesso()) {
					setorNoBanco.novo(acesso.getSetor());
				}
			}

			// salvando funcionario no banco de dados
			funcNobanco.novo(funcionario);

			for (Acesso acesso : funcionario.getAcesso()) {
				// passando id do funcionario
				acesso.setFunc(funcionario);

				// salvando os acesso do funcionario
				acessoNobanco.novo(acesso);
			}

			result.include("sucesso", "funcionario cadastrado com sucesso");
			result.forwardTo(this).lista(1, null);
		} catch (PersistenceException e) {
			result.include("sucesso", "Erro ao tentar cadastra funcionario, no banco de dados.").redirectTo(this).form(funcionario);
			
		}		
	}

	@Post
	@Path(value = "/edit/")
	public void editar(@Valid Funcionario funcionario, UploadedFile imagem) {
		logger.debug("funcionario controller editar");
		Arquivo arquivo = null;

		if (imagem != null) {
			logger.debug("pegando URI (key da foto) e armazenando no funcionario ... ");
			arquivo = upload(imagem);
			arquivo.setId(funcionario.getArquivo().getId());
			validator.validate(arquivo);
		}

		validator.onErrorRedirectTo(this).form(funcionario);

		// atualizar arquivo no banco de dados
		if (imagem != null && arquivo != null) {
			arquivoNobanco.atualizar(arquivo);
		}

		funcionario.setAcesso(listaAcessocomIdsetor((List<Acesso>) funcionario.getAcesso(), funcionario.getId()));

		funcNobanco.atualizar(funcionario);

		for (Acesso acesso : funcionario.getAcesso()) {
			// passando id do funcionario
			acesso.setFunc(funcionario);

			// salvando os acesso do funcionario
			acessoNobanco.novo(acesso);
		}

		result.include("sucesso", "funcionario alterado com sucesso");
		result.redirectTo(this).lista(1, null);
	}

	@Get
	@Path(value = "/deletar/{id}")
	public void remover(Long id) {
		if (funcNobanco.comId(id) == null) {
			result.notFound();
		}
		Funcionario funcionario = funcNobanco.comId(id);
		Arquivo arquivo = arquivoNobanco.comId(funcionario.getArquivo().getId());
		arquivoNobanco.remover(arquivo);
		funcNobanco.remover(funcionario);
		result.include("sucesso", "funcionario removindo com sucesso");
		result.redirectTo(this).lista(1, null);
	}
	
	@Get
	@Path(value = "/ativar/{id}")
	public void ativar(Long id){
		if(funcNobanco.comId(id) == null){
			result.notFound();
		}
		funcNobanco.ativarComId(id);		
		result.include("sucesso", "funcionario ativo com sucesso");
		result.redirectTo(this).lista(1, null);
	}
	
	@Get
	@Path(value = "/desativar/{id}")
	public void desativar(Long id){
		if(funcNobanco.comId(id) == null){
			result.notFound();
		}
		funcNobanco.desativarComId(id);			
		result.include("sucesso", "funcionario desativado com sucesso");
		result.redirectTo(this).lista(1, null);
	}

	@Get
	@Path(value = { "/edit/{id}" })
	public void formedit(Long id) {
		if (id == null) {
			result.notFound();
		}
		Funcionario funcionario = funcNobanco.comId(id);

		if (funcionario == null) {
			result.notFound();
		}

		result.forwardTo(this).form(funcionario);
	}

	// grava imagem no banco de dados ...
	protected Arquivo upload(UploadedFile filedata) {
		Arquivo arquivo = new Arquivo();
		try {
			arquivo.setNome(filedata.getFileName());
			arquivo.setConteudo(ByteStreams.toByteArray(filedata.getFile()));
			arquivo.setContentType(filedata.getContentType());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return arquivo;
	}

	protected List<Acesso> listaAcessocomIdsetor(List<Acesso> listaDoform, Long id) {
		List<Setor> setoreDoBanco = setorNoBanco.todos();
		logger.info("metodo lista acesso com id setor");

		if (listaDoform == null) {
			listaDoform = new ArrayList<Acesso>();
		}

		for (Setor setor : setoreDoBanco) {
			for (Acesso acesso : listaDoform) {
				if (setor.getNome().equalsIgnoreCase(acesso.getSetor().getNome())) {
					acesso.setSetor(setor);
				}
			}
		}
		return listaAcessoGravaSetor(listaDoform, id);
	}

	protected List<Acesso> listaAcessoGravaSetor(List<Acesso> listaDoform, Long id) {
		List<Acesso> listaAcessoFuncionarioDobanco = null;

		if (id != null) {
			listaAcessoFuncionarioDobanco = acessoNobanco.listarPorId(id);

			for (Acesso acesso : listaAcessoFuncionarioDobanco) {
				if (!listaDoform.contains(acesso)) {
					acessoNobanco.remover(acesso);
				}
			}
		}

		for (Acesso acesso : listaDoform) {
			if (acesso.getSetor().getId() == null) {
				setorNoBanco.novo(acesso.getSetor());
			}
		}
		return listaDoform;
	}

}
