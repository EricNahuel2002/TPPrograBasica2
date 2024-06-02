package ar.edu.unlam.pb2.paquetetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;


import org.junit.Before;
import org.junit.Test;
import ar.edu.unlam.pb2.dominio.*;
import ar.edu.unlam.pb2.paquetetest.*;

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
		
		Mesa mesa = new Mesa(123);
		
		ReservaCliente reservaCliente = new ReservaCliente(reserva, cliente, mesa);
		
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
	

	// metodos calcular sueldo
	@Test
	public void queSePuedaCalcularElSueldoDeUnEncargado() throws EmpleadoNoEncontradoException {

		Empleado encargado = new Encargado(123, "Julieta", LocalDate.of(2000,5,19));
		restaurante.agregarEmpleado(encargado);

		Empleado meseroA = new Mesero(189, "Flor", LocalDate.of(2007,7,30));
		restaurante.agregarEmpleado(meseroA);
		Empleado meseroB = new Mesero(7457, "Juana", LocalDate.of(2015,7,30));
		restaurante.agregarEmpleado(meseroB);
		Empleado meseroC = new Mesero(59, "Flor", LocalDate.of(2008,7,30));
		restaurante.agregarEmpleado(meseroC);

		restaurante.cargarMeseroACargoDelEncargado(encargado, meseroA);
		restaurante.cargarMeseroACargoDelEncargado(encargado, meseroB);
		restaurante.cargarMeseroACargoDelEncargado(encargado, meseroC);
		restaurante.cargarValorHoraDeUnEmpleado(123, 100.0);
		restaurante.cargarHorasTrabajadasDeUnEmpleado(123, 20); 
		restaurante.cargarSueldoEmpleado(123);

		Double sueldoObtenido = restaurante.obtenerSueldoDeUnEmpleado(123);
		Double sueldoDeseado = 20 * 100.0 + encargado.calcularAniosDeAntiguedad()*5000 + 3*500;

		assertEquals(sueldoDeseado, sueldoObtenido);

	}

	@Test
	public void queSePuedaCalcularElSueldoDeUnMesero() throws EmpleadoNoEncontradoException {
		Empleado mesero = new Mesero(112, "Juanito", LocalDate.of(2020,6,2));
		restaurante.agregarEmpleado(mesero);
		restaurante.cargarValorHoraDeUnEmpleado(112, 100.0);
		restaurante.cargarHorasTrabajadasDeUnEmpleado(112, 20);

		Cliente cliente = new Cliente(584, "nombreCliente");
		restaurante.agregarCliente(cliente);

		Reserva reserva = new Reserva(8546,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		
		Mesa mesa = new Mesa(123);
		ReservaCliente reservaCliente = new ReservaCliente(reserva, cliente, mesa);
		restaurante.agregarReservaCliente(reservaCliente);
		
		Pedido pedido = new Pedido (reservaCliente, mesero);
		restaurante.agregarPedido(pedido);

		Reserva reserva2 = new Reserva(854,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0)); 
		restaurante.agregarReserva(reserva2);

		Mesa mesa2 = new Mesa(21);
		ReservaCliente reservaCliente2 = new ReservaCliente(reserva2, cliente, mesa2);
		restaurante.agregarReservaCliente(reservaCliente2);
		
		Pedido pedido2 = new Pedido (reservaCliente2, mesero);
		restaurante.agregarPedido(pedido2);

		restaurante.cargarSueldoEmpleado(112);

		Double sueldoObtenido = restaurante.obtenerSueldoDeUnEmpleado(112);
		Double sueldoDeseado = 20 * 100.0 + mesero.calcularAniosDeAntiguedad()*1000 + 100;

		assertEquals(sueldoDeseado, sueldoObtenido);
	}
	
	@Test
	public void queSePuedaCalcularElSueldoDeUnCajero() throws EmpleadoNoEncontradoException {
		Empleado cajero = new Cajero(76, "Juanito", LocalDate.of(2020,6,2));
		restaurante.agregarEmpleado(cajero);
		restaurante.cargarValorHoraDeUnEmpleado(76, 100.0);
		restaurante.cargarHorasTrabajadasDeUnEmpleado(76, 20);
		restaurante.cargarSueldoEmpleado(76);

		Double sueldoObtenido = restaurante.obtenerSueldoDeUnEmpleado(76);
		Double sueldoDeseado = 20*100.0 + cajero.calcularAniosDeAntiguedad()*3000.0 ;

		assertEquals(sueldoDeseado, sueldoObtenido);

	}
	
	@Test
	public void queSePuedaCalcularElSueldoDeUnEncargadoSinEmpleadosACargo() throws EmpleadoNoEncontradoException {

		Empleado encargado = new Encargado(123, "Julieta", LocalDate.of(2000,5,19));
		restaurante.agregarEmpleado(encargado);

		restaurante.cargarValorHoraDeUnEmpleado(123, 9800.0);
		restaurante.cargarHorasTrabajadasDeUnEmpleado(123, 270); 
		restaurante.cargarSueldoEmpleado(123);

		Double sueldoObtenido = restaurante.obtenerSueldoDeUnEmpleado(123);
		Double sueldoDeseado = 270 * 9800.0 + encargado.calcularAniosDeAntiguedad()*5000;

		assertEquals(sueldoDeseado, sueldoObtenido);

	}

	@Test
	public void queSePuedaCalcularElSueldoDeUnMeseroSinPedidosRealizados() throws EmpleadoNoEncontradoException {
		Empleado mesero = new Mesero(112, "Juanito", LocalDate.of(2020,6,2));
		restaurante.agregarEmpleado(mesero);
		restaurante.cargarValorHoraDeUnEmpleado(112, 100.0);
		restaurante.cargarHorasTrabajadasDeUnEmpleado(112, 20);

		restaurante.cargarSueldoEmpleado(112);

		Double sueldoObtenido = restaurante.obtenerSueldoDeUnEmpleado(112);
		Double sueldoDeseado = 20 * 100.0 + mesero.calcularAniosDeAntiguedad()*1000;

		assertEquals(sueldoDeseado, sueldoObtenido);
	}

	@Test
	public void queSePuedaCalcularElSueldoDeUnCajeroSinAniosDeAntiguedad() throws EmpleadoNoEncontradoException {
		Empleado cajero = new Cajero(76, "Juanito", LocalDate.of(2024,4,2));
		restaurante.agregarEmpleado(cajero);
		restaurante.cargarValorHoraDeUnEmpleado(76, 100.0);
		restaurante.cargarHorasTrabajadasDeUnEmpleado(76, 20);
		restaurante.cargarSueldoEmpleado(76);

		Double sueldoObtenido = restaurante.obtenerSueldoDeUnEmpleado(76);
		Double sueldoDeseado = 20*100.0;

		assertEquals(sueldoDeseado, sueldoObtenido);

	}
	
	// metodos obtener
	@Test
	public void queSePuedaObtenerLaCantidadDeEmpleadosQueTrabajaEnUnRestaurante() {
		Empleado mesero1 = new Mesero(1,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero1);
		
		Empleado mesero2 = new Mesero(5,"pim",LocalDate.of(2013, 04, 01));
		this.restaurante.agregarEmpleado(mesero2);
		
		Empleado mesero3 = new Mesero(7,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarEmpleado(mesero3);
		
		Empleado mesero4 = new Mesero(3,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero4);
		
		Empleado mesero5 = new Mesero(2,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero5);
		
		Empleado mesero6 = new Mesero(6,"pim",LocalDate.of(2013, 04, 01));
		this.restaurante.agregarEmpleado(mesero6);
		
		Empleado mesero7 = new Mesero(8,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarEmpleado(mesero7);
		
		Empleado mesero8 = new Mesero(4,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero8);
	
		
		List<Empleado> cantidadTotaldeEmpleados = this.restaurante.obtenerListaDeEmpleadosOrdenadosDescendentePorSueldo();
		
		assertEquals(8,(int) cantidadTotaldeEmpleados.size());
	}
	@ Test
	public void queSePuedaOrdenarPorCodigoATodosLosEmpleadosQueTrabajanEnElrestauranteSinQueSeRepitan() {
		Empleado mesero1 = new Mesero(1,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero1);
		
		Empleado mesero2 = new Mesero(5,"pim",LocalDate.of(2013, 04, 01));
		this.restaurante.agregarEmpleado(mesero2);
		
		Empleado mesero3 = new Mesero(7,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarEmpleado(mesero3);
		
		Empleado mesero4 = new Mesero(3,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero4);
		
		Empleado mesero5 = new Mesero(2,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero5);
		
		Empleado mesero6 = new Mesero(6,"pim",LocalDate.of(2013, 04, 01));
		this.restaurante.agregarEmpleado(mesero6);
		
		Empleado mesero7 = new Mesero(8,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarEmpleado(mesero7);
		
		Empleado mesero8 = new Mesero(4,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero8);
		
		Empleado mesero9 = new Mesero(4,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero9);
		
		TreeSet<Empleado> listaDeEmpleados = this.restaurante.obtenerListaDeEmpleadosOrdenadosAscendenteSinQueSeRepitaElCodigo();
		
		assertEquals(8, listaDeEmpleados.size());
		
		assertEquals(1,(int)listaDeEmpleados.first().getCodigo());
		assertEquals(8, (int)listaDeEmpleados.last().getCodigo());
		
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
		this.restaurante.agregarEmpleado(mesero1);
		
		Empleado mesero2 = new Mesero(5,"pim",LocalDate.of(2013, 04, 01));
		this.restaurante.agregarEmpleado(mesero2);
		
		Empleado mesero3 = new Mesero(7,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarEmpleado(mesero3);
		
		Empleado mesero4 = new Mesero(3,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero4);
		
		Empleado mesero5 = new Mesero(2,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero5);
		
		Empleado mesero6 = new Mesero(6,"pim",LocalDate.of(2013, 04, 01));
		this.restaurante.agregarEmpleado(mesero6);
		
		Empleado mesero7 = new Mesero(8,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarEmpleado(mesero7);
		
		Empleado mesero8 = new Mesero(4,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero8);
	
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

	@Test
	public void queSePuedaOrdenarLosSueldosDeTodosLosEmpleadosQueTrabajanEnUnRestaurante() {
		// cajeros sueldo
		Empleado cajero = new Cajero(11, "Juanito", LocalDate.of(2024,4,2));
		restaurante.agregarEmpleado(cajero);
		restaurante.cargarValorHoraDeUnEmpleado(11, 100.0);
		restaurante.cargarHorasTrabajadasDeUnEmpleado(11, 20);
		restaurante.cargarSueldoEmpleado(11);
		
		Empleado cajero2 = new Cajero(12, "Juanito", LocalDate.of(2024,4,2));
		restaurante.agregarEmpleado(cajero2);
		restaurante.cargarValorHoraDeUnEmpleado(12, 500.0);
		restaurante.cargarHorasTrabajadasDeUnEmpleado(12, 10);
		restaurante.cargarSueldoEmpleado(12);
		
		// meseros sueldo
		Empleado mesero1 = new Mesero(4,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero1);
        this.restaurante.cargarValorHoraDeUnEmpleado(4, 800.0);
		this.restaurante.cargarHorasTrabajadasDeUnEmpleado(4, 10); 
		this.restaurante.cargarSueldoEmpleado(4);

		Empleado mesero2 = new Mesero(5,"pim",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero2);
		this.restaurante.cargarValorHoraDeUnEmpleado(5, 800.0);
		this.restaurante.cargarHorasTrabajadasDeUnEmpleado(5, 10); 
		this.restaurante.cargarSueldoEmpleado(5);
		
		Empleado mesero3 = new Mesero(7,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarEmpleado(mesero3);	
		this.restaurante.cargarValorHoraDeUnEmpleado(7, 800.0);
		this.restaurante.cargarHorasTrabajadasDeUnEmpleado(7, 15); 
		this.restaurante.cargarSueldoEmpleado(7);
		
		
		Empleado mesero4 = new Mesero(3,"pimi",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero4);	
		this.restaurante.cargarValorHoraDeUnEmpleado(3, 800.0);
		this.restaurante.cargarHorasTrabajadasDeUnEmpleado(3, 13); 
		this.restaurante.cargarSueldoEmpleado(3);
		
		Empleado mesero5 = new Mesero(2,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero5);	
		this.restaurante.cargarValorHoraDeUnEmpleado(2, 800.0);
		this.restaurante.cargarHorasTrabajadasDeUnEmpleado(2, 20); 
		this.restaurante.cargarSueldoEmpleado(2);
		
		// encargados sueldo
		Empleado encargado = new Encargado(1, "Santi", LocalDate.of(2003, 10, 10));
		this.restaurante.agregarEmpleado(encargado);
        this.restaurante.cargarValorHoraDeUnEmpleado(1, 500.0);
		this.restaurante.cargarHorasTrabajadasDeUnEmpleado(1, 8); 
		this.restaurante.cargarSueldoEmpleado(1);
		this.restaurante.cargarMeseroACargoDelEncargado(encargado, mesero1);
		this.restaurante.cargarMeseroACargoDelEncargado(encargado, mesero2);
		this.restaurante.cargarMeseroACargoDelEncargado(encargado, mesero3);
		
		Empleado encargado2 = new Encargado(10, "Santiago", LocalDate.of(2003, 10, 10));
		this.restaurante.agregarEmpleado(encargado2);
        this.restaurante.cargarValorHoraDeUnEmpleado(10, 500.0);
		this.restaurante.cargarHorasTrabajadasDeUnEmpleado(10, 15); 
		this.restaurante.cargarSueldoEmpleado(10);
		this.restaurante.cargarMeseroACargoDelEncargado(encargado2, mesero4);
		this.restaurante.cargarMeseroACargoDelEncargado(encargado2, mesero5);
		
		List<Empleado> obtenerListaTotalDeEmpleados = this.restaurante.obtenerListaDeEmpleadosOrdenadosDescendentePorSueldo();
	    Double primer = 107500.0;
	    Double segundo = 104000.0;
	    Double tercero = 28000.0;
	    Double cuarto = 27000.0;
	    Double quinto = 22400.0;
	    Double sexto = 20000.0;
	    Double septimo = 20000.0;
	    Double octavo = 5000.0;
	    Double noveno = 2000.0;
	    
		assertEquals(primer, (Double)obtenerListaTotalDeEmpleados.get(0).getSueldo());
		assertEquals(segundo, (Double)obtenerListaTotalDeEmpleados.get(1).getSueldo());
		assertEquals(tercero, (Double)obtenerListaTotalDeEmpleados.get(2).getSueldo());
		assertEquals(cuarto, (Double)obtenerListaTotalDeEmpleados.get(3).getSueldo());
		assertEquals(quinto, (Double)obtenerListaTotalDeEmpleados.get(4).getSueldo());
		assertEquals(sexto, (Double)obtenerListaTotalDeEmpleados.get(5).getSueldo());
		assertEquals(septimo, (Double)obtenerListaTotalDeEmpleados.get(6).getSueldo());
		assertEquals(octavo, (Double)obtenerListaTotalDeEmpleados.get(7).getSueldo());
		assertEquals(noveno, (Double)obtenerListaTotalDeEmpleados.get(8).getSueldo());
		
	}

	@Test 
	public void queSePuedaObtenerLaCantidadDeMEserosQueTrabajanEnELRestaurante() {
		Empleado mesero1 = new Mesero(4,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero1);

		Empleado mesero2 = new Mesero(5,"pim",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero2);
		
		Empleado mesero3 = new Mesero(7,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarEmpleado(mesero3);
		
		
		Empleado mesero4 = new Mesero(3,"pimi",LocalDate.of(2014, 04, 01));
		this.restaurante.agregarEmpleado(mesero4);
		
		Empleado mesero5 = new Mesero(2,"pimenta",LocalDate.of(2017, 04, 01));
		this.restaurante.agregarEmpleado(mesero5);
		
		List<Empleado> cantidadDeMeseros = this.restaurante.obtenerListaDeMeseros();
		
		assertEquals(5,(int) cantidadDeMeseros.size());
	}
	@Test 
	public void queSePuedaObtenerListaDeLosCodigoDeLosMeserosOrdenadosPorAntiguedadDescendeteQueTrabajaEnElRestaurante() {
		Empleado mesero1 = new Mesero(4,"pimenta",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero1);

		Empleado mesero2 = new Mesero(5,"pim",LocalDate.of(2012, 04, 01));
		this.restaurante.agregarEmpleado(mesero2);
		
		Empleado mesero3 = new Mesero(7,"menta",LocalDate.of(2009, 04, 01));
		this.restaurante.agregarEmpleado(mesero3);
		
		
		Empleado mesero4 = new Mesero(3,"pimi",LocalDate.of(2014, 04, 01));
		this.restaurante.agregarEmpleado(mesero4);
		
		Empleado mesero5 = new Mesero(2,"pimenta",LocalDate.of(2017, 04, 01));
		this.restaurante.agregarEmpleado(mesero5);
		
		List<Empleado> obtenerListaDeMeseros = this.restaurante.obtenerListaDeMeserosPorAntiguedadDescendente();
		
		assertEquals(7, (int)obtenerListaDeMeseros.get(0).getCodigo());
		assertEquals(4, (int)obtenerListaDeMeseros.get(1).getCodigo());
		assertEquals(5, (int)obtenerListaDeMeseros.get(2).getCodigo());
		assertEquals(3, (int)obtenerListaDeMeseros.get(3).getCodigo());
		assertEquals(2, (int)obtenerListaDeMeseros.get(4).getCodigo());
	}
	
	
	
	@Test
	public void queSePuedaObtenerListaDeClienteSinQueSeRepitaElCodigo() {
		
		Cliente cliente = new Cliente(1 ,"Bautista");
		restaurante.agregarCliente(cliente);
		
		Cliente cliente2 = new Cliente(2 ,"Bau");
		restaurante.agregarCliente(cliente2);
		
		Cliente cliente3 = new Cliente(3 ,"tista");
		restaurante.agregarCliente(cliente3);
		
		Cliente cliente4 = new Cliente(4 ,"Basta");
		restaurante.agregarCliente(cliente4);
		
		Cliente cliente5 = new Cliente(5 ,"Bauti");
		restaurante.agregarCliente(cliente5);
		
		
		// para que no me agregue debe estar el nommbre y el numero pero 
		// queria saber si puedo sacar el nombre y dejar el numero xq asi si alguin se equivoca y pone
		// un mismo codigo no tendria q apacer y asi poder arrglarlo
		Cliente cliente6 = new Cliente(5 ,"Bauti");
		restaurante.agregarCliente(cliente6);
		
		
		HashSet<Cliente> clientes = this.restaurante.obtenerCantidadDeClientes();
		
		assertEquals(5, (int) clientes.size());
	}


	@Test
	public void queSePuedaCrearUnaReservaCliente() {
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		Cliente cliente = new Cliente(1, "Juan");

		Mesa mesa = new Mesa(12);
		ReservaCliente reservaCliente = new ReservaCliente(reserva, cliente, mesa);

		assertNotNull(reservaCliente);
	}

	@Test
	public void queUnClientePuedaHacerUnaReserva()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);

		Boolean reservaRealizada = restaurante.realizarReserva(reserva, cliente,null);

		assertTrue(reservaRealizada);
	}

	@Test(expected = ClienteNoEncontradoException.class)
	public void dadoQueUnClientePuedeHacerUnaReservaSiNoSeLoEncuentraQueSeLanceLaExceptionAdecuada()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1, "Juan");

		restaurante.realizarReserva(reserva, cliente,null);
	}

	@Test(expected = ReservaNoEncontradaException.class)
	public void dadoQueUnClientePuedeHacerUnaReservaSiNoSeEncuentraLaReservaQueSeLanceLaExceptionAdecuada()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);

		restaurante.realizarReserva(reserva, cliente,null);
	}

	@Test(expected = ReservaClienteDuplicadoException.class)
	public void dadoQueUnClientePuedeHacerUnaReservaQueNoPuedaHaberLaMismaReservaClienteDosVeces()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);

		restaurante.realizarReserva(reserva, cliente,null);
		restaurante.realizarReserva(reserva, cliente,null);
	}
	
	//-----------------------PARTE MESA -------------------------------

	@Test
	public void queSePuedaCrearUnaMesa() {
		Integer numero = 1;
		Mesa mesa = new Mesa(numero);

		assertNotNull(mesa);
	}

	@Test
	public void queSePuedaAgregarUnaMesaAlRestaurente() {
		Mesa mesa = new Mesa(1);

		Boolean mesaAgregada = restaurante.agregarMesa(mesa);

		assertTrue(mesaAgregada);
	}
	
	@Test
	public void queSePuedaBuscarUnaMesa() {
		Mesa mesa = new Mesa(1);
		restaurante.agregarMesa(mesa);
		
		Mesa mesaObtenida = restaurante.buscarMesa(mesa);
		
		assertEquals(mesa,mesaObtenida);
	}
	
	@Test
	public void queSePuedaObtenerUnaReservaEntreLasReservasClientesDelRestaurante() throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva,cliente,null);
		
		Reserva reservaObtenida = restaurante.obtenerReservaDeReservasClientes(reserva);
		
		assertEquals(reserva,reservaObtenida);
	}

	@Test
	public void queSePuedaAsignarUnaMesaAUnaReserva() throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesa mesa = new Mesa(1);
		restaurante.agregarMesa(mesa);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva,cliente,mesa);
		
		Reserva reservaObtenida = restaurante.obtenerReservaDeReservasClientes(reserva);
		
		assertEquals(mesa,reservaObtenida.getMesa());
	}
	
	@Test (expected = MesaNoEncontrada.class)
	public void dadoQueSePuedeAsignarUnaMesaAUnaReservaSiNoSeEncuentraLaMesaQueLanceException() throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesa mesa = new Mesa(1);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva,cliente,mesa);
	}
	
	@Test (expected = MesaYaAsignadaAReserva.class)
	public void dadoQueSePuedeAsignarUnaMesaAUnaReservaSiEsaReservaYaTieneAsignadaUnaMesaQueLanceException() throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesa mesa = new Mesa(1);
		restaurante.agregarMesa(mesa);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva,cliente,mesa);
		restaurante.realizarReserva(reserva,cliente,mesa);
	}
	
	
	//----------------------------------- PARTE MESA FIN --------------------

	@Test
	public void queSePuedaBuscarUnaReservaCliente()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);

		ReservaCliente rcEncontrada = restaurante.buscarUnaReservaCliente(cliente, reserva);

		assertEquals(cliente, rcEncontrada.getCliente());
		assertEquals(reserva, rcEncontrada.getReserva());
	}

	@Test
	public void queSePuedaCrearUnPedido()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		ReservaCliente rcEncontrada = restaurante.buscarUnaReservaCliente(cliente, reserva);

		Pedido pedido = new Pedido(rcEncontrada, mesero);

		assertNotNull(pedido);
	}

	@Test
	public void queUnMeseroTomeUnPedido() throws ClienteNoEncontradoException, ReservaNoEncontradaException,
			PedidoDuplicadoException, EmpleadoNoEncontradoException, PedidoYaTomado, ReservaClienteDuplicadoException,
			ReservaClienteNoEncontrado, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);

		Boolean pedidoTomadoPorMesero = restaurante.queUnMeseroTomeUnPedido(reserva, cliente, mesero);

		assertTrue(pedidoTomadoPorMesero);
	}

	@Test(expected = EmpleadoNoEncontradoException.class)
	public void dadoQueUnMeseroTomaUnPedidoSiNoSeLoEncuentraQueSeLanceUnaExceptionAdecuada()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, EmpleadoNoEncontradoException,
			ReservaClienteDuplicadoException, PedidoDuplicadoException, PedidoYaTomado, ReservaClienteNoEncontrado, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);

		restaurante.queUnMeseroTomeUnPedido(reserva, cliente, mesero);
	}

	@Test(expected = ReservaClienteNoEncontrado.class)
	public void dadoQueUnMeseroTomaUnPedidoSiNoSeEncuentraElPedidoQueLanceException()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, ReservaClienteDuplicadoException,
			EmpleadoNoEncontradoException, PedidoDuplicadoException, PedidoYaTomado, ReservaClienteNoEncontrado, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Cliente cliente2 = new Cliente(2, "Enrique");
		restaurante.agregarCliente(cliente2);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);

		restaurante.queUnMeseroTomeUnPedido(reserva, cliente2, mesero);
	}

	@Test(expected = PedidoDuplicadoException.class)
	public void dadoQueUnMeseroTomaLaReservaDeUnClienteQueNoSeDeLaMismaCombinacionDeReservaClienteMeseroDosVeces()
			throws ClienteNoEncontradoException, ReservaNoEncontradaException, PedidoYaTomado,
			EmpleadoNoEncontradoException, PedidoDuplicadoException, ReservaClienteDuplicadoException,
			ReservaClienteNoEncontrado, MesaNoEncontrada, MesaYaAsignadaAReserva {
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		Cliente cliente = new Cliente(1, "Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1, LocalDate.of(2024, 5, 20), LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReserva(reserva, cliente,null);

		restaurante.queUnMeseroTomeUnPedido(reserva, cliente, mesero);
		restaurante.queUnMeseroTomeUnPedido(reserva, cliente, mesero);
	}

	@Test
	public void queSePuedaAsignarEmpleadosAUnEncargado()
			throws EmpleadoNoEncontradoException, EmpleadoYaAsignadoAEncargado {
		Encargado encargado = new Encargado(15, "pepe", LocalDate.of(2009, 1, 5));
		Mesero mesero = new Mesero(15, "pepe", LocalDate.of(2009, 1, 5));
		Mesero mesero2 = new Mesero(12, "lucas", LocalDate.of(2008, 5, 5));
		restaurante.agregarEmpleado(encargado);
		restaurante.agregarEmpleado(mesero);
		restaurante.agregarEmpleado(mesero2);

		Boolean empleadoAsignadoAEncargado = restaurante.asignarEmpleadoAEncargado(encargado, mesero);
		restaurante.asignarEmpleadoAEncargado(encargado, mesero2);
		Empleado empleado = restaurante.buscarUnEmpleado(encargado.getCodigo());

		assertTrue(empleadoAsignadoAEncargado);
		assertEquals(2, ((Encargado) empleado).getEmpleadosACargo().size());
	}

	@Test(expected = EmpleadoNoEncontradoException.class)
	public void dadoQueSePuedeAsignarEmpleadosAUnEncargadoSiNoSeEncuentraElEncargadoQueSeLanceException()
			throws EmpleadoNoEncontradoException, EmpleadoYaAsignadoAEncargado {
		Encargado encargado = new Encargado(15, "pepe", LocalDate.of(2009, 1, 5));
		Mesero mesero = new Mesero(16, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);

		restaurante.asignarEmpleadoAEncargado(encargado, mesero);
	}

	@Test(expected = EmpleadoYaAsignadoAEncargado.class)
	public void dadoQueSePuedeAsignarEmpleadosAUnEncargadoSiUnEmpleadoYaFueAsignadoAEseEncargadoQueLanceException()
			throws EmpleadoNoEncontradoException, EmpleadoYaAsignadoAEncargado {
		Encargado encargado = new Encargado(15, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(encargado);
		Mesero mesero = new Mesero(16, "pepe", LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);

		restaurante.asignarEmpleadoAEncargado(encargado, mesero);
		restaurante.asignarEmpleadoAEncargado(encargado, mesero);
	}


	@Test (expected = EmpleadoNoEncontradoException.class)
	public void queLanceUnaExcepcionSiQuieroObtenerElSueldoDeUnEmpleadoQueNoExiste() throws EmpleadoNoEncontradoException {
		Double sueldoObtenido = restaurante.obtenerSueldoDeUnEmpleado(101);
	}
	

}
