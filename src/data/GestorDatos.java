package data;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import domain.Contenido;
import domain.Pelicula;
import domain.Resena;
import domain.Serie;

public class GestorDatos {
	 private ArrayList<Resena> resenas;

	
	 public static ArrayList<Contenido> cargarCSV(String ruta){
		 ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
		 
		 try {
			 File f = new File(ruta);
			 Scanner sc = new Scanner(f);
		     while (sc.hasNextLine()) {
		    	 String linea = sc.nextLine();
		    	 String[] partes = linea.split(";");

		    	 String tipo = partes[0];
		    	 String titulo = partes[1];
		            
		    	 ArrayList<String> generos = new ArrayList<String>();
		    	 for (String genero : partes[2].split(",")) {
		    		 generos.add(genero);
		    	 }
		            
		    	 ArrayList<Double> puntuaciones = new ArrayList<Double>();
		    	 for (String puntuacion : partes[3].split(",")) {
		    		 puntuaciones.add(Double.parseDouble(puntuacion));
		    	 }
		            
		    	 ArrayList<String> cast = new ArrayList<String>();
		    	 for (String actor : partes[4].split(",")) {
		    		 cast.add(actor);
		    	 }

		    	 if (tipo.equalsIgnoreCase("Pelicula")) {
		    		 int duracion = Integer.parseInt(partes[5]);
		    		 Pelicula peli = new Pelicula(titulo, generos, puntuaciones, cast, duracion);
		    		 contenidos.add(peli);
		    	 } else if (tipo.equalsIgnoreCase("Serie")) {
		    		 int temporadas = Integer.parseInt(partes[5]);
		    		 Serie serie = new Serie(titulo, generos, puntuaciones, cast, temporadas); 
		    		 contenidos.add(serie);
		    	 }
		        }
 
	 } catch (Exception e ) {
		 System.err.println("Error al leer el archivo CSV");
	 }
		 return contenidos;
} 
	 
	 
	 
	 
	 public static ArrayList<Contenido> mejorValoradas (ArrayList<Contenido> contenidos) {
		 ArrayList<Contenido> mejorValoradas = new ArrayList<Contenido>();
		 for (Contenido contenido : contenidos) {
			 if(contenido.getPuntuacionMedia() >= 4.5) {
				 mejorValoradas.add(contenido);
			 }
		 }
		 
		 return mejorValoradas;
	 }
	
	 
	 
	 public void agregarResena(Resena r) {
		 resenas.add(r);
	 }

	 public static ArrayList<Contenido> soloPeliculas(ArrayList<Contenido> contenidos){
		 ArrayList<Contenido> peliculas = new ArrayList<Contenido>();
		 for(Contenido c : contenidos) {
			 if(c instanceof Pelicula) {
				 peliculas.add(c);
			 }
		 } 
		 return peliculas;
	 }
	 
	 public static ArrayList<Contenido> soloSeries(ArrayList<Contenido> contenidos){
		 ArrayList<Contenido> series = new ArrayList<Contenido>();
		 for(Contenido c : contenidos) {
			 if(c instanceof Serie) {
				 series.add(c);
			 }
		 } 
		 return series;
	 }

}
