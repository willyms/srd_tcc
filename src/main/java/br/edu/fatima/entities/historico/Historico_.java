package br.edu.fatima.entities.historico;

import br.edu.fatima.entities.DefaultEntity_;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.sector.Setor;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-21T09:53:11.859-0200")
@StaticMetamodel(Historico.class)
public class Historico_ extends DefaultEntity_ {
	public static volatile SingularAttribute<Historico, LocalDate> dataentrada;
	public static volatile SingularAttribute<Historico, LocalTime> horaentrada;
	public static volatile SingularAttribute<Historico, LocalDate> datasaida;
	public static volatile SingularAttribute<Historico, LocalTime> horasaida;
	public static volatile SingularAttribute<Historico, Boolean> liberado;
	public static volatile SingularAttribute<Historico, Funcionario> funcionario;
	public static volatile SingularAttribute<Historico, Setor> setor;
}
