package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
<<<<<<< HEAD
  
=======

>>>>>>> branch 'master' of git@github.com:oihanec-7/tridente.git
public class VentanaLoading extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JProgressBar progressBar; // Nuevo componente: Barra de progreso

	public VentanaLoading() {
		setTitle("Cargando...");
		setSize(300, 150); 
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		// Usamos BorderLayout para organizar: Texto al centro, Barra abajo
		setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(14, 28, 59));

		// 1. Configuración del Label (Texto)
		label = new JLabel("Iniciando sistema...", SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of git@github.com:oihanec-7/tridente.git
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

		setVisible(true);

		// Iniciamos el proceso de carga en un hilo aparte
		iniciarCarga();
	}

	private void iniciarCarga() {
		// Creamos el hilo trabajador
		Thread hilo = new Thread(() -> {
			for (int i = 0; i <= 100; i++) {
				final int porcentaje = i;

				// Actualizamos la GUI dentro del EDT
				SwingUtilities.invokeLater(() -> {
					progressBar.setValue(porcentaje);
					label.setText("Cargando... " + porcentaje + "%");
				});
				// -------------------------------

				try {
					// Simulamos trabajo pesado (dormir el hilo trabajador)
					// Si aumentas este número, la carga será más lenta
					Thread.sleep(30); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// Cuando termina el bucle, cerramos la ventana
			SwingUtilities.invokeLater(() -> {
				label.setText("¡Carga completa!");
				try {
					// Una pequeña pausa final para que el usuario vea el 100%
					Thread.sleep(500);
				} catch (InterruptedException e) {}
				dispose(); // Cierra la ventana
				System.out.println("Ventana de carga cerrada. Iniciando aplicación principal...");
				// Aquí podrías llamar a: new VentanaPrincipal();
			});
		});

		hilo.start();
	}

	// Main para probarlo independientemente
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new VentanaLoading());
	}
}