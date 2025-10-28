package gui;

import java.util.List;

import javax.swing.JFrame;

import domain.Usuario;

public class VentanaPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public void ventana(List<Usuario> usuarios) {
		this.setTitle("Ventana Principal");
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	

}
