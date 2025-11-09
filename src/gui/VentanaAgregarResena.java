package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.GestorDatos;
import domain.Contenido;
import domain.Resena;
import domain.Usuario;

public class VentanaAgregarResena extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Usuario usuarioActual;
	private ArrayList<Contenido> contenidos;
		
	public VentanaAgregarResena(Usuario usuario, ArrayList<Contenido> contenidos) {
		this.usuarioActual = usuario;
        this.contenidos = GestorDatos.cargarCSV("src/data/contenido.csv");
		
		setTitle("Agregar reseña");
		setSize(450,350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		inicializarVentana();
	}
	
	public void inicializarVentana() {	
		// Panel Principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(155, 178, 204));
        
        // Titulo
        JLabel lblTitulo = new JLabel("Nueva Reseña");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(102, 24, 27));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
        // ComboBox con titulos de contenidos
        ArrayList<String> titulos = new ArrayList<>();
        for (Contenido c : this.contenidos) {
        	titulos.add(c.getTitulo());
        }
        JComboBox<String> comboTitulos = new JComboBox<>(titulos.toArray(new String[0]));
        comboTitulos.setPreferredSize(new Dimension(200, 25)); 
        
        JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCombo.add(new JLabel("Título:"));
        panelCombo.add(comboTitulos);
        panelCombo.setBackground(new Color(155, 178, 204));
               
        // Puntuacion con estrellas
        JPanel panelPuntuacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPuntuacion.add(new JLabel("Puntuación:"));
        panelPuntuacion.setBackground(new Color(155, 178, 204));
        
        int numEstrellas = 5;
        JButton[] estrellas = new JButton[numEstrellas];
        final int[] puntuacionSeleccionada = {0};
        
        Font fontEstrellas = new Font("Segoe UI Symbol", Font.PLAIN, 22);
        Color colorEstrella = new Color(255, 215, 0);
        Color colorVacia = new Color(200, 200, 200);
        
        for (int i = 0; i < numEstrellas; i++) {
        	int indice = i;
        	estrellas[i] = new JButton("⭐");
            estrellas[i].setFont(fontEstrellas);
            estrellas[i].setBorderPainted(false);
            estrellas[i].setFocusPainted(false);
            estrellas[i].setContentAreaFilled(false);
            estrellas[i].setForeground(colorVacia);
            
            estrellas[i].addMouseListener(new java.awt.event.MouseAdapter() {
            	public void mouseEntered (java.awt.event.MouseEvent e) {
            		for (int j = 0; j <= indice; j++) {
            			estrellas[j].setText("★");
            			estrellas[j].setForeground(colorEstrella);
            		}
            		for (int j = indice + 1; j < numEstrellas; j++) {
            			estrellas[j].setText("☆");
            			estrellas[j].setForeground(colorVacia);
            		}
            	}
            	
            	public void mouseExited(java.awt.event.MouseEvent e) {
            		for(int j = 0; j < numEstrellas; j++) {
            			if (j < puntuacionSeleccionada[0]) {
            				estrellas[j].setText("★");
            				 estrellas[j].setForeground(colorEstrella);
                        } else {
                            estrellas[j].setText("☆");
                            estrellas[j].setForeground(colorVacia);
            			}
            		}
            	}
            });
            
            estrellas[i].addActionListener(e -> {
            	puntuacionSeleccionada[0] = indice + 1;
            	for (int j = 0; j < numEstrellas; j++) {
            		 if (j < puntuacionSeleccionada[0]) {
                         estrellas[j].setText("★");
                         estrellas[j].setForeground(colorEstrella);
                     } else {
                         estrellas[j].setText("☆");
                         estrellas[j].setForeground(colorVacia);
                     }
            	}
            });
            
            panelPuntuacion.add(estrellas[i]);
        }
        
        // Botones inferiores
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar reseña");
        btnGuardar.setBackground(new Color(102, 24, 27));
        btnGuardar.setForeground(Color.WHITE);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(102, 24, 27));
        btnCancelar.setForeground(Color.WHITE);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        panelBotones.setBackground(new Color(155, 178, 204));
        
        // Accion Cancelar
        btnCancelar.addActionListener(e -> dispose());
        
        // Accion Guardar
        btnGuardar.addActionListener(e -> {
        	String tituloSeleccionado = (String) comboTitulos.getSelectedItem();
        	Contenido contenidoSeleccionado = null;
            for (Contenido c : contenidos) {
                if (c.getTitulo().equalsIgnoreCase(tituloSeleccionado)) {
                    contenidoSeleccionado = c;
                    break;
                }
            }
            if (contenidoSeleccionado == null) {
            	JOptionPane.showMessageDialog(this, "No se encontro el contenido seleccionado.");
            	return;
            }
            if (puntuacionSeleccionada[0] == 0) {
            	JOptionPane.showMessageDialog(this, "Seleccione una puntuación.");
                return;
            }
            int puntuacion = puntuacionSeleccionada[0];
            
            // Guardar la valoración
            Resena nuevaResena = new Resena(usuarioActual, contenidoSeleccionado, (double) puntuacion);
            usuarioActual.agregarValorada(nuevaResena);
            
            JOptionPane.showMessageDialog(this, "Reseña agregada correctamente.");
            dispose();
        });
        
        // Añadir todo al panel principal
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(panelCombo);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelPuntuacion);
        panelPrincipal.add(Box.createVerticalGlue());
        panelPrincipal.add(panelBotones);

        add(panelPrincipal);
        setVisible(true);
    
	}
}


