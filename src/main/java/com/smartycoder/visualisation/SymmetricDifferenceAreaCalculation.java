package com.smartycoder.visualisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import com.smartycoder.ui.DrawingCommand;
import com.smartycoder.ui.JTSVisualisationPanel;
/**
 * 
 * @see https://www.smartycoder.com
 *
 */
public class SymmetricDifferenceAreaCalculation {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSVisualisationPanel panel = new JTSVisualisationPanel();

				JFrame frame = new JFrame("JTS Visualisation - Symmetric Difference Area Calculation");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates = new Coordinate[] { new Coordinate(150, 60), 
						new Coordinate(325, 180),new Coordinate(90, 150), new Coordinate(150, 60)};

				Coordinate[] coordinates2 = new Coordinate[] { new Coordinate(210, 120), new Coordinate(350, 120),
						new Coordinate(400, 210),new Coordinate(250, 300), new Coordinate(180, 150), new Coordinate(210, 120)};
				
				Polygon bluePolygon = geometryFactory.createPolygon(coordinates);
				
				Polygon yellowPolygon = geometryFactory.createPolygon(coordinates2);
				
				MultiPolygon symmetricDifferencePolygon = (MultiPolygon) yellowPolygon.symDifference(bluePolygon);
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						Coordinate[] coords = symmetricDifferencePolygon.getCoordinates();
						
						int[] xler = new int[coords.length];
						int[] yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(new Color(Color.ORANGE.getRed(), Color.ORANGE.getGreen(),Color.ORANGE.getBlue(), 200));
						
						g.fillPolygon(xler, yler, coords.length);
						
						g.setColor(Color.white);
						
						g.drawString("The union of two differences of polygons from each other:", 60, 330);
						
						g.drawString("Symmetric Difference", 60, 350);

						
					}
				});

			}
		});

	}
}
