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
       
        
        // Botón añadir a mi Lista
        JButton añadirLista = new JButton("Añadir a mi Lista");
        añadirLista.setAlignmentX(LEFT_ALIGNMENT);
        añadirLista.setBackground(new Color(155, 178, 204));
        añadirLista.setFont(new Font("Arial", Font.BOLD, 14));
        añadirLista.setForeground(Color.white);
        añadirLista.setFocusPainted(false);
        añadirLista.setBorder(BorderFactory.createEmptyBorder(8,15,8,15));
        
        panelDerecha.add(Box.createRigidArea(new Dimension(0, 30)));
        
        ImageIcon iconoCine = new ImageIcon("images/cine.png");
        Image escalarImagen = iconoCine.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        añadirLista.setIcon(new ImageIcon(escalarImagen));
        añadirLista.setHorizontalAlignment(JButton.LEFT);
        añadirLista.setIconTextGap(8);
        
        añadirLista.addActionListener(e -> {
        	if(usuario != null && contenido != null) {
        		if(!usuario.getMiLista().contains(contenido)) {
        			usuario.getMiLista().add(contenido);
        			JOptionPane.showMessageDialog(this,
                            contenido.getTitulo() + " se ha añadido a tu lista.",
                            "Añadido", JOptionPane.INFORMATION_MESSAGE);
        		}else {
                    JOptionPane.showMessageDialog(this,
                            "Ya tienes " + contenido.getTitulo() + " en tu lista.",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                }
        	}
        });
        
        panelDerecha.add(añadirLista);
        panelPrincipal.add(panelDerecha, BorderLayout.CENTER);
        
        add(panelPrincipal);
        
	}
	
}


