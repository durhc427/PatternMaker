/*		PatternMaker 	Program 3 Assignment CSCI 282B
 * 		Group Members: 	Han Nguyen, Kenneth Logan, Cameron Durham
 * 		Purpose: 		Allows user to select among three patterns to create and customize
 * 		Date: 			November 24, 2017
 * 		Filename: 		SquarePanel.java
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.security.SecureRandom;

public class SquarePanel extends PatternPanel {

	private static final long serialVersionUID = 1L;
	
	//initial coordinate for first square
	private final int TOPLEFTX = 100, TOPLEFTY = 100;
	
	//width and height of drawing panel
	private final int WIDTH = 600, HEIGHT = 600;
	
	//random generator for selecting colors
	SecureRandom random = new SecureRandom();

	//SquarePanel constructor sets default values
	public SquarePanel(int order) {
		super(order);
		patternName = "Sierpinski's Carpet";
		setNewColor(Color.WHITE);
	}

	//drawPattern called by paintComponent to create
	//	Sierpienski's carpet pattern
	@Override
	public void drawPattern(int...numbers) {
		
		//order determines depth of Sierpinski's Carpet
		int order = numbers[0];
		
		//x1, y1: initial coordinates for top left corner of square
		int x1 = numbers[1];
		int y1 = numbers[2];
		
		//side length
		int side = numbers[3];
		
		
		// change to current color if user changed drawing color
		if (super.getColor() != Color.BLACK) {
			drawing.setColor(getColor());
		}

		// draw using randomly selected colors
		else
			drawing.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

		//draw square 1/9th the area of original square
		//drawn in center of square
		drawing.fillRect(x1+side/3,y1+side/3, side/3, side/3);

		//recursively draw squares in remaining 8 sections of square
		//	decrement the order to eventually stop recursion process
		if( order != 1)
		{	
			drawPattern(order - 1, x1 + 0*side/3, y1 + 0*side/3, side/3, side/3);
			drawPattern(order - 1, x1 + 1*side/3, y1 + 0*side/3, side/3, side/3);
			drawPattern(order - 1, x1 + 2*side/3, y1 + 0*side/3, side/3, side/3);
			drawPattern(order - 1, x1 + 0*side/3, y1 + 1*side/3, side/3, side/3);
			drawPattern(order - 1, x1 + 0*side/3, y1 + 2*side/3, side/3, side/3);
			drawPattern(order - 1, x1 + 2*side/3, y1 + 1*side/3, side/3, side/3);
			drawPattern(order - 1, x1 + 1*side/3, y1 + 2*side/3, side/3, side/3);
			drawPattern(order - 1, x1 + 2*side/3, y1 + 2*side/3, side/3, side/3);

		}

		
	}
	
	//paintComponent creates drawing
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawing = (Graphics2D) g;
		
		// change to current color if user changed drawing color
		if(getColor() != Color.BLACK) {
			drawing.setColor(getColor());
		}
		// draw using random selected colors
		else
			drawing.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

		
		// Draw initial square region for fractal	
		drawing.drawRect(TOPLEFTX, TOPLEFTY, WIDTH, HEIGHT);
		
		// Recursively draw fractal of given order in square region		
		drawPattern(getOrder(), TOPLEFTX, TOPLEFTY, WIDTH, HEIGHT);
	}

}
