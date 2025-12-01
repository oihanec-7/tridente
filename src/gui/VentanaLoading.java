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
<<<<<<< HEAD
		// Añadimos un poco de margen vacío alrededor del texto
		label.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 10, 20, 10));
		add(label, BorderLayout.CENTER);
 
		// 2. Configuración de la JProgressBar
		progressBar = new JProgressBar(0, 100); // Mínimo 0, Máximo 100
		progressBar.setValue(0);
		progressBar.setStringPainted(true); // Muestra el porcentaje % escrito
		progressBar.setForeground(new Color(102, 24, 27)); // Color de la barra (Verde)
		progressBar.setBackground(Color.WHITE); // Color del fondo de la barra
		progressBar.setFont(new Font("Arial", Font.BOLD, 12));
		// La ponemos en la parte inferior (SOUTH)
		add(progressBar, BorderLayout.SOUTH);

=======
		add(label);
		
>>>>>>> branch 'master' of git@github.com:oihanec-7/tridente.git
		setVisible(true);
		
		// forzar un refresco de swing antes de la carga pesada 
		SwingUtilities.invokeLater(() -> {
			repaint();
			toFront(); // trae la pantalla al frente inmediatamente
		});
		}

}
