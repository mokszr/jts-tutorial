package com.smartycoder.visualisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

import com.smartycoder.ui.DrawingCommand;
import com.smartycoder.ui.JTSVisualisationPanel;
/**
 * 
 * @see https://www.smartycoder.com
 *
 */
public class BufferAreaCalculation {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSVisualisationPanel panel = new JTSVisualisationPanel();

				JFrame frame = new JFrame("JTS Visualisation - Buffer Area Calculation");
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
				
				Polygon bufferedYellowPolygon = (Polygon) yellowPolygon.buffer(30);
				
				Polygon bufferedBluePolygon = (Polygon) bluePolygon.buffer(30);
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						Coordinate[] coords = bufferedYellowPolygon.getCoordinates();
						
						int[] xler = new int[coords.length];
						int[] yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(new Color(Color.CYAN.getRed(), Color.CYAN.getGreen(),Color.CYAN.getBlue(), 220));
						
						g.fillPolygon(xler, yler, coords.length);
						
						coords = yellowPolygon.getCoordinates();
						
						xler = new int[coords.length];
						yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(new Color(Color.YELLOW.getRed(), Color.YELLOW.getGreen(),Color.YELLOW.getBlue(), 170));
						
						g.fillPolygon(xler, yler, coords.length);
						
						coords = bufferedBluePolygon.getCoordinates();
						
						xler = new int[coords.length];
						yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(new Color(Color.RED.getRed(), Color.RED.getGreen(),Color.RED.getBlue(), 130));
						
						g.fillPolygon(xler, yler, coords.length);
						
						
						coords = bluePolygon.getCoordinates();
						
						xler = new int[coords.length];
						yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(new Color(Color.BLUE.getRed(), Color.BLUE.getGreen(),Color.BLUE.getBlue(), 160));
						
						g.fillPolygon(xler, yler, coords.length);
						
						g.setColor(Color.WHITE);
						
						g.drawString("30 unit buffered polygons of yellow and blue polygons", 60, 350);
						

						
					}
				});

			}
		});

	}
}
