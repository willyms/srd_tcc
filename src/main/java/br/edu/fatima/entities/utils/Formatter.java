package br.edu.fatima.entities.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.fatima.entities.repositories.func.ReposFuncionario;
import br.edu.fatima.entities.repositories.historico.ReposHistorico;

@Named("formatter")
@ApplicationScoped
public class Formatter {
	private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	@Inject private ReposHistorico historicoNobanco;
	@Inject private ReposFuncionario funcionarioNoBanco;
	
	
	public String localDateTime(LocalDateTime value) {
		return value.format(PATTERN);
	}
	
	public Long retornaQuantidadeFuncionario(LocalDate date, int page, String nome){
		return historicoNobanco.quantidadeFunc(date, page, (nome.equalsIgnoreCase("todos") ? "" : nome));
	}
	
	public String dataAtual(){
		String atual = LocalDate.now().toString();
		return atual;
	}
	
	public boolean Eadministrator(Long id){
		return funcionarioNoBanco.Eadministrator(id);
	}
}
