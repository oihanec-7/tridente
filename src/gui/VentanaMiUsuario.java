package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import domain.Usuario;

public class VentanaMiUsuario extends JFrame{
	
	public VentanaMiUsuario(Usuario usuario) {
		
		setTitle("Perfil de Usuario:" + usuario.getNombre_usuario());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,300);
		setLayout(new BorderLayout(15,15));
		setLocationRelativeTo(null);
		
		// Panel principal 
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		// Panel izquierdo - Foto perfil
		JLabel fotoPerfil = new JLabel();
		//ImageIcon icono = ImageIcon(""); // Poner la foto
		//Image imagenEscalada = icono.getImage().getScaledInstance(120,120, Image.SCALE_SMOOTH);
		fotoPerfil.setIcon(new ImageIcon("icono_perfil.jpg"));
		fotoPerfil.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panelPrincipal.add(fotoPerfil, BorderLayout.WEST);
		
		// Panel derecho - Datos usuario
		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		panelDatos.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
		
		// Nombre de Usuario (en grande)
        JLabel labelNombreUsuario = new JLabel(usuario.getNombre_usuario());
        labelNombreUsuario.setFont(new Font("Arial", Font.BOLD, 20)); 

        // Nombre y Apellidos
        JLabel labelNombreCompleto = new JLabel(usuario.getNombre() + " " + usuario.getApellido());
        JLabel labelEmail = new JLabel(usuario.getEmail());
        JLabel labelResenas = new JLabel("Reseñas:" + usuario.getNum_resenas());
            
        
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnCerrarSesion.setBackground(new Color(220, 50, 50));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 13));
        btnCerrarSesion.addActionListener(e -> System.exit(0));
        
        panelDatos.add(labelNombreUsuario);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(labelNombreCompleto);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(labelEmail);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(labelResenas);
        panelDatos.add(Box.createVerticalGlue());
        panelDatos.add(btnCerrarSesion);
        
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        add(panelPrincipal);
        setVisible(true);
		
	
    }
}


