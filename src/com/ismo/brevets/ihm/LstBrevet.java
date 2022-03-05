package com.ismo.brevets.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.ismo.brevets.dao.DaoInventeur;
import com.ismo.brevets.dao.DaoInvention;
import com.ismo.brevets.ihm.AbstractModel.BrevetAbstractModel;
import com.ismo.brevets.metier.IMetier;
import com.ismo.brevets.metier.METIER;
import com.ismo.brevets.metier.MetierFactory;
import com.ismo.brevets.models.Brevet;
import com.ismo.brevets.models.Inventeur;
import com.ismo.brevets.models.Invention;
import com.toedter.calendar.JDateChooser;

public class LstBrevet extends JInternalFrame {
	private JTable table;
	private JTextField txtNum;
	private JComboBox<Invention> cmbInvention;
	private JComboBox<Inventeur> cmbInventeur;
	private JDateChooser DtdateValidation;
	private JDateChooser dtDateDepot;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnEnregistre;
	private JButton btnAnnuller;
	private JPanel TopPanel, BottomPanel;
	private BrevetAbstractModel brevetModel;
	private JTextField txtDescription;
	private int selectedRowIndex = -1;
	private String op = "add";
	private IMetier<Brevet> daoBrevet;

	public LstBrevet() {
		setBounds(100, 100, 916, 484);

		JScrollPane scrollPane = new JScrollPane();
		TopPanel = new JPanel();
		TopPanel.setVisible(false);
		TopPanel.setPreferredSize(new Dimension(10, 100));
		BottomPanel = new JPanel();
		BottomPanel.setPreferredSize(new Dimension(10, 100));

		scrollPane.setBounds(64, 60, 295, 132);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(TopPanel, BorderLayout.NORTH);
		TopPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Num :");
		lblNewLabel.setBounds(34, 25, 46, 14);
		TopPanel.add(lblNewLabel);

		txtNum = new JTextField();
		txtNum.setVisible(false);
		txtNum.setBounds(34, 57, 110, 20);
		TopPanel.add(txtNum);
		txtNum.setColumns(10);

		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setBounds(178, 25, 84, 14);
		TopPanel.add(lblDescription);

		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(178, 57, 110, 20);
		TopPanel.add(txtDescription);

		dtDateDepot = new JDateChooser();
		dtDateDepot.setBounds(322, 57, 110, 20);
		TopPanel.add(dtDateDepot);

		JLabel lblNewLabel_1_1 = new JLabel("Date Depot : ");
		lblNewLabel_1_1.setBounds(322, 25, 84, 14);
		TopPanel.add(lblNewLabel_1_1);

		DtdateValidation = new JDateChooser();
		DtdateValidation.setBounds(466, 57, 110, 20);
		TopPanel.add(DtdateValidation);

		JLabel lblNewLabel_1_1_1 = new JLabel("Date Validation :");
		lblNewLabel_1_1_1.setBounds(466, 25, 95, 14);
		TopPanel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Inventeur :");
		lblNewLabel_1_1_1_1.setBounds(610, 25, 70, 14);
		TopPanel.add(lblNewLabel_1_1_1_1);

		cmbInventeur = new JComboBox<Inventeur>();
		cmbInventeur.setBounds(610, 56, 110, 22);
		TopPanel.add(cmbInventeur);

		cmbInvention = new JComboBox<Invention>();
		cmbInvention.setBounds(754, 55, 110, 22);
		TopPanel.add(cmbInvention);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Invention :");
		lblNewLabel_1_1_1_1_1.setBounds(754, 24, 84, 14);
		TopPanel.add(lblNewLabel_1_1_1_1_1);
		getContentPane().add(BottomPanel, BorderLayout.SOUTH);

		btnAjouter = new JButton("Ajouter");

		BottomPanel.add(btnAjouter);

		btnModifier = new JButton("Modifier");
		BottomPanel.add(btnModifier);

		btnSupprimer = new JButton("Supprimer");
		BottomPanel.add(btnSupprimer);

		btnEnregistre = new JButton("Enregistre");
		btnEnregistre.setVisible(false);
		BottomPanel.add(btnEnregistre);

		btnAnnuller = new JButton("Annuller");
		btnAnnuller.setVisible(false);

		BottomPanel.add(btnAnnuller);

		table = new JTable();
		daoBrevet =MetierFactory.getMetier(METIER.BREVET);
		brevetModel = new BrevetAbstractModel(daoBrevet.getAll());
		table.setModel(brevetModel);
		scrollPane.setViewportView(table);

		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activateControls(true);

			}
		});

		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activateControls(false);

			}
		});

		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnee un Line");
					return;
				}
				activateControls(true);
				fill();
				op = "edit";
				selectedRowIndex = table.getSelectedRow();
			}
		});

		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnee un Line");
					return;
				}

				int r = JOptionPane.showConfirmDialog(null, "voulez vous vraiment Supprimer;", "Supprimer",
						JOptionPane.YES_NO_OPTION);
				if (r == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(txtNum.getText());
					daoBrevet.delete(new Brevet(id));
					brevetModel.remove(table.getSelectedRow());
				}
			}
		});

		btnEnregistre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {

				if (txtDescription.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Description champ est vide");
					return;
				}
				if (dtDateDepot.getDate() == null) {

					JOptionPane.showMessageDialog(null, "date depot champ est vide");
					return;
				}
				if (DtdateValidation.getDate() == null) {

					JOptionPane.showMessageDialog(null, "date validation champ est vide");
					return;
				}

				activateControls(false);

				LocalDate d = dtDateDepot.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate dd = DtdateValidation.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				Brevet brevet = new Brevet(txtDescription.getText(), d, dd,
						cmbInvention.getItemAt(cmbInvention.getSelectedIndex()),

						cmbInventeur.getItemAt(cmbInventeur.getSelectedIndex()));
				if (op.equals("add")) {
					daoBrevet.save(brevet);
					brevetModel.add(brevet);
					txtNum.setText(String.valueOf(brevet.getNum()));
					return;
				}

				brevet.setNum(Integer.parseInt(txtNum.getText()));
				daoBrevet.update(brevet);
				brevetModel.update(brevet, selectedRowIndex);
			}
		});

	}

	void activateControls(boolean b) {
		table.setEnabled(!b);
		TopPanel.setVisible(b);
		txtNum.setVisible(!b);
		btnAjouter.setVisible(!b);
		btnModifier.setVisible(!b);
		btnSupprimer.setVisible(!b);
		btnEnregistre.setVisible(b);
		btnAnnuller.setVisible(b);
		if (cmbInventeur.getItemCount() == 0) {
			DaoInventeur doaInventeur = new DaoInventeur();
			doaInventeur.getAll().forEach(cmbInventeur::addItem);
		}
		if (cmbInvention.getItemCount() == 0) {
			DaoInvention doaInvention = new DaoInvention();
			doaInvention.getAll().forEach(cmbInvention::addItem);
		}
	}

	void fill() {
		txtNum.setText(String.valueOf(brevetModel.getValueAt(table.getSelectedRow(), 0)));
		txtDescription.setText(String.valueOf(brevetModel.getValueAt(table.getSelectedRow(), 1)));
		LocalDate d = (LocalDate) brevetModel.getValueAt(table.getSelectedRow(), 2);
		LocalDate dd = (LocalDate) brevetModel.getValueAt(table.getSelectedRow(), 3);
		dtDateDepot.setDate(Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		DtdateValidation.setDate(Date.from(dd.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		cmbInventeur.setSelectedItem(brevetModel.getValueAt(table.getSelectedRow(), 4));
		cmbInvention.setSelectedItem(brevetModel.getValueAt(table.getSelectedRow(), 5));
	}
}
