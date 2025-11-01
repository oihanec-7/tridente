package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Contenido;
import domain.Usuario;

public class VentanaMiLista extends JFrame{
	private static final long serialVersionUID = 1L;
	private ArrayList<Contenido> miLista;
	private Usuario usuario;
	private JButton botonmenu;
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
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(new Color(217, 108, 70));
		panelPrincipal.setOpaque(true);
		
		//Mantener el boton de menu y el menu desplegable y añadir la barra de buscar arriba
		//Panel superior (barra buscar + boton menu)
		JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
		panelSuperior.setBackground(new Color(217, 108, 70));
		
		botonmenu = new JButton("☰");
		botonmenu.setSize(new Dimension(50,30));
		panelSuperior.add(botonmenu, BorderLayout.WEST);
		botonmenu.addActionListener(e -> {
			
		});
		
	}
	

}
