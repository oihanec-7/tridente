package domain;

public class Resena {
	private Usuario usuario;
	private Contenido contenido;
	private Double puntuacion; // entre 1 y 5
	
	
	public Resena(Usuario usuario, Contenido contenido, Double puntuacion) {
		super();
		this.usuario = usuario;
		this.contenido = contenido;
		this.puntuacion = puntuacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Contenido getContenido() {
		return contenido;
	}


	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}


	public Double getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
	
	
	

}
