package com.ismo.brevets.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ismo.brevets.ihm.AbstractModel.InventionAbstractModel;
import com.ismo.brevets.metier.IMetier;
import com.ismo.brevets.metier.METIER;
import com.ismo.brevets.metier.MetierFactory;
import com.ismo.brevets.models.Domaine;
import com.ismo.brevets.models.Invention;

public class LstInvention extends JInternalFrame {

	private JTable table;
	private JTextField txtNum;
	private JTextField txtResume;
	private JTextField txtDescriptif;
	private JComboBox<Domaine> cBDomaine;
	private JButton btnAjouter;
	private JButton btnEnregistre;
	private JButton btnModifier;
	private JButton btnAnnuller;
	private JButton btnSupprimer;
	private String op = "add";
	private int selectedRowIndex = -1;

	public LstInvention() {
		setTitle("Gestion des Inventions");
		setBounds(100, 100, 593, 398);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 60, 295, 132);

		JPanel scrollTop = new JPanel();
		scrollTop.setPreferredSize(new Dimension(1, 100));
		JPanel scrollBottom = new JPanel();
		scrollBottom.setPreferredSize(new Dimension(1, 100));

		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(scrollTop, BorderLayout.NORTH);
		getContentPane().add(scrollBottom, BorderLayout.SOUTH);
		scrollBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

		btnAjouter = new JButton("Ajouter");
		scrollBottom.add(btnAjouter);

		btnModifier = new JButton("Modifier");
		scrollBottom.add(btnModifier);

		btnSupprimer = new JButton("Supprimer");
		scrollBottom.add(btnSupprimer);

		btnEnregistre = new JButton("Enregistre");

		btnEnregistre.setVisible(false);
		scrollBottom.add(btnEnregistre);

		btnAnnuller = new JButton("Annuller");

		btnAnnuller.setVisible(false);
		scrollBottom.add(btnAnnuller);
		scrollTop.setLayout(null);

		JLabel lblNum = new JLabel("Num :");
		lblNum.setBounds(37, 13, 76, 25);
		scrollTop.add(lblNum);

		JLabel lblResume = new JLabel("Resume :");
		lblResume.setBounds(149, 13, 76, 25);
		scrollTop.add(lblResume);

		JLabel lblDescriptif = new JLabel("Descriptif :");
		lblDescriptif.setBounds(307, 13, 76, 25);
		scrollTop.add(lblDescriptif);

		JLabel lblDomaine = new JLabel("Domaine :");
		lblDomaine.setBounds(442, 13, 76, 25);
		scrollTop.add(lblDomaine);

		txtNum = new JTextField();
		txtNum.setBounds(37, 50, 76, 25);
		txtNum.setEditable(false);
		scrollTop.add(txtNum);
		txtNum.setColumns(10);

		txtResume = new JTextField();
		txtResume.setBounds(149, 50, 127, 25);
		txtResume.setEditable(false);
		txtResume.setColumns(10);
		scrollTop.add(txtResume);

		txtDescriptif = new JTextField();
		txtDescriptif.setBounds(307, 50, 112, 25);
		txtDescriptif.setEditable(false);
		txtDescriptif.setColumns(10);
		scrollTop.add(txtDescriptif);

		cBDomaine = new JComboBox<Domaine>();
		cBDomaine.setBounds(442, 50, 98, 25);
		scrollTop.add(cBDomaine);
		table = new JTable();
		table.setFillsViewportHeight(true);

		table.setPreferredScrollableViewportSize(new Dimension(440, 377));
		table.setBounds(64, 60, 295, 132);
		setTitle("Gestion des Inventions");
		//
		//
		//

		IMetier<Invention> daoInvention = MetierFactory.getMetier(METIER.INVENTION);
		InventionAbstractModel inovetionModel = new InventionAbstractModel(daoInvention.getAll());
		table.setModel(inovetionModel);

		IMetier<Domaine> domaine = MetierFactory.getMetier(METIER.DOMAINE);
		//
		domaine.getAll().forEach(cBDomaine::addItem);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				txtResume.setText(String.valueOf(inovetionModel.getValueAt(table.getSelectedRow(), 1)));
				txtDescriptif.setText(String.valueOf(inovetionModel.getValueAt(table.getSelectedRow(), 2)));
				txtNum.setText(String.valueOf(inovetionModel.getValueAt(table.getSelectedRow(), 0)));
				cBDomaine.setSelectedItem(inovetionModel.getValueAt(table.getSelectedRow(), 3));
				selectedRowIndex = table.getSelectedRow();
			}
		});

		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activateControls(true);
				op = "add";
				txtNum.setText("");
				txtDescriptif.setText("");
				txtResume.setText("");
			}
		});
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnee un Line");
					return;
				}
				activateControls(true);
				op = "edit";
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
					daoInvention.delete(new Invention(id));
					inovetionModel.remove(table.getSelectedRow());
				}
			}
		});
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activateControls(false);
				selectedRowIndex = table.getSelectedRow();
			}
		});
		btnEnregistre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtDescriptif.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Descriptif champ est vide");
					return;
				}
				if (txtResume.getText().isBlank()) {

					JOptionPane.showMessageDialog(null, "Resume champ est vide");
					return;
				}
				activateControls(false);

				Invention i = new Invention(txtDescriptif.getText(), txtResume.getText(),
						cBDomaine.getItemAt(cBDomaine.getSelectedIndex()));
				if (op.equals("add")) {
					daoInvention.save(i);
					inovetionModel.add(i);
					return;
				}

				i.setNum(Integer.parseInt(txtNum.getText()));
				daoInvention.update(i);
				inovetionModel.update(i, selectedRowIndex);
			}
		});

		scrollPane.setViewportView(table);

	}

	void activateControls(boolean b) {
		table.setEnabled(!b);
		txtResume.setEditable(b);
		txtDescriptif.setEditable(b);
		cBDomaine.setEditable(b);
		btnAjouter.setVisible(!b);
		btnModifier.setVisible(!b);
		btnSupprimer.setVisible(!b);
		btnEnregistre.setVisible(b);
		btnAnnuller.setVisible(b);
	}
}
