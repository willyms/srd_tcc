package br.edu.fatima.entities.funcionario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.edu.fatima.entities.DefaultEntity;
import br.edu.fatima.entities.acesso.Acesso;
import br.edu.fatima.entities.arquivo.Arquivo;
import br.edu.fatima.entities.historico.Historico;
import br.edu.fatima.entities.utils.SRDFuture;
import br.edu.fatima.entities.utils.interfac.UniqueKey;

@Entity
@Table(name = "tb_employers", 
		uniqueConstraints={ 	
							@UniqueConstraint(columnNames={"cl_cpf"})
							})
@UniqueKey.List(value = { @UniqueKey(property = "cpf", message="{validator.funcionario.cpf.unico}"), @UniqueKey(property = "nome", message="{validator.funcionario.nome.unico}") })
public class Funcionario extends DefaultEntity {

	/**
	 * validator.funcionario.cpf.unico
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Column(name = "cl_cpf", unique=true, updatable=true)
	@Size(min=11, max=14, message="{validator.funcionario.cpf.tamanho}")
	private String cpf;

	@Getter
	@Setter	
	@Column(name = "cl_name", unique=true, updatable=true)
	@NotNull(message ="{validator.funcionario.nome.vazio}")
	@Size(min=10, message="{validator.funcionario.nome.tamanho}")
	private String nome;

	@Getter
	@Setter
	@SRDFuture
	@Column(name = "cl_date_entry")	 
	private LocalDate dataentrada;
	
	@Getter
	@Setter
	@Column(name = "cl_time_entry")	   
	private LocalTime horaentrada;

	@Getter
	@Setter
	@SRDFuture
	@Column(name = "cl_date_exit")
	private LocalDate datasaida;
		
	@Getter
	@Setter
	@Column(name = "cl_time_exit")	   
	private LocalTime horasaida;
	
	@Getter
	@Setter
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "func", cascade = CascadeType.ALL)
	private Collection<Acesso> acesso;
	
	@Getter
	@Setter
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	private Collection<Historico> historico;
	
	@Setter
	@Getter
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "file_id", referencedColumnName = "id")
	@OneToOne
	private Arquivo arquivo;
	
	@Transient
	@Setter
	@Getter
	private String foto;
		
}
