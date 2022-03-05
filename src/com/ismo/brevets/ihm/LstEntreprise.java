package com.ismo.brevets.ihm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ismo.brevets.ihm.AbstractModel.EntrpriseAbstractModel;
import com.ismo.brevets.metier.IMetier;
import com.ismo.brevets.metier.METIER;
import com.ismo.brevets.metier.MetierFactory;
import com.ismo.brevets.models.Entreprise;

public class LstEntreprise extends JInternalFrame {

	private JTable table;
	private JTextField txtVille;
	private JTextField txtCA;
	private JTextField txtActivite;
	private JTextField txtNom;
	private JTextField txtNum;
	private JButton btnAjouter;
	private JButton btnEnregistre;
	private JButton btnAnnuller;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private int selectedRowIndex = -1;
	private String op = "add";

	public LstEntreprise() {
		setBounds(100, 100, 839, 429);

		JScrollPane scrollPane = new JScrollPane();
		JPanel leftPanel = new JPanel();
		Dimension dimension = new Dimension(150, HEIGHT);
		leftPanel.setPreferredSize(dimension);
		JPanel RightPanel = new JPanel();

		RightPanel.setPreferredSize(dimension);

		scrollPane.setBounds(64, 60, 295, 132);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JLabel lblNewLabel = new JLabel("Num :");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setPreferredSize(new Dimension(63, 14));
		leftPanel.add(lblNewLabel);

		txtNum = new JTextField();
		txtNum.setEditable(false);
		txtNum.setSize(new Dimension(21, 0));
		txtNum.setPreferredSize(new Dimension(39, 20));
		leftPanel.add(txtNum);
		txtNum.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nom :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setPreferredSize(new Dimension(63, 14));
		leftPanel.add(lblNewLabel_1);

		txtNom = new JTextField();
		txtNom.setEditable(false);
		txtNom.setSize(new Dimension(21, 0));
		txtNom.setPreferredSize(new Dimension(39, 20));
		txtNom.setColumns(10);
		leftPanel.add(txtNom);

		JLabel lblNewLabel_2 = new JLabel("Activite :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setPreferredSize(new Dimension(63, 14));
		leftPanel.add(lblNewLabel_2);

		txtActivite = new JTextField();
		txtActivite.setEditable(false);
		txtActivite.setSize(new Dimension(21, 0));
		txtActivite.setPreferredSize(new Dimension(39, 20));
		txtActivite.setColumns(10);
		leftPanel.add(txtActivite);

		JLabel lblNewLabel_3 = new JLabel("CA :");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setPreferredSize(new Dimension(63, 14));
		leftPanel.add(lblNewLabel_3);

		txtCA = new JTextField();
		txtCA.setEditable(false);
		txtCA.setSize(new Dimension(21, 0));
		txtCA.setPreferredSize(new Dimension(39, 20));
		txtCA.setColumns(10);
		leftPanel.add(txtCA);

		JLabel lblNewLabel_4 = new JLabel("Ville :");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setPreferredSize(new Dimension(63, 14));
		leftPanel.add(lblNewLabel_4);

		txtVille = new JTextField();
		txtVille.setEditable(false);
		txtVille.setSize(new Dimension(21, 0));
		txtVille.setPreferredSize(new Dimension(39, 20));
		txtVille.setColumns(10);
		leftPanel.add(txtVille);
		getContentPane().add(RightPanel, BorderLayout.EAST);
		RightPanel.setLayout(null);

		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(10, 48, 130, 23);
		RightPanel.add(btnAjouter);

		btnEnregistre = new JButton("Enregistre");
		btnEnregistre.setVisible(false);
		btnEnregistre.setBounds(10, 118, 130, 23);
		RightPanel.add(btnEnregistre);

		btnModifier = new JButton("Modifier");
		btnModifier.setBounds(10, 188, 130, 23);
		RightPanel.add(btnModifier);

		btnAnnuller = new JButton("Annuller");
		btnAnnuller.setVisible(false);
		btnAnnuller.setBounds(10, 258, 130, 23);
		RightPanel.add(btnAnnuller);

		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(10, 328, 130, 23);
		RightPanel.add(btnSupprimer);

		table = new JTable();
		IMetier<Entreprise> daoEntreprise = MetierFactory.getMetier(METIER.ENTREPRISE);
		EntrpriseAbstractModel entrpriseModel = new EntrpriseAbstractModel(daoEntreprise.getAll());
		table.setModel(entrpriseModel);
		scrollPane.setViewportView(table);

		//
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activateControls(true);
				op = "add";
				txtNum.setText("");
				txtNom.setText("");
				txtVille.setText("");
				txtActivite.setText("");
				txtCA.setText("");
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

				int r = JOptionPane.showConfirmDialog(null, "voulez vous vraiment Supprimer;", "Supprimer",
						JOptionPane.YES_NO_OPTION);
				if (r == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(txtNum.getText());
					daoEntreprise.delete(new Entreprise(id));
					entrpriseModel.remove(table.getSelectedRow());
				}
			}
		});

		btnEnregistre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {

				if (txtNom.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Nom champ est vide");
					return;
				}
				if (txtVille.getText().isBlank()) {

					JOptionPane.showMessageDialog(null, "Ville champ est vide");
					return;
				}
				if (txtCA.getText().isBlank()) {

					JOptionPane.showMessageDialog(null, "CA champ est vide");
					return;
				}
				if (txtActivite.getText().isBlank()) {

					JOptionPane.showMessageDialog(null, "Activite champ est vide");
					return;
				}
				activateControls(false);

				Entreprise e = new Entreprise(txtNom.getText(), txtActivite.getText(),
						Double.parseDouble(txtCA.getText()), txtVille.getText());
				if (op.equals("add")) {
					daoEntreprise.save(e);
					entrpriseModel.add(e);
					txtNum.setText(String.valueOf(e.getNum()));
					return;
				}

				e.setNum(Integer.parseInt(txtNum.getText()));
				daoEntreprise.update(e);
				entrpriseModel.update(e, selectedRowIndex);
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				txtNum.setText(String.valueOf(entrpriseModel.getValueAt(table.getSelectedRow(), 0)));
				txtNom.setText(String.valueOf(entrpriseModel.getValueAt(table.getSelectedRow(), 1)));
				txtActivite.setText(String.valueOf(entrpriseModel.getValueAt(table.getSelectedRow(), 2)));
				txtCA.setText(String.valueOf(entrpriseModel.getValueAt(table.getSelectedRow(), 3)));
				txtVille.setText(String.valueOf(entrpriseModel.getValueAt(table.getSelectedRow(), 4)));
				selectedRowIndex = table.getSelectedRow();
			}
		});

	}

	void activateControls(boolean b) {
		table.setEnabled(!b);
		txtNom.setEditable(b);
		txtVille.setEditable(b);
		txtActivite.setEditable(b);
		txtCA.setEditable(b);
		btnAjouter.setVisible(!b);
		btnModifier.setVisible(!b);
		btnSupprimer.setVisible(!b);
		btnEnregistre.setVisible(b);
		btnAnnuller.setVisible(b);
	}
}
