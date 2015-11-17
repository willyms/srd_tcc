package br.edu.fatima.tads.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Random;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.edu.fatima.entities.historico.Historico;
import br.edu.fatima.entities.repositories.func.ReposFuncionario;
import br.edu.fatima.entities.repositories.historico.ReposHistorico;
import br.edu.fatima.entities.repositories.setor.ReposSetor;
import br.edu.fatima.entities.repositories.usuario.ReposUsuario;
import br.edu.fatima.entities.utils.SrdUtils;
import br.edu.fatima.tads.controllers.usuario.auth.Admin;

@Controller
public class HomeController {
	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject ReposFuncionario funNobanco;
	@Inject ReposSetor setorNobanco;
	@Inject Result result;
	@Inject ReposUsuario usuarioNobanco;
	@Inject ReposHistorico historicoNobanco;
	@Inject ReposBancoDados gerenciaBancoDados;
	

	@Get("/")
	@Admin
	public void redirecione() {
		result.permanentlyRedirectTo(this).index();
	}

	@Get
	@Path("/dashboard/")
	@Admin
	public void index() {
		result.include("lista_setores", setorNobanco.todos());
	}
	
	@Get("/dashboard/historico/{page}/{nome}/{dataentrada}")
	@Admin
	public void historico(int page, String nome, String dataentrada) {		
		LocalDate strDate =  (SrdUtils.isNullOrBlank(dataentrada) ? LocalDate.now() :  LocalDate.parse(dataentrada));
		String nome_ = (nome.equalsIgnoreCase("todos") ? "" : nome);
		result.include("data", strDate);
		result.include("id_funcionario", (nome.equalsIgnoreCase("todos") ? "todos" : nome));
		result.include("pagina", page);
		result.include("total_pagina",historicoNobanco.totalnumber (strDate, nome_));
		result.include("historico", historicoNobanco.filter(page, strDate, nome_));
		result.include("historico2", historicoNobanco.paginator(page, strDate, nome_));
	}
	
	@Post
	@Admin
	public void formFilter(String nome, LocalDate dataentrada){
		result.redirectTo(this).historico(1, (SrdUtils.isEmpty(nome)? "todos" : nome), (dataentrada == null ? LocalDate.now().toString() : dataentrada.toString()));
	}
	
	@Get("/cadsetores")
	@Admin
	public void cadsetores() {
		gerenciaBancoDados.cadastraSetores();
		result.use(Results.json()).from("Sucesso Cadastro de Setores").serialize();
	}

	@Get("/cadastrafuncionario")
	@Admin
	public void cadfuncionario() {
		gerenciaBancoDados.cadastraFuncionario();
		result.use(Results.json()).from("Sucesso Cadastro de funcionario").serialize();
	}

	@Get("/cadastraacesso")
	@Admin
	public void cadacesso() {
		Historico h = new Historico();
		Random gerador = new Random();
		for (int i = 1; i < 32; i++) {				
			h.setDataentrada(LocalDate.of(2015, Month.OCTOBER, i));
			h.setHoraentrada(LocalTime.of(8, gerador.nextInt(15)));

			h.setFuncionario(funNobanco.comId(11));
			h.setSetor(setorNobanco.comId(9));
			h.setLiberado(false);
			try {				
				if(h.getDataentrada().getDayOfWeek().getValue() != 6 || h.getDataentrada().getDayOfWeek().getValue() != 7  ){
					System.out.println(i+" ) "+h.getDataentrada().getDayOfWeek().name());
					historicoNobanco.atualizar(h);
				}				
			} catch (Exception e) {
				e.getMessage();
			}
		}
		result.use(Results.json()).from("Sucesso Cadastro de funcionario").serialize();
	}
	
	public void historicoAcesso(){
		
	}
}
