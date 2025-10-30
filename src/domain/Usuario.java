package domain;

public class Usuario {
	private String nombre;
	private String nombre_usuario;
	private String contraseña;
	private String apellido;
	private String email;
	private int num_resenas;


	public Usuario(String nombre, String nombre_usuario, String contraseña, String apellido, String email, int num_resenas) {
		super();
		this.nombre = nombre;
		this.nombre_usuario = nombre_usuario;
		this.contraseña = contraseña;
		this.apellido = apellido;
		this.email = email;
		this.num_resenas = num_resenas;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getEmail() {
		return email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getApellido() {
		return apellido;
	}

	public int getNum_resenas() {
		return num_resenas;
	}

	
	

}
