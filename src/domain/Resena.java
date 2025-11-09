package domain;

import java.time.LocalDate;

public class Resena {
	private Usuario usuario;
	private Contenido contenido;
	private Double puntuacion; // entre 1 y 5
	private LocalDate fechaResena;
	
	
	public Resena(Usuario usuario, Contenido contenido, Double puntuacion) {
		super();
		this.usuario = usuario;
		this.contenido = contenido;
		this.puntuacion = puntuacion;
		this.fechaResena = LocalDate.now();
		
	}

	public LocalDate getFechaResena() {
		return fechaResena;
	}

	public Usuario getUsuario() {
		return usuario;
	}


	public Contenido getContenido() {
		return contenido;
	}


	public Double getPuntuacion() {
		return puntuacion;
	}



}
