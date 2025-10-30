package app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import domain.Pelicula;
import domain.Usuario;
import gui.VentanaCatalogo;
import gui.VentanaContenido;
import gui.VentanaLogin;
import gui.VentanaMiUsuario;
import gui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
	
		ArrayList<String> generos = new ArrayList<String>();
		generos.add("Infantil");
		generos.add("Humor");
		ArrayList<Double> puntuaciones = new ArrayList<Double>();
		puntuaciones.add(4.5);
		puntuaciones.add(3.5);
		puntuaciones.add((double) 5);
		puntuaciones.add(4.3);
		ArrayList<String> actores = new ArrayList<String>();
		actores.add("Drew Starkey");
		actores.add("Jacob Elordi");
		
		Pelicula peliPrueba = new Pelicula("Cars", generos, puntuaciones, actores, 150);
		
	    SwingUtilities.invokeLater(() -> {
	        // Lista de usuarios de prueba
	        List<Usuario> usuariosPrueba = new ArrayList<>();
	        usuariosPrueba.add(new Usuario("Juan", "juan123", "1234", "Pérez", "juan@email.com"));
	        usuariosPrueba.add(new Usuario("Ana", "ana456", "abcd", "García", "ana@email.com"));

            VentanaLogin login = new VentanaLogin(usuariosPrueba);
            login.setVisible(true);
	        
//       	VentanaContenido vc = new VentanaContenido(peliPrueba);

//	       	VentanaContenido vc = new VentanaContenido(peliPrueba);
//	        vc.setVisible(true);
//	        
//	        Usuario usuarioPrueb = new Usuario("Juan", "juan123", "1234", "Pérez", "juan@email.com", 22);
//	        VentanaMiUsuario ventanaPerfil = new VentanaMiUsuario(usuarioPrueb);
//	        ventanaPerfil.setVisible(true);
	        
	    });
	    
	


	}

}
