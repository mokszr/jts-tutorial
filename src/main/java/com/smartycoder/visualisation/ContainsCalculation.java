package com.smartycoder.visualisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import com.smartycoder.ui.DrawingCommand;
import com.smartycoder.ui.JTSVisualisationPanel;
/**
 * 
 * @see https://www.smartycoder.com
 *
 */
public class ContainsCalculation {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSVisualisationPanel panel = new JTSVisualisationPanel();

				JFrame frame = new JFrame("JTS Visualisation - Contains Calculation");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates = new Coordinate[] { new Coordinate(250, 60), new Coordinate(300, 150),  new Coordinate(190, 150),new Coordinate(250, 60)};

				Polygon polygon = geometryFactory.createPolygon(coordinates);
				
				Point point = geometryFactory.createPoint(new Coordinate(100, 100));
				
				Point point2 = geometryFactory.createPoint(new Coordinate(250, 85));
				
				boolean polygonContainsPoint1 = polygon.contains(point);
				
				boolean polygonContainsPoint2 = polygon.contains(point2);
				
				boolean point1WithinPolygon = point.within(polygon);
				
				boolean point2WithinPolygon = point2.within(polygon);

				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						Coordinate[] coords = polygon.getCoordinates();
						
						int[] xler = new int[coords.length];
						int[] yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(Color.BLUE);
						
						g.fillPolygon(xler, yler, coords.length);

						g.setColor(Color.WHITE);
						
						g.drawString("(" + xler[0] + ", " + yler[0] + ")"  , 240, 50);
						
						g.drawString("(" + xler[1] + ", " + yler[1] + ")"  , 290, 165);
						
						g.drawString("(" + xler[2] + ", " + yler[2] + ")"  , 180, 165);
						
						g.drawString("(" + (int)point.getX() + ", " + (int)point.getY() + ")"  , 90, 90);
						
						g.fillRect((int)point.getX(), (int)point.getY(), 4, 4);
						
						g.drawString("(" + (int)point2.getX() + ", " + (int)point2.getY() + ")"  , 275, 85);

						g.setColor(Color.YELLOW);

						g.fillRect((int)point2.getX(), (int)point2.getY(), 4, 4);
						
						g.setColor(Color.white);
						
						
						g.drawString("Polygon contains point 1? " + polygonContainsPoint1 , 30, 40);

						g.drawString("Point 1 within polygon? " + point1WithinPolygon , 30, 60);
						
						g.drawString("Polygon contains point 2? " + polygonContainsPoint2 , 200, 200);

						g.drawString("Point 2 within polygon? " + point2WithinPolygon , 200, 220);

					}
				});

			}
		});

	}
}
