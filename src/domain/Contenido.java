package domain;

public class Contenido {
	private String titulo;
	private String descripcion;
	private int duracion; // en minutos

	public Contenido(String titulo, String descripcion, int duracion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.duracion = duracion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

}
