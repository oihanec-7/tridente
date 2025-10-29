package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
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

import domain.Pelicula;
import domain.Usuario;

public class VentanaPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelMenu;
	private JButton botonMenu;
	private JTextField buscador;
	private JPanel panelCarruseles;
	private JButton agregarResena;
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
		JPanel panelSuperior = new JPanel(new BorderLayout(10,10));
		
		botonMenu = new JButton("☰");
		botonMenu.setSize(new Dimension(50, 30));
		panelSuperior.add(botonMenu, BorderLayout.WEST);
		botonMenu.addActionListener(e -> {
			panelMenu.setVisible(!panelMenu.isVisible()); 
		});
		
			// la lupa esta fea	
		buscador = new JTextField(30);
		
		JLabel imagen = new JLabel();
		JPanel panelBuscador = new JPanel(new BorderLayout(5, 5));
		ImageIcon imagenLupa = new ImageIcon("images/lupa.png");
		Image escalarImagen = imagenLupa.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imagen.setIcon(new ImageIcon(escalarImagen));
		
		
		panelBuscador.add(buscador, BorderLayout.CENTER);
		panelBuscador.add(imagen, BorderLayout.EAST);
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
		    		boton.addActionListener(e -> {
		    			this.setVisible(true);
		    			this.panelMenu.setVisible(false);
		    		});
		    		break;
		    	case "Mi Lista":
		    		boton.addActionListener(e -> {});
		    		break;
		    	case "Valoradas":
		    		boton.addActionListener(e -> {});
		    		break;
		    	case "Mi Usuario":
		    		boton.addActionListener(e -> {
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
		
		
		
		//Paneles de scrol (recomendados y mejor valorados)
		panelCarruseles = new JPanel();
		panelCarruseles.setLayout(new BoxLayout(panelCarruseles, BoxLayout.Y_AXIS));
		panelCarruseles.add(Box.createRigidArea(new Dimension(0, 70)));
		
		panelCarruseles.add(crearCarrusel("Recomendadas para ti")); 
		panelCarruseles.add(Box.createRigidArea(new Dimension(0, 20)));
		panelCarruseles.add(crearCarrusel("Mejor valoradas")); 

		JScrollPane scrollPanelCarruseles = new JScrollPane(
                panelCarruseles,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
		
		panelPrincipal.add(scrollPanelCarruseles, BorderLayout.CENTER);
		
		
		
		
		
		
		
		this.add(panelPrincipal);
	}

	private JPanel crearCarrusel(String titulo) {
		JPanel panelCarruselCompleto = new JPanel();
        panelCarruselCompleto.setLayout(new BoxLayout(panelCarruselCompleto, BoxLayout.Y_AXIS));
		
		JLabel labelRecomendadas = new JLabel(titulo);
		labelRecomendadas.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelRecomendadas.setFont(new Font("Arial", Font.BOLD, 12));
		panelCarruselCompleto.add(labelRecomendadas);
		panelCarruselCompleto.add(Box.createRigidArea(new Dimension(0, 5))); 

	
		JPanel carrusel = new JPanel();
		carrusel.setLayout(new BoxLayout(carrusel, BoxLayout.X_AXIS));
		for (int i = 0; i < 20; i++) {
		    JButton boton = new JButton("Peli " + (i + 1));
		    boton.setPreferredSize(new Dimension(120, 160));
		    boton.setMaximumSize(new Dimension(120, 160));
		    boton.setMinimumSize(new Dimension(120, 160));
		    boton.addActionListener(e -> mostrarInformacion());
		    carrusel.add(boton);
		    carrusel.add(Box.createRigidArea(new Dimension(10, 0)));
		}
		
		carrusel.setPreferredSize(new Dimension(1300, 160));
		carrusel.setMaximumSize(new Dimension(1300, 160));
		
		
		JScrollPane scrollCarrusel = new JScrollPane(carrusel,
		        JScrollPane.VERTICAL_SCROLLBAR_NEVER,
		        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollCarrusel.setPreferredSize(new Dimension(600, 160));
		scrollCarrusel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));
		scrollCarrusel.setBorder(null);
        scrollCarrusel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelCarruselCompleto.add(scrollCarrusel);
		return panelCarruselCompleto;
	}





	private void mostrarInformacion() {
	    VentanaContenido ventanaPelicula = new VentanaContenido(new Pelicula("titulo", null, null, null, 120));
	    ventanaPelicula.setVisible(true);
	    
	}







	
	
	
}
