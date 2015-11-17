package br.edu.fatima.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-08-21T16:08:59.720-0300")
@StaticMetamodel(Telefone.class)
public class Telefone_ {
	public static volatile SingularAttribute<Telefone, Long> id;
	public static volatile SingularAttribute<Telefone, Boolean> ativo;
	public static volatile SingularAttribute<Telefone, String> ddd;
	public static volatile SingularAttribute<Telefone, String> telefone;
	public static volatile SingularAttribute<Telefone, String> TipoTelefone;
	public static volatile SingularAttribute<Telefone, String> contato;
}
