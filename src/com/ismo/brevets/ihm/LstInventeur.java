package com.ismo.brevets.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ismo.brevets.ihm.AbstractModel.InventeurAbstractModel;
import com.ismo.brevets.metier.IMetier;
import com.ismo.brevets.metier.METIER;
import com.ismo.brevets.metier.MetierFactory;
import com.ismo.brevets.models.Entreprise;
import com.ismo.brevets.models.Inventeur;
import com.toedter.calendar.JDateChooser;

public class LstInventeur extends JInternalFrame {

	private JTable table;
	private JTextField txtNum;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAdresse;
	private JButton btnAjouter;
	private JButton btnEnregistre;
	private JButton btnModifier;
	private JButton btnAnnuller;
	private JButton btnSupprimer;
	private JComboBox<Entreprise> CmbEnterprise;
	private String op = "add";
	private int selectedRowIndex = -1;
	private JDateChooser dateChooser;

	public LstInventeur() {
		setTitle("Gestion des Inenteurs");

		setBounds(100, 100, 626, 451);

		JScrollPane scrollPane = new JScrollPane();
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(70, 170));

		scrollPane.setBounds(64, 60, 295, 132);
		getContentPane().add(scrollPane);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Num :");
		lblNewLabel.setBounds(50, 14, 46, 14);
		bottomPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nom :");
		lblNewLabel_1.setBounds(50, 40, 46, 14);
		bottomPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Prenom :");
		lblNewLabel_1_1.setBounds(50, 66, 87, 14);
		bottomPanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Adresse :");
		lblNewLabel_1_1_1.setBounds(50, 92, 87, 14);
		bottomPanel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Date Naiss :");
		lblNewLabel_1_1_2.setBounds(50, 118, 87, 14);
		bottomPanel.add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Enterprise :");
		lblNewLabel_1_1_2_1.setBounds(50, 144, 75, 14);
		bottomPanel.add(lblNewLabel_1_1_2_1);

		txtNum = new JTextField();
		txtNum.setEditable(false);
		txtNum.setBounds(158, 11, 165, 20);
		bottomPanel.add(txtNum);
		txtNum.setColumns(10);


		txtNom = new JTextField();
		txtNom.setEditable(false);
		txtNom.setColumns(10);
		txtNom.setBounds(158, 37, 165, 20);
		bottomPanel.add(txtNom);

		txtPrenom = new JTextField();
		txtPrenom.setEditable(false);
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(158, 63, 165, 20);
		bottomPanel.add(txtPrenom);

		txtAdresse = new JTextField();
		txtAdresse.setEditable(false);
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(158, 89, 165, 20);
		bottomPanel.add(txtAdresse);

		dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.getCalendarButton().setSize(165, 20);
		dateChooser.getCalendarButton().setLocation(140, 0);
		dateChooser.setBounds(158, 115, 165, 20);
		bottomPanel.add(dateChooser);

		CmbEnterprise = new JComboBox<Entreprise>();
		CmbEnterprise.setEditable(true);
		CmbEnterprise.setBounds(158, 140, 165, 22);
		bottomPanel.add(CmbEnterprise);


		JPanel panel = new JPanel();
		panel.setBounds(400, 14, 146, 151);
		bottomPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnAjouter = new JButton("Ajouter");

		btnAjouter.setPreferredSize(new Dimension(100, 23));
		panel.add(btnAjouter);

		btnEnregistre = new JButton("Enregistre");

		btnEnregistre.setVisible(false);
		btnEnregistre.setPreferredSize(new Dimension(100, 23));
		panel.add(btnEnregistre);

		btnModifier = new JButton("Modifier");

		btnModifier.setPreferredSize(new Dimension(100, 23));
		panel.add(btnModifier);

		btnAnnuller = new JButton("Annuller");

		btnAnnuller.setVisible(false);
		btnAnnuller.setPreferredSize(new Dimension(100, 23));
		panel.add(btnAnnuller);

		btnSupprimer = new JButton("Supprrimer");

		btnSupprimer.setPreferredSize(new Dimension(100, 23));
		panel.add(btnSupprimer);

		table = new JTable();
		table.setBounds(64, 60, 295, 132);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// my code
		IMetier<Inventeur> daoInventeur = MetierFactory.getMetier(METIER.INVENTEUR);
		InventeurAbstractModel inventeurModel = new InventeurAbstractModel(daoInventeur.getAll());

		IMetier<Entreprise> daoEntreprise = MetierFactory.getMetier(METIER.ENTREPRISE);
		daoEntreprise.getAll().forEach(CmbEnterprise::addItem);

		table.setModel(inventeurModel);
		scrollPane.setViewportView(table);

		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activateControls(true);
				op = "add";
				txtNum.setText("");
				txtNom.setText("");
				txtPrenom.setText("");
				txtAdresse.setText("");
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

				int r = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment Supprimer, Operation va supprimer tout les brevets de cette inveteurs", "Supprimer",
						JOptionPane.YES_NO_OPTION);
				if (r == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(txtNum.getText());
					daoInventeur.delete(new Inventeur(id));
					inventeurModel.remove(table.getSelectedRow());
				}
			}
		});
		btnEnregistre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtNom.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Nom champ est vide");
					return;
				}
				if (txtPrenom.getText().isBlank()) {

					JOptionPane.showMessageDialog(null, "Prenom champ est vide");
					return;
				}
				if (txtAdresse.getText().isBlank()) {

					JOptionPane.showMessageDialog(null, "Adresse champ est vide");
					return;
				}
				if (dateChooser.getDate() == null) {

					JOptionPane.showMessageDialog(null, "Choisi un date est vide");
					return;
				}
				activateControls(false);

				LocalDate d = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				Inventeur i = new Inventeur(txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(), d,
						CmbEnterprise.getItemAt(CmbEnterprise.getSelectedIndex()));
				if (op.equals("add")) {
					daoInventeur.save(i);
					inventeurModel.add(i);
					return;
				}

				i.setNum(Integer.parseInt(txtNum.getText()));
				daoInventeur.update(i);
				inventeurModel.update(i, selectedRowIndex);
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				txtNom.setText(String.valueOf(inventeurModel.getValueAt(table.getSelectedRow(), 1)));
				txtPrenom.setText(String.valueOf(inventeurModel.getValueAt(table.getSelectedRow(), 2)));
				txtAdresse.setText(String.valueOf(inventeurModel.getValueAt(table.getSelectedRow(), 3)));
				txtNum.setText(String.valueOf(inventeurModel.getValueAt(table.getSelectedRow(), 0)));
				CmbEnterprise.setSelectedItem(inventeurModel.getValueAt(table.getSelectedRow(), 5));
				LocalDate d = (LocalDate) inventeurModel.getValueAt(table.getSelectedRow(), 4);
				dateChooser.setDate(Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant()));
				selectedRowIndex = table.getSelectedRow();
			}
		});

	}

	void activateControls(boolean b) {
		table.setEnabled(!b);
		txtNom.setEditable(b);
		txtPrenom.setEditable(b);
		txtAdresse.setEditable(b);
		dateChooser.setEnabled(b);
		CmbEnterprise.setEditable(b);
		btnAjouter.setVisible(!b);
		btnModifier.setVisible(!b);
		btnSupprimer.setVisible(!b);
		btnEnregistre.setVisible(b);
		btnAnnuller.setVisible(b);
	}
}
