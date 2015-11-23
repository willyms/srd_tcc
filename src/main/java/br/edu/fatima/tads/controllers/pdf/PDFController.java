package br.edu.fatima.tads.controllers.pdf;

import static br.com.caelum.vraptor.jasperreports.formats.ExportFormats.pdf;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.repositories.func.ReposFuncionario;

@Controller
@Path(value = { "/pdf" })
public class PDFController {
	

	@Inject private ReposFuncionario funcNoBanco;
	@Inject private ServletContext servletContext;
	@Inject private HttpServletRequest httpServletContext;
	
	@Get
	@Path(value = { "/funcionario/{id}" })
	public Download pdfReport(Long id){		
		Report report = generateReport(funcNoBanco.comId(id));
		return new ReportDownload(report, pdf(), true);
	}

	private Report generateReport(Funcionario f) {	
		List<Funcionario> list_funcionario = new  ArrayList<Funcionario>();
					
		String logo = "http://" + httpServletContext.getServerName() + ":" + httpServletContext.getServerPort() + httpServletContext.getContextPath()+"/login/imagem/"+f.getArquivo().getId()+"/perfil";	
		String modelo = servletContext.getRealPath("/WEB-INF/reports/images/modelo-teste-frente.png");
		String qrcode ="http://" + httpServletContext.getServerName() + ":" + httpServletContext.getServerPort() + httpServletContext.getContextPath()+"/login/qrcode/"+f.getId();
		f.setFoto(logo);
		list_funcionario.add(f);	
		return new FuncionarioReport(list_funcionario)
									.addParameter("LOGO", "S. R. D.")
									.addParameter("QRCODE", qrcode)
									.addParameter("MODELO", modelo);
	}
	
}
