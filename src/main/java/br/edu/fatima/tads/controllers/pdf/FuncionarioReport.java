package br.edu.fatima.tads.controllers.pdf;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.caelum.vraptor.jasperreports.Report;
import br.edu.fatima.entities.funcionario.Funcionario;

public class FuncionarioReport implements Report {
	
	private List<Funcionario> data;
	private Map<String, Object> parameters;
	
	
	
	public FuncionarioReport(List<Funcionario> data) {
		this.data = data;
		this.parameters = new HashMap<String, Object>();
	}
	
	
	@Override
	public Report addParameter(String key, Object value) {
        this.parameters.put(key, value);
        return this;
    }
	
	@Override
    public Collection<Funcionario> getData() {
        return data;
    }

	@Override
    public String getFileName() {
        return "report" + System.currentTimeMillis();
    }

	@Override
    public Map<String, Object> getParameters() {
        return this.parameters;
    }

	@Override
    public String getTemplate() {
        return "index.jasper";
    }

	@Override
    public boolean isCacheable() {
        return true;
    }

}
