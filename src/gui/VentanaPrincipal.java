package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import data.GestorDatos;
import domain.Contenido;
import domain.Pelicula;
import domain.Usuario;

public class VentanaPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelMenu;
	private JButton botonMenu;
	private JPanel panelCarruseles;
	private JButton botonResena;
	private Usuario usuario;
	private ArrayList<Contenido> listaContenidos;
	private ArrayList<Contenido> listaMejorValoradas;
	private ArrayList<Contenido> listaPeliculas;
	private ArrayList<Contenido> listaSeries;
	
	public VentanaPrincipal(Usuario usuario) throws HeadlessException {
		super();
		this.usuario = usuario;
		this.listaContenidos = GestorDatos.cargarCSV("src/data/contenido.csv");
		this.listaMejorValoradas = GestorDatos.mejorValoradas(listaContenidos);
		this.listaPeliculas = GestorDatos.soloPeliculas(listaContenidos);
		this.listaSeries = GestorDatos.soloSeries(listaContenidos);
		
		this.setTitle("Ventana Principal");
		this.setSize(1300, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
		
		panelPrincipal.setBackground(new Color(155, 178, 240));
		panelPrincipal.setOpaque(true);
		
		inicializarComponentes();
	}


	private void inicializarComponentes() {		
		//Panel superior para el boton del menu
		JPanel panelSuperior = new JPanel(new BorderLayout());
		
		botonMenu = new JButton("☰");
		botonMenu.setSize(new Dimension(50, 50));
		botonMenu.setBackground(new Color(14, 28, 59));
		botonMenu.setForeground(Color.WHITE);
		botonMenu.setFocusPainted(false);    
		panelSuperior.add(botonMenu, BorderLayout.WEST);
		botonMenu.addActionListener(e -> {
			panelMenu.setVisible(!panelMenu.isVisible()); 
		});
		panelSuperior.setBackground(new Color(155, 178, 204));
		panelSuperior.setBorder(null);
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		
		
		
		// Panel (desplegable) del menu
		panelMenu = new JPanel();
		panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
		panelMenu.setPreferredSize(new Dimension(150, 0));  
		panelMenu.setMaximumSize(new Dimension(150, Integer.MAX_VALUE));
		panelMenu.setBackground(new Color(243, 200, 207));
		
		String[] etiquetasBotones = {"Inicio", "Catalogo", "Mi Lista", "Valoradas", "Mi Usuario"};
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
		    		boton.addActionListener(e -> {
		    			this.setVisible(true);
		    			this.panelMenu.setVisible(false);
		    		});
		    		break;
		    	case "Catalogo":
		    		boton.addActionListener(e -> {
		    			this.setVisible(false);
		    			VentanaCatalogo catalogo = new VentanaCatalogo(usuario);
		    		    catalogo.setVisible(true);
		    		});
		    		break;
		    	case "Mi Lista":
		    		boton.addActionListener(e -> {
		    			this.setVisible(false);
		    			VentanaMiLista vm = new VentanaMiLista(usuario);
		    			vm.setVisible(true);
		    		});
		    		break;
		    	case "Valoradas":
		    		boton.addActionListener(e -> {});
		    		break;
		    	case "Mi Usuario":
		    		boton.addActionListener(e -> {
		    			this.setVisible(true);
		    			VentanaMiUsuario miUsuario = new VentanaMiUsuario(usuario);
		    		    miUsuario.setVisible(true);
		    		}
		    				);
		    		break;
		    }
		    panelMenu.add(boton);
		}
		panelMenu.setVisible(false);
		panelPrincipal.add(panelMenu, BorderLayout.WEST);
		
		
		
		//Paneles de scroll (mas el nombre de la app y el boton "Añadir reseña")
		panelCarruseles = new JPanel();
		panelCarruseles.setLayout(new BoxLayout(panelCarruseles, BoxLayout.Y_AXIS));
		panelCarruseles.add(Box.createRigidArea(new Dimension(0, 30)));

			//Panel para el titulo y el boton
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.X_AXIS));
		panelTitulo.setBackground(new Color(102, 24, 27));
		panelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT); 
		panelTitulo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

		JLabel nombreApp = new JLabel("TRIDENTE");
		nombreApp.setFont(new Font("Arial Black", Font.BOLD, 36));
		nombreApp.setForeground(Color.WHITE);
		panelTitulo.add(nombreApp);

		panelTitulo.add(Box.createHorizontalGlue());

		botonResena = new JButton("+");
		botonResena.setPreferredSize(new Dimension(60, 60));
		botonResena.setMaximumSize(new Dimension(60, 60));
		botonResena.setFont(new Font("Arial", Font.BOLD, 24));
		botonResena.setForeground(Color.WHITE);
		botonResena.setBackground(new Color(14, 28, 59));
		botonResena.setOpaque(true);
		panelTitulo.add(botonResena);
		botonResena.addActionListener(e -> {
			VentanaAgregarResena vr = new VentanaAgregarResena(usuario, listaContenidos);
			vr.setVisible(true);
		});
		
		panelCarruseles.add(panelTitulo);
