package com.ismo.brevets.ihm.AbstractModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ismo.brevets.models.Entreprise;
import com.ismo.brevets.models.Inventeur;

public class EntrpriseAbstractModel extends AbstractTableModel {

	private List<Entreprise> entreprises;
	private String headers[] = { "NUM", "NOM ENTREPRISE", "ACTIVITE", "CA", "VILLE" };

	public EntrpriseAbstractModel(List<Entreprise> all) {
		entreprises = all;
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}

	@Override
	public int getRowCount() {
		return entreprises.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Entreprise b = entreprises.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return b.getNum();
		case 1:
			return b.getNom();
		case 2:
			return b.getActivite();
		case 3:
			return b.getCa();
		case 4:
			return b.getVille();
		}
		return null;
	}

	public void add(Entreprise i) {
		entreprises.add(i);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public void remove(int i) {
		entreprises.remove(i);
		fireTableRowsDeleted(i - 1, i - 1);
	}

	public void update(Entreprise im, int i) {
		entreprises.set(i, im);
		fireTableRowsUpdated(i, i);

	}

}
