/*		PatternMaker 	Program 3 Assignment CSCI 282B
 * 		Group Members: 	Han Nguyen, Kenneth Logan, Cameron Durham
 * 		Purpose: 		Allows user to select among three patterns to create and customize
 * 		Date: 			November 24, 2017
 * 		Filename: 		TrianglePanel.java
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.security.SecureRandom;

public class TrianglePanel extends PatternPanel {
	
	private static final long serialVersionUID = 1L;

	//fields to draw the initial triangle
	private final int TOPX = 400, TOPY = 100;
	private final int LEFTX = 50, LEFTY = 700;
	private final int RIGHTX = 750, RIGHTY = 700;

	//random benerator for selecting random colors
	SecureRandom random = new SecureRandom();

	//constructor for TrianglePanel
	//	sets default values and pattern name
	public TrianglePanel(int order) {

		super(order);
		patternName = "Sierpinski's Triangle";
		setNewColor(Color.WHITE);
	}

	//draws a triangle by connecting midpoint of coordinates
	//	calls itself recursively to specified pattern level
	@Override
	public void drawPattern(int...numbers) {
		//level to draw pattern 
		int order = numbers[0];

		//assign parameters to variables
		int topx = numbers[1];
		int topy = numbers[2];
		int leftx = numbers[3];
		int lefty = numbers[4];
		int rightx = numbers[5];
		int righty = numbers[6];
		

		// change to current color if user changed drawing color
		if(getColor() != Color.BLACK) {
			drawing.setColor(getColor());
		}
		// draw using randomly selected colors
		else
			drawing.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

		//draws triangle between input points
		drawing.drawLine((leftx+topx)/2, (lefty+topy)/2,(rightx+topx)/2, (righty+topy)/2);
		drawing.drawLine((rightx+topx)/2, (righty+topy)/2,(leftx+rightx)/2,(lefty+righty)/2);
		drawing.drawLine((leftx+rightx)/2,(lefty+righty)/2, (leftx+topx)/2, (lefty+topy)/2);
		
		//recursively draw triangles in remaining three corners of triangle
		//	stop when last order "1" is reached
		if(order != 1) {
			drawPattern(order-1, (leftx+topx)/2, (lefty+topy)/2, leftx, lefty,(leftx+rightx)/2,(lefty+righty)/2 );
			drawPattern(order-1, topx, topy,(leftx+topx)/2, (lefty+topy)/2,(rightx+topx)/2, (righty+topy)/2);
			drawPattern(order-1, (rightx+topx)/2, (righty+topy)/2, (leftx+rightx)/2,(lefty+righty)/2, rightx,righty);
		}
		
	}

	// draw Sierpinski's Triangles on graphics object	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		drawing = (Graphics2D) g;
		
		// change to current color if user changed drawing color
		if(getColor() != Color.BLACK) {
			drawing.setColor(getColor());
		}
		
		// draw using randomly selected colors		
		else
			drawing.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

		
		// draw first triangular region for fractal
		drawing.drawLine(TOPX, TOPY, LEFTX, LEFTY);
		drawing.drawLine(TOPX, TOPY, RIGHTX, RIGHTY);
		drawing.drawLine(LEFTX, LEFTY, RIGHTX, RIGHTY);

		// recursively draw fractal of given order in triangular region
		drawPattern(getOrder(), TOPX, TOPY, LEFTX, LEFTY, RIGHTX, RIGHTY);
	}
}