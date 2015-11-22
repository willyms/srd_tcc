package br.edu.fatima.entities.arquivo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import br.edu.fatima.entities.DefaultEntity;
import br.edu.fatima.entities.funcionario.Funcionario;

@Entity
@Table(name = "tb_file")
public class Arquivo extends DefaultEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message="{validator.arquivo.nome.notnull}")
	@Getter
	@Setter
	@Column(name= "cl_name")
	private String nome;
	
	@Getter
	@Setter
    @Lob
    @NotNull(message="{validator.arquivo.nome.notnull}")
    @Size(min = 0, max = 3145728, message = "{validator.arquivo.nome.tamanho}")
    @Column(name = "cl_byte")
	private byte[] conteudo;
    
	@Getter
	@Setter
	@NotNull(message="{validator.arquivo.nome.notnull}")
	@Pattern(regexp = "image/jpeg|image/png|image/jpg|image/*", message = "{validator.arquivo.formator}")
	@Column(name = "cl_type")
	private String contentType; 
	
	@Setter
	@Getter
	@OneToOne(mappedBy = "arquivo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Funcionario funcionario;
	 
}
