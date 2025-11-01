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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import domain.Contenido;
import domain.Usuario;

public class VentanaMiLista extends JFrame {
    private static final long serialVersionUID = 1L;
    private ArrayList<Contenido> miLista;
    private Usuario usuario;
    private JButton botonMenu;
    private JPanel panelPrincipal = new JPanel(new BorderLayout());
    private JPanel panelMenu;
    private JScrollPane scrollPortadas;
    private JTextField buscador;

    public VentanaMiLista(Usuario usuario) {
        this.usuario = usuario;
        this.miLista = usuario.getMiLista(); // Obtener la lista de contenido del usuario

        setTitle("Mi Lista");
        setSize(1300, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        inicializarVentana();
    }

    private void inicializarVentana() {
        panelPrincipal.setBackground(new Color(155, 178, 204));
        panelPrincipal.setOpaque(true);

        // Panel superior: buscador + botón menú
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
        panelSuperior.setBackground(new Color(155, 178, 204));

        botonMenu = new JButton("☰");
        botonMenu.setPreferredSize(new Dimension(50, 50));
        botonMenu.setBackground(new Color(14, 28, 59));
        botonMenu.setForeground(Color.WHITE);
        botonMenu.setFocusPainted(false);
        panelSuperior.add(botonMenu);

        botonMenu.addActionListener(e -> panelMenu.setVisible(!panelMenu.isVisible()));

        buscador = new JTextField(30);
        panelSuperior.add(Box.createHorizontalStrut(10));
        panelSuperior.add(buscador);

        ImageIcon imagenLupa = new ImageIcon("images/lupa.png");
        Image escalarImagen = imagenLupa.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel etiquetaLupa = new JLabel(new ImageIcon(escalarImagen));
        panelSuperior.add(Box.createHorizontalStrut(5));
        panelSuperior.add(etiquetaLupa);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        // Panel menú lateral
        panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setPreferredSize(new Dimension(150, 0));
        panelMenu.setMaximumSize(new Dimension(150, Integer.MAX_VALUE));
        panelMenu.setBackground(new Color(243, 200, 207));

        String[] etiquetasBotones = {"Inicio", "Catalogo", "Mi Lista", "Valoradas", "Mi Usuario"};
        for (String etiqueta : etiquetasBotones) {
            JButton boton = new JButton(etiqueta);
            boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            boton.setFocusPainted(false);
            boton.setBorderPainted(false);
            boton.setContentAreaFilled(false);
            boton.setOpaque(false);
            boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            switch (etiqueta) {
                case "Inicio":
                    boton.addActionListener(e -> {
                        setVisible(false);
                        new VentanaPrincipal(usuario).setVisible(true);
                    });
                    break;
                case "Catalogo":
                    boton.addActionListener(e -> {
                        setVisible(false);
                        new VentanaCatalogo(usuario).setVisible(true);
                    });
                    break;
                case "Mi Lista":
                    boton.addActionListener(e -> {
                        setVisible(true);
                        panelMenu.setVisible(true);
                    });
                    break;
                case "Valoradas":
                    boton.addActionListener(e -> {});
                    break;
                case "Mi Usuario":
                    boton.addActionListener(e -> {
                        setVisible(false);
                        new VentanaMiUsuario(usuario).setVisible(true);
                    });
                    break;
            }

            panelMenu.add(boton);
        }
        panelMenu.setVisible(false);
        panelPrincipal.add(panelMenu, BorderLayout.WEST);

        // Panel de portadas
        JPanel panelPortadas = crearPanelContenidos(miLista);
        scrollPortadas = new JScrollPane(panelPortadas,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelPrincipal.add(scrollPortadas, BorderLayout.CENTER);

        // Buscador
        buscador.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void changedUpdate(DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = buscador.getText().toLowerCase();
                ArrayList<Contenido> filtradas = new ArrayList<>();
                for (Contenido c : miLista) {
                    if (c.getTitulo().toLowerCase().contains(texto)) {
                        filtradas.add(c);
                    }
                }
                scrollPortadas.setViewportView(crearPanelContenidos(filtradas));
                scrollPortadas.revalidate();
                scrollPortadas.repaint();
            }
        });

        add(panelPrincipal);
    }

    private JPanel crearPanelContenidos(ArrayList<Contenido> lista) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(155, 178, 204));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        int botonesPorFila = 9;
        JPanel fila = null;
        int contador = 0;

        for (Contenido c : lista) {
            if (contador % botonesPorFila == 0) {
                fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
                fila.setBackground(new Color(155, 178, 204));
                panel.add(fila);
            }

            ImageIcon portadaIcon = new ImageIcon(c.getRutaPortada());
            Image escalarPortada = portadaIcon.getImage().getScaledInstance(120, 170, Image.SCALE_SMOOTH);
            JButton boton = new JButton(new ImageIcon(escalarPortada));
            boton.setPreferredSize(new Dimension(120, 170));
            boton.setBorderPainted(false);
            boton.addActionListener(e -> new VentanaContenido(c).setVisible(true));
            fila.add(boton);
            contador++;
        }

        return panel;
    }

    // Método para actualizar la lista si el usuario añade contenido mientras la ventana está abierta
    public void actualizarMiLista() {
        miLista = usuario.getMiLista();
        scrollPortadas.setViewportView(crearPanelContenidos(miLista));
        scrollPortadas.revalidate();
        scrollPortadas.repaint();
    }
}