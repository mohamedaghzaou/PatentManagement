package com.ismo.brevets.util;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graphics {

	public static JInternalFrame ShowGraphic(List<Object[]> l, String formTitle, String chartTitle,String xAxisTitle,String yAxisTitle) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		for (Object[] objects : l) {
			dataSet.addValue(Integer.parseInt(String.valueOf(objects[1])), String.valueOf(objects[0]),
					String.valueOf(objects[0]));
		}

		JFreeChart chart1 = ChartFactory.createBarChart(chartTitle, yAxisTitle,
				xAxisTitle, dataSet, PlotOrientation.HORIZONTAL, true, true, true);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(new ChartPanel(chart1));
		JInternalFrame frame = new JInternalFrame();
		frame.setTitle(formTitle);
		frame.setBounds(100, 100, 800, 450);
		frame.add(contentPane);
		return frame;

	}

}
