package ar.edu.unlam.pb2.paquetetest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.Cajero;
import ar.edu.unlam.pb2.dominio.Cliente;
import ar.edu.unlam.pb2.dominio.Empleado;
import ar.edu.unlam.pb2.dominio.Encargado;
import ar.edu.unlam.pb2.dominio.Mesero;
import ar.edu.unlam.pb2.dominio.Reserva;
import ar.edu.unlam.pb2.dominio.ReservaCliente;
import ar.edu.unlam.pb2.dominio.Restaurante;



public class TestBase {
	
	private static final String NOMBRE_RESTAURANTE = "Luigi";
	private Restaurante restaurante;

	@Before
	public void init() {
		this.restaurante = new Restaurante(NOMBRE_RESTAURANTE);
	}
	
	@Test
	public void queUnEncargadoSeaUnaInstanciaDeLaClaseEncargado() {
		
	    Empleado encargado = new Encargado(001, "Santi", LocalDate.of(2003, 10, 10));

	    assertTrue(encargado instanceof Encargado);
	}
	
	
	@Test
	public void queUnEncargadoSeaUnaSubClaseDeEmpleado() {
		
	    Empleado encargado = new Encargado(001, "Santi", LocalDate.of(2003, 10, 10));

	    assertTrue(encargado instanceof Empleado);
	}
	
	
	
	@Test
	public void queUnCajeroSeaUnaInstanciaDeLaClaseCajero() {
		
		Empleado cajero = new Cajero(002, "Ana", LocalDate.of(1998, 12, 19));

	    assertTrue(cajero instanceof Cajero);
	}
	
	
	@Test
	public void queUnCajeroSeaUnaSubClaseDeEmpleado() {
		
		Empleado cajero = new Cajero(002, "Ana", LocalDate.of(1998, 12, 19));

	    assertTrue(cajero instanceof Empleado);
	}
	
	@Test
	public void queUnMeseroSeaUnaInstanciaDeLaClaseMesero() {
		
		Empleado mesero = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));

	    assertTrue(mesero instanceof Mesero);
	}
	
	
	@Test
	public void queUnMeseroSeaUnaSubClaseDeEmpleado() {
		Empleado mesero = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));

	    assertTrue(mesero instanceof Empleado);
	}
	
	
	
	@Test
	public void queSePuedaAgregarUnEmpleadoAlRestaurante() {
		
		Empleado mesero = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));
		
		Boolean empleadoAgregado = restaurante.agregarEmpleado(mesero);
		
		assertTrue(empleadoAgregado);
	
	}
	
	@Test
	public void queNoSePuedaAgregarUnEmpleadoConElMismoCodigo() {

		Boolean seAgregoEmpleado = restaurante.agregarEmpleado(new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12)));
		Boolean seAgregoEmpleado2 = restaurante.agregarEmpleado(new Mesero(003, "Ana", LocalDate.of(1998, 12, 19)));
		
		assertTrue(seAgregoEmpleado);
		assertFalse(seAgregoEmpleado2);
	}
	
	@Test
	public void queSePuedaDespedirAUnEmpleado() {
		
		Empleado mesero = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));
		
		restaurante.agregarEmpleado(mesero);
		
		Boolean empleadoDespedido = restaurante.despedirUnEmpleado(mesero.getCodigo());
		
		assertTrue(empleadoDespedido);
	}
	
	@Test
	public void queSePuedaAgregarUnClienteAlRestaurante() {
		
		Cliente cliente = new Cliente(001 ,"Bautista");
		
		Boolean seAgregoAlCliente = restaurante.agregarCliente(cliente);
		
		assertTrue(seAgregoAlCliente);
	}
	
	@Test
	public void queSePuedaAgregarUnaReservaAlRestaurante() {

		Reserva reserva = new Reserva(001, LocalDate.of(2024, 5, 28), LocalTime.of(21, 0));
		
		Boolean seAgregoReserva = restaurante.agregarReserva(reserva);
		
		assertTrue(seAgregoReserva);
	}
	
	@Test
	public void queSePuedaAgregarUnaReservaClienteAlRestaurante() { 
		
		Reserva reserva = new Reserva(001, LocalDate.of(2024, 5, 28), LocalTime.of(21, 0));
		
		Cliente cliente = new Cliente(001 ,"Bautista");
		
		ReservaCliente reservaCliente = new ReservaCliente(reserva, cliente);
		
		Boolean seAgregoReservaCliente = restaurante.agregarReservaCliente(reservaCliente);
		
		assertTrue(seAgregoReservaCliente);
	}
	
	
	
}
