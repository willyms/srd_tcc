package br.edu.fatima.entities.funcionario;

import br.edu.fatima.entities.DefaultEntity_;
import br.edu.fatima.entities.acesso.Acesso;
import br.edu.fatima.entities.arquivo.Arquivo;
import br.edu.fatima.entities.historico.Historico;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-12-07T00:03:46.144-0200")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ extends DefaultEntity_ {
	public static volatile SingularAttribute<Funcionario, String> cpf;
	public static volatile SingularAttribute<Funcionario, String> nome;
	public static volatile SingularAttribute<Funcionario, LocalDate> dataentrada;
	public static volatile SingularAttribute<Funcionario, LocalTime> horaentrada;
	public static volatile SingularAttribute<Funcionario, LocalDate> datasaida;
	public static volatile SingularAttribute<Funcionario, LocalTime> horasaida;
	public static volatile CollectionAttribute<Funcionario, Acesso> acesso;
	public static volatile CollectionAttribute<Funcionario, Historico> historico;
	public static volatile SingularAttribute<Funcionario, Arquivo> arquivo;
}
