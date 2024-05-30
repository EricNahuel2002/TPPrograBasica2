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
	
	@ Test
	public void queSePuedaOrdenarPorCodigoATodosLosEmpleadosQueTrabajanEnElrestauranteSinQueSeRepitan() {
		Empleado mesero1 = new Mesero(1,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero1);
		
		Empleado mesero2 = new Mesero(5,"pim",LocalDate.of(2013, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero2);
		
		Empleado mesero3 = new Mesero(7,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero3);
		
		Empleado mesero4 = new Mesero(3,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero4);
		
		Empleado mesero5 = new Mesero(2,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero5);
		
		Empleado mesero6 = new Mesero(6,"pim",LocalDate.of(2013, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero6);
		
		Empleado mesero7 = new Mesero(8,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero7);
		
		Empleado mesero8 = new Mesero(4,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero8);
		
		Empleado mesero9 = new Mesero(4,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero9);
		
		TreeSet<Empleado> listaDeEmpleados = this.restaurante.obtenerListaDeEmpleadosOrdenadosSinQueSeRepitaElCodigo();
		
		// diferentes formas de comprabar el test 
		// primera para ver si me devuelve el mismo tamanio 
		assertEquals(8, listaDeEmpleados.size());
		
		//segunda para ver si el primero y el ultimo son iguales a los que ingrese
		assertEquals(1,(int)listaDeEmpleados.first().getCodigo());
		assertEquals(8, (int)listaDeEmpleados.last().getCodigo());
		
		// tercera y la mas segura es recorrer un forech }
		int i = 0;
		for (Empleado empleado : listaDeEmpleados) {
			switch(i) {
			case 0 : assertEquals(1, (int) empleado.getCodigo());
			    break;
			case 1 : assertEquals(2, (int) empleado.getCodigo());
			    break;
		    case 2 : assertEquals(3, (int) empleado.getCodigo());
	        	break;
		    case 3 : assertEquals(4, (int) empleado.getCodigo());
		        break;
	        case 4 : assertEquals(5, (int) empleado.getCodigo());
	            break;
	        case 5 : assertEquals(6, (int) empleado.getCodigo());
             	break;
            case 6 : assertEquals(7, (int) empleado.getCodigo());
                break;
            case 7 : assertEquals(8, (int) empleado.getCodigo());
                break;
			}
			
			i++;
				
		}
		
	}
	
	
	@Test
	public void queSePuedaOrdenarLaListaDeEmpleadosPorUnOrdenEspecificoSinQUeSeRepitaAlgunCodigoDescendentemente() {
		Empleado mesero1 = new Mesero(1,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero1);
		
		Empleado mesero2 = new Mesero(5,"pim",LocalDate.of(2013, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero2);
		
		Empleado mesero3 = new Mesero(7,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero3);
		
		Empleado mesero4 = new Mesero(3,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero4);
		
		Empleado mesero5 = new Mesero(2,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero5);
		
		Empleado mesero6 = new Mesero(6,"pim",LocalDate.of(2013, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero6);
		
		Empleado mesero7 = new Mesero(8,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero7);
		
		Empleado mesero8 = new Mesero(4,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarUnEmpleadoAlRestaurante(mesero8);
		
		TreeSet <Empleado> listaOrdenada = this.restaurante.obtenerListaDeEmpleadosOrdenadosPorUnOrdenEnEspecifico(new OrdenDescendiente());
	    
		assertEquals(8, listaOrdenada.size());
		
		assertEquals(8,(int) listaOrdenada.first().getCodigo());
	    assertEquals(1, (int)listaOrdenada.last().getCodigo());
	    
	    int i =0;
	    for (Empleado empleado : listaOrdenada) {
			switch(i) {
			case 0 : assertEquals(8, (int) empleado.getCodigo());
			break;
			case 1 : assertEquals(7, (int) empleado.getCodigo());
			break;
			case 2 : assertEquals(6, (int) empleado.getCodigo());
			break;
			case 3 : assertEquals(5, (int) empleado.getCodigo());
			break;
			case 4 : assertEquals(4, (int) empleado.getCodigo());
			break;
			case 5 : assertEquals(3, (int) empleado.getCodigo());
			break;
			case 6 : assertEquals(2, (int) empleado.getCodigo());
			break;
			case 7 : assertEquals(1, (int) empleado.getCodigo());
			break;
			}
			i++;
		}
	}

	
	
}
