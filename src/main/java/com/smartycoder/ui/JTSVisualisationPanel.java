package com.smartycoder.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
/**
 * 
 * @see https://www.smartycoder.com
 *
 */
@SuppressWarnings("serial")
public class JTSVisualisationPanel extends JPanel {

	private List<DrawingCommand> drawPathCommand = new ArrayList<>();
	
	public JTSVisualisationPanel(){
		setSize(450, 450);
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
 
		g.setColor(Color.darkGray);
		
		g.fillRect(0, 0, 600, 600);
		
		g.setColor(Color.WHITE);
		
		g.fillRect(10, 10, 4, 4);
	 
		g.drawLine(10, 10, 10, 600);
		
		g.drawLine(10, 10, 600, 10);
		
		for(int i = 10; i <= 600; i += 50) {
			
			g.drawString(Integer.toString(i), i, 10);
			
			g.drawString(Integer.toString(i), 10, i);
		}
		
		g.setColor(Color.BLACK);
		
		for (DrawingCommand drawingCommand : drawPathCommand) {
			
			drawingCommand.doDrawing(g);
		}
		
	}

	public void addDrawCommand(DrawingCommand c) {
		this.drawPathCommand.add(c);
		this.repaint();
	}
}
