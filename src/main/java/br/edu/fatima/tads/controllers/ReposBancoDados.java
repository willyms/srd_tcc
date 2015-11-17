package br.edu.fatima.tads.controllers;

import java.time.LocalTime;
import java.util.Random;

import javax.inject.Inject;

import br.edu.fatima.entities.acesso.Acesso;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.repositories.acesso.ReposAcesso;
import br.edu.fatima.entities.repositories.func.ReposFuncionario;
import br.edu.fatima.entities.repositories.setor.ReposSetor;
import br.edu.fatima.entities.sector.Setor;

public class ReposBancoDados {
	private String [] setores = {
					 "Financeiro",
					 "Jurídico", 
					 "Marketing", 
					 "Compras", 
					 "Vendas", 
					 "Administrativo", 
					 "Operacional", 
					 "Recursos humanos",
				     "Diretoria",
				     "CPD"};
	
	@Inject private ReposSetor setorNobanco;
	@Inject private ReposFuncionario funcNoBanco;
	@Inject private ReposAcesso acessoNobanco;
	
	public void cadastraSetores(){		
		for (String nome : setores) {
			Setor s = new Setor();
			s.setNome(nome);
			setorNobanco.novo(s);
		}
	}
	
	public void cadastraFuncionario(){
		Funcionario f = new Funcionario();
		Acesso a = new Acesso();
		Random gerador = new Random();
		
		f.setNome("Francisco William");
		f.setCpf("000.000.000-00");
		f.setHoraentrada(LocalTime.of(8, 12));
		f.setHorasaida(LocalTime.of(18, 30));
		
		funcNoBanco.novo(f);
		
		a.setSetor(setorNobanco.comId(gerador.nextInt(10)+1));
		a.setFunc(f);
		
		acessoNobanco.novo(a);
		
	}
}
