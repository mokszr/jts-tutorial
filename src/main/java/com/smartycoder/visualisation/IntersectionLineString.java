package com.smartycoder.visualisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

import com.smartycoder.ui.DrawingCommand;
import com.smartycoder.ui.JTSVisualisationPanel;
/**
 * 
 * @see https://www.smartycoder.com
 *
 */
public class IntersectionLineString {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSVisualisationPanel panel = new JTSVisualisationPanel();

				JFrame frame = new JFrame("JTS Visualisation - Intersection LineString");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates = new Coordinate[] { new Coordinate(60, 60), new Coordinate(150, 150) };

				LineString lineString1 = geometryFactory.createLineString(coordinates);

				LineString lineString2 = geometryFactory.createLineString(new Coordinate[] { new Coordinate(110, 20), new Coordinate(30, 250) });

				Point intersectionPoint = (Point) lineString1.intersection(lineString2);
				
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {

						g.setColor(Color.white);
						
						g.drawLine((int) lineString1.getCoordinateN(0).getX(),(int) lineString1.getCoordinateN(0).getY(),
								(int) lineString1.getCoordinateN(1).getX(), (int) lineString1.getCoordinateN(1).getX());
 
						g.drawLine((int) lineString2.getCoordinateN(0).getX(),(int) lineString2.getCoordinateN(0).getY(),
								(int) lineString2.getCoordinateN(1).getX(), (int) lineString2.getCoordinateN(1).getY());
						
						g.setColor(Color.RED);
						
						g.fillRect((int) intersectionPoint.getX(),(int) intersectionPoint.getY(), 10, 10);
						
					}
				});

			}
		});

	}
}
