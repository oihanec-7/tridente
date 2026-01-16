package gui;

import java.util.ArrayList;
import java.util.List;

import domain.Contenido;

public class OrdenadorRecursivo {
	
	// Merge sort recursivo para ordenar peliculas por duracion ascendente
	
	public static ArrayList<Contenido> ordenarPorPuntuacionAsc(List<Contenido> contenidos){
		
		// Caso base: lista con 0 o 1 elemento ya esta ordenada
		if (contenidos.size() <= 1) {
			return new ArrayList<>(contenidos);
		}
		// Dididir la lista a la mitad
		int medio = contenidos.size() / 2;
		ArrayList<Contenido> izquierda = ordenarPorPuntuacionAsc(new ArrayList<>(contenidos.subList(0, medio)));
		ArrayList<Contenido> derecha = ordenarPorPuntuacionAsc(new ArrayList<>(contenidos.subList(medio, contenidos.size())));
		
		// Combinar las mitades ordenadas
		return fusionarPorPuntuacionAsc(izquierda, derecha);
		
		
		
	}
	
	private static ArrayList<Contenido> fusionarPorPuntuacionAsc(ArrayList<Contenido> izq, ArrayList<Contenido> der){
		ArrayList<Contenido> resultado = new ArrayList<>();
		int i = 0;
		int j = 0;
		
		// Comparar elementos de las dos listas y se añade el menor
		while (i < izq.size() && j < der.size()) {
			if (izq.get(i).getPuntuacionMedia() <= der.get(j).getPuntuacionMedia()) {
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
	
	public static ArrayList<Contenido> ordenarPorPuntuacionDesc(List<Contenido> contenidos){
		
		if (contenidos.size() <= 1) {
			return new ArrayList<>(contenidos);
		}
		
		int medio = contenidos.size() / 2;
		ArrayList<Contenido> izquierda = ordenarPorPuntuacionDesc(new ArrayList<>(contenidos.subList(0, medio)));
		ArrayList<Contenido> derecha = ordenarPorPuntuacionDesc(new ArrayList<>(contenidos.subList(medio, contenidos.size())));
		
		return fusionarPorPuntuacionDesc(izquierda, derecha);
		
	
	}
	private static ArrayList<Contenido> fusionarPorPuntuacionDesc(ArrayList<Contenido> izq, ArrayList<Contenido> der){
		ArrayList<Contenido> resultado = new ArrayList<>();
		int i = 0;
		int j = 0;
		
		while (i < izq.size() && j < der.size()) {
			// Comprara en orden inverso para descendente
			if (izq.get(i).getPuntuacionMedia() >= der.get(j).getPuntuacionMedia()) {
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
	
	
	// Métodos para titulo de la A-Z y de la Z-A
	public static ArrayList<Contenido> ordenarPorTituloAsc(List<Contenido> contenidos){
		if(contenidos.size() <= 1) {
			return new ArrayList<>(contenidos);
		}
		int medio = contenidos.size() / 2;
		ArrayList<Contenido> izquierda = ordenarPorTituloAsc(new ArrayList<>(contenidos.subList(0, medio)));
		ArrayList<Contenido> derecha = ordenarPorTituloAsc(new ArrayList<>(contenidos.subList(medio, contenidos.size())));
		
		return fusionarPorTituloAsc(izquierda, derecha);
	}
	
	
	private static ArrayList<Contenido> fusionarPorTituloAsc(ArrayList<Contenido> izq, ArrayList<Contenido> der){
		ArrayList<Contenido> resultado = new ArrayList<>();
		int i = 0; 
		int j = 0;
		
		while (i < izq.size() && j < der.size()) {
			
			if(izq.get(i).getTitulo().compareToIgnoreCase(der.get(j).getTitulo()) <= 0) {
				resultado.add(izq.get(i++));
			} else {
				resultado.add(der.get(j++));
			}
		}
		while (i < izq.size()) resultado.add(izq.get(i++));
		while (j < der.size()) resultado.add(der.get(j++));
		
		return resultado;
		}
	
	
	public static ArrayList<Contenido> ordenarPorTituloDesc(List<Contenido> contenidos){
		if(contenidos.size() <= 1) {
			return new ArrayList<>(contenidos);
		}
		int medio = contenidos.size() / 2;
		ArrayList<Contenido> izquierda = ordenarPorTituloDesc(new ArrayList<>(contenidos.subList(0, medio)));
		ArrayList<Contenido> derecha = ordenarPorTituloDesc(new ArrayList<>(contenidos.subList(medio, contenidos.size())));
		
		return fusionarPorTituloDesc(izquierda, derecha);
	}
	
	
	private static ArrayList<Contenido> fusionarPorTituloDesc(ArrayList<Contenido> izq, ArrayList<Contenido> der){
		ArrayList<Contenido> resultado = new ArrayList<>();
		int i = 0;
		int j = 0;
		
		while (i < izq.size() && j < der.size()) {
			if(izq.get(i).getTitulo().compareToIgnoreCase(der.get(j).getTitulo()) >= 0) {
				resultado.add(izq.get(i++));
			} else {
				resultado.add(der.get(j++));
			}
		}
		while(i < izq.size()) resultado.add(izq.get(i++));
		while(j < der.size()) resultado.add(der.get(j++));
		
		return resultado;
	}
	
		
}