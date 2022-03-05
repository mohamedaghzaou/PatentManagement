package com.ismo.brevets.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ismo.brevets.dao.DaoEntreprise;
import com.ismo.brevets.dao.DaoInvention;
import com.ismo.brevets.util.Graphics;
import com.ismo.brevets.util.OpenReport;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;

	private Map<String, JInternalFrame> internalFrames = new HashMap<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setTitle("Gestion des brevets");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 442);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnInventeur = new JMenu("Inventeurs");
		menuBar.add(mnInventeur);

		JMenuItem mntmListeDesInventeurs = new JMenuItem("Liste des inventeurs");
		mntmListeDesInventeurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getInternalFrame("inventeur");
			}
		});
		mnInventeur.add(mntmListeDesInventeurs);

		JMenu mnInventions = new JMenu("Inventions");
		menuBar.add(mnInventions);

		JMenuItem mntmListeDesInventions = new JMenuItem("Liste des inventions");
		mntmListeDesInventions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getInternalFrame("invention");

			}
		});
		mnInventions.add(mntmListeDesInventions);

		JMenu mnEntreprises = new JMenu("Entreprises");
		menuBar.add(mnEntreprises);

		JMenuItem mntmListeDesEntreprises = new JMenuItem("Liste des entreprises");
		mntmListeDesEntreprises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInternalFrame("entreprise");
			}
		});
		mnEntreprises.add(mntmListeDesEntreprises);

		JMenu mnBrevets = new JMenu("Brevets");
		menuBar.add(mnBrevets);

		JMenuItem mntmListeDesBrevets = new JMenuItem("Liste des brevets");
		mntmListeDesBrevets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInternalFrame("brevet");

			}
		});
		mnBrevets.add(mntmListeDesBrevets);

		JMenu mnReporting = new JMenu("Reporting");
		menuBar.add(mnReporting);

		JMenuItem mntmListeDesInventeurs_1 = new JMenuItem("Liste des inventeurs");
		mntmListeDesInventeurs_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenReport.Open("RptInventeur", "List des Inventeur");
			}
		});
		mnReporting.add(mntmListeDesInventeurs_1);

		JMenuItem mntmListeDesInventions_1 = new JMenuItem("Liste des inventions");
		mntmListeDesInventions_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenReport.Open("RptInvention", "List des Inventions");

			}
		});
		mnReporting.add(mntmListeDesInventions_1);

		JMenuItem mntmListeDesEntreprises_1 = new JMenuItem("Liste des entreprises");
		mntmListeDesEntreprises_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenReport.Open("RptEntreprise", "List des Entreprises");
			}
		});
		mnReporting.add(mntmListeDesEntreprises_1);

		JMenuItem mntmListeDesBrevets_1 = new JMenuItem("Liste des brevets");
		mntmListeDesBrevets_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenReport.Open("RptBrevet", "List des Brevets");

			}
		});
		mnReporting.add(mntmListeDesBrevets_1);

		JMenu mnGraphes = new JMenu("Graphes");
		menuBar.add(mnGraphes);

		JMenuItem mntmInventionParEntreprise = new JMenuItem("Invention par entreprise");
		mntmInventionParEntreprise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInternalFrame("inventionParEntreprise");

			}
		});
		mnGraphes.add(mntmInventionParEntreprise);

		JMenuItem mntmInventionParDomaine = new JMenuItem("Invention par domaine");
		mntmInventionParDomaine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInternalFrame("inventionParDomaine");
			}
		});
		mnGraphes.add(mntmInventionParDomaine);

		JMenu mnAPropos = new JMenu("A propos");
		menuBar.add(mnAPropos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	void getInternalFrame(String i) {
		JInternalFrame l = null;
		if (internalFrames.containsKey(i)) {
			internalFrames.get(i).setVisible(false);
			desktopPane.remove(internalFrames.get(i));
		}

		switch (i) {
		case "inventeur":
			l = new LstInventeur();
			desktopPane.add(l);
			l.setVisible(true);
			internalFrames.put("inventeur", l);
			break;
		case "invention":
			l = new LstInvention();
			desktopPane.add(l);
			l.setVisible(true);
			internalFrames.put("invention", l);
			break;
		case "entreprise":
			l = new LstEntreprise();
			desktopPane.add(l);
			l.setVisible(true);
			internalFrames.put("entreprise", l);
			break;
		case "brevet":
			l = new LstBrevet();
			desktopPane.add(l);
			l.setVisible(true);
			internalFrames.put("brevet", l);
			break;
		case "inventionParEntreprise":
			DaoEntreprise ee = new DaoEntreprise();
			List<Object[]> list = ee.InventionParEntreprise();
			l = Graphics.ShowGraphic(list, "Graphic des inventions Par entreprise", "Invention Par Entreprise",
					"Nombre des inventions", "Nom des Entreprises");
			desktopPane.add(l);
			l.setVisible(true);
			internalFrames.put("inventionParEntreprise", l);
			break;
		case "inventionParDomaine":
			DaoInvention domaine = new DaoInvention();
			list = domaine.inoventionParDomaine();
			l = Graphics.ShowGraphic(list, "Graphic des inventions Par domaine", "Invention Par domaine",
					"Nombbre des inventions", "Nom des Domaine");
			desktopPane.add(l);
			l.setVisible(true);
			internalFrames.put("inventionParDomaine", l);
			break;
		}

	}
}
