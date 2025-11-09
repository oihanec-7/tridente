package domain;

import java.util.ArrayList;

import gui.VentanaMiLista;

public class Usuario {
	private String nombre;
	private String nombre_usuario;
	private String contraseña;
	private String apellido;
	private String email;
	private ArrayList<Contenido> miLista;
	private ArrayList<Resena> listaValoradas;
	private VentanaMiLista ventanaMiLista;



	public Usuario(String nombre, String nombre_usuario, String contraseña, String apellido, String email) {
		super();
		this.nombre = nombre;
		this.nombre_usuario = nombre_usuario;
		this.contraseña = contraseña;
		this.apellido = apellido;
		this.email = email;
		this.miLista = new ArrayList<Contenido>();
		this.listaValoradas = new ArrayList<Resena>();
		this.ventanaMiLista = null;
		
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

	public ArrayList<Resena> getListaValoradas() {
		return listaValoradas;
	}
	
	public void agregarValorada(Resena r) {
		listaValoradas.add(r);
	}

	public ArrayList<Contenido> getMiLista() {
		return miLista;
	}

	public void setMiLista(ArrayList<Contenido> miLista) {
		this.miLista = miLista;
	}

	public void setVentanaMiLista(VentanaMiLista v) { 
	    this.ventanaMiLista = v; 
	}

	public VentanaMiLista getVentanaMiLista() { 
	    return this.ventanaMiLista; 
	}
	
	
	

}
