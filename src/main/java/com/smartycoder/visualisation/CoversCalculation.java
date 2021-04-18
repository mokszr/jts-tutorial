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
public class CoversCalculation {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				JTSVisualisationPanel panel = new JTSVisualisationPanel();

				JFrame frame = new JFrame("JTS Visualisation - Covers Calculation");
				frame.setLayout(new BorderLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.add(panel, BorderLayout.CENTER);

				frame.pack();
				frame.setSize(450, 450);
				frame.setVisible(true);

				GeometryFactory geometryFactory = new GeometryFactory();

				Coordinate[] coordinates = new Coordinate[] { new Coordinate(250, 110), new Coordinate(300, 200),  new Coordinate(190, 200),new Coordinate(250, 110)};

				Polygon polygon = geometryFactory.createPolygon(coordinates);
				
				Coordinate[] quadrilateralCoordinates = new Coordinate[] { new Coordinate(110, 80), new Coordinate(370, 90),  new Coordinate(370, 250),new Coordinate(100, 250) , new Coordinate(110, 80)};

				Polygon quadrilateral = geometryFactory.createPolygon(quadrilateralCoordinates);
				
				boolean quadrilateralCoversPolygon = quadrilateral.covers(polygon);
				
				boolean polygonCoveredByQuadrilateral = polygon.coveredBy(quadrilateral);

				panel.addDrawCommand(new DrawingCommand() {

					@Override
					public void doDrawing(Graphics g) {
						
						Coordinate[] coords = quadrilateral.getCoordinates();
						
						int[] xler = new int[coords.length];
						int[] yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}
						

						g.setColor(Color.GREEN);
						
						g.fillPolygon(xler, yler, coords.length);
						
						g.setColor(Color.BLACK);
						
						g.drawString("(" + xler[0] + ", " + yler[0] + ")"  , 240, 100);
						
						g.drawString("(" + xler[1] + ", " + yler[1] + ")"  , 290, 215);
						
						g.drawString("(" + xler[2] + ", " + yler[2] + ")"  , 180, 215);
						
						coords = polygon.getCoordinates();
						
						xler = new int[coords.length];
						yler = new int[coords.length];
						
						for (int i = 0; i < coords.length; i++) {
							xler[i] = (int) coords[i].getX();
							yler[i] = (int) coords[i].getY();
						}

						g.setColor(Color.BLUE);
						
						g.fillPolygon(xler, yler, coords.length);

						g.setColor(Color.WHITE);

						g.drawString("(" + xler[0] + ", " + yler[0] + ")"  , 100, 65);
						
						g.drawString("(" + xler[1] + ", " + yler[1] + ")"  , 370, 75);
						
						g.drawString("(" + xler[2] + ", " + yler[2] + ")"  , 370, 265);
						
						g.drawString("(" + xler[3] + ", " + yler[3] + ")"  , 100, 265);
						
						g.drawString("Quadrilateral covers triangle? "  + quadrilateralCoversPolygon, 40, 285);
					
						g.drawString("Triangle covered by quadrilateral? "  + polygonCoveredByQuadrilateral, 40, 310);


					}
				});

			}
		});

	}
}
