package br.edu.fatima.entities.arquivo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.edu.fatima.entities.DefaultEntity;
import br.edu.fatima.entities.funcionario.Funcionario;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_file")
public class Arquivo extends DefaultEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Column(name= "cl_name")
	private String nome;
	
	@Getter
	@Setter
    @Lob
    @Size(min = 0, max = 3145728, message = "Tamanho maximo é 3 MB")
    @Column(name = "cl_byte")
	private byte[] conteudo;
    
	@Getter
	@Setter
	@Pattern(regexp = "image/jpeg|image/png", message = "{arquivo.invalido}")
	@Column(name = "cl_type")
	private String contentType; 
	
	@Setter
	@Getter
	@OneToOne(mappedBy = "arquivo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Funcionario funcionario;
}
