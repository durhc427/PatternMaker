/*		PatternMaker 	Program 3 Assignment CSCI 282B
 * 		Group Members: 	Han Nguyen, Kenneth Logan, Cameron Durham
 * 		Purpose: 		Allows user to select among three patterns to create and customize
 * 		Date: 			November 24, 2017
 * 		Filename: 		PatternMaker.java
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Font;


public class PatternMaker extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	//constants for window size
	private static final int WINDOW_WIDTH = 900;
	private static final int WINDOW_HEIGHT = 900;

	//default values for min/max pattern levels
	private static int MIN = 3, MAX = 8;
	
	//JButtons for user interface
	private JButton increase, decrease, setBlue, setPink, setRainbow, setGreen;

	//pattern name and level labels
	private JLabel titleLabel, patternLevel;
	
	//panels hold graphics object and buttons for user
	private JPanel drawingPanel, buttonPanel;

	//pattern panel class for instantiating Spiral, Square, or Triangle patterns
	private PatternPanel drawing;

	public void init(String PatternName) {

		// initialize buttonPanel to hold control buttons
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setBackground(Color.white);
		buttonPanel.setOpaque(true);

		// initialize titleLable to display pattern name
		titleLabel = new JLabel(PatternName + "   ");
		titleLabel.setFont(new Font ("Arial", Font.BOLD, 15));
		titleLabel.setForeground(Color.black);
		buttonPanel.add(titleLabel);

		// initialize to display pattern level number
		patternLevel = new JLabel(" Pattern Level: 1  ");
		patternLevel.setFont(new Font ("Arial", Font.BOLD, 15));
		patternLevel.setForeground(Color.black);
		buttonPanel.add(patternLevel);

		increase = new JButton("Increase");
		increase.setFont(new Font ("Castellar", Font.BOLD, 15));
		increase.addActionListener(this);

		decrease = new JButton("Decrease");
		decrease.setFont(new Font ("Castellar", Font.BOLD, 15));
		decrease.addActionListener(this);

		// set colors and fonts for each button
		setBlue = new JButton("Blue");
		setBlue.setForeground(Color.BLUE);
		setBlue.setFont(new Font ("Calibri", Font.BOLD, 16));
		setBlue.addActionListener(this);

		setPink = new JButton("Pink");
		setPink.setForeground(Color.PINK);
		setPink.setFont(new Font ("Calibri", Font.BOLD, 16));
		setPink.addActionListener(this);

		setRainbow = new JButton("Rainbow");
		setRainbow.setFont(new Font ("Calibri", Font.BOLD, 16));
		setRainbow.addActionListener(this);

		setGreen = new JButton("Green");
		setGreen.setForeground(Color.GREEN);
		setGreen.setFont(new Font ("Calibri", Font.BOLD, 16));
		setGreen.addActionListener(this);
		
		// add all buttons to their panel
		buttonPanel.add(titleLabel);
		buttonPanel.add(decrease);
		buttonPanel.add(increase);
		buttonPanel.add(patternLevel);
		buttonPanel.add(setBlue);
		buttonPanel.add(setPink);
		buttonPanel.add(setRainbow);
		buttonPanel.add(setGreen);

		// instantiate the specific pattern 
		//   from user's choice in FirstButtonPanel		
		if (PatternName == "Spiral") {
			drawing = new SpiralPanel(3);
		}
		
		else if(PatternName == "Sierpinski's Carpet") {
			// min/max values for levels allowed
			//  for the square fractal
			MIN = 1;
			MAX = 5;
			drawing = new SquarePanel(1);
		}
		
		else if(PatternName == "Sierpinski's Triangle") {
			// min/max values for levels allowed
			//   for the triangle fractal
			MIN = 1;
			MAX = 7;
			drawing = new TrianglePanel(1);
		}
		
	
		//initialize drawing panel and add elements
		drawingPanel = new JPanel();
		drawingPanel.add(buttonPanel);
		drawingPanel.add(drawing);

		// add the content to drawing panel
		getContentPane().add(drawingPanel);

		//set size for the window
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	
	public void actionPerformed(ActionEvent event) {
		
		// store the current order number
		int userOrderNum = drawing.getOrder();

		// increase level number if less than max
		if (event.getSource() == increase) {
			if (userOrderNum < MAX)
				userOrderNum++;
		} 
		// decrease level number if greater than min
		else if (event.getSource() == decrease) {
			if (userOrderNum > MIN)
				userOrderNum--;
		}

		// set current color to user's choice
		else if (event.getSource() == setBlue)
			drawing.setNewColor(Color.BLUE);

		else if (event.getSource() == setPink)
			drawing.setNewColor(Color.PINK);

		else if (event.getSource() == setRainbow)
			drawing.setNewColor(Color.BLACK);

		else if (event.getSource() == setGreen)
			drawing.setNewColor(Color.GREEN);

		// rewrite the pattern label
		patternLevel.setText(" Pattern Level: " + ((MIN==1?userOrderNum:userOrderNum-2)));
		
		// set new order number from user
		drawing.setOrder(userOrderNum);

		// redraw the pattern
		repaint();

	}

}