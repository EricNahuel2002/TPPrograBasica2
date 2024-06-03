package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;
import java.util.List;

public interface IRestaurante {
	Boolean agregarEmpleado(Empleado empleado);
	Boolean despedirUnEmpleado(Integer codigo);
	Empleado buscarUnEmpleado(Integer codigo);
	List<Empleado> obtenerListaDeEmpleadosOrdenadosDescendentePorSueldo();
	List<Empleado> obtenerListaDeEncargadosOrdenadoDeMayorAMenorPorSueldo();
	List<Empleado> obtenerListaDeMeserosOrdenadoDeMayorAMenorPorSueldo();
	List<Empleado> obtenerListaDeCajerosOrdenadoDeMayorAMenorPorSueldo();
	Empleado obtenerElMeseroDelMes() throws NoHayEmpleadoDelMesException;
	HashSet<Cliente> obtenerLaCantidadDeClientesQueFueronAlRestaurante();
	Boolean agregarCliente(Cliente cliente);
	Cliente buscarUnCliente(Integer id);
	Double calcularElSueldoEnBaseALaAntiguedadDeUnEmpleado(Empleado empleado);
	Boolean agregarReserva(Reserva reserva);
}
