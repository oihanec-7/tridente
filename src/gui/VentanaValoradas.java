package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.Flow;

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
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;
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
    private JTable tablaContenido;
    private DefaultTableModel modeloContenido;
 
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
        
        // Crear Tabla
        crearTabla(usuario.getListaValoradas());
//        JScrollPane scrollTabla = new JScrollPane(tablaValoradas);
//        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);
        crearTablaContenido();
        
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.add(new JScrollPane(tablaValoradas));
        panelCentro.add(Box.createVerticalStrut(10));
        panelCentro.add(new JScrollPane(tablaContenido));
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        
        
        //Listener para seleccionar fila
        tablaValoradas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaValoradas.getSelectionModel().addListSelectionListener(e -> {
        	if(!e.getValueIsAdjusting()) {
        		int fila = tablaValoradas.getSelectedRow();
        		if(fila >= 0) {
        			ModeloDeDatosValoradas modelo = (ModeloDeDatosValoradas) tablaValoradas.getModel();
        			Resena resenaSeleccionada = modelo.getResenaAt(fila);
        			actualizarTablaDetalles(resenaSeleccionada);
        		}
        	}
        	
        });
        
        
        // Buscador con filtrado
		buscador.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void insertUpdate(DocumentEvent e) { filtrar(); }

            private void filtrar() {
            	String texto = buscador.getText().toLowerCase();
            	ModeloDeDatosValoradas modelo = (ModeloDeDatosValoradas) tablaValoradas.getModel();
            	modelo.filtrar(texto);
				/*
				 * for (Resena r: usuario.getListaValoradas()) { if
				 * (r.getContenido().getTitulo().toLowerCase().contains(texto)) {
				 * modeloFiltrado.addRow(new Object[]{ r.getContenido().getTitulo(),
				 * r.getFechaResena(), r.getPuntuacion().intValue() }); } }
				 * tablaValoradas.setModel(modeloFiltrado);
				 */
//            	tablaValoradas.getColumnModel().getColumn(2)
//            		.setCellRenderer(new EstrellaRenderer());
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
		tablaValoradas.getTableHeader().setReorderingAllowed(false);
		tablaValoradas.getTableHeader().setPreferredSize(new Dimension(tablaValoradas.getTableHeader().getWidth(), 60));
		
		//Redenrer para el encabezado
		tablaValoradas.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				
				
				JLabel result = (JLabel) super.getTableCellRendererComponent(
		                table, value, isSelected, hasFocus, row, column);
				
				
				result.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
				result.setBorder(BorderFactory.createLineBorder(Color.black));
				result.setOpaque(true);
				result.setHorizontalAlignment(JLabel.CENTER);
				result.setText("");
				
				if(column == 0) {
					ImageIcon peli = new ImageIcon("images/contenido.png");
					Image escalar = peli.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
					ImageIcon imagen = new ImageIcon(escalar);
					result.setIcon(imagen);
					
					
				} else if(column == 1) {
					ImageIcon calendario = new ImageIcon("images/cal.png");
					Image escalar = calendario.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
					ImageIcon imagen = new ImageIcon(escalar);
					result.setIcon(imagen);
				
					
				} else if(column == 2){
					ImageIcon val = new ImageIcon("images/valoracion.png");
					Image escalar = val.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
					ImageIcon imagen = new ImageIcon(escalar);
					result.setIcon(imagen);
					
				}else {
		           	ImageIcon genero = new ImageIcon("images/genero2.png");
					Image escalar = genero.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
					ImageIcon imagen = new ImageIcon(escalar);
					result.setIcon(imagen);
			        
				}
			
				
				return result;
			}
		});
		
		tablaValoradas.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column){
				
				ModeloDeDatosValoradas modelo = (ModeloDeDatosValoradas) table.getModel();
				Resena resena = modelo.getResenaAt(row);
				
				 JPanel panel = new JPanel();
				 panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			     panel.setOpaque(true);
				
				for (String genero : resena.getContenido().getGenero()) {
		            JLabel etiqueta = new JLabel(genero);
		            etiqueta.setFont(new Font("SansSerif", Font.BOLD, 12));
		            etiqueta.setForeground(Color.WHITE);
		            etiqueta.setOpaque(true);
		            etiqueta.setBackground(new Color(14, 28, 59));
		            etiqueta.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		            etiqueta.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		            panel.add(Box.createVerticalStrut(20));
		            panel.add(etiqueta);
		            panel.add(Box.createVerticalStrut(1));
		        }

				
				if(row % 2 == 0) {
					panel.setBackground(new Color(155, 178, 204));
				} else {
					panel.setBackground(new Color(185, 200, 220));
				}
				
				if(isSelected) {
					panel.setBackground(new Color(255, 230, 128));
					panel.setOpaque(true);
				}
				
				
				panel.setFont(new FontUIResource("SansSerif", Font.BOLD, 16));
				panel.setBorder(BorderFactory.createLineBorder(Color.black));
				panel.setOpaque(true);
			
				return panel;
			}
			
			
		});
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
				result.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
					int puntuacion = (int)Math.round(resena.getPuntuacion());
					switch (puntuacion) {
					case 1: 
						ImageIcon unaestrella = new ImageIcon("images/1estrella.png");
						Image escalar1 = unaestrella.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
						ImageIcon imagen1 = new ImageIcon(escalar1);
						result.setIcon(imagen1);
						break;
	
					case 2: 
						ImageIcon dosestrellas = new ImageIcon("images/2estrellas.png");
						Image escalar2 = dosestrellas.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
						ImageIcon imagen2 = new ImageIcon(escalar2);
						result.setIcon(imagen2);
						
						break;
					
					case 3: 
						ImageIcon tresestrell = new ImageIcon("images/3estrellas.png");
						Image escalar3 = tresestrell.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
						ImageIcon imagen3 = new ImageIcon(escalar3);
						result.setIcon(imagen3);
						break;
					
					case 4: 
						ImageIcon cuatroestrell = new ImageIcon("images/4estrellas.png");
						Image escalar4 = cuatroestrell.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
						ImageIcon imagen4 = new ImageIcon(escalar4);
						result.setIcon(imagen4);
						break;
					
					case 5: 
						ImageIcon cincoestrell = new ImageIcon("images/5estrellas.png");
						Image escalar5 = cincoestrell.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
						ImageIcon imagen5 = new ImageIcon(escalar5);
						result.setIcon(imagen5);
						break;

					default:
						break;
					}
					
					result.setText("");
					result.setToolTipText(String.valueOf(puntuacion));
					
				} else {
					result.setText(value.toString());
				}
				
				if(row % 2 == 0) {
					result.setBackground(new Color(155, 178, 204));
				} else {
					result.setBackground(new Color(185, 200, 220));
				}
				
				if(isSelected) {
					result.setBackground(new Color(255, 230, 128));
					result.setOpaque(true);
				}
				
				
				result.setFont(new FontUIResource("SansSerif", Font.BOLD, 16));
			
				return result;
			}
		};
		tablaValoradas.setDefaultRenderer(Object.class, miCellRenderer);
		//tablaValoradas.getColumnModel().getColumn(2).setCellRenderer(new EstrellaRenderer());
	
	}

	 // Renderer para mostrar estrellas dibujadas (sin imágenes)
