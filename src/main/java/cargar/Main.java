package cargar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Estancia;
import model.Habitacione;
import model.Hotel;

public class Main {

	private static EntityManager em;

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
		em = emf.createEntityManager();
		int accion;
		do {
			List<Hotel> hoteles = getHoteles();
			System.out.println("Hoteles:");
			for (int i = 0; i < hoteles.size(); i++) {
				System.out.println(i + 1 + " " + hoteles.get(i).getNombre() + " " + hoteles.get(i).getLocalidad());
			}
			System.out.println("Selecione hotel: ");
			int opcionHotel = sc.nextInt();

			System.out.println("Habitaciones del hotel selecionado: ");
			List<Habitacione> habitaciones = hoteles.get(opcionHotel - 1).getHabitaciones();
			for (int i = 0; i < habitaciones.size(); i++) {
				System.out.println(i + 1 + " " + habitaciones.get(i).getId().getNumHabitacion() + " "
						+ habitaciones.get(i).getPreciodia());
			}
			sc.nextLine();
			System.out.println("Selecione habitacion: ");
			int opcionHabitacion = sc.nextInt();

			System.out.println("Estancias de la habitacion selecionada: ");
			List<Estancia> estancias = habitaciones.get(opcionHabitacion - 1).getEstancias();

			for (int i = 0; i < estancias.size(); i++) {
				System.out.println(i + 1 + " " + estancias.get(i).getNombre() + "/ " + estancias.get(i).getFechaInicio()
						+ "/ " + estancias.get(i).getFechaFin());
			}
			System.out.println("¿Que quieres hacer?");
			System.out.println("1 Crear");
			System.out.println("2 Eliminar");
			System.out.println("3 Actualizar");
			System.out.println("4 Cerrar");

			sc.nextLine();
			accion = sc.nextInt();
			sc.nextLine();
			if (accion < 1 || accion > 4) {
				throw new RuntimeException("Accion no valida");
			}
			if (accion == 1) {
				System.out.println("Introdusca los datos de la estancia a crear");
				Estancia estancia = new Estancia();
				System.out.println("Introduzca su nombre");
				String nombre = sc.nextLine();
				estancia.setNombre(nombre);
				System.out.println("Introduzca su fecha inicio dd/MM/yyyy");
				String fechaInicio = sc.nextLine();
				Date fi = new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicio);
				estancia.setFechaInicio(fi);
				System.out.println("Introduzaca si fecha fin dd/MM/yyyy");
				String fechaFin = sc.nextLine();
				Date ff = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFin);
				estancia.setFechaFin(ff);
				estancia.setHabitacione(habitaciones.get(opcionHabitacion - 1));
				em.getTransaction().begin();
				;
				em.persist(estancia);
				em.flush();
				em.getTransaction().commit();
				em.clear();
			}

			if (accion == 2) {
				System.out.println("Selecione una estancia a eliminar");
				int estanciaElegida = sc.nextInt();
				sc.nextLine();
				Estancia estancia = estancias.get(estanciaElegida - 1);
				em.getTransaction().begin();
				em.remove(estancia);
				em.flush();
				em.getTransaction().commit();
				em.clear();
			}

			if (accion == 3) {
				System.out.println("Selecione una estancia a actualizar");

				int estanciaElegida = sc.nextInt();
				sc.nextLine();
				Estancia estancia = estancias.get(estanciaElegida - 1);
				System.out.println("Introduzca su nombre");
				String nombre = sc.nextLine();
				estancia.setNombre(nombre);
				System.out.println("Introduzca su fecha inicio dd/MM/yyyy");
				String fechaInicio = sc.nextLine();
				Date fi = new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicio);
				estancia.setFechaInicio(fi);
				System.out.println("Introduzaca si fecha fin dd/MM/yyyy");
				String fechaFin = sc.nextLine();
				Date ff = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFin);
				estancia.setFechaFin(ff);
				em.getTransaction().begin();
				em.persist(estancia);
				em.flush();
				em.getTransaction().commit();
				em.clear();
			}
		} while (accion != 4);
		sc.close();
		em.close();
	}

	private static List<Hotel> getHoteles() {
		List<Hotel> hoteles = em.createQuery("SELECT hot FROM Hotel hot", Hotel.class).getResultList();
		return hoteles;
	}

}
