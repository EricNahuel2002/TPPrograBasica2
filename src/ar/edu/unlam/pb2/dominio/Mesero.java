package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Mesero extends Empleado {
	
	private Integer cantidadPedidos;

	public Mesero(Integer codigo, String nombre, LocalDate anioIngreso) {
		super(codigo, nombre, anioIngreso);
		this.cantidadPedidos = 0;
		setValorPorAnioAntiguedad(1000.0);
	}
	//cantidad de pedidos
	//propina o bono extra en base a la cantidad de pedidos

	@Override
	public Double calcularSueldo() {
		return getHorasTrabajadas()*getValorHora() + calcularAniosDeAntiguedad()*getValorPorAnioAntiguedad() + calcularBonoPorCantidadDePedidos();
	}

	public Double calcularBonoPorCantidadDePedidos() {

		Double bono = 0.0;

		switch(cantidadPedidos) {
		case 1:
			if (cantidadPedidos >= 5 && cantidadPedidos < 10) {
                bono = 500.0;
                break;
            }
		case 2:
			if (cantidadPedidos >= 10 && cantidadPedidos < 20) {
                bono = 800.0;
                break;
            }
		case 3:
			if (cantidadPedidos >= 20) {
                bono = 1000.0;
                break;
            }
		case 4:
			if(cantidadPedidos >= 1 && cantidadPedidos < 5) {
				bono = 100.0;
				break;
			}
        default:
        	bono = 0.0;
        	break;
		}
		return bono;
	}

	public Integer getCantidadPedidos() {
		return cantidadPedidos;
	}

	public void setCantidadPedidos(Integer cantidadPedidos) {
		this.cantidadPedidos = cantidadPedidos;
	}

	public void incrementarCantidadPedidos() {
		cantidadPedidos++;
	}
	
}
