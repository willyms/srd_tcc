package br.edu.fatima.entities.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.fatima.entities.repositories.func.ReposFuncionario;
import br.edu.fatima.entities.repositories.historico.ReposHistorico;
import br.edu.fatima.entities.repositories.usuario.ReposUsuario;

@Named("formatter")
@ApplicationScoped
public class Formatter {
	
	Logger logger = LoggerFactory.getLogger(Formatter.class);
	
	private static final DateTimeFormatter PATTERNDATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private static final DateTimeFormatter PATTERNDATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	@Inject private ReposHistorico historicoNobanco;
	@Inject private ReposFuncionario funcionarioNoBanco;
	@Inject private ReposUsuario usuarioNobanco;
	
	
	public String localDateTime(LocalDateTime value) {
		return value.format(PATTERNDATETIME);
	}
	
	public String localDate(LocalDate value) {
		return value.format(PATTERNDATE);
	}
	
	public Long retornaQuantidadeFuncionario(LocalDate date, int page, String nome){
		return historicoNobanco.quantidadeFunc(date, page, (nome.equalsIgnoreCase("todos") ? "" : nome));
	}
	
	public String dataAtual(){
		String atual = LocalDate.now().toString();
		return atual;
	}
	
	public boolean Eadministrator(Long id){
		return (usuarioNobanco.verificarFuncionario(funcionarioNoBanco.comId(id)) == null);
	}
}
