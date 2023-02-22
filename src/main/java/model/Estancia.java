package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the estancias database table.
 * 
 */
@Entity
@Table(name="estancias")
@NamedQuery(name="Estancia.findAll", query="SELECT e FROM Estancia e")
public class Estancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	private String nombre;

	//bi-directional many-to-one association to Habitacione
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="codHotel", referencedColumnName="codHotel"),
		@JoinColumn(name="numHabitacion", referencedColumnName="numHabitacion")
		})
	private Habitacione habitacione;

	public Estancia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Habitacione getHabitacione() {
		return this.habitacione;
	}

	public void setHabitacione(Habitacione habitacione) {
		this.habitacione = habitacione;
	}

}