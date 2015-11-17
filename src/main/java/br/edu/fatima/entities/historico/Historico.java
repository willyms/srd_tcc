package br.edu.fatima.entities.historico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.fatima.entities.DefaultEntity;
import br.edu.fatima.entities.funcionario.Funcionario;
import br.edu.fatima.entities.sector.Setor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_history_acess")
public class Historico  extends DefaultEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3196282927787761461L;

	@Getter
	@Setter
	@Column(name = "cl_date_entry")	   
	private LocalDate dataentrada;
	
	@Getter
	@Setter
	@Column(name = "cl_time_entry")	   
	private LocalTime horaentrada;

	@Getter
	@Setter
	@Column(name = "cl_date_exit")
	private LocalDate datasaida;
	
	
	@Getter
	@Setter
	@Column(name = "cl_time_exit")	   
	private LocalTime horasaida;
	
	@Getter
	@Setter
	@Column(name="cl_liberado")
	private boolean liberado;
		
	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name="cl_employrs")
	private Funcionario funcionario;
	
	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name="cl_sector")
	private Setor setor;
	
	
	public Historico registaEntrada(Funcionario funcionario, Setor setor){
		Historico h = new Historico();
		h.setDataentrada(LocalDate.now());
		h.setHoraentrada(LocalTime.now());
		h.setLiberado(true);
		h.setFuncionario(funcionario);
		h.setSetor(setor);		
		return h;
	}
	
	public  Historico registaSaida(Historico historico){
		Historico h = new Historico();
		h.setDataentrada(historico.getDataentrada());
		h.setHoraentrada(historico.getHoraentrada());
		h.setDatasaida(LocalDate.now());
		h.setHorasaida(LocalTime.now());
		h.setFuncionario(historico.getFuncionario());
		h.setSetor(historico.getSetor());
		h.setLiberado(true);
		return h;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataentrada == null) ? 0 : dataentrada.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean retVal = false;
		if(obj == null) return false;
		if(obj == this) return true;
		
		if(obj instanceof Historico){
			Historico ptr = (Historico)obj;
			retVal = ptr.dataentrada.equals(this.dataentrada);
		}
		return retVal;
	}

	public Historico() {	}
	
	
	
}
