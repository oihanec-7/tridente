package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import domain.Contenido;
import domain.Usuario;

public class VentanaValoradas extends JFrame {
	
	private static final long serialVersionUID1 = 1L;
  	private ArrayList<Contenido> listaUsuario;
  	private Usuario usuario;
  	private JButton botonMenu;
    private JPanel panelPrincipal = new JPanel(new BorderLayout());
    private JPanel panelMenu;
    private JScrollPane scrollPortadas;
    private JTextField buscador;

	public VentanaValoradas(Usuario usuario) {
        this.usuario = usuario;
        this.listaUsuario = usuario.getListaValoradas();
        
        this.setTitle("Mi Lista");
        this.setSize(1300, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	    inicializarVentana();
    }

	private void inicializarVentana() {
        panelPrincipal.setBackground(new Color(155, 178, 204));
        panelPrincipal.setOpaque(true);

        // Panel superior (menú + buscador)
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
        panelSuperior.setBackground(new Color(155, 178, 204));
        panelSuperior.setBorder(null);

        botonMenu = new JButton("☰");
        botonMenu.setPreferredSize(new Dimension(50, 50));
        botonMenu.setBackground(new Color(14, 28, 59));
        botonMenu.setForeground(Color.WHITE);
        botonMenu.setFocusPainted(false);
        panelSuperior.add(botonMenu);

        botonMenu.addActionListener(e -> 
        	panelMenu.setVisible(!panelMenu.isVisible()));

        buscador = new JTextField(30);
        panelSuperior.add(Box.createHorizontalStrut(10));
        panelSuperior.add(buscador);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        // Panel del menú lateral
        panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setPreferredSize(new Dimension(150, 0));
        panelMenu.setBackground(new Color(243, 200, 207));

        String[] etiquetas = {"Inicio", "Catalogo", "Mi Lista", "Valoradas", "Mi Usuario"};
        for (String etiqueta : etiquetas) {
            JButton boton = new JButton(etiqueta);
            boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            boton.setFocusPainted(false);
            boton.setBorderPainted(false);
            boton.setContentAreaFilled(false);
            boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            switch (etiqueta) {
                case "Inicio":
                    boton.addActionListener(e -> {
                        this.setVisible(false);
                        new VentanaPrincipal(usuario).setVisible(true);
                    });
                    break;
                case "Catalogo":
                    boton.addActionListener(e -> {
                        this.setVisible(false);
                        new VentanaCatalogo(usuario).setVisible(true);
                    });
                    break;
                case "Mi Lista":
                    boton.addActionListener(e -> {
                        this.setVisible(true);
                        panelMenu.setVisible(false);
                    });
                    break;
                case "Valoradas":
                    boton.addActionListener(e -> {});
                    break;
                case "Mi Usuario":
                    boton.addActionListener(e -> {
                        this.setVisible(false);
                        new VentanaMiUsuario(usuario).setVisible(true);
                    });
                    break;
            }
            panelMenu.add(boton);
        }

        panelMenu.setVisible(false);
        panelPrincipal.add(panelMenu, BorderLayout.WEST);

        // Panel central con las portadas
        JPanel panelPortadas = anadirContenidos(listaUsuario);
    	panelPortadas.setBackground(new Color(155, 178, 204));
    	
        scrollPortadas = new JScrollPane(panelPortadas,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelPrincipal.add(scrollPortadas, BorderLayout.CENTER);

        // Buscador con filtrado
		buscador.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void insertUpdate(DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = buscador.getText().toLowerCase();
                ArrayList<Contenido> filtradas = new ArrayList<>();
                for (Contenido c : listaUsuario) {
                    if (c.getTitulo().toLowerCase().contains(texto)) {
                        filtradas.add(c);
                    }
                }
                JPanel panelActualizado = anadirContenidos(filtradas);
                scrollPortadas.setViewportView(panelActualizado);
                scrollPortadas.revalidate();
                scrollPortadas.repaint();
            }
        });

        add(panelPrincipal);
			    
	}

	private JPanel anadirContenidos(ArrayList<Contenido> contenidos) {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(155, 178, 204));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        int botonesPorFila = 9;
        JPanel fila = null;
        int contador = 0;

        for (Contenido c : contenidos) {
            if (contador % botonesPorFila == 0) {
                fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
                fila.setBackground(new Color(155, 178, 204));
                panelPrincipal.add(fila);
            }
            // Botones para cada contenido
            ImageIcon portadaIcon = new ImageIcon(c.getRutaPortada());
            Image imagenEscalada = portadaIcon.getImage().getScaledInstance(120, 170, Image.SCALE_SMOOTH);
            JButton boton = new JButton(new ImageIcon(imagenEscalada));
            boton.setPreferredSize(new Dimension(120, 170));
            boton.setBorderPainted(false);

            boton.addActionListener(e -> {
            	VentanaContenido ventana = new VentanaContenido(c, usuario);
            	ventana.setVisible(true);
        	});
        	
            fila.add(boton);
            contador++;
        }

        return panelPrincipal;
	}
}


