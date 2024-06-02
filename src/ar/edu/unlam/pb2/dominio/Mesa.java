package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class Mesa {

	private Integer numero;

	public Mesa(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesa other = (Mesa) obj;
		return Objects.equals(numero, other.numero);
	}

}
