package gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import domain.Pelicula;

public class VentanaAgregarResena extends JFrame{
	
	public VentanaAgregarResena(List<String> titulos) {
		setTitle("Agregar reseña");
		setSize(500,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Agregar nueva reseña");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
        // ComboBox con titulos de peliculas o series
        JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCombo.add(new JLabel("Título:"));
        JComboBox<String> comboTitulos = new JComboBox<>(titulos.toArray(new String[0]));
        panelCombo.add(comboTitulos);
        
        // Area de texto
        JLabel labelResena = new JLabel("Tu reseña:");
        JTextArea txtResena = new JTextArea(6,14);
        txtResena.setLineWrap(true);
        txtResena.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtResena);
        
        // Puntuacion
        JPanel panelPuntuacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPuntuacion.add(new JLabel("Puntuación:"));
        ButtonGroup grupoEstrellas = new ButtonGroup();
        for (int i = 1; i <= 5; i++) {
            JRadioButton estrella = new JRadioButton(String.valueOf(i));
            grupoEstrellas.add(estrella);
            panelPuntuacion.add(estrella);
        }
        
        // Botones inferiores
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar reseña");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        // Accion Cancelar
        btnCancelar.addActionListener(e -> dispose());
        
        // Añadir todo al panel principal
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(panelCombo);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(labelResena);
        panelPrincipal.add(scroll);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelPuntuacion);
        panelPrincipal.add(Box.createVerticalGlue());
        panelPrincipal.add(panelBotones);

        add(panelPrincipal);
        setVisible(true);
    
	}

}
