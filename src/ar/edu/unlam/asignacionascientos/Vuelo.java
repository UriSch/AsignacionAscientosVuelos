package ar.edu.unlam.asignacionascientos;

import java.util.HashSet;

public class Vuelo {

	
	private Integer id;
	private String origen;
	private String destino;
	private Avion  avion;
	private HashSet<Pasajero> pasajeros;
	
	public Vuelo(Integer id, String origen, String destino, Avion avion) {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.avion = avion;
		this.pasajeros = new HashSet<Pasajero>();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vuelo other = (Vuelo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getOrigen() {
		return origen;
	}
	
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public Avion getAvion() {
		return avion;
	}
	
	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public HashSet<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(HashSet<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}
}
