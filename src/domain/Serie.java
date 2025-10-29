package domain;

import java.util.ArrayList;

public class Serie extends Contenido{
	private int temporadas;
	
	public Serie(String titulo, ArrayList<String> genero, ArrayList<Double> puntuaciones,  ArrayList<String> cast, int temporadas) {
		super(titulo, genero, puntuaciones, cast);
		this.temporadas = temporadas;
	}

	public int getTemporadas() {
		return temporadas;
	}

	
}
