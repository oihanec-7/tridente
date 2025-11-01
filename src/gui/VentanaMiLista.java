package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Contenido;
import domain.Usuario;

public class VentanaMiLista extends JFrame{
	private static final long serialVersionUID = 1L;
	private ArrayList<Contenido> miLista;
	private Usuario usuario;
	private JButton botonmenu;
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel panelMenu;
	private JTextField buscador;
	
	public VentanaMiLista(Usuario usuario) {
		this.usuario = usuario;
		this.miLista = usuario.getListaValoradas();
		
		this.setTitle("Mi Lista");
		this.setSize(1300, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		inicializarVentana();
		
	}
	
	private void inicializarVentana() {
		panelPrincipal.setBackground(new Color(217, 108, 70));
		panelPrincipal.setOpaque(true);
		
		//Mantener el boton de menu y el menu desplegable y añadir la barra de buscar arriba
		//Panel superior (barra buscar + boton menu)
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
		panelSuperior.setBackground(new Color(217, 108, 70));
		
		botonmenu = new JButton("☰");
		botonmenu.setSize(new Dimension(50,30));
		botonmenu.setBackground(new Color(140, 60, 85));
		botonmenu.setForeground(Color.WHITE);
		botonmenu.setFocusPainted(false); 
		panelSuperior.add(botonmenu);
		botonmenu.addActionListener(e -> {
			panelMenu.setVisible(!panelMenu.isVisible());
		});
		
		//lupa
		buscador = new JTextField(30);
		panelSuperior.add(buscador);
		
		ImageIcon imagenLupa = new ImageIcon("images/lupa.png");
		Image escalarImagen = imagenLupa.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconoEscalado = new ImageIcon(escalarImagen);
		JLabel etiquetaLupa = new JLabel(iconoEscalado);
		panelSuperior.add(etiquetaLupa);
		
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		
		//Panel desplegable del menu
		panelMenu = new JPanel();
		panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
		panelMenu.setPreferredSize(new Dimension(150, 0));
		panelMenu.setMaximumSize(new Dimension(150, Integer.MAX_VALUE));
		panelMenu.setBackground(new Color(242, 201, 185));
		
		String[] etiquetaBotones = {"Inicio", "Catalogo", "Mi Lista", "Valoradas", "Mi Usuario"};
		for(String etiqueta: etiquetaBotones) {
			JButton boton = new JButton(etiqueta);
			boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
			
			boton.setFocusPainted(false);
			boton.setBorderPainted(false);
			boton.setContentAreaFilled(false);
			boton.setOpaque(false);
			boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			//Asignación de action listeners
			switch (etiqueta) {
				case "Inicio": 
					boton.addActionListener(e -> {
						this.setVisible(false);
						VentanaPrincipal vp = new VentanaPrincipal(usuario);
						vp.setVisible(true);	
					});
					break;
				case "Catalogo":
					boton.addActionListener(e -> {
						this.setVisible(false);
						VentanaCatalogo vc = new VentanaCatalogo(usuario);
						vc.setVisible(true);
					});
					break;
				case "Mi Lista":
					boton.addActionListener(e -> {
						this.setVisible(true);
						this.panelMenu.setVisible(true);
						
					});
					break;
				case "Valoradas":
					boton.addActionListener(e -> {});
					break;
				case "Mi Usuario":
					boton.addActionListener(e -> {
						this.setVisible(false);
						VentanaMiUsuario vu = new VentanaMiUsuario(usuario);
						vu.setVisible(true);
					});
					break;
			
			}
			panelMenu.add(boton);
		}
		panelMenu.setVisible(false);
		panelPrincipal.add(panelMenu, BorderLayout.WEST);
		
		//Añadir las pelis y series que esten en la lista de Usuario
		JPanel panelPortadas = anadirContenidos(this.miLista);
		
		
	

	}
	
	private JPanel anadirContenidos(ArrayList<Contenido> miLista) {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		panelPrincipal.setBackground(new Color(217, 108, 70));
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));
		
		
		return panelPrincipal;
		
		
	}
}
