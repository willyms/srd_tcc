package br.edu.fatima.entities.utils;

import java.util.Collection;

import br.edu.fatima.entities.acesso.Acesso;


public class Compare {
	
	public static boolean contains(Collection<Acesso> list, String nome) {
		for (Acesso acesso : list) {
			if(acesso.getSetor().getNome().equalsIgnoreCase(nome)){
				return true;
			}
		}
		return false;
	}
}
