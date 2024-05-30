package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Cajero extends Empleado {
	
	public Cajero(Integer codigo, String nombre, LocalDate anioIngreso) {
		super(codigo, nombre, anioIngreso);
	}

	@Override
	public Double calcularSueldo() {
		return getHorasTrabajadas()*getValorHora() + calcularAniosDeAntiguedad()*3000;
	}
	
	
}
