/*		PatternMaker 	Program 3 Assignment CSCI 282B
 * 		Group Members: 	Han Nguyen, Kenneth Logan, Cameron Durham
 * 		Purpose:	 	Allows user to select among three patterns to create and customize
 * 		Date: 			November 24, 2017
 * 		Filename: 		SpiralPanel.java
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.security.SecureRandom;

public class SpiralPanel extends PatternPanel {

	private static final long serialVersionUID = 1L;
	
	//scaling constant used to draw spiral
	//	up to the drawing panel sides
	private int SCALING_CONST;

	//SpiralPanel constructor sets default values
	public SpiralPanel(int order) {
		super(order);
		patternName = "Spiral";
		SCALING_CONST = PANEL_HEIGHT / (order * 40);

	}
	
	//called by paintComponent to draws shape pattern
	//	Pattern: Circle, Rectangle, Triangle
	@Override
	public void drawPattern(int...numbers){
		
		int order = numbers[0]; //selects which shape to draw
		int x1 = numbers[1]; //x coordinate
		int y1 = numbers[2]; //y coordinate
		int sideSize = numbers[3]; //sets shape size
		
		//selects which shape to draw
		switch (order % 3) {
		
		case (0): //fill a circle
			drawing.fillOval(x1, y1, sideSize / 2, sideSize / 2);
			break;
			
		case (1): //fill a square
			drawing.fillRect(x1, y1, sideSize / 2, sideSize / 2);
			break;
			
		case (2): { //draw a new path for an equilateral triangle
			GeneralPath triangle = new GeneralPath();
			x1 += sideSize / 3;
			y1 += sideSize / 3;
			int[] xTrianglePoints = { x1, x1 + (sideSize / 3), x1 - (sideSize / 3) };
			int[] yTrianglePoints = { y1 - (sideSize / 3), y1 + (sideSize / 3), y1 + (sideSize / 3) };
			
			//move triangle to first x1,y1 point
			triangle.moveTo(xTrianglePoints[0], yTrianglePoints[0]);

			// connect the dots on the triangle
			for (int count = 1; count < xTrianglePoints.length; count++)
				triangle.lineTo(xTrianglePoints[count], yTrianglePoints[count]);
			//finish connecting path
			triangle.closePath();
			
			//fill triangle shape
			drawing.fill(triangle);

		}
		}

	}
	
	//paintComponent draws the pattern on graphics object
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		//new random generator for colors
		SecureRandom random = new SecureRandom();
		
		//upcast Graphics 2D to Graphics object
		drawing = (Graphics2D) g;
		
		//set the color to current color
		drawing.setColor(getColor());

		//set coordinates in center of screen
		drawing.translate(PANEL_WIDTH / 2, PANEL_HEIGHT / 2);

		//draw circle, square, rectangle pattern using drawPattern method
		for (int count = 1; count <= super.getOrder() * 10; count++) {

			// change to current color if user changed drawing color
			if (getColor() != Color.BLACK) {
				drawing.setColor(getColor());
			}

			// draw using randomly selected colors
			else
				drawing.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

			// center points to draw next shape
			int x1 = (int) (count * SCALING_CONST * Math.cos(count * Math.PI / 10));
			int y1 = (int) (count * SCALING_CONST * Math.sin(count * Math.PI / 10));

			// draw shapes at new point in spiral
			drawPattern(count, x1, y1, count * SCALING_CONST / 3);
		}

	}
}
