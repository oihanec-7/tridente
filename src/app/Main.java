package app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import domain.Usuario;
import gui.VentanaLogin;
import gui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
	
	    SwingUtilities.invokeLater(() -> {
	        // Lista de usuarios de prueba
	        List<Usuario> usuariosPrueba = new ArrayList<>();
	        usuariosPrueba.add(new Usuario("Juan", "juan123", "1234", "Pérez", "juan@email.com"));
	        usuariosPrueba.add(new Usuario("Ana", "ana456", "abcd", "García", "ana@email.com"));
	      

            VentanaLogin login = new VentanaLogin(usuariosPrueba);
            login.setVisible(true);
	    });
	


	}

}
