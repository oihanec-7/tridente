package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class VentanaLoading extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel label;
	
	public VentanaLoading() {
		setTitle("Cargando...");
		setSize(250,120);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setBackground(new Color(14,28,59));
		
		label = new JLabel("Cargando...", SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		add(label);
		
		setVisible(true);
		
		// forzar un refresco de swing antes de la carga pesada 
		SwingUtilities.invokeLater(() -> {
			repaint();
			toFront(); // trae la pantalla al frente inmediatamente
		});
		}

}
