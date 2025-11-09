package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Recomendador {
		
	public static ArrayList<Contenido> recomendarPorGenero(Usuario usuario, ArrayList<Contenido> catalogo){
		ArrayList<String> generosUsuario = new ArrayList<String>();
		for (Contenido contenido : usuario.getMiLista()) {
			for (String genero : contenido.getGenero()) {
				if (!generosUsuario.contains(genero)) {
					generosUsuario.add(genero);
				}
			}
		}
		
	    Set<Contenido> recomendadasSet = new HashSet<>(); 
		for (Contenido cont : catalogo) {
			if(!usuario.getMiLista().contains(cont)) {
				for (String gen : cont.getGenero()) {
					if(generosUsuario.contains(gen)) {
						recomendadasSet.add(cont);
					}
				}
			}
		}	
		return new ArrayList<Contenido>(recomendadasSet);
	}

}
