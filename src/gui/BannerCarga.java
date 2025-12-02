package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class BannerCarga extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JLabel lblPortada;
    private Thread hiloCarrusel;
    private boolean carruselActivo = true;
    
    // Lista de portadas que se irán mostrando
    private String[] portadas;
    
    public BannerCarga(String[] rutasPortadas) {
        this.portadas = rutasPortadas;
        configurarVentana();
        iniciarCarrusel();
    }
    
    private void configurarVentana() {
        setLayout(new BorderLayout());
        setUndecorated(true); // Sin borde
        
        // Panel principal con fondo oscuro
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(20, 20, 20));
        panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(255, 50, 50), 3));
        
        // Título superior
        JLabel titulo = new JLabel("🎬 CARGANDO CATÁLOGO...", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        titulo.setBackground(new Color(30, 30, 30));
        titulo.setOpaque(true);
        
        // Label para las portadas (centro)
        lblPortada = new JLabel();
        lblPortada.setHorizontalAlignment(JLabel.CENTER);
        lblPortada.setVerticalAlignment(JLabel.CENTER);
        lblPortada.setBackground(new Color(20, 20, 20));
        lblPortada.setOpaque(true);
        
        // Mensaje inferior
        JLabel mensaje = new JLabel("Preparando contenido multimedia...", JLabel.CENTER);
        mensaje.setFont(new Font("Arial", Font.ITALIC, 12));
        mensaje.setForeground(new Color(150, 150, 150));
        mensaje.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(lblPortada, BorderLayout.CENTER);
        panelPrincipal.add(mensaje, BorderLayout.SOUTH);
        
        add(panelPrincipal);
        
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    // Método para iniciar el carrusel (copiado de GymBanner)
    public void iniciarCarrusel() {
        hiloCarrusel = new Thread(() -> {
            int i = 0;
            try {
                while (carruselActivo) {
                    String rutaPortada = portadas[i % portadas.length];
                    actualizarPortada(rutaPortada);
                    Thread.sleep(1500); // Cambia cada 1.5 segundos
                    i++;
                }
            } catch (InterruptedException e) {
                System.out.println("Carrusel detenido");
            }
        });
        hiloCarrusel.start();
    }
    
    // Método para actualizar la imagen de la portada
    private void actualizarPortada(String rutaImagen) {
        SwingUtilities.invokeLater(() -> {
            ImageIcon icon = new ImageIcon(rutaImagen);
            // Escalar la imagen para que se vea bien
            Image img = icon.getImage().getScaledInstance(350, 400, Image.SCALE_SMOOTH);
            lblPortada.setIcon(new ImageIcon(img));
        });
    }
    
    // Método para detener el carrusel y cerrar el splash
    public void cerrar() {
        carruselActivo = false;
        if (hiloCarrusel != null) {
            hiloCarrusel.interrupt();
        }
        SwingUtilities.invokeLater(() -> {
            setVisible(false);
            dispose();
        });
    }
}
