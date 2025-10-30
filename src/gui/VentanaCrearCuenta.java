package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import domain.Usuario;

public class VentanaCrearCuenta extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField txt_nombre;
	private JTextField txt_apellido;
	private JTextField txt_nombre_usuario;
	private JTextField txt_email;
	private JPasswordField txt_contraseña;
	private JButton iniciar_Sesion;
	
	public VentanaCrearCuenta (List<Usuario> usuarios) {
		//Configuración de la ventana principal
		this.setTitle("Crear Cuenta");
		this.setSize(500, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//Colores
		Color fondoPrincipal = new Color(40, 40, 40);
        Color fondoCampo = new Color(220, 220, 220);
        Color colorTexto = Color.BLACK;
        Color titulo = Color.WHITE;
        Color botonColor = new Color(0, 120, 215);
        
        //Bordes
        Border bordes = BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(Color.GRAY, 1, true),
        		BorderFactory.createEmptyBorder(5,10,5,10)
        );
        	
        

		//Panel principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBackground(fondoPrincipal);
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
	
		//Labels y Jtextfields
		JLabel crear_cuenta = new JLabel("Crear Cuenta");
		crear_cuenta.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
		crear_cuenta.setAlignmentX(CENTER_ALIGNMENT);
		crear_cuenta.setForeground(titulo);
		
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		nombre.setAlignmentX(LEFT_ALIGNMENT);
		txt_nombre = new JTextField(10);
		txt_nombre.setBackground(fondoCampo);
		txt_nombre.setForeground(colorTexto);
		txt_nombre.setBorder(bordes);
		txt_nombre.setMaximumSize(new Dimension(300, 30));
		
		
		JLabel apellido = new JLabel("Apellido");
		apellido.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		apellido.setAlignmentX(LEFT_ALIGNMENT);
		txt_apellido = new JTextField(10);
		txt_apellido.setBackground(fondoCampo);
		txt_apellido.setForeground(colorTexto);
		txt_apellido.setBorder(bordes);
		txt_apellido.setMaximumSize(new Dimension(300, 30));
		
		JLabel nombre_usuario = new JLabel("Nombre Usuario");
		nombre_usuario.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		nombre_usuario.setAlignmentX(LEFT_ALIGNMENT);
		txt_nombre_usuario = new JTextField(10);
		txt_nombre_usuario.setBackground(fondoCampo);
		txt_nombre_usuario.setForeground(colorTexto);
		txt_nombre_usuario.setBorder(bordes);
		txt_nombre_usuario.setMaximumSize(new Dimension(300, 30));
		
		
		JLabel email = new JLabel("Email");
		email.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		email.setAlignmentX(LEFT_ALIGNMENT);
		txt_email = new JTextField(10);
		txt_email.setBackground(fondoCampo);
		txt_email.setForeground(colorTexto);
		txt_email.setMaximumSize(new Dimension(300, 30));
		
		
		
		JLabel contraseña = new JLabel("Contraseña");
		contraseña.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		contraseña.setAlignmentX(LEFT_ALIGNMENT);
		txt_contraseña = new JPasswordField(10);
		txt_contraseña.setBackground(fondoCampo);
		txt_contraseña.setForeground(colorTexto);
		txt_contraseña.setBorder(bordes);
		txt_contraseña.setMaximumSize(new Dimension(300, 30));
		
		iniciar_Sesion = new JButton("Iniciar Sesión");
		iniciar_Sesion.setMaximumSize(new Dimension(150, 35));
		iniciar_Sesion.setAlignmentX(CENTER_ALIGNMENT);
		iniciar_Sesion.setBackground(botonColor);
		iniciar_Sesion.setFocusPainted(false);
		iniciar_Sesion.setForeground(Color.WHITE);
		
		iniciar_Sesion.addActionListener(e -> {
			this.setVisible(false);
			VentanaLogin vl = new VentanaLogin(usuarios);
			vl.setVisible(true);
		});
		
		
		
		
		
		
		
		panelPrincipal.add(crear_cuenta);
		panelPrincipal.add(Box.createVerticalStrut(25));
		panelPrincipal.add(nombre);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(txt_nombre);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(apellido);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(txt_apellido);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(nombre_usuario);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(txt_nombre_usuario);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(email);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(txt_email);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(contraseña);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(txt_contraseña);
		panelPrincipal.add(Box.createVerticalStrut(25));
		panelPrincipal.add(iniciar_Sesion);
		
		
		this.add(panelPrincipal);
		
		

		
		
	}
	

}
