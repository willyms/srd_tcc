package br.edu.fatima.entities;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-24T13:16:37.048-0200")
@StaticMetamodel(DefaultEntity.class)
public class DefaultEntity_ {
	public static volatile SingularAttribute<DefaultEntity, Long> id;
	public static volatile SingularAttribute<DefaultEntity, Boolean> ativo;
	public static volatile SingularAttribute<DefaultEntity, LocalDateTime> dataCriacao;
}
