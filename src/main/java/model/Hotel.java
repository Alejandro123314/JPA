package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the hotel database table.
 * 
 */
@Entity
@NamedQuery(name="Hotel.findAll", query="SELECT h FROM Hotel h")
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codHotel;

	private String localidad;

	private String nombre;

	//bi-directional many-to-one association to Habitacione
	@OneToMany(mappedBy="hotel")
	private List<Habitacione> habitaciones;

	public Hotel() {
	}

	public String getCodHotel() {
		return this.codHotel;
	}

	public void setCodHotel(String codHotel) {
		this.codHotel = codHotel;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Habitacione> getHabitaciones() {
		return this.habitaciones;
	}

	public void setHabitaciones(List<Habitacione> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public Habitacione addHabitacione(Habitacione habitacione) {
		getHabitaciones().add(habitacione);
		habitacione.setHotel(this);

		return habitacione;
	}

	public Habitacione removeHabitacione(Habitacione habitacione) {
		getHabitaciones().remove(habitacione);
		habitacione.setHotel(null);

		return habitacione;
	}

}