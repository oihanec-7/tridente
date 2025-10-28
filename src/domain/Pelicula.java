package domain;

import java.util.ArrayList;

public class Pelicula extends Contenido{
	private int duracion; // en minutos

	public Pelicula(String titulo, ArrayList<String> genero, ArrayList<Double> puntuaciones, int duracion) {
		super(titulo, genero, puntuaciones);
		this.duracion = duracion;
	}

	
	public int getDuracion() {
		return duracion;
	}
	
	

	


}
