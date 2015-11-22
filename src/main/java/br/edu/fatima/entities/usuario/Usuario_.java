package br.edu.fatima.entities.usuario;

import br.edu.fatima.entities.DefaultEntity_;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.sector.Setor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-21T09:53:11.983-0200")
@StaticMetamodel(Usuario.class)
public class Usuario_ extends DefaultEntity_ {
	public static volatile SingularAttribute<Usuario, String> username;
	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile SingularAttribute<Usuario, Perfil> perfil;
	public static volatile SingularAttribute<Usuario, Setor> setor;
	public static volatile SingularAttribute<Usuario, Funcionario> funcionario;
}
