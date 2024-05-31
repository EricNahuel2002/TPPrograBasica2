package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Encargado extends Empleado {

	
	private List<Empleado> meseros;
	private Double valorPorEmpleadoACargo;
	
	public Encargado(Integer codigo, String nombre, LocalDate anioIngreso) {
		super(codigo, nombre, anioIngreso);
		this.meseros = new ArrayList<>();
		setValorPorAnioAntiguedad(5000.0);
		valorPorEmpleadoACargo = 500.0;
	}
	
	@Override
	public Double calcularSueldo() {
		return getHorasTrabajadas()*getValorHora() + calcularAniosDeAntiguedad()*getValorPorAnioAntiguedad() + calcularExtraPorMeserosACargo();
	}

	public Double calcularExtraPorMeserosACargo() {
		return meseros.size() * valorPorEmpleadoACargo;
	}

	public Boolean agregarMeseroACargo(Empleado mesero) {

		return meseros.add(mesero);
	}
	
	
}
