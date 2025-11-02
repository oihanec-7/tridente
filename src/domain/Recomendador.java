package domain;

import java.util.ArrayList;

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
		
		ArrayList<Contenido> recomendados = new ArrayList<Contenido>();
		for (Contenido cont : catalogo) {
			if(!usuario.getMiLista().contains(cont)) {
				for (String gen : cont.getGenero()) {
					if(generosUsuario.contains(gen)) {
						recomendados.add(cont);
					}
				}
			}
		}	
		
		return recomendados;
	}

}
