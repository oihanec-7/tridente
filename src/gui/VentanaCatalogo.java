package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data.GestorDatos;
import domain.Contenido;
import domain.Usuario;

public class VentanaCatalogo extends JFrame{

	private static final long serialVersionUID = 1L;
	private ArrayList<Contenido> listaContenidos;
	private Usuario usuario;
	private JButton botonMenu;
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelMenu;
	private JScrollPane scrollPortadas;
	private JTextField buscador;
	
	public VentanaCatalogo(Usuario usuario) {
		this.usuario = usuario;
		this.listaContenidos = GestorDatos.cargarCSV("src/data/contenido.csv");
		
		this.setTitle("Catalogo");
		this.setSize(1300, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		inicializarVentana();
	}

	private void inicializarVentana() {
		panelPrincipal.setBackground(new Color(155, 178, 204));
		panelPrincipal.setOpaque(true);
		
		//Mantener el boton de menu y el menu desplegable y añadir la barra de buscar arriba
			//Panel superior (barra buscar + boton menu)
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
		panelSuperior.setBackground(new Color(155, 178, 204));
		panelSuperior.setBorder(null);
		
		botonMenu = new JButton("☰");
		botonMenu.setSize(new Dimension(50, 50));
        botonMenu.setBackground(new Color(14, 28, 59));
        botonMenu.setForeground(Color.WHITE);
        botonMenu.setFocusPainted(false);
        panelSuperior.add(botonMenu);
		botonMenu.addActionListener(e -> {
				panelMenu.setVisible(!panelMenu.isVisible()); 
			});
		
				
		buscador = new JTextField(30);
		panelSuperior.add(Box.createHorizontalStrut(10));
		panelSuperior.add(buscador);
					
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
						this.setVisible(false);
						VentanaPrincipal inicio = new VentanaPrincipal(usuario);
						inicio.setVisible(true);
					});
					break;
				case "Catalogo":
					boton.addActionListener(e -> {
						this.setVisible(true);
						this.panelMenu.setVisible(false);
					});
					break;
				case "Mi Lista":
					boton.addActionListener(e -> {
						this.setVisible(false);
						VentanaMiLista vm = new VentanaMiLista(usuario);
						usuario.setVentanaMiLista(vm);
						vm.setVisible(true);
					});
					break;
				case "Valoradas":
					boton.addActionListener(e -> {
						this.setVisible(false);
		    			VentanaValoradas vv = new VentanaValoradas(usuario);
		    			vv.setVisible(true);
					});
					break;
				case "Mi Usuario":
					boton.addActionListener(e -> {
						this.setVisible(false);
						VentanaMiUsuario miUsuario = new VentanaMiUsuario(usuario);
						miUsuario.setVisible(true);
					});
					break;
			}
			panelMenu.add(boton);
		}
		panelMenu.setVisible(false);
		panelPrincipal.add(panelMenu, BorderLayout.WEST);
		
				
		//Añadir todas las pelis y series
		JPanel panelPortadas = anadirContenidos(this.listaContenidos);
		panelPortadas.setBackground(new Color(155, 178, 204));

		scrollPortadas = new JScrollPane(panelPortadas,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelPrincipal.add(scrollPortadas, BorderLayout.CENTER);
		
				
		//Listener del buscador (para filtrar por titulo)
		buscador.getDocument().addDocumentListener(new DocumentListener() {
					
			@Override
			public void removeUpdate(DocumentEvent e) { filtrar();}
			@Override
			public void insertUpdate(DocumentEvent e) {filtrar();}
			@Override
			public void changedUpdate(DocumentEvent e) {filtrar();}
				
					
			public void filtrar() {
				String texto = buscador.getText().toLowerCase();
				ArrayList<Contenido> filtradas = new ArrayList<>();
				for (Contenido c : listaContenidos) {
					if (c.getTitulo().toLowerCase().contains(texto)) {
						filtradas.add(c);
					}
				}

				JPanel panelActualizado = anadirContenidos(filtradas);
				panelActualizado.setBackground(new Color(155, 178, 204));
						
				scrollPortadas.setViewportView(panelActualizado);
				scrollPortadas.revalidate();
				scrollPortadas.repaint();	
			}
		});
						
		add(panelPrincipal);
	}
	
	private JPanel anadirContenidos(ArrayList<Contenido> contenidos) {
	    JPanel panelPrincipal = new JPanel();
	    panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
	    panelPrincipal.setBackground(new Color(155, 178, 204));
	    panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
	    panelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));

	    int botonesPorFila = 9; 
	    JPanel fila = null;
	    int contador = 0;

	    for (Contenido c : contenidos) {
	        if (contador % botonesPorFila == 0) {
	            fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); 
	            fila.setBackground(new Color(155, 178, 204));
	            panelPrincipal.add(fila);
	        }

	        // Crear botones para cada peli/serie
	        ImageIcon portadaIcon = new ImageIcon(c.getRutaPortada());
	        Image imagenEscalada = portadaIcon.getImage().getScaledInstance(120, 170, Image.SCALE_SMOOTH);
	        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
	        JButton boton = new JButton(iconoEscalado);
	        boton.setPreferredSize(new Dimension(120, 170));
	        boton.setBorderPainted(false);
	        boton.addActionListener(e -> {
	            VentanaContenido ventana = new VentanaContenido(c, usuario);
	            ventana.setVisible(true);
	        });

	        fila.add(boton);
	        contador++;
	    }

	    return panelPrincipal;
	}
}
