package br.edu.fatima.entities.sector;

import br.edu.fatima.entities.DefaultEntity_;
import br.edu.fatima.entities.acesso.Acesso;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-24T13:16:37.324-0200")
@StaticMetamodel(Setor.class)
public class Setor_ extends DefaultEntity_ {
	public static volatile SingularAttribute<Setor, String> nome;
	public static volatile CollectionAttribute<Setor, Acesso> acesso;
}
