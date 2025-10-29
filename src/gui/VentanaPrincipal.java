package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Usuario;

public class VentanaPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelMenu;
	private JButton botonMenu;
	private JTextField buscador;
	private JPanel panelCarruseles;
	private JButton agrgarResena;
	private Usuario usuario;
	
	
	public VentanaPrincipal(Usuario usuario) throws HeadlessException {
		super();
		this.usuario = usuario;
		
		
		this.setTitle("Ventana Principal");
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		inicializarComponentes();
	}


	
	
	
	private void inicializarComponentes() {		
		
		//Panel superior (barra buscar + boton menu)
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout());
		
		botonMenu = new JButton("☰");
		botonMenu.setSize(new Dimension(50, 30));
		panelSuperior.add(botonMenu, BorderLayout.WEST);
		botonMenu.addActionListener(e -> desplegarMenu());
		
			// no sale la lupa : lupa a la derecha es un boton para buscar
		buscador = new JTextField(30);
		JPanel panelBuscador = new JPanel(new BorderLayout(8, 20));
		ImageIcon iconoLupa = new ImageIcon("/data/images/lupa.png");
		JLabel icono = new JLabel(iconoLupa);
		panelBuscador.add(buscador, BorderLayout.CENTER);
		panelBuscador.add(icono, BorderLayout.WEST);
		panelSuperior.add(panelBuscador, BorderLayout.CENTER);
		
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		
		
		// Panel (desplegable) del menu
		panelMenu = new JPanel();
		panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
		panelMenu.setPreferredSize(new Dimension(150, 0));  
		panelMenu.setMaximumSize(new Dimension(150, Integer.MAX_VALUE));
		panelMenu.setBackground(Color.LIGHT_GRAY);
		
		String[] etiquetasBotones = {"Inicio", "Mi Lista", "Valoradas", "Mi Usuario"};
		for (String etiqueta : etiquetasBotones) {
			JButton boton = new JButton(etiqueta);
		    boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		    
			boton.setFocusPainted(false);
		    boton.setBorderPainted(false);
		    boton.setContentAreaFilled(false);
		    boton.setOpaque(false);
		    boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    
		    //Asignacion de Action Listeners
		    switch (etiqueta) {
		    	case "Inicio":
		    		boton.addActionListener(e -> abrirVentanaInicio());
		    		break;
		    	case "Mi Lista":
		    		boton.addActionListener(e -> abrirVentanaMiLista());
		    		break;
		    	case "Valoradas":
		    		boton.addActionListener(e -> abrirVentanaValoradas());
		    		break;
		    	case "Mi Usuario":
		    		boton.addActionListener(e -> abrirVentanaMiUsuario());
		    		break;
		    }
		    panelMenu.add(boton);
		}
		panelMenu.setVisible(false);
		panelPrincipal.add(panelMenu, BorderLayout.WEST);
		
		
		//Paneles de scrol (recomendados y mejor valorados)
		
		
		
		
		this.add(panelPrincipal);
	}


	private void abrirVentanaMiUsuario() {
		VentanaMiUsuario miUsuario = new VentanaMiUsuario(usuario);
	    miUsuario.setVisible(true);
	}





	private Object abrirVentanaValoradas() {
		// TODO Auto-generated method stub
		return null;
	}





	private Object abrirVentanaMiLista() {
		// TODO Auto-generated method stub
		return null;
	}





	private void abrirVentanaInicio() {
		this.setVisible(true);
		this.panelMenu.setVisible(false);
	}





	private void desplegarMenu() {
		panelMenu.setVisible(!panelMenu.isVisible()); 
	}
	
	
	
}
