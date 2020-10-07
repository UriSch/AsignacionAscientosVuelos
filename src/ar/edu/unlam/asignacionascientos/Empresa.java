package ar.edu.unlam.asignacionascientos;

import java.util.ArrayList;
import java.util.HashSet;

public class Empresa {

	private String nombre;
	private HashSet<Vuelo> vuelos;
	private HashSet<Pasajero> pasajeros;
	private HashSet<AsignacionAsciento> asignacionesAsientos;
	private HashSet<Avion> aviones;

	public Empresa(String nombre) {

		this.nombre = nombre;
		this.vuelos = new HashSet<Vuelo>();
		this.pasajeros = new HashSet<Pasajero>();
		this.asignacionesAsientos = new HashSet<AsignacionAsciento>();
		this.aviones=new HashSet<>();

	}

	public Boolean registrarAvion(Avion avion) {
		Boolean registrado = this.aviones.add(avion);
		return registrado;
	}

	public Boolean regitrarVuelo(Vuelo vuelo) {
		Boolean registrado = this.vuelos.add(vuelo);
		return registrado;
	}

	public Boolean registarPasajero(Pasajero pasajero) {
		Boolean registrado = this.pasajeros.add(pasajero);
		return registrado;
	}
	
	public Avion buscarAvion(Integer idAvion) {
		Avion avion = null;
		
		for (Avion avionBuscado : aviones) {
			if(avionBuscado.getId().equals(idAvion))
				avion = avionBuscado;
		}
	
		return avion;
	}
	
	public Vuelo buscarVuelo(Integer idVuelo) {
		Vuelo vuelo = null;
		
		for (Vuelo vueloBuscado : vuelos) {
			if(vueloBuscado.getId().equals(idVuelo))
				vuelo = vueloBuscado;
		}
	
		return vuelo;
	}
	
	public Pasajero buscarPasajero(Integer dniPasajero) {
		Pasajero pasajero = null;
		
		for (Pasajero pasajeroBuscado : pasajeros) {
			if(pasajeroBuscado.getDni().equals(dniPasajero))
				pasajero = pasajeroBuscado;
		}
		return pasajero;
	}

	public Boolean asignarPasajeroAUnVuelo(Integer idVuelo, Integer dni) {
		Boolean asignado = false;
		if(buscarVuelo(idVuelo)!= null) {
			asignado = buscarVuelo(idVuelo).getPasajeros().add(buscarPasajero(dni));
		}
		return asignado;
	}
	
	public Boolean asignarAsientoPasajeroParaUnVuelo(Integer idVuelo, Integer dni, String numeroAciento) {
		Boolean asignado = false;
		if(buscarVuelo(idVuelo)!= null) {
			ArrayList<String> listaAsientos = buscarVuelo(idVuelo).getAvion().getListaAscientos();
			for (String string : listaAsientos) {
				if (string.equals(numeroAciento)) {
					AsignacionAsciento asignacion = new AsignacionAsciento(asignacionesAsientos.size(), buscarVuelo(idVuelo), buscarPasajero(dni), numeroAciento);
					asignado = asignacionesAsientos.add(asignacion);
				}
			}
		}
		return asignado;
	}

	public Boolean verificarSiExisteUnAsientoEnUnAvion(Integer idAvion, String asciento) {
		Boolean existe = false;
		if(buscarAvion(idAvion)!= null) {
			ArrayList <String> listaDeAsientos = buscarAvion(idAvion).getListaAscientos();
			for (String string : listaDeAsientos) {
				if(string.equals(asciento))
					existe = true;
			}
		}
		return existe;
	}
                                                                 
	public Boolean verificarAsientoDiponibleParaUnVuelo(Integer idVuelo, String asciento) {
		Boolean disponible = true;
		Avion avion = buscarVuelo(idVuelo).getAvion();
		if(avion != null) {
			ArrayList <String> listaDeAsientos = avion.getListaAscientos();
			for (String string : listaDeAsientos) {
				if (string.equals(asciento)) {
					disponible = false;
				}
			}
		}
		return disponible;
	}

	public HashSet<String> obtenerListaDeascientoDeUnAvion(Integer idAvion) {
		HashSet<String> listaAsientos = null;
		if(buscarAvion(idAvion)!=null) 
			listaAsientos = new HashSet<String>(buscarAvion(idAvion).getListaAscientos());
		return listaAsientos;
	}

	public HashSet<String> obtenerListaDeAscientoOcupadosDeUnVuelo(Integer idVuelo) {
		HashSet<String> listaDeAsientosOcupados = null;
		if(buscarVuelo(idVuelo)!=null) {
			listaDeAsientosOcupados = new HashSet<String>(obtenerListaDeascientoDeUnAvion(buscarVuelo(idVuelo).getAvion().getId()));
			listaDeAsientosOcupados.removeAll(null);
		}
		return listaDeAsientosOcupados;
	}

	public HashSet<String> obtenerListaDeAscientoDisponibleDeUnVuelo(Integer idVuelo) {
		HashSet<String> listaAsientosDisponibles = null;
		if(buscarVuelo(idVuelo)!=null) {
		listaAsientosDisponibles = new HashSet<String>(buscarVuelo(idVuelo).getAvion().getListaAscientos());
		listaAsientosDisponibles.removeAll(obtenerListaDeAscientoOcupadosDeUnVuelo(idVuelo));
		}
		return  listaAsientosDisponibles;
	}
	
	public Boolean cambiarAscientoDeUnPasajeroParaUnVuelo(Integer idVuelo, Integer dni, String nuevoAsciento) {
		Boolean sePudo = false;
		if(buscarVuelo(idVuelo)!=null) {
			for (AsignacionAsciento asignacionAsciento : asignacionesAsientos) {
				if(asignacionAsciento.getVuelo().equals(buscarVuelo(idVuelo))) {
					sePudo = true;
					asignacionAsciento.setAsciento(nuevoAsciento);
				}
			}
		}
		return sePudo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
