package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout; 
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

import domain.Usuario;

public class VentanaLogin extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JTextField userTxt;
	private JPasswordField passwordTxt;
	private JButton singIn;
	private JButton crearCuenta;
	private List<Usuario> usuarios;
	
	private void addPlaceholderBehavior(JTextField field, String placeholder) {
	    field.setText(placeholder);
	    field.setForeground(Color.GRAY);

	    field.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (field.getText().equals(placeholder)) {
	                field.setText("");
	                field.setForeground(Color.BLACK);
	                
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (field.getText().isEmpty()) {
	                field.setText(placeholder);
	                field.setForeground(Color.GRAY);
	            }
	        }
	    });
	}
	
	
	public VentanaLogin(List<Usuario> usuarios) {
		this.usuarios = usuarios;
		
		//Configuración de la ventana
		this.setTitle("Tridente - Login");
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Colores
		Color fondoPrincipal = new Color(14, 28, 59);
		Color fondoCampos = new Color(255, 255, 255);
		Color botonColor = new Color(102, 24, 27);
		Color textoCampos = Color.BLACK;
			
				
		//Creación del panel principal que posteriormente se divide en otros dos paneles
		JPanel panelPrincipal = new JPanel(new GridLayout(1,2));
		panelPrincipal.setBackground(fondoPrincipal);
		
		//Creación y ajustes de la imagen(logo)
		JLabel imagen = new JLabel();
		
		ImageIcon logo = new ImageIcon("resources/images/logo.png");
		Image escalarImagen = logo.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		imagen.setIcon(new ImageIcon(escalarImagen));
		imagen.setHorizontalAlignment(JLabel.CENTER);
		imagen.setVerticalAlignment(JLabel.CENTER);
		
		//El logo estará situado en el panel izquierdo
		JPanel panelIzquierdo = new JPanel(new BorderLayout());
		panelIzquierdo.setPreferredSize(new Dimension(350,400));
		panelIzquierdo.add(imagen, BorderLayout.CENTER);
		panelIzquierdo.setBackground(fondoPrincipal);
		
		//Panel derecho con inicio de sesión
		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
		panelDerecho.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		panelDerecho.setBackground(fondoPrincipal);
		
		//Panel para centrar formulario
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		formPanel.setBackground(fondoPrincipal);
		
		//Labels y Jtextfields(campos)
		JLabel labelusuario = new JLabel("Usuario");
		labelusuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelusuario.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		labelusuario.setForeground(Color.WHITE);
		
		
		userTxt = new JTextField();
		userTxt.setMaximumSize(new Dimension(400, 40));
		userTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		userTxt.setBackground(fondoCampos);
		userTxt.setForeground(textoCampos);
		addPlaceholderBehavior(userTxt, "Usuario");
		
		
		JLabel labelPassWord = new JLabel("Contraseña");
		labelPassWord.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelPassWord.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		labelPassWord.setForeground(Color.WHITE);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setMaximumSize(new Dimension(400, 45));
		passwordTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordTxt.setBackground(fondoCampos);
		passwordTxt.setForeground(textoCampos);
		addPlaceholderBehavior(passwordTxt, "Password");
		
		//evento de teclado, si le damos a enter y la contraseña es correcta 
		//entramos en la ventana princiapl
		passwordTxt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					verificarUsuario();
				}
				
				
			}
		});
		
		singIn = new JButton("Sing In");
		singIn.setMaximumSize(new Dimension(150, 35));
		singIn.setAlignmentX(Component.CENTER_ALIGNMENT);
		singIn.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
		singIn.setBackground(botonColor);
		singIn.setForeground(Color.WHITE);
		singIn.setFocusPainted(false);
		singIn.setOpaque(true); // asegura que se pinte el fondo
		
		// cambia el cursor cuando pase sobre el botón
		singIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		//cambia el color del boton cuando el cursor pasa por encima
		//singIn.addMouseListener(new java.awt.event.MouseAdapter() {
			//public void mouseEntered(java.awt.event.MouseEvent evt) {
				//singIn.setBackground(new Color(0, 100, 200));
			//}
			//public void mouseExited(java.awt.event.MouseEvent evt) {
				//singIn.setBackground(new Color(0, 120, 215));
			//}
			
		//});
		
		singIn.addActionListener(e -> verificarUsuario());
		
		//boton crear cuenta
		crearCuenta = new JButton("Crear Cuenta");
		crearCuenta.setMaximumSize(new Dimension(150, 35));
		crearCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
		crearCuenta.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
		crearCuenta.setBackground(botonColor);
		crearCuenta.setForeground(Color.WHITE);
		crearCuenta.setFocusPainted(false);
		crearCuenta.setOpaque(true);
		
		// cambia el cursor cuando pase sobre el boton
		crearCuenta.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
		
		//cambia el color del boton cuando el cursor pasa por encima
		//crearCuenta.addMouseListener(new java.awt.event.MouseAdapter(){
			//public void mouseEntered(java.awt.event.MouseAdapter evt) {
				//crearCuenta.setBackground(new Color(0, 100, 200));
			//}
			//public void mouseExited(java.awt.event.MouseAdapter evt) {
				//crearCuenta.setBackground(new Color(0, 120, 215));
			//}
			
		//});
		
		crearCuenta.addActionListener(e -> {
			this.setVisible(false);
			VentanaCrearCuenta ventana_cuenta = new VentanaCrearCuenta(usuarios);
			ventana_cuenta.setVisible(true);
		});
		
		//Cambia el borde del campo de texto cuando recibe o pierde foco.
		//Campo activo: al enfocar se resalta en azul
		//al perde el foco vuelve a gris
	
		
		
		formPanel.add(labelusuario);
		formPanel.add(Box.createVerticalStrut(10));
		formPanel.add(userTxt);
		formPanel.add(Box.createVerticalStrut(15));
		formPanel.add(labelPassWord);
		formPanel.add(Box.createVerticalStrut(15));
		formPanel.add(passwordTxt);
		formPanel.add(Box.createVerticalStrut(20));
		formPanel.add(singIn);
		formPanel.add(Box.createVerticalStrut(15));
		formPanel.add(crearCuenta);
		
		panelDerecho.add(Box.createVerticalGlue());
		panelDerecho.add(formPanel);
		panelDerecho.add(Box.createVerticalGlue());
	
		
		panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
		panelPrincipal.add(panelDerecho, BorderLayout.CENTER);
		
		
		this.add(panelPrincipal);
		
	} 
	
	private void verificarUsuario() {
		String textoUsuario = userTxt.getText();
		String textoPassword = new String(passwordTxt.getPassword());
		
		for(Usuario u: usuarios) {
			if(textoUsuario.equals(u.getNombre_usuario()) && textoPassword.equals(u.getContraseña())) {
				this.setVisible(false);
				
				// ventana de carga 
				VentanaLoading loading = new VentanaLoading();
				loading.setVisible(true);
				
				SwingWorker<Void, Void> worker = new SwingWorker<>() {
		            private VentanaPrincipal principal;

		            @Override
		            protected Void doInBackground() throws Exception {
		                principal = new VentanaPrincipal(u);
		                return null;
		            }

		            @Override
		            protected void done() {
		                loading.dispose();  
		                principal.setVisible(true);  
		            }
		        };

		        worker.execute();
				return;
			}
		}
		
		//Si el usuario y la contraseña son incorrectos:
		JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", 
														"Error de login", JOptionPane.ERROR_MESSAGE);
		userTxt.setText("");
		passwordTxt.setText("");
		userTxt.requestFocus(); //pone el cursor en el campo user text automaticamente
		
	}
	
}


