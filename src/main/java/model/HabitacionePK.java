package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the habitaciones database table.
 * 
 */
@Embeddable
public class HabitacionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String numHabitacion;

	@Column(insertable=false, updatable=false)
	private String codHotel;

	public HabitacionePK() {
	}
	public String getNumHabitacion() {
		return this.numHabitacion;
	}
	public void setNumHabitacion(String numHabitacion) {
		this.numHabitacion = numHabitacion;
	}
	public String getCodHotel() {
		return this.codHotel;
	}
	public void setCodHotel(String codHotel) {
		this.codHotel = codHotel;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HabitacionePK)) {
			return false;
		}
		HabitacionePK castOther = (HabitacionePK)other;
		return 
			this.numHabitacion.equals(castOther.numHabitacion)
			&& this.codHotel.equals(castOther.codHotel);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numHabitacion.hashCode();
		hash = hash * prime + this.codHotel.hashCode();
		
		return hash;
	}
}