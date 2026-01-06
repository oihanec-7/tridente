package gui;

import java.util.ArrayList;
import java.util.List;

import domain.Contenido;
import domain.Pelicula;

public class OrdenadorRecursivo {
	
	// Merge sort recursivo para ordenar peliculas por duracion ascendente
	
	public static ArrayList<Contenido> ordenarPorDuracionAsc(List<Contenido> contenidos){
		
		// Caso base: lista con 0 o 1 elemento ya esta ordenada
		if (peliculas.size() <= 1) {
			return new ArrayList<>(peliculas);
		}
		// Dididir la lista a la mitad
		int medio = peliculas.size() / 2;
		List<Pelicula> izquierda = ordenarPorDuracionAsc(peliculas.subList(0, medio));
		List<Pelicula> derecha = ordenarPorDuracionAsc(peliculas.subList(medio, peliculas.size()));
		
		// Combinar las mitades ordenadas
		return fusionarPorDuracionAsc(izquierda, derecha);
		
		
		
	}
	
	private static List<Pelicula> fusionarPorDuracionAsc(List<Pelicula> izq, List<Pelicula> der){
		List<Pelicula> resultado = new ArrayList<>();
		int i = 0;
		int j = 0;
		
		// Comparar elementos de las dos listas y se añade el menor
		while (i < izq.size() && j < der.size()) {
			if (izq.get(i).getDuracion() <= der.get(j).getDuracion()) {
				resultado.add(izq.get(i));
				i++;
			} else {
				resultado.add(der.get(j));
				j++;
			}
		}
		
		// Se añaden los elementos restantes de la lista izquierda
		while (i < izq.size()) {
			resultado.add(izq.get(i));
			i++;
		}
		// Se añaden los elementos restantes de la lista derecha
		while (j < der.size()) {
			resultado.add(der.get(j));
			j++;
		}
		return resultado;
	}
	
	// Merge Sort recursivo para ordenar peliculas por duracion en orden descendente
	
	public static List<Pelicula> ordenarPorDuracionDesc(List<Pelicula> peliculas){
		
		if (peliculas.size() <= 1) {
			return new ArrayList<>(peliculas);
		}
		
		int medio = peliculas.size() / 2;
		List<Pelicula> izquierda = ordenarPorDuracionDesc(peliculas.subList(0, medio));
		List<Pelicula> derecha = ordenarPorDuracionAsc(peliculas.subList(medio, peliculas.size()));
		
		return fusionarPorDuracionDesc(izquierda, derecha);
		
	
	}
	private static List<Pelicula> fusionarPorDuracionDesc(List<Pelicula> izq, List<Pelicula> der){
		List<Pelicula> resultado = new ArrayList<>();
		int i = 0;
		int j = 0;
		
		while (i < izq.size() && j < der.size()) {
			// Comprara en orden inverso para descendente
			if (izq.get(i).getDuracion() >= der.get(j).getDuracion()) {
				resultado.add(izq.get(i));
				i++;
			} else {
				resultado.add(der.get(j));
				j++;
			}
		}
		
		while (i < izq.size()) {
			resultado.add(izq.get(i));
			i++;
		}
		while( j < der.size()) {
			resultado.add(der.get(j));
			j++;
		}
		return resultado;
	}
	
	// Merge Sort que permite ordenar por diferentes criterios
	
	public static List<Pelicula> ordenarPorCriterio(List<Pelicula> peliculas, String criterio, boolean ascendente){
		
		if (peliculas.size() <= 1) {
			return new ArrayList<>(peliculas);
		}
		int medio = peliculas.size() / 2;
		
		List<Pelicula> izquierda = ordenarPorCriterio(peliculas.subList(0, medio), criterio, ascendente);
		List<Pelicula> derecha = ordenarPorCriterio(peliculas.subList(medio, peliculas.size()), criterio, ascendente);
		
		return fusionarPorCriterio(izquierda, derecha, criterio, ascendente);
	}
				
	private static List<Pelicula> fusionarPorCriterio(List<Pelicula> izq, List<Pelicula> der, String criterio, boolean ascendente){
		
		List<Pelicula> resultado = new ArrayList<>();
		int i = 0;
		int j = 0;
		
		while (i < izq.size() && j < der.size()) {
			boolean agregarIzquierda = false;
			
			switch(criterio.toLowerCase()) {
				case "duracion":
					if (ascendente) {
						agregarIzquierda = izq.get(i).getDuracion() <= der.get(j).getDuracion();
					} else {
						agregarIzquierda = izq.get(i).getDuracion() >= der.get(j).getDuracion();
						
					}
					break;
				
				case "titulo":
					if (ascendente) {
						agregarIzquierda = izq.get(i).getTitulo().compareToIgnoreCase(der.get(j).getTitulo()) <= 0;
					} else {
						agregarIzquierda = izq.get(i).getTitulo().compareToIgnoreCase(der.get(j).getTitulo()) >= 0;
					}
					break;
				default:
					agregarIzquierda = izq.get(i).getDuracion() <= der.get(j).getDuracion();
			}
			if (agregarIzquierda) {
				resultado.add(izq.get(i));
				i++;
			} else {
				resultado.add(der.get(j));
				j++;
			}
		}
		while (i < izq.size()) {
			resultado.add(izq.get(i));
			i ++;
		}
		while (j < der.size()) {
			resultado.add(der.get(j));
			j++;
		}
		return resultado;				
				
		
	}
	
}
