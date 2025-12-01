package gui;
 
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import domain.Resena;
import domain.Usuario;

public class ModeloDeDatosValoradas extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	private String[] cabecera = {"Contenido", "Fecha", "Valoracion", "Genero"};
	private ArrayList<Resena> listaValoradas;
	 

	public ModeloDeDatosValoradas(ArrayList<Resena> listaValoradas) {
		if(listaValoradas == null) {
			this.listaValoradas = new ArrayList<>();
			
		}else {
			this.listaValoradas = listaValoradas;
			
		}
		
	}
	
	public Resena getResenaAt(int fila) {
		if(fila < 0 || fila >= listaValoradas.size()) {
			return null;
		} else {
			return listaValoradas.get(fila);
		}
	}

	@Override
	public int getRowCount() {	
		return listaValoradas.size();
	}

	@Override
	public int getColumnCount() {
		return cabecera.length;
	}
	

	@Override
	public String getColumnName(int column) {
		return cabecera[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Resena resena = listaValoradas.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return resena.getContenido().getTitulo();
			
		case 1:
			return resena.getFechaResena();
		
		
		case 2:
			return resena.getPuntuacion();
		
		
		case 3:
			return resena.getContenido().getGenero();
		
		default:
			return null;

		}
		
		
		
		
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
	
		return false;
	}
	
	

}
