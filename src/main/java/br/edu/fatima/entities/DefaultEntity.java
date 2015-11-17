package br.edu.fatima.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class DefaultEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="my_seq_gen")
	@SequenceGenerator(name="my_seq_gen", sequenceName="ENTITY_SEQ")
	@Getter
	@Setter
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Getter
	@Setter
	@Column(name = "cl_status", nullable = false)
	private boolean ativo = true;

	@Getter
	@Setter
	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao = LocalDateTime.now();

}
