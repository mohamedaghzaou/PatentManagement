package com.ismo.brevets.ihm.AbstractModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ismo.brevets.models.Brevet;

public class BrevetAbstractModel extends AbstractTableModel {

	private String[] headers = { "NUM BREVET", "DESCRIPTION", "DATE DEPOT", "DATE VALIDATION", "INVENTEUR",
			"INVENTION" };
	private List<Brevet> brevets;
	
	
	

	public BrevetAbstractModel(List<Brevet> brevets) {
		super();
		this.brevets = brevets;
	}

	@Override
	public String getColumnName(int column) {

		return headers[column];
	}

	@Override
	public int getRowCount() {
		return brevets.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Brevet b = brevets.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return b.getNum();
		case 1:
			return b.getDescription();
		case 2:
			return b.getDateDepot();
		case 3:
			return b.getDateValidation();
		case 4:
			return b.getInventeur();
		case 5:
			return b.getInvention();
		}
		return null;
	}

	public void add(Brevet i) {
		brevets.add(i);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public void remove(int i) {
		brevets.remove(i);
		fireTableRowsDeleted(i - 1, i - 1);
	}

	public void update(Brevet im, int i) {
		brevets.set(i, im);
		fireTableRowsUpdated(i, i);

	}

}
