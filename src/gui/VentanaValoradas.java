package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.lang.reflect.Array;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Contenido;
import domain.Resena;
import domain.Usuario;

public class VentanaValoradas extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
  	private Usuario usuario;
    private JTextField buscador;
    private JTable tablaValoradas;
    //private DefaultTableModel modeloTabla;
    private JButton botonMenu;
    private JPanel panelMenu;
   

	public VentanaValoradas(Usuario usuario) {
        this.usuario = usuario;  
        this.setTitle("Valoradas");
        this.setSize(1300, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      

	    inicializarVentana();
    }

	private void inicializarVentana() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(155, 178, 204));
        panelPrincipal.setOpaque(true);
       
        // Panel superior (menú + buscador)
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
        panelSuperior.setBackground(new Color(155, 178, 204));
        panelSuperior.setOpaque(true);

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
        panelMenu.setOpaque(true);

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
        
        // Tabla
        crearTabla(usuario.getListaValoradas());
        JScrollPane scrollTabla = new JScrollPane(tablaValoradas);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);
        
        
        // Buscador con filtrado
		buscador.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void insertUpdate(DocumentEvent e) { filtrar(); }

            private void filtrar() {
            	String texto = buscador.getText().toLowerCase();
            	DefaultTableModel modeloFiltrado = new DefaultTableModel(
            		new Object[]{"Título", "Fecha", "Valoración"}, 0
            	);
            	for (Resena r: usuario.getListaValoradas()) {
            		if (r.getContenido().getTitulo().toLowerCase().contains(texto)) {
                        modeloFiltrado.addRow(new Object[]{
                                r.getContenido().getTitulo(),
                                r.getFechaResena(),
                                r.getPuntuacion().intValue()
                        });
            		}
            	}
            	tablaValoradas.setModel(modeloFiltrado);
            	tablaValoradas.getColumnModel().getColumn(2)
            		.setCellRenderer(new EstrellaRenderer());
            }
		});

        add(panelPrincipal);
			    
	}
	
	private void crearTabla(ArrayList<Resena> lista) {
//		String[] columnas = {"Título", "Fecha", "Valoración"};
//		modeloTabla = new DefaultTableModel(columnas, 0);
//		
//		for (Resena r : lista) {
//			modeloTabla.addRow(new Object[] {
//					r.getContenido().getTitulo(),
//                    r.getFechaResena(),
//                    r.getPuntuacion().intValue()
//					
//			});
//		}
		
		
		ModeloDeDatosValoradas modeloDatos = new ModeloDeDatosValoradas(lista);
		tablaValoradas = new JTable(modeloDatos);
		
		
		//tablaValoradas = new JTable(modeloTabla);
		tablaValoradas.setRowHeight(150);
		tablaValoradas.setFont(new Font("SansSerif", Font.PLAIN, 14));
//		tablaValoradas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//			 public Component getTableCellRendererComponent(JTable table, 
//					 										Object value,
//											                boolean isSelected, 
//											                boolean hasFocus, 
//											                int row, 
//											                int column) {
//				 
//				 Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//				 comp.setBackground(row % 2 == 0 ? new Color(220, 230, 250) : Color.WHITE);
//				 return comp;
//			 }
//		});
		
		TableCellRenderer miCellRenderer = new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, 
					boolean isSelected, boolean hasFocus,
					int row, int column) {
				
				JLabel result = new JLabel(value.toString());
				result.setHorizontalAlignment(JLabel.CENTER);
				result.setOpaque(true);
				
				ModeloDeDatosValoradas modelo = (ModeloDeDatosValoradas) table.getModel();
				Resena resena = modelo.getResenaAt(row);
				
				if(column == 0) {
					String path = resena.getContenido().getRutaPortada();
					ImageIcon imagen = new ImageIcon(path);
					Image escalar = imagen.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
					result.setIcon(new ImageIcon(escalar));
					result.setText("");
					result.setToolTipText(resena.getContenido().getTitulo());
					
				} else if(column == 2) {
					int numEstrellas = resena.getPuntuacion().intValue();
					StringBuilder estrellas = new StringBuilder();
					for(int i=1; i<=5; i++) {
						if(i<=numEstrellas) {
							estrellas.append("⭐");
						} else {
							estrellas.append("");
						}
					}
					result.setText(estrellas.toString());
					
					
				} else {
					result.setText(value.toString());
				}
				
			
				return result;
			}
		};
		tablaValoradas.setDefaultRenderer(Object.class, miCellRenderer);
		tablaValoradas.getColumnModel().getColumn(2).setCellRenderer(new EstrellaRenderer());
	
	}

	 // Renderer para mostrar estrellas dibujadas (sin imágenes)
    class EstrellaRenderer extends DefaultTableCellRenderer {
       

		@Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            int numEstrellas = (value instanceof Integer) ? (Integer) value : 0;
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            panel.setOpaque(true);
            
                      
            for (int i = 1; i <= 5; i++) {
                JLabel estrella = new JLabel(i <= numEstrellas ? "⭐" : "");
                estrella.setFont(new Font("Dialog", Font.PLAIN, 18));
                panel.add(estrella);
            }

            return panel;
        }
	}
}


