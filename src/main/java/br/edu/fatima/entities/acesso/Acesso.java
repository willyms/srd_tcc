package br.edu.fatima.entities.acesso;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import br.edu.fatima.entities.DefaultEntity;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.sector.Setor;

@Entity
@Table(name="tb_acess")
public class Acesso  extends DefaultEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@JoinColumn(name = "employers_id", referencedColumnName = "id")
	@ManyToOne
	private Funcionario func;
	
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "sector_id", referencedColumnName = "id")
	private Setor setor;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((func == null) ? 0 : func.hashCode());
		result = prime * result + ((setor == null) ? 0 : setor.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object object) {
		boolean isEqual= false;

	    if (object != null && object instanceof Acesso)
	    {
	        isEqual = (this.setor.getNome() == ((Acesso) object).getSetor().getNome());
	    }

	    return isEqual;
	}
	
}
