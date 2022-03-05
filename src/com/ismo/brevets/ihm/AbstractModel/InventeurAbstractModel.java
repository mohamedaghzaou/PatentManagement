package com.ismo.brevets.ihm.AbstractModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ismo.brevets.models.Inventeur;

public class InventeurAbstractModel extends AbstractTableModel {

	private String headers[] = { "NUM INVENTEUR", "NOM", "PRENOM", "ADRESSE", "DATE NAISS", "ENTREPRISE" };
	private List<Inventeur> inventeurs;

	public InventeurAbstractModel(List<Inventeur> inventeurs) {
		super();
		this.inventeurs = inventeurs;
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}

	@Override
	public int getRowCount() {
		return inventeurs.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Inventeur b = inventeurs.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return b.getNum();
		case 1:
			return b.getNom();
		case 2:
			return b.getPrenom();
		case 3:
			return b.getAdresse();
		case 4:
			return b.getDate_nais();
		case 5:
			return b.getEntreprise();
		}
		return null;
	}

	public void add(Inventeur i) {
		inventeurs.add(i);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public void remove(int i) {
		inventeurs.remove(i);
		fireTableRowsDeleted(i - 1, i - 1);
	}

	public void update(Inventeur im, int i) {
		inventeurs.set(i, im);
		fireTableRowsUpdated(i, i);

	}

}
