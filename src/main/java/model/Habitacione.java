package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the habitaciones database table.
 * 
 */
@Entity
@Table(name="habitaciones")
@NamedQuery(name="Habitacione.findAll", query="SELECT h FROM Habitacione h")
public class Habitacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HabitacionePK id;

	private int preciodia;

	//bi-directional many-to-one association to Estancia
	@OneToMany(mappedBy="habitacione")
	private List<Estancia> estancias;

	//bi-directional many-to-one association to Hotel
	@ManyToOne
	@JoinColumn(name="codHotel", insertable = false, updatable = false)
	private Hotel hotel;

	public Habitacione() {
	}

	public HabitacionePK getId() {
		return this.id;
	}

	public void setId(HabitacionePK id) {
		this.id = id;
	}

	public int getPreciodia() {
		return this.preciodia;
	}

	public void setPreciodia(int preciodia) {
		this.preciodia = preciodia;
	}

	public List<Estancia> getEstancias() {
		return this.estancias;
	}

	public void setEstancias(List<Estancia> estancias) {
		this.estancias = estancias;
	}

	public Estancia addEstancia(Estancia estancia) {
		getEstancias().add(estancia);
		estancia.setHabitacione(this);

		return estancia;
	}

	public Estancia removeEstancia(Estancia estancia) {
		getEstancias().remove(estancia);
		estancia.setHabitacione(null);

		return estancia;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}