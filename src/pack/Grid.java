package pack;

import java.awt.Color;
import java.awt.Graphics;

public class Grid{
	enum blockContains { unknown, obstacle, noObstacle, robot }
	blockContains[][] grid = new blockContains[9][7];
	float[] xInGrid = new float[9];
	float[] yInGrid = new float[7];

	
	
	//finds the spacing of the X and Y coordinates on the window in pixels
	public Grid(){
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[j].length; j++)
			{
				grid[i][j] = blockContains.unknown;
			}
		}
		
	}
	
	
	public void drawGrid(Graphics g, float width, float height){
		float positionX = 0;
		for(int i = 0; i<=8; i++){						 
			xInGrid[i] = positionX;
			positionX += (width / 9);
		}
		
		float positionY = 0;
		for(int i = 0; i<=6; i++){			
			positionY += (height / 8);
			yInGrid[i] = positionY;
		}
		
		for(int i = 0; i<=8; i++){
			for(int j = 0; j <=6; j++){
								
				switch(grid[i][j]){	
					case unknown:
						g.setColor(Color.GRAY);
						g.fillRect(Math.round(xInGrid[i]), Math.round(yInGrid[j]) ,Math.round(xInGrid[i] + (width/9)) , Math.round(yInGrid[j] + (height/8)));
						break;
						
					case obstacle:
						g.setColor(Color.RED);
						g.fillRect(Math.round(xInGrid[i]), Math.round(yInGrid[j]) , Math.round(xInGrid[i] + (width/9)) , Math.round(yInGrid[j] + (height/8)));		
						break;
						
					case noObstacle:
						g.setColor(Color.WHITE);
						g.fillRect(Math.round(xInGrid[i]), Math.round(yInGrid[j]) , Math.round(xInGrid[i] + (width/9)) , Math.round(yInGrid[j] + (height/8)));			
						break;
					case robot:
						g.setColor(Color.YELLOW);
						g.fillRect(Math.round(xInGrid[i]), Math.round(yInGrid[j]) , Math.round(xInGrid[i] + (width/9)) , Math.round(yInGrid[j] + (height/8)));
						break;
				}
				g.setColor(Color.BLACK);
				g.drawRect(Math.round(xInGrid[i]), Math.round(yInGrid[j]) ,Math.round(xInGrid[i] + (width/9)) , Math.round(yInGrid[j] + (height/8)));
				
			}
		}
	}
	
	public void checkForObstacle(int x, int y, float width, float height, int angle, float distance){
		int obstacleX = 4;
		int obstacleY = 6;
		for(int i = 0; i<=8; i++){
			for(int j = 0; j <=6; j++){
				if(x > xInGrid[i] && (x < (xInGrid[i] + width/9)) && y > yInGrid[j] && y < (yInGrid[j] + height/8)){
					grid[i][j] = blockContains.obstacle;
					obstacleX = i;
					obstacleY = j;
					break;
				}
			}
			if (obstacleX != 4 || obstacleY != 6)
			{
				break;
			}
		}
		
		int xCoord;
		int yCoord;
		
			if (angle > 0){
				for (float i = 0; i < distance; i += 0.05){
					xCoord = (int) ((width /2) + (Math.sin(Math.toRadians(angle)) * i * (width / 2)));
					yCoord = (int) (height - 50 - (Math.cos(Math.toRadians(angle)) * i * (width / 2)));									
					for(int k = 0; k<=8; k++){
						for(int j = 0; j <=6; j++){
							if(xCoord > xInGrid[k] && (xCoord < (xInGrid[k] + width/9)) && yCoord > yInGrid[j] && yCoord < (yInGrid[j] + height/8)){
								if (grid[k][j] != blockContains.obstacle){
									grid[k][j] = blockContains.noObstacle;
								}
							}
						}			
					}
				}
				
		} else {
			for (float i = 0; i < distance; i += 0.05){
				xCoord = (int) ((width /2) - (Math.sin(Math.toRadians(-angle)) * i * (width / 2)));
				yCoord = (int) (height - 50 - (Math.cos(Math.toRadians(-angle)) * i * (width / 2)));
											
				for(int k = 0; k<=8; k++){
					for(int j = 0; j <=6; j++){
						if(xCoord > xInGrid[k] && (xCoord < (xInGrid[k] + width/9)) && yCoord > yInGrid[j] && yCoord < (yInGrid[j] + height/8)){
							if (grid[k][j] != blockContains.obstacle){
								grid[k][j] = blockContains.noObstacle;
							}							
						}
					}				
				}						
			}
	
		}
			grid[4][6] = blockContains.robot;
	}
		
}


	
