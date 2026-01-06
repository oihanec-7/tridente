package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

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
	
	private List<Usuario> usuarios;
	
	public VentanaMiUsuario(Usuario usuario) {
		this.usuarios = usuarios;
		
		setTitle("Perfil de Usuario:" + usuario.getNombre_usuario());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,300);
		setLayout(new BorderLayout(15,15));
		setLocationRelativeTo(null);
		 
		// Panel principal 
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(new Color(155, 178, 204));
		
		// Panel izquierdo - Foto perfil
		ImageIcon icon = new ImageIcon("images/cast.png");
		JLabel fotoPerfil = new JLabel();
		
		
		Image escalarImagen = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		fotoPerfil.setIcon(new ImageIcon(escalarImagen));
		fotoPerfil.setHorizontalAlignment(JLabel.CENTER);
		fotoPerfil.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	
		
		//El logo estará situado en el panel izquierdo
		JPanel panelIzquierdo = new JPanel(new BorderLayout());
		//panelIzquierdo.setPreferredSize(new Dimension(350,400));
		panelIzquierdo.add(fotoPerfil, BorderLayout.CENTER);
		panelIzquierdo.setBackground(new Color(243, 200, 207));
		panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
		
		// Panel derecho - Datos usuario
		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		panelDatos.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
		panelDatos.setBackground(new Color(155, 178, 204));
		
		// Nombre de Usuario (en grande)
        JLabel labelNombreUsuario = new JLabel(usuario.getNombre_usuario());
        labelNombreUsuario.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20)); 

        // Nombre y Apellidos
        JLabel labelNombreCompleto = new JLabel(usuario.getNombre() + " " + usuario.getApellido());
        JLabel labelEmail = new JLabel(usuario.getEmail());
        JLabel labelResenas = new JLabel("Numero de reseñas hechas:" + " " + usuario.getListaValoradas().size());
        labelNombreCompleto.setFont(new Font("Arial", Font.PLAIN ,15));
        labelNombreCompleto.setForeground(new Color(14, 28, 59));
        labelEmail.setFont(new Font("Arial", Font.PLAIN ,15));
        labelEmail.setForeground(new Color(14, 28, 59));
        //labelResenas.setFont(new Font("Arial", Font.PLAIN ,15));
        //labelResenas.setForeground(new Color(14, 28, 59));
        
        // Boton Cerrar Sesion
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnCerrarSesion.setBackground(new Color(102, 24, 27));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 10));
        
        btnCerrarSesion.addActionListener(e -> {
        	VentanaLogin ventana = new VentanaLogin(usuarios);
        	ventana.setVisible(true);
        	dispose();
        });
        
        panelDatos.add(labelNombreUsuario);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(labelNombreCompleto);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(labelEmail);
        panelDatos.add(Box.createVerticalStrut(10));
//        panelDatos.add(labelResenas);
        panelDatos.add(Box.createVerticalGlue());
        panelDatos.add(btnCerrarSesion);
        
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        add(panelPrincipal);
        setVisible(true);
		
	
    }
}


