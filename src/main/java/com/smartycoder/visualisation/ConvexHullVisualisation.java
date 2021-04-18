package com.smartycoder.visualisation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import com.smartycoder.ui.DrawingCommand;
import com.smartycoder.ui.JTSVisualisationPanel;
/**
 * 
 * @see https://www.smartycoder.com
 *
 */
public class ConvexHullVisualisation {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSVisualisationPanel panel = new JTSVisualisationPanel();

				JFrame frame = new JFrame("JTS Visualisation - Convex Hull");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Random randomX = new Random(System.currentTimeMillis());
				
				Random randomY = new Random(System.currentTimeMillis() + 100);
				
				List<Coordinate> pointCoordinates = new ArrayList<>();

				int pointCount = 100;
				
				Point[] points = new Point[pointCount];

				for(int i = 1; i <= pointCount; i++) {
					
					int xRandomlySelected = randomX.nextInt(250) + 65;
					
					int yRandomlySelected = randomY.nextInt(250) + 50;
					
					System.out.println(i + ". randomly selected point " + xRandomlySelected + ", " + yRandomlySelected);

					Coordinate coordinate = new Coordinate(xRandomlySelected, yRandomlySelected);
					
					pointCoordinates.add(coordinate);
				
					points[i-1] = geometryFactory.createPoint(coordinate);
				}
				
				MultiPoint multiPoint = geometryFactory.createMultiPoint(points);
				 
				Polygon convexHull = (Polygon) multiPoint.convexHull();
				
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
							
							Coordinate[] coords = convexHull.getCoordinates();
							
							int[] xler = new int[coords.length];
							int[] yler = new int[coords.length];
							
							for (int i = 0; i < coords.length; i++) {
								xler[i] = (int) coords[i].getX();
								yler[i] = (int) coords[i].getY();
							}

							g.setColor(Color.RED);
							
							g.drawPolygon(xler, yler, coords.length);
					 
							for (int i = 0; i < pointCoordinates.size(); i++) {
								
								g.setColor(Color.WHITE);
								
								g.fillOval((int)pointCoordinates.get(i).getX(), (int) pointCoordinates.get(i).getY(), 3, 3);
							}
							
					}
				});

			}
		});

	}
}
