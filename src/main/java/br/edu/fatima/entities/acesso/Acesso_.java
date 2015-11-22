package br.edu.fatima.entities.acesso;

import br.edu.fatima.entities.DefaultEntity_;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.sector.Setor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-21T09:53:11.342-0200")
@StaticMetamodel(Acesso.class)
public class Acesso_ extends DefaultEntity_ {
	public static volatile SingularAttribute<Acesso, Funcionario> func;
	public static volatile SingularAttribute<Acesso, Setor> setor;
}
