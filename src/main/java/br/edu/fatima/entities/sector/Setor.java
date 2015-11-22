package br.edu.fatima.entities.sector;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import br.edu.fatima.entities.DefaultEntity;
import br.edu.fatima.entities.acesso.Acesso;

@Entity
@Table(name = "tb_sector")
public class Setor extends DefaultEntity {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Column(name = "cl_name", length = 50)	
	@Size(max=50, min=3, message="{validator.setor.nome.tamanho}")
	private String nome;


	@Getter
	@Setter
	@OneToMany(mappedBy = "setor", cascade = CascadeType.ALL)
	private Collection<Acesso> acesso;

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setor other = (Setor) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
}
