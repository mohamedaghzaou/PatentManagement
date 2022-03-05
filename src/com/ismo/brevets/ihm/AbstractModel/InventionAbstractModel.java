package com.ismo.brevets.ihm.AbstractModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ismo.brevets.models.Invention;

public class InventionAbstractModel extends AbstractTableModel {

	private List<Invention> inventions;
	private String headers[] = { "NUM INVENTION", "RESUME", "DESCRIPTIF", "DOMAINE" };

	public InventionAbstractModel(List<Invention> all) {
		inventions = all;
	}

	@Override
	public String getColumnName(int column) {
		return headers[column];
	}

	@Override
	public int getRowCount() {
		return inventions.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Invention b = inventions.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return b.getNum();
		case 1:
			return b.getResume();
		case 2:
			return b.getDescriptif();
		case 3:
			return b.getDomaine();
		}
		return null;
	}

	public void add(Invention i) {
		inventions.add(i);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	public void remove(int i) {
		inventions.remove(i);
		fireTableRowsDeleted(i - 1, i - 1);

	}

	public void update(Invention im, int i) {
		inventions.set(i, im);
		fireTableRowsUpdated(i, i);
	}
}
