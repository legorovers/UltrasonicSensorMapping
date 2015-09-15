package pack;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PlotPanel extends JPanel {
	int angle;
	float distance;
	float width;
	float height;
	int xCoord;
	int yCoord;
	Grid grid;
	
	public void createGrid(){
		
		grid = new Grid();
		 
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		width = getWidth();
		height = getHeight();
		
		if (angle > 0){
			xCoord = (int) ((width /2) + (Math.sin(Math.toRadians(angle)) * distance * (width / 2)));
			yCoord = (int) (height - 50 - (Math.cos(Math.toRadians(angle)) * distance * (width / 2)));	
			g.drawString("distance = " + printDistance(distance) + " metres", 10, 10);
			grid.checkForObstacle(xCoord, yCoord, width, height, angle, distance);
			grid.drawGrid(g, width, height);
			g.setColor(Color.GRAY);
			g.drawLine((int)width / 2, (int) (height - (height / 16)), xCoord, yCoord);
			g.setColor(Color.BLACK);
			System.out.println("angle = " + angle + " distance = " + distance + " X = " + xCoord + " Y = " + yCoord);
		
		} else { 	
			xCoord = (int) ((width /2) - (Math.sin(Math.toRadians(-angle)) * distance * (width / 2)));
			yCoord = (int) (height - 50 - (Math.cos(Math.toRadians(-angle)) * distance * (width / 2)));	
			g.drawString("distance = " + printDistance(distance) + " metres", 10, 10);
			grid.checkForObstacle(xCoord, yCoord, width, height, angle, distance);
			grid.drawGrid(g, width, height);
			g.setColor(Color.GRAY);
			g.drawLine((int)width / 2,(int) (height - (height / 16)), xCoord, yCoord);
			g.setColor(Color.BLACK);
			System.out.println("angle = " + angle + " distance = " + distance + " X = " + xCoord + " Y = " + yCoord);
		}		
	}	
	
	public void updateUI(float distance, int angle){
		this.distance = distance;
		this.angle = angle;
		revalidate();
		repaint();
	}

	public String printDistance(float d) {
		if (d > 2.5){
			return ">2.5";
		} else {
			return Float.toString(d);
		}		
	}
}
