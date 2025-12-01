
package app;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import domain.Pelicula;
import domain.Serie;
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
		
		
	    SwingUtilities.invokeLater(() -> {
	    	List<Usuario> usuariosPrueba = new ArrayList<>();

            // Crear un usuario con contenido
            Usuario juan = new Usuario("Juan", "juan123", "1234", "Pérez", "juan@email.com");

            // Añadir películas y series a su lista
            ArrayList<String> genero = new ArrayList<>();
            genero.add("Ciencia ficcion");
            genero.add("Accion");
            ArrayList<Double> puntuacione = new ArrayList<>();
            puntuacione.add(4.7);
            puntuacione.add(4.8);
            puntuacione.add(4.6);
            puntuacione.add(4.9);
            ArrayList<String> cast = new ArrayList<>();
            cast.add("Chris Pratt");
            cast.add("Zoe Saldana");
            Pelicula guardians = new Pelicula("Guardians of the Galaxy", genero, puntuacione, cast, "images/guardians_of_the_galaxy.jpg", 121);
//            juan.getMiLista().add(guardians);

            // Serie
            ArrayList<String> generosSerie = new ArrayList<>();
            generosSerie.add("Drama");
            generosSerie.add("Accion");
            ArrayList<Double> puntuacionesSerie = new ArrayList<>();
            puntuacionesSerie.add(5.0);
            puntuacionesSerie.add(4.9);
            puntuacionesSerie.add(4.8);
            puntuacionesSerie.add(5.0);
            ArrayList<String> castSerie = new ArrayList<>();
            castSerie.add("Bryan Cranston");
            castSerie.add("Aaron Paul");
            Serie breakingBad = new Serie("Breaking Bad", generosSerie, puntuacionesSerie, castSerie, "images/breaking_bad.jpg", 5);
//            juan.getMiLista().add(breakingBad);

            // Añadir usuario a la lista de prueba
            usuariosPrueba.add(juan);

            // Otro usuario sin contenido
            usuariosPrueba.add(new Usuario("Ana", "ana456", "abcd", "García", "ana@email.com"));
            
            VentanaLogin login = new VentanaLogin(usuariosPrueba);
            login.setVisible(true);
	        
//            VentanaContenido vc = new VentanaContenido(peliPrueba);

//	       	VentanaContenido vc = new VentanaContenido(peliPrueba);
//	        vc.setVisible(true);
//	        
//	        Usuario usuarioPrueb = new Usuario("Juan", "juan123", "1234", "Pérez", "juan@email.com", 22);
//	        VentanaMiUsuario ventanaPerfil = new VentanaMiUsuario(usuarioPrueb);
//	        ventanaPerfil.setVisible(true);
	        
            
	    });
	    
	


	}

}
