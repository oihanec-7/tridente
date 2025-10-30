package gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		
        JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCombo.add(new JLabel("Título:"));
        JComboBox<String> comboTitulos = new JComboBox<>(titulos.toArray(new String[0]));
        panelCombo.add(comboTitulos);
        
        JLabel labelResena = new JLabel("Tu reseña:");
        JTextArea txtResena = new JTextArea(6,14);
        txtResena.setLineWrap(true);
        txtResena.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtResena);
        
        
	}

}
