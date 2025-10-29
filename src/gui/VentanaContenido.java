package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Contenido;
import domain.Usuario;

public class VentanaContenido extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public VentanaContenido(Contenido contenido) {
		setTitle(contenido.getTitulo());
	    setSize(600, 400);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLayout(new BorderLayout(10, 10));	
		
	   
		//Panel de la izquierda
		JPanel panelIzquierda = new JPanel();
        panelIzquierda.setLayout(new BorderLayout(5, 5));
        
        ImageIcon portada = new ImageIcon("images/usuario.png"); 	// FALTA PONER LA IMAGEN QUE CORRESPONDEE
        JLabel labelPortada = new JLabel(portada);
        labelPortada.setPreferredSize(new Dimension(150, 200)); 
        panelIzquierda.add(labelPortada, BorderLayout.NORTH);
        
        JLabel labelPuntuacion = new JLabel("Puntuacion: " + String.format("%.1f", contenido.getPuntuacionMedia()));
        labelPuntuacion.setFont(new Font("Arial", Font.BOLD, 14));
        panelIzquierda.add(labelPuntuacion, BorderLayout.CENTER);
        
        panelIzquierda.setBorder(new EmptyBorder(20, 20, 20, 20)); 
        add(panelIzquierda, BorderLayout.WEST);
	
        
        //Panel de la derecha
        JPanel panelDerecha = new JPanel();
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel(contenido.getTitulo());
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setAlignmentX(LEFT_ALIGNMENT);
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
        panelDerecha.add(labelActores);

        
        panelDerecha.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(panelDerecha, BorderLayout.CENTER);
	}
	
	
	
	
	
	
}


