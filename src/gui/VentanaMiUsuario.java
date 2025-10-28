package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Usuario;

public class VentanaMiUsuario extends JFrame{
	
	public VentanaMiUsuario(Usuario usuario) {
		
		setTitle("Perfil de Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,300);
		setLayout(new BorderLayout(10,10));
		
		JLabel titulo = new JLabel("Mi Perfil");
		add(titulo, BorderLayout.NORTH);

		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new GridLayout(4,2,10,10));
		
		// Nombre de usuario
		panelInfo.add(new JLabel("Username:"));
		panelInfo.add(new JLabel(usuario.getNombre_usuario()));
		
		// Nombre y Apellidos
		panelInfo.add(new JLabel("Nombre y Apellidos:"));
		panelInfo.add(new JLabel(usuario.getNombre() + usuario.getApellido()));
		
		// Email
		panelInfo.add(new JLabel("Email:"));
		panelInfo.add(new JLabel(usuario.getEmail()));
		
		add(panelInfo, BorderLayout.CENTER);
		
		
		
	}

}
