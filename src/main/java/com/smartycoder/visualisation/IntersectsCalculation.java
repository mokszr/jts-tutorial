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
public class IntersectsCalculation {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSVisualisationPanel panel = new JTSVisualisationPanel();

				JFrame frame = new JFrame("JTS Visualisation - Intersects Calculation");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates = new Coordinate[] { new Coordinate(50, 50), new Coordinate(150, 150) };

				LineString line1 = geometryFactory.createLineString(coordinates);

				LineString line2 = geometryFactory.createLineString(new Coordinate[] { new Coordinate(80, 40), new Coordinate(30, 250) });

				boolean line1IntersectsLine2 = line1.intersects(line2);
				
				Point intersectionPoint = (Point) line1.intersection(line2);
				
				LineString line3 = geometryFactory.createLineString(new Coordinate[] { new Coordinate(210, 75), new Coordinate(350, 170) });

				
				boolean line3DisjointLine1 = line3.disjoint(line1);
				
				boolean line3IntersectsLine1 = line3.intersects(line1);
								
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						g.setColor(Color.white);
						
						g.drawLine((int) line1.getCoordinateN(0).getX(),(int) line1.getCoordinateN(0).getY(),
								(int) line1.getCoordinateN(1).getX(), (int) line1.getCoordinateN(1).getX());
 
						g.drawLine((int) line2.getCoordinateN(0).getX(),(int) line2.getCoordinateN(0).getY(),
								(int) line2.getCoordinateN(1).getX(), (int) line2.getCoordinateN(1).getY());
						
						g.drawLine((int) line3.getCoordinateN(0).getX(),(int) line3.getCoordinateN(0).getY(),
								(int) line3.getCoordinateN(1).getX(), (int) line3.getCoordinateN(1).getY());
						
						g.setColor(Color.RED);
						
						g.fillRect((int) intersectionPoint.getX(),(int) intersectionPoint.getY(), 10, 10);
					
						g.setColor(Color.white);
						
						g.drawString("Line 1 and Line 2 intersects? " + line1IntersectsLine2, 60, 250);
						
						g.drawString("Line 3 and Line 1 intersects? " + line3IntersectsLine1, 70, 280);
						
						g.drawString("Line 3 and Line 1 disjoint? " + line3DisjointLine1, 70, 300);


						
					}
				});

			}
		});

	}
}
