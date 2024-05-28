package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Mesero extends Empleado {
	
	private Integer cantidadDePedidosTomados;
	private static final Double PORCIENTO_A_SUBIR_ANTES = 0.40;
	private static final Double PORCIENTO_A_SUBIR_DESPUES = 0.35;
	private final Double PRIMA_POR_PEDIDO = 50.0;

	public Mesero(Integer codigo, String nombre, LocalDate anioIngreso) {
		super(codigo, nombre, anioIngreso);
		this.cantidadDePedidosTomados = 0;
	}
	
}
