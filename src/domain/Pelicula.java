package domain;

import java.util.ArrayList;

public class Pelicula extends Contenido{
	private int duracion; // en minutos
	private String titulo;

	public Pelicula(String titulo, ArrayList<String> genero, ArrayList<Double> puntuaciones, ArrayList<String> cast, int duracion) {
		super(titulo, genero, puntuaciones, cast);
		this.duracion = duracion;
		this.titulo = titulo;
	}

	
	public int getDuracion() {
		return duracion;
	}


	public String getTitulo() {
		return titulo;
	}



}
