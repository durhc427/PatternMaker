/*		PatternMaker 	Program 3 Assignment CSCI 282B
 * 		Group Members: 	Han Nguyen, Kenneth Logan, Cameron Durham
 * 		Purpose: 		Allows user to select among three patterns to create and customize
 * 		Date: 			November 24, 2017
 * 		Filename: 		FirstDialogBox.java
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstDialogBox extends JFrame implements ActionListener {
	
	//create buttons and panels for dialog box
	private final JButton squareFractal;
	private final JButton spiralMaker;
	private final JButton triangleFractal;
	private final JButton exit;
	private final JPanel patternButtons, exitButtonPanel;
	private static final long serialVersionUID = 1L;

	public FirstDialogBox() {

		//create JPanel and panel title
		super("Pattern Maker");
		patternButtons = new JPanel();
		
		//set border around row of top buttons
		patternButtons.setBorder( BorderFactory.createEmptyBorder(20, 20, 30, 30));
		patternButtons.setLayout(new BoxLayout(patternButtons, BoxLayout.LINE_AXIS));	
		patternButtons.setBackground(Color.BLACK);
		patternButtons.setOpaque(true);
		
		//initialize lower panel to hold "Exit" button
		exitButtonPanel = new JPanel();
		exitButtonPanel.setLayout(new BoxLayout(exitButtonPanel, BoxLayout.Y_AXIS));
		exitButtonPanel.setBackground(Color.BLACK);
		exitButtonPanel.setOpaque(true);
		
		//create button to make square fractal
		squareFractal = new JButton("Square Fractal");
		patternButtons.add(squareFractal);
		squareFractal.setFont(new Font ("Calibri", Font.BOLD,20));
		squareFractal.addActionListener(this);

		//create button to make spiral
		spiralMaker = new JButton("Spiral");
		patternButtons.add(spiralMaker);
		spiralMaker.setFont(new Font ("Calibri", Font.BOLD,20));
		spiralMaker.addActionListener(this);
		
		//create button to make triangle fractal
		triangleFractal = new JButton("Triangle Fractal");
		patternButtons.add(triangleFractal);
		triangleFractal.setFont(new Font ("Calibri", Font.BOLD,20));
		triangleFractal.addActionListener(this);
		
		//create exit button
		exit = new JButton("Exit");
		exitButtonPanel.add(exit);
		exit.setAlignmentX(CENTER_ALIGNMENT);
		exit.setFont(new Font ("Calibri", Font.BOLD, 20));
		exit.addActionListener(this);

		//add filler space between top/lower buttons
		add(Box.createVerticalGlue());
	
		//initialize container
		Container container = getContentPane();
		
		//set container background and add buttons
		container.setBackground(Color.BLACK);
		container.add(patternButtons,BorderLayout.PAGE_START);
		container.add(exitButtonPanel,BorderLayout.PAGE_END);

	}

	//main method
	public static void main(String[] args) {
		
		//create object 
		FirstDialogBox optionPanel = new FirstDialogBox();
		
		//set to terminate program on close
		optionPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set dialog box size
		optionPanel.setSize(450, 200);
		
		//set location in center of screen
		optionPanel.setLocationRelativeTo(null);
		
		//make panel visible
		optionPanel.setVisible(true);

	}

	//action handler
	public void actionPerformed(ActionEvent event) {
		
		//create new spiral panel if button is pressed
		if (event.getSource() == spiralMaker) {
			PatternMaker maker = new PatternMaker();

			maker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			maker.init("Spiral");
			maker.setVisible(true);

		}
		
		//create new square fractal panel if Square Fractal button is pressed
		else if (event.getSource() == squareFractal) {

			PatternMaker maker = new PatternMaker();

			maker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			maker.init("Sierpinski's Carpet");
			maker.setVisible(true);

		}
		
		//create new triangle fractal panel if Triangle Fractal button is pressed
		else if (event.getSource() == triangleFractal) {
			
			PatternMaker maker = new PatternMaker();
			maker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			maker.init("Sierpinski's Triangle");
			maker.setVisible(true);
			
		}
		
		else if (event.getSource() == exit) {
			System.exit(0);
		}

	}

}
