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
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;

import com.smartycoder.ui.DrawingCommand;
import com.smartycoder.ui.JTSVisualisationPanel;
/**
 * 
 * @see https://www.smartycoder.com
 *
 */
public class DelaunayTriangulation {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSVisualisationPanel panel = new JTSVisualisationPanel();

				JFrame frame = new JFrame("JTS Visualisation - Delaunay Triangulation");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Random randomX = new Random(System.currentTimeMillis());
				
				Random randomY = new Random(System.currentTimeMillis() + 100);
				
				List<Coordinate> coords = new ArrayList<>();

				int pointCount = 100;

				for(int i = 1; i <= pointCount; i++) {
					
					int xRandomlySelected = randomX.nextInt(405) + 25;
					
					int yRandomlySelected = randomY.nextInt(405) + 20;
					
					System.out.println(i + ". randomly selected point " + xRandomlySelected + ", " + yRandomlySelected);

					coords.add(new Coordinate(xRandomlySelected, yRandomlySelected));
				
				}
				
				DelaunayTriangulationBuilder triangleBuilder = new DelaunayTriangulationBuilder();
				
				triangleBuilder.setSites(coords);
				
				Geometry triangles = triangleBuilder.getTriangles(geometryFactory);
				
				List<Polygon> trianglesProduced = new ArrayList<>();
				
				if(triangles instanceof GeometryCollection) {
					
					GeometryCollection geometryCollection = (GeometryCollection) triangles;
					
					System.out.println("Produced triangles count: " + geometryCollection.getNumGeometries());
					
					for(int i = 0; i < geometryCollection.getNumGeometries(); i++) {
						
						Polygon triangle = (Polygon) geometryCollection.getGeometryN(i);
						
						trianglesProduced.add(triangle);
					}
					
				}
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						for (Polygon triangle : trianglesProduced) {
							
							Coordinate[] coords = triangle.getCoordinates();
							
							int[] xler = new int[coords.length];
							int[] yler = new int[coords.length];
							
							for (int i = 0; i < coords.length; i++) {
								xler[i] = (int) coords[i].getX();
								yler[i] = (int) coords[i].getY();
							}

							g.setColor(Color.RED);
							
							g.drawPolygon(xler, yler, coords.length);
						}

					}
				});

			}
		});

	}
}
