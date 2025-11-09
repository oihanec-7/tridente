package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JButton volverLogIn;
	
	public VentanaCrearCuenta (List<Usuario> usuarios) {
		//Configuración de la ventana principal
		this.setTitle("Crear Cuenta");
		this.setSize(400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//Colores
		Color fondoPrincipal = new Color(155, 178, 204);
        Color fondoCampo = new Color(220, 220, 220);
        Color colorTexto = Color.BLACK;
        Color titulo = Color.WHITE;
        Color labelColor = Color.WHITE;
        Color botonColor = new Color(14, 28, 59);
        
        //Bordes
        Border bordes = BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(Color.GRAY, 1, true),
        		BorderFactory.createEmptyBorder(5,10,5,10)
        );
        	
       
		//Panel principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBackground(fondoPrincipal);
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panelPrincipal.setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel formJPanel = new JPanel();
		formJPanel.setLayout(new BoxLayout(formJPanel, BoxLayout.Y_AXIS));
		formJPanel.setBackground(fondoPrincipal);
		formJPanel.setAlignmentX(LEFT_ALIGNMENT);
	
		//Labels y Jtextfields
		JLabel crear_cuenta = new JLabel("Crear Cuenta");
		crear_cuenta.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
		crear_cuenta.setAlignmentX(LEFT_ALIGNMENT);
		crear_cuenta.setForeground(titulo);
		
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		nombre.setAlignmentX(LEFT_ALIGNMENT);
		nombre.setForeground(labelColor);
		txt_nombre = new JTextField(10);
		txt_nombre.setBackground(fondoCampo);
		txt_nombre.setForeground(colorTexto);
		txt_nombre.setBorder(bordes);
		txt_nombre.setMaximumSize(new Dimension(300, 30));
		txt_nombre.setAlignmentX(LEFT_ALIGNMENT);
		
		
		JLabel apellido = new JLabel("Apellido");
		apellido.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		apellido.setAlignmentX(LEFT_ALIGNMENT);
		apellido.setForeground(labelColor);
		txt_apellido = new JTextField(10);
		txt_apellido.setBackground(fondoCampo);
		txt_apellido.setForeground(colorTexto);
		txt_apellido.setBorder(bordes);
		txt_apellido.setMaximumSize(new Dimension(300, 30));
		txt_apellido.setAlignmentX(LEFT_ALIGNMENT);
		
		JLabel nombre_usuario = new JLabel("Nombre Usuario");
		nombre_usuario.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		nombre_usuario.setAlignmentX(LEFT_ALIGNMENT);
		nombre_usuario.setForeground(labelColor);
		txt_nombre_usuario = new JTextField(10);
		txt_nombre_usuario.setBackground(fondoCampo);
		txt_nombre_usuario.setForeground(colorTexto);
		txt_nombre_usuario.setBorder(bordes);
		txt_nombre_usuario.setMaximumSize(new Dimension(300, 30));
		txt_nombre_usuario.setAlignmentX(LEFT_ALIGNMENT);
		
		
		JLabel email = new JLabel("Email");
		email.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		email.setAlignmentX(LEFT_ALIGNMENT);
		email.setForeground(labelColor);
		txt_email = new JTextField(10);
		txt_email.setBackground(fondoCampo);
		txt_email.setForeground(colorTexto);
		txt_email.setBorder(bordes);
		txt_email.setMaximumSize(new Dimension(300, 30));
		txt_email.setAlignmentX(LEFT_ALIGNMENT);
		
		
		
		JLabel contraseña = new JLabel("Contraseña");
		contraseña.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		contraseña.setAlignmentX(LEFT_ALIGNMENT);
		contraseña.setForeground(labelColor);
		txt_contraseña = new JPasswordField(10);
		txt_contraseña.setBackground(fondoCampo);
		txt_contraseña.setForeground(colorTexto);
		txt_contraseña.setBorder(bordes);
		txt_contraseña.setMaximumSize(new Dimension(300, 30));
		txt_contraseña.setAlignmentX(LEFT_ALIGNMENT);
		
		//boton iniciar sesion
		iniciar_Sesion = new JButton("Iniciar Sesión");
		iniciar_Sesion.setMaximumSize(new Dimension(150, 35));
		iniciar_Sesion.setAlignmentX(LEFT_ALIGNMENT);
		iniciar_Sesion.setBackground(botonColor);
		iniciar_Sesion.setFocusPainted(false);
		iniciar_Sesion.setForeground(Color.WHITE);
		iniciar_Sesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		iniciar_Sesion.addActionListener(e -> crearNuevaCuenta(usuarios));
		
		
		//boton para volver atrás
		volverLogIn = new JButton("<html><u>Ya tengo una cuenta</u></html>");
		volverLogIn.setMaximumSize(new Dimension(150, 35));
		volverLogIn.setAlignmentX(LEFT_ALIGNMENT);
		volverLogIn.setBackground(botonColor);
		volverLogIn.setForeground(Color.BLACK);
		volverLogIn.setFocusPainted(false);
		volverLogIn.setBorderPainted(false);
		volverLogIn.setContentAreaFilled(false);
		volverLogIn.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
		volverLogIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		volverLogIn.addActionListener(e -> {
			this.setVisible(false);
			VentanaLogin vl = new VentanaLogin(usuarios);
			vl.setVisible(true);
		});
		
		
	
		
		formJPanel.add(nombre);
		formJPanel.add(Box.createVerticalStrut(15));
		formJPanel.add(txt_nombre);
		formJPanel.add(Box.createVerticalStrut(15));
		formJPanel.add(apellido);
		formJPanel.add(Box.createVerticalStrut(15));
		formJPanel.add(txt_apellido);
		formJPanel.add(Box.createVerticalStrut(15));
		formJPanel.add(nombre_usuario);
		formJPanel.add(Box.createVerticalStrut(15));
		formJPanel.add(txt_nombre_usuario);
		formJPanel.add(Box.createVerticalStrut(15));
		formJPanel.add(email);
		formJPanel.add(Box.createVerticalStrut(15));
		formJPanel.add(txt_email);
		formJPanel.add(Box.createVerticalStrut(15));
		formJPanel.add(contraseña);
		formJPanel.add(Box.createVerticalStrut(15));
		formJPanel.add(txt_contraseña);
		
		
		panelPrincipal.add(crear_cuenta);
		panelPrincipal.add(Box.createVerticalStrut(15));
	
		panelPrincipal.add(formJPanel);
		panelPrincipal.add(Box.createVerticalStrut(30));
		panelPrincipal.add(iniciar_Sesion);
		panelPrincipal.add(Box.createVerticalStrut(10));
		panelPrincipal.add(volverLogIn);
		
		
		
		this.setLayout(new BorderLayout());
		this.add(panelPrincipal, BorderLayout.CENTER);
			
		
	}
	
	private void crearNuevaCuenta(List<Usuario> usuarios) {
		String nombreC = txt_nombre.getText();
		String apellidoC = txt_apellido.getText();
		String nombre_usuarioC = txt_nombre_usuario.getText();
		String emailC = txt_email.getText();
		String contraseña = new String(txt_contraseña.getPassword());
		
		if(nombreC.isEmpty() || apellidoC.isEmpty() || nombre_usuarioC.isEmpty() || 
				emailC.isEmpty() || contraseña.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor completa todos los campos", "Campos vacíos", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for(Usuario u: usuarios) {
			if(emailC.equals(u.getEmail())) {
				JOptionPane.showMessageDialog(this, "Este email ya está siendo utilizado", "Error Crear Cuenta", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		Usuario usuario = new Usuario(nombreC, nombre_usuarioC, contraseña, apellidoC, emailC);
		usuarios.add(usuario);
		
		this.setVisible(false);
		VentanaLogin vl = new VentanaLogin(usuarios);
		vl.setVisible(true);
		
		
	}
	

}
