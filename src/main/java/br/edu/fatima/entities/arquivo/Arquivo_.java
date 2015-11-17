package br.edu.fatima.entities.arquivo;

import br.edu.fatima.entities.DefaultEntity_;
import br.edu.fatima.entities.funcionario.Funcionario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-24T13:16:34.928-0200")
@StaticMetamodel(Arquivo.class)
public class Arquivo_ extends DefaultEntity_ {
	public static volatile SingularAttribute<Arquivo, String> nome;
	public static volatile SingularAttribute<Arquivo, byte[]> conteudo;
	public static volatile SingularAttribute<Arquivo, String> contentType;
	public static volatile SingularAttribute<Arquivo, Funcionario> funcionario;
}
