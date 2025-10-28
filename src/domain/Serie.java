package domain;

import java.util.ArrayList;

public class Serie extends Contenido{
	private int temporadas;
	
	public Serie(String titulo, ArrayList<String> genero, ArrayList<Double> puntuaciones, int temporadas) {
		super(titulo, genero, puntuaciones);
		this.temporadas = temporadas;
	}

	public int getTemporadas() {
		return temporadas;
	}

	
}
