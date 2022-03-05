package com.ismo.brevets.util;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class OpenReport {

	private static final String DATABASE_NAME = "dbbrevets";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	private static final String FOLDER_NAME = "Reports/";

	public static void Open(String name, String title) {
		Connection conn = null; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DATABASE_NAME, USERNAME, PASSWORD);

			JasperPrint print = JasperFillManager.fillReport(FOLDER_NAME + name + ".jasper", null, conn);
			JRViewer viewer = new JRViewer(print);
			JFrame frame = new JFrame(title);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.getContentPane().add(viewer);
			frame.setSize(new Dimension(750, 650));
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}

	}

}
