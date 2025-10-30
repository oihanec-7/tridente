package domain;

import java.util.ArrayList;

public class Serie extends Contenido{
	private int temporadas;
	private String titulo;
	
	public Serie(String titulo, ArrayList<String> genero, ArrayList<Double> puntuaciones,  ArrayList<String> cast, int temporadas) {
		super(titulo, genero, puntuaciones, cast);
		this.temporadas = temporadas;
		this.titulo = titulo;
	}

	public int getTemporadas() {
		return temporadas;
	}

	public String getTitulo() {
		return titulo;
	}

	
}
