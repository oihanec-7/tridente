package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import data.GestorDatos;
import domain.Contenido;
import domain.Usuario;

public class VentanaContenido extends JFrame{
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Contenido contenido;
	
	public VentanaContenido(Contenido contenido, Usuario usuario) {
		this.usuario = usuario;
		this.contenido = contenido;
		
		
		setTitle(contenido.getTitulo());
	    setSize(500, 300);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(new BorderLayout(15, 15));	
		
	   
		//Panel principal
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(new Color(50, 70, 120));
        
		//Panel izquierdo (Foto portada y puntuacion)
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBackground(new Color(50, 70, 120));
        panelIzquierdo.setBorder(new EmptyBorder(20, 20, 20, 20)); 
        
        //Portada
        ImageIcon portada = new ImageIcon(contenido.getRutaPortada()); 	
		Image imagenAjustada = portada.getImage().getScaledInstance(120, 170, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenAjustada);
        JLabel fotoPortada = new JLabel(iconoEscalado);
        fotoPortada.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelIzquierdo.add(fotoPortada);
		
		panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 20)));
        
			//Puntuacion
        JLabel labelPuntuacion = new JLabel("Puntuacion: " + String.format("%.1f", contenido.getPuntuacionMedia()));
        labelPuntuacion.setFont(new Font("Arial", Font.BOLD, 14));
        labelPuntuacion.setForeground(Color.white);
        labelPuntuacion.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelIzquierdo.add(labelPuntuacion);
        
		panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);


        //Panel de la derecha
        JPanel panelDerecha = new JPanel();
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.Y_AXIS));
        panelDerecha.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
        panelDerecha.setBackground(new Color(50, 70, 120));

        
        JLabel titulo = new JLabel(contenido.getTitulo());
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setAlignmentX(LEFT_ALIGNMENT);
        titulo.setForeground(Color.WHITE);
        panelDerecha.add(titulo);
        
		panelDerecha.add(Box.createRigidArea(new Dimension(0, 20)));
        JLabel labelGenero = new JLabel("Género: ");
        ArrayList<String> generos = contenido.getGenero();
        for (int i = 0; i < generos.size(); i++) {
            labelGenero.setText(labelGenero.getText() + generos.get(i));
            if (i != generos.size() - 1) {
                labelGenero.setText(labelGenero.getText() + ", ");
            }
        }
        labelGenero.setFont(new Font("Arial", Font.PLAIN, 16));
        labelGenero.setForeground(Color.white);
        labelGenero.setAlignmentX(LEFT_ALIGNMENT);
        panelDerecha.add(labelGenero);
        
        JLabel labelActores = new JLabel("Cast: ");
        ArrayList<String> actores = contenido.getCast();
        for (int i = 0; i < actores.size(); i++) {
        	labelActores.setText(labelActores.getText() + actores.get(i));
            if (i != actores.size() - 1) {
            	labelActores.setText(labelActores.getText() + ", ");
            }
        }
        labelActores.setFont(new Font("Arial", Font.PLAIN, 16));
        labelActores.setAlignmentX(LEFT_ALIGNMENT);
        labelActores.setForeground(Color.WHITE);
        panelDerecha.add(labelActores);

        
        panelDerecha.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        boolean enMilista = usuario.getMiLista().contains(contenido);
        JButton botonLista;
        if(enMilista) {
        	botonLista = new JButton("Quitar de mi Lista");
        	botonLista.setBackground(new Color(200, 50, 50));
        	ImageIcon basura = new ImageIcon("images/quitar.png");
        	Image escalarBasura = basura.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        	botonLista.setIcon(new ImageIcon(escalarBasura));

        	botonLista.addActionListener(e -> {
	          	int confirm = JOptionPane.showConfirmDialog(
	                    this,
	                    "¿Estás seguro de que quieres quitar " + contenido.getTitulo() + " de tu lista?",
	                    "Confirmar",
	                    JOptionPane.YES_NO_OPTION,
	                    JOptionPane.WARNING_MESSAGE
	            );
	          	if(confirm == JOptionPane.YES_OPTION) {
	          		usuario.getMiLista().contains(contenido);
	          		if (usuario.getMiLista() != null) {
		          		usuario.getVentanaMiLista().actualizarMiLista();
					}
		          		
	          		JOptionPane.showMessageDialog(
	                        this,
	                        contenido.getTitulo() + " se ha eliminado de tu lista.",
	                        "Eliminado",
	                        JOptionPane.INFORMATION_MESSAGE
	                );
	          		this.dispose();
	          	}
	          
	          		
        	});  	
        		
        }
        else {
        	botonLista = new JButton("Añadir a mi Lista");
        	botonLista.setBackground(new Color(155, 178, 204));
        	ImageIcon cine = new ImageIcon("images/cine.png");
        	Image escalarCine = cine.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        	botonLista.setIcon(new ImageIcon(escalarCine));
    
        	
          	botonLista.addActionListener(e -> {
          		usuario.getMiLista().add(contenido);
          		JOptionPane.showMessageDialog(this,
                        contenido.getTitulo() + " se ha añadido a tu lista.",
                        "Añadido", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); 
          		
          	});
        }
        
        botonLista.setAlignmentX(LEFT_ALIGNMENT);
    	botonLista.setFont(new Font("Arial", Font.BOLD, 14));
     	botonLista.setForeground(Color.black);
    	botonLista.setFocusPainted(false);
    	botonLista.setBorder(BorderFactory.createEmptyBorder(8,15,8,15));
     	botonLista.setHorizontalAlignment(JButton.LEFT);
      	botonLista.setIconTextGap(8);
    	
        panelDerecha.add(Box.createRigidArea(new Dimension(0, 30)));
        
        panelDerecha.add(botonLista);
        panelPrincipal.add(panelDerecha, BorderLayout.CENTER);
        
        add(panelPrincipal);
        
	}
	
}


