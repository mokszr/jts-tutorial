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
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.index.quadtree.Quadtree;

import com.smartycoder.ui.DrawingCommand;
import com.smartycoder.ui.JTSVisualisationPanel;

/**
 * 
 * @see https://www.smartycoder.com
 *
 */
public class QuadtreeVisualisation {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSVisualisationPanel panel = new JTSVisualisationPanel();

				JFrame frame = new JFrame("JTS Visualisation - QuadTree ");
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

				Quadtree quadTree = new Quadtree();
				
				for(int i = 1; i <= pointCount; i++) {
					
					int xRandomlySelected = randomX.nextInt(250) + 65;
					
					int yRandomlySelected = randomY.nextInt(250) + 50;
					
					Coordinate coordinate = new Coordinate(xRandomlySelected, yRandomlySelected);
					
					pointCoordinates.add(coordinate);
				
					points[i-1] = geometryFactory.createPoint(coordinate);
					
					quadTree.insert(points[i-1].getEnvelopeInternal(), points[i-1]);
					
				}
				
				Coordinate[] coordinates = new Coordinate[] { new Coordinate(150, 60), new Coordinate(250, 60),  new Coordinate(250, 250),new Coordinate(150, 250), new Coordinate(150, 60)};
				
				Polygon searchPolygon = geometryFactory.createPolygon(coordinates);
				
				List<Object> searchPossibleCoveredPoints = quadTree.query(searchPolygon.getEnvelopeInternal());
				
				List<Point> falsePositives = new ArrayList<>();
				
				for (Object c : searchPossibleCoveredPoints) {
					Point p = (Point) c;
					
					System.out.println(p + " intersects polygon: " + searchPolygon.intersects(p) + " envlope " + p.getEnvelopeInternal());
					
					if(!searchPolygon.intersects(p)) {
						falsePositives.add(p);
					}
				}
				
				searchPossibleCoveredPoints.removeAll(falsePositives);
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
							
							Coordinate[] coords = searchPolygon.getCoordinates();
							
							int[] xler = new int[coords.length];
							int[] yler = new int[coords.length];
							
							for (int i = 0; i < coords.length; i++) {
								xler[i] = (int) coords[i].getX();
								yler[i] = (int) coords[i].getY();
							}

							g.setColor(Color.RED);
							
							g.drawPolygon(xler, yler, coords.length);
					 
							for (int i = 0; i < pointCoordinates.size(); i++) {
								
								g.setColor(Color.BLUE);
								
								Point point = points[i];
								
								if(searchPossibleCoveredPoints.contains(point)) {
						
									g.setColor(Color.RED);
								}
								
								g.fillOval((int)pointCoordinates.get(i).getX(), (int) pointCoordinates.get(i).getY(), 5, 5);
							}
					}
				});

			}
		});

	}
}
