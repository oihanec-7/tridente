package domain;

import java.util.ArrayList;

public abstract class Contenido {
	private String titulo;
	private ArrayList<String> genero;
	private ArrayList<Double> puntuaciones; 
	private ArrayList<String> cast;
	private String rutaPortada;

	

	public Contenido(String titulo, ArrayList<String> genero, ArrayList<Double> puntuaciones, ArrayList<String> cast, String rutaPortada) {
		super();
		this.titulo = titulo;
		this.genero = genero;
		this.puntuaciones = puntuaciones;
		this.cast = cast;
		this.rutaPortada = rutaPortada;
	}

	public String getRutaPortada() {
		return rutaPortada;
	}

	public void setRutaPortada(String rutaPortada) {
		this.rutaPortada = rutaPortada;
	}

	public ArrayList<String> getCast() {
		return cast;
	}

	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<String> getGenero() {
		return genero;
	}

	public void setGenero(ArrayList<String> genero) {
		this.genero = genero;
	}

	public ArrayList<Double> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(ArrayList<Double> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

	public double getPuntuacionMedia() {
		double suma = 0.0;
		for (Double puntuacion : puntuaciones) {
			suma += puntuacion;	
		}
		return suma / puntuaciones.size(); 
	}
	
	public void agregarPuntuacion(Double puntuacion) {
		puntuaciones.add(puntuacion);
		
	}

}
