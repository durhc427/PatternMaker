/*		PatternMaker 	Program 3 Assignment CSCI 282B
 * 		Group Members: 	Han Nguyen, Kenneth Logan, Cameron Durham
 * 		Purpose: 		Allows user to select among three patterns to create and customize
 * 		Date: 			November 24, 2017
 * 		Filename: 		PatternMaker.java
 */


import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

//abstract class PatternPanel holds basic methods 
//	for pattern creation and customization
public abstract class PatternPanel extends JPanel {

	//declare fields
	protected static final long serialVersionUID = 1L;
	
	//constants for drawing panel width and height
	//protected so SpiralPanel can access values
	protected final int PANEL_WIDTH = 800;
	protected final int PANEL_HEIGHT = 800;
	
	//currentOrder sets how far to draw the shapes
	private int currentOrder;
	
	//currentColor is checked in drawPattern/paintComponent
	//to set shape color
	private Color currentColor;
	
	//Graphics2D object to hold drawing
	protected Graphics2D drawing;
	
	//String to set title of pattern
	protected String patternName;

	//abstract method accepts variable-length of arguments
	//to specialize drawing for specific patterns
	public abstract void drawPattern(int...numbers);

	//sets default values when invoked by subclasses
	public PatternPanel(int order) {
		currentOrder = order;
		currentColor = Color.BLACK;
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	}
	
	//currentOrder setter
	public void setOrder(int currentOrder) {
		this.currentOrder = currentOrder;
	}

	//currentOrder getter
	public int getOrder() {
		return currentOrder;
	}

	//currentColor setter
	public void setNewColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	//current color getter
	public Color getColor() {
		return currentColor;
	}

	//current name getter
	public String getName() {
		return patternName;
	}


}
