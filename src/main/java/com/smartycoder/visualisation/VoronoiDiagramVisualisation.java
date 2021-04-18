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
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import com.smartycoder.ui.DrawingCommand;
import com.smartycoder.ui.JTSVisualisationPanel;
/**
 * 
 * @see https://www.smartycoder.com
 *
 */
public class VoronoiDiagramVisualisation {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSVisualisationPanel panel = new JTSVisualisationPanel();

				JFrame frame = new JFrame("JTS Visualisation - Voronoi Diagram");
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
					
					int xRandomlySelected = randomX.nextInt(250) + 65;
					
					int yRandomlySelected = randomY.nextInt(250) + 50;
					
					System.out.println(i + ". randomly selected point " + xRandomlySelected + ", " + yRandomlySelected);

					coords.add(new Coordinate(xRandomlySelected, yRandomlySelected));
				
				}
				
				VoronoiDiagramBuilder diagramBuilder = new VoronoiDiagramBuilder();
				
				diagramBuilder.setSites(coords);
				
				Geometry polygonCollection = diagramBuilder.getDiagram(geometryFactory);
				
				List<Polygon> producedPolygons = new ArrayList<>();
				
				if(polygonCollection instanceof GeometryCollection) {
					
					GeometryCollection geometryCollection = (GeometryCollection) polygonCollection;
					
					System.out.println("Produced polygon count: " + geometryCollection.getNumGeometries());
					
					for(int i = 0; i < geometryCollection.getNumGeometries(); i++) {
						
						Polygon polygon = (Polygon) geometryCollection.getGeometryN(i);
						
						producedPolygons.add(polygon);
					}
					
				}
				
				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						for (Polygon poligon : producedPolygons) {
							
							Coordinate[] coords = poligon.getCoordinates();
							
							int[] xler = new int[coords.length];
							int[] yler = new int[coords.length];
							
							for (int i = 0; i < coords.length; i++) {
								xler[i] = (int) coords[i].getX();
								yler[i] = (int) coords[i].getY();
							}

							g.setColor(Color.RED);
							
							g.drawPolygon(xler, yler, coords.length);
						}
						
						for (int i = 0; i < coords.size(); i++) {
							
							g.setColor(Color.white);
							
							g.fillOval((int)coords.get(i).getX(), (int) coords.get(i).getY(), 3, 3);
						}

					}
				});

			}
		});

	}
}
