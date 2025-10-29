package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import domain.Usuario;

public class VentanaMiUsuario extends JFrame{
	
	public VentanaMiUsuario(Usuario usuario) {
		
		setTitle("Perfil de Usuario:" + usuario.getNombre_usuario());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600,300);
		setLayout(new BorderLayout(15,15));
		
		JLabel fotoPerfil = new JLabel();
		fotoPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		fotoPerfil.setVerticalAlignment(SwingConstants.CENTER);
		
			
		JLabel titulo = new JLabel("Mi Perfil");
		add(titulo, BorderLayout.NORTH);

		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
		
		// Nombre de Usuario (en grande)
        JLabel labelNombreUsuario = new JLabel(usuario.getNombre_usuario());
        labelNombreUsuario.setFont(new Font("Arial", Font.BOLD, 28)); // Fuente grande
        //labelNombreUsuario.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear a la izquierda
        panelInfo.add(labelNombreUsuario);
        //panelInfo.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio vertical

        // Nombre y Apellidos
        JLabel labelNombreCompleto = new JLabel(usuario.getNombre() + usuario.getApellido());
        labelNombreCompleto.setFont(new Font("SansSerif", Font.PLAIN, 18));
        //labelNombreCompleto.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelInfo.add(labelNombreCompleto);
        //panelInfo.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio vertical

        // Email
        JLabel labelEmail = new JLabel(usuario.getEmail());
        labelEmail.setFont(new Font("SansSerif", Font.PLAIN, 16));
        //labelEmail.setForeground(Color.GRAY); // Un color más suave
        //labelEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelInfo.add(labelEmail);
		
		
		
	}

}
