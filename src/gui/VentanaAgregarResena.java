package gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import data.GestorDatos;
import domain.Contenido;
import domain.Pelicula;
import domain.Resena;
import domain.Usuario;

public class VentanaAgregarResena extends JFrame{
	
	private GestorDatos gestor;
	private Usuario usuarioActual;
	private ArrayList<Contenido> contenidos;
		
	public VentanaAgregarResena(Usuario usuario, ArrayList<Contenido> contenidos, GestorDatos gestor) {
		this.usuarioActual = usuario;
        this.contenidos = contenidos;
        this.gestor = gestor;
		
		setTitle("Agregar reseña");
		setSize(400,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Agregar nueva reseña");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
        // ComboBox con titulos de contenidos
        ArrayList<String> titulos = new ArrayList<>();
        for (Contenido c : contenidos) {
        	titulos.add(c.getTitulo());
        }
        JComboBox<String> comboTitulos = new JComboBox<>(titulos.toArray(new String[0]));
        
        JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCombo.add(new JLabel("Título:"));
        panelCombo.add(comboTitulos);
               
        // Puntuacion
        JPanel panelPuntuacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPuntuacion.add(new JLabel("Puntuación:"));
        ButtonGroup grupoEstrellas = new ButtonGroup();
        JRadioButton[] estrellas = new JRadioButton[5];
        for (int i = 1; i <= 5; i++) {
            estrellas[i] = new JRadioButton(String.valueOf(i + 1));
            estrellas[i].setActionCommand(String.valueOf(i + 1));
            grupoEstrellas.add(estrellas[i]);
            panelPuntuacion.add(estrellas[i]);
        }
        
        // Botones inferiores
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar reseña");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
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
            if (grupoEstrellas.getSelection() == null) {
            	JOptionPane.showMessageDialog(this, "Seleccione una puntuación.");
                return;
            }
            double puntuacion = Double.parseDouble(grupoEstrellas.getSelection().getActionCommand());
            
            // Crear la reseña y agregarla al gestor
            Resena resena = new Resena(usuarioActual, contenidoSeleccionado, puntuacion);
            gestor.agregarResena(resena);
            
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