//    class EstrellaRenderer extends DefaultTableCellRenderer {
//       
//
//		@Override
//        public Component getTableCellRendererComponent(JTable table, Object value,
//                                                       boolean isSelected, boolean hasFocus, int row, int column) {
//            int numEstrellas = (value instanceof Integer) ? (Integer) value : 0;
//            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
//            panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
//            panel.setOpaque(true);
//            
//                      
//            for (int i = 1; i <= 5; i++) {
//                JLabel estrella = new JLabel(i <= numEstrellas ? "⭐" : "");
//                estrella.setFont(new Font("Dialog", Font.PLAIN, 18));
//                panel.add(estrella);
//            }
//
//            return panel;
//        }
//	}
	
	private void crearTablaContenido(){
		String[] columnas = {"Titulo", "Puntuación Media", "Cast"};
		modeloContenido = new DefaultTableModel(columnas, 0);
		tablaContenido = new JTable(modeloContenido);
		tablaContenido.setRowHeight(30);
		
	}
	
	private void actualizarTablaDetalles(Resena resena) {
		modeloContenido.setRowCount(0);
		
		modeloContenido.addRow(new Object[]{"Titulo", resena.getContenido().getTitulo()});
		modeloContenido.addRow(new Object[]{"Puntuacion Media", resena.getContenido().getPuntuacionMedia()});
		String castTexto = ""; 
		if(resena.getContenido().getCast() != null && !resena.getContenido().getCast().isEmpty()) {
			for(String actor: resena.getContenido().getCast()) {
				castTexto += actor + ", ";
			}
			castTexto = castTexto.substring(0, castTexto.length() - 2);
			
		} else {
			castTexto = "No disponible";
		}
		modeloContenido.addRow(new Object[] {"Cast", castTexto});;
	
	}
	
	
	
}