//		panelCarruseles.add(Box.createRigidArea(new Dimension(0, 30))); 
		
		// Añadimos los scrolls al panelCarruseles
		panelCarruseles.add(Box.createRigidArea(new Dimension(0, 70)));
		
		panelCarruseles.add(crearCarrusel("Recomendadas para ti", this.listaContenidos)); 
		panelCarruseles.add(Box.createRigidArea(new Dimension(0, 20)));
		panelCarruseles.add(crearCarrusel("Mejor valoradas", this.listaMejorValoradas)); 
		panelCarruseles.add(Box.createRigidArea(new Dimension(0, 20)));
		panelCarruseles.add(crearCarrusel("Peliculas", this.listaPeliculas));
		panelCarruseles.add(Box.createRigidArea(new Dimension(0, 20)));
		panelCarruseles.add(crearCarrusel("Series", this.listaSeries));
		panelCarruseles.add(Box.createRigidArea(new Dimension(0, 20)));
		
		panelCarruseles.setBackground(new Color(155, 178, 204));
		
		JScrollPane scrollPanelCarruseles = new JScrollPane(
                panelCarruseles,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
		scrollPanelCarruseles.setBorder(null);
		panelPrincipal.add(scrollPanelCarruseles, BorderLayout.CENTER);
		this.add(panelPrincipal);
	}

	
	
	
	private JPanel crearCarrusel(String titulo, ArrayList<Contenido> lista) {
		//Panel que contiene el titulo y el carrusel
		 JPanel panelCarruselCompleto = new JPanel();
		 panelCarruselCompleto.setLayout(new BoxLayout(panelCarruselCompleto, BoxLayout.Y_AXIS));
		 panelCarruselCompleto.setBackground(new Color(155, 178, 204));
		 
		 //Establecer el titulo del carrusel
		 JLabel labelTitulo = new JLabel(titulo);
		 labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		 labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT); 
		 panelCarruselCompleto.add(labelTitulo);
		 panelCarruselCompleto.add(Box.createRigidArea(new Dimension(0, 5)));
		 
		 //Panel con el carrusel de pelis/series
		 JPanel panelContenido = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		 panelContenido.setBackground(new Color(155, 178, 204)); 
		 for (Contenido c : lista) {
			 ImageIcon portada = new ImageIcon(c.getRutaPortada());
			 Image imagenAjustada = portada.getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);
			 ImageIcon iconoEscalado = new ImageIcon(imagenAjustada);
		        
			 JButton boton = new JButton(iconoEscalado);
			 boton.setPreferredSize(new Dimension(120, 160));
			 boton.addActionListener(e -> new VentanaContenido(c).setVisible(true));
			 panelContenido.add(boton);
		 }
		 
		 //Añadir scroll horizontal
		 JScrollPane scrollCarrusel = new JScrollPane(
				 panelContenido,
				 JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
				 );
		 scrollCarrusel.setPreferredSize(new Dimension(1200, 180));
		 scrollCarrusel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
		 scrollCarrusel.setBorder(null);
		 scrollCarrusel.setAlignmentX(Component.LEFT_ALIGNMENT);

		 panelCarruselCompleto.add(scrollCarrusel);

		 return panelCarruselCompleto;
		 }	
	
}