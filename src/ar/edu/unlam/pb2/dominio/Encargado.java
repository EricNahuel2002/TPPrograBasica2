package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Encargado extends Empleado {

	
	private List<Empleado> meseros;
	
	public Encargado(Integer codigo, String nombre, LocalDate anioIngreso) {
		super(codigo, nombre, anioIngreso);
		this.meseros = new ArrayList<>();
	}
	
	@Override
	public Double calcularSueldo() {
		return getHorasTrabajadas()*getValorHora() + calcularAniosDeAntiguedad()*5000 + calcularExtraPorMeserosACargo();
	}

	public Double calcularExtraPorMeserosACargo() {
		return meseros.size() * 500.0;
	}

	public Boolean agregarMeseroACargo(Empleado mesero) {

		return meseros.add(mesero);
	}
	
	
}
