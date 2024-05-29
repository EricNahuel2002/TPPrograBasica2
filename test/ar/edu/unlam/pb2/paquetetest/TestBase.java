package ar.edu.unlam.pb2.paquetetest;

import static org.junit.Assert.assertEquals;
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
		
		Cliente cliente = new Cliente(001, "Bautista");
		
		ReservaCliente reservaCliente = new ReservaCliente(reserva, cliente);
		
		Boolean seAgregoReservaCliente = restaurante.agregarReservaCliente(reservaCliente);
		
		assertTrue(seAgregoReservaCliente);
	}
	
	
	@Test
	public void queSePuedaBuscarUnEmpleadoPorSuId() {
		
		Empleado empleadoABuscar = new Mesero(003, "Pedro", LocalDate.of(2005, 05, 12));
		
		restaurante.agregarEmpleado(empleadoABuscar);
		
		Empleado empleadoObtenido = restaurante.buscarUnEmpleado(empleadoABuscar.getCodigo());
		
		assertEquals(empleadoABuscar, empleadoObtenido);
		
	}
	
	@Test
	public void queSePuedaBuscarUnClientePorSuNumero() {
		
		Cliente clienteABuscar = new Cliente(001, "Bautista");
		
		restaurante.agregarCliente(clienteABuscar);
		
		Cliente clienteObtenido = restaurante.buscarUnCliente(clienteABuscar.getNumero());
		
		assertEquals(clienteABuscar, clienteObtenido);
	}
	
	@Test
	public void queSePuedaObtenerListaDeEmpleadosOrdenadoDeMayorAMenorPorSueldo() {
		
		Empleado encargado = new Encargado (3,"magnolia",500.0,LocalDate.of(2002, 11,10),8);
		this.restaurante.agregarEmpleado(encargado);
		
		Empleado encargado2 = new Encargado (6,"mag",700.0,LocalDate.of(2006,12,05),5);
		this.restaurante.agregarEmpleado(encargado2);
		
		Integer codigo = 1;
		String nombre ="cleo";
		Double sueldo = 400.0;
		LocalDate anio_ingreso = LocalDate.of(2004, 06, 12);
		Empleado cajero = new Cajero(codigo,nombre,sueldo,anio_ingreso);
		this.restaurante.agregarEmpleado(cajero);
		
		Empleado cajero2 = new Cajero(7,"cleopatra",200.0,LocalDate.of(2014, 06, 10));
		this.restaurante.agregarEmpleado(cajero2);
		
		Empleado mesero = new Mesero(2,"pimenta",300.0,LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero);
		
		Empleado mesero2 = new Mesero(5,"pim",100.0,LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado;
		
		// ejecucion
		List<Empleado>empleadosObtenidos = this.restaurante.obtenerListaDeEmpleadosOrdenadoDeMayorAMenorPorSueldo();
		
		// verificacion
		Double precioEsperado1 = 700.0;
		Double precioEsperado2 = 500.0;
		Double precioEsperado3 = 400.0;
		Double precioEsperado4 = 300.0;
		Double precioEsperado5 = 200.0;
		Double precioEsperado6 = 100.0;
		
		assertEquals(precioEsperado1, (Double)empleadosObtenidos.get(0).getSueldo());
		assertEquals(precioEsperado2, (Double)empleadosObtenidos.get(1).getSueldo());
		assertEquals(precioEsperado3, (Double)empleadosObtenidos.get(2).getSueldo());
		assertEquals(precioEsperado4, (Double)empleadosObtenidos.get(3).getSueldo());
		assertEquals(precioEsperado5, (Double)empleadosObtenidos.get(4).getSueldo());
		assertEquals(precioEsperado6, (Double)empleadosObtenidos.get(5).getSueldo());
	}
	
}
