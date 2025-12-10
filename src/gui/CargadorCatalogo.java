package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import domain.Contenido;
import domain.Recomendador;
import domain.Usuario;

public class CargadorCatalogo {
	
	//Constructor generado con IA
    public static void abrirCatalogoConCarga(Usuario usuario, ArrayList<Contenido> catalogo) {
        // 1. Obtener películas recomendadas usando tu Recomendador
        String[] rutasPortadas = obtenerRutasPortadas(usuario, catalogo);
        
        // 2. Mostrar splash con carrusel de las películas recomendadas
        BannerCarga splash = new BannerCarga(rutasPortadas);
        splash.setVisible(true);
        
        // 3. Cargar la ventana del catálogo en segundo plano
        SwingWorker<JFrame, Void> worker = new SwingWorker<>() {
            
            @Override
            protected JFrame doInBackground() throws Exception {
                // Simular carga de tu ventana (ajusta el tiempo según necesites)
                Thread.sleep(5000); // 5 segundos para que se vean varias portadas
                
                return new VentanaCatalogo(usuario);
            }
            
            @Override
            protected void done() {
                try {
                    JFrame catalogo = get();
                    
                    // Cerrar splash y mostrar catálogo
                    splash.cerrar();
                    catalogo.setVisible(true);
                    
                } catch (Exception e) {
                    splash.cerrar();
                    JOptionPane.showMessageDialog(
                        null,
                        "Error al cargar el catálogo: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        };
        
        worker.execute();
    }
    
    // Método para obtener las rutas de las portadas recomendadas
    private static String[] obtenerRutasPortadas(Usuario usuario, ArrayList<Contenido> catalogo) {
        // Obtener películas recomendadas usando tu Recomendador
        ArrayList<Contenido> recomendadas = Recomendador.recomendarPorGenero(usuario, catalogo);
        
        if (recomendadas.isEmpty() || recomendadas.size() < 3) {
            recomendadas = new ArrayList<>();
            for (int i = 0; i < Math.min(5, catalogo.size()); i++) {
                recomendadas.add(catalogo.get(i));
            }
        }
        
        // Máximo 10 portadas para el carrusel)
        int numPortadas = Math.min(recomendadas.size(), 10);
        String[] rutas = new String[numPortadas];
        
        for (int i = 0; i < numPortadas; i++) {
            rutas[i] = recomendadas.get(i).getRutaPortada();
        }
        
        return rutas;
    }
    
    // Ventana de catálogo de ejemplo (reemplaza con tu ventana real)
    private static JFrame crearVentanaCatalogo() {
        JFrame ventana = new JFrame("Catálogo de Películas y Series");
        ventana.setSize(900, 600);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("🎬 CATÁLOGO CARGADO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextArea info = new JTextArea();
        info.setText("¡Tu catálogo ya está listo!\n\n" +
                    "Mientras esperabas, viste un carrusel de portadas.\n\n" +
                    "Esta es tu ventana original del catálogo.");
        info.setEditable(false);
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        info.setMargin(new Insets(20, 20, 20, 20));
        
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(new JScrollPane(info), BorderLayout.CENTER);
        
        ventana.setContentPane(panel);
        return ventana;
    }
    
    // Main para probar
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame menuPrincipal = new JFrame("Menú Principal");
            menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuPrincipal.setSize(400, 200);
            menuPrincipal.setLocationRelativeTo(null);
            
            JPanel panel = new JPanel();
            JButton btnAbrir = new JButton("Abrir Catálogo");
            btnAbrir.setFont(new Font("Arial", Font.BOLD, 16));
            
            // ESTO ES LO QUE PONDRÍAS EN TU MENÚ
            // Necesitas pasar el usuario y el catálogo
            btnAbrir.addActionListener(e -> {
                // EJEMPLO: asume que tienes acceso a usuario y catalogo
                // Usuario usuario = ...; // tu usuario actual
                // ArrayList<Contenido> catalogo = ...; // tu catálogo
                
                // CargadorCatalogo.abrirCatalogoConCarga(usuario, catalogo);
                
                // Para la demo, mostramos un mensaje
                JOptionPane.showMessageDialog(null, 
                    "En tu código real, aquí pasarías:\n" +
                    "CargadorCatalogo.abrirCatalogoConCarga(usuario, catalogo);");
            });
            
            panel.add(new JLabel("Haz clic para ver el splash:"));
	            panel.add(btnAbrir);
	            
	            menuPrincipal.add(panel);
	            menuPrincipal.setVisible(true);
	        });
	    }
	}


