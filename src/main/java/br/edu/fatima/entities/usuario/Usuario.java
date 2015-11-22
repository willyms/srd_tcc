package br.edu.fatima.entities.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.edu.fatima.entities.DefaultEntity;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.sector.Setor;
import br.edu.fatima.entities.utils.interfac.Matches;
import br.edu.fatima.entities.utils.interfac.UniqueKey;

@Entity
@Table(name = "tb_user")
@EqualsAndHashCode(exclude={"username", "password"}, callSuper=true)
@Matches(fields={"password"}, verifyFields={"passVerify"})
//@UniqueKey.List(value = { @UniqueKey(property = "username"), @UniqueKey(property = "perfil") }) // more than one unique keys
@UniqueKey(property = "username")
public class Usuario extends DefaultEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2465128315467405095L;

	@Setter @Getter
	@Column(name = "cl_username", unique= true)	
	private String username;
	
	@Setter @Getter
	@Column(name = "cl_password", unique= true)	
	@Size(min=6, max=50, message="{validator.usuario.password.tamanho}")
	private String password;	
	
	@Setter @Getter
	@Transient
	private String passVerify;	
	
	@Getter @Setter @Enumerated(EnumType.STRING)
	private Perfil perfil;
	
	@Getter @Setter
	@OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "sector_id", referencedColumnName="id")
	private Setor setor;
	
	@Getter @Setter
	@OneToOne(optional= true, fetch = FetchType.EAGER)
	@JoinColumn(name = "cl_employers_id", referencedColumnName="id")
	private Funcionario funcionario;
	
	@AssertTrue(message="{validator.usuario.passverify}")
	private boolean isValid(){
		return this.password.equals(this.passVerify);
	}

}
