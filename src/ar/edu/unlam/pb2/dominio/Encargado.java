package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Encargado extends Empleado {

	
	private List<Empleado> empleadosACargo;
	private Double valorPorEmpleadoACargo;
	
	public Encargado(Integer codigo, String nombre, LocalDate anioIngreso) {
		super(codigo, nombre, anioIngreso);
		this.empleadosACargo = new ArrayList<>();
		setValorPorAnioAntiguedad(5000.0);
		valorPorEmpleadoACargo = 500.0;
	}
	
	@Override
	public Double calcularSueldo() {
		return getHorasTrabajadas()*getValorHora() + calcularAniosDeAntiguedad()*getValorPorAnioAntiguedad() + calcularExtraPorMeserosACargo();
	}

	public Double calcularExtraPorMeserosACargo() {
		return empleadosACargo.size() * valorPorEmpleadoACargo;
	}

	public List<Empleado> getEmpleadosACargo() {
		return empleadosACargo;
	}

	public Boolean asignarEmpleado(Empleado mesero) {
		return empleadosACargo.add(mesero);
	}
	
	
}
