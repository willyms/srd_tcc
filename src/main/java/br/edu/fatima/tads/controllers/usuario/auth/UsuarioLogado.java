package br.edu.fatima.tads.controllers.usuario.auth;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.hibernate.loader.custom.Return;

import br.edu.fatima.entities.sector.Setor_;
import br.edu.fatima.entities.usuario.Usuario;

@SessionScoped
public class UsuarioLogado implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1846938069793552274L;
	
	@Inject private Usuario usuario;
	
	public void loga(Usuario usuario){
		this.usuario = usuario;
	}
	
	public boolean isLogado() {
		return this.usuario.getId() != null;
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}
	
	public void desloga() {
		this.usuario = null;
	}
	
	public Long retornaSetor(){
		return this.usuario.getSetor().getId();
	}
	
	public boolean isAdmin (){	
		//usuario.getPerfil().name().equalsIgnoreCase("ADMIN")		
		return usuario != null ? usuario.getPerfil().name().equalsIgnoreCase("ADMIN") : false ;
	}
	
}
