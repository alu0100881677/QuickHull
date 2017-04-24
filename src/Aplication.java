/**
* Subject: 	Programación de Aplicaciones Interactivas
* Practice: P11 QuickHull
* E-mail:   alu0100881677@ull.edu.es 
* Date      20/4/2017
* Program:  This class, named Aplication, extendens JPanel. It contains the complete GUI for the program,
* 			we should instance an object of it and add it to a JFrame.
* @author Guillermo Esquivel González
* @version 1.0.0
*/


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Definition of the class
 * @author Guille
 *
 */
public class Aplication extends JPanel {
	//private attributes of the class
	private final int	WIDTH = 800;  
	private final int	HEIGHT = 800; 
	private final int	HGAP = 5;
	private final int	VGAP = 5;
	private MainPanel	mainPanel;
	private ButtonsPanel btnPanel;
	private QuickHull	quickHull;
	private ArrayList<Point>solution;
	
	/**
	 * Constructor of the class
	 * @param points
	 * @param speed
	 */
	public Aplication(final int points, final int speed) {
		quickHull = new QuickHull();
		solution = new ArrayList<Point>();
		setLayout(new BorderLayout(HGAP, VGAP));
		setSize(new Dimension(WIDTH, HEIGHT));
		addPanels(points, speed);
		setVisible(true);
	}

	/**
	 * This function sets up all the panels of the Interface
	 * @param points
	 * @param speed
	 */
	private void addPanels(final int points, final int speed) {
		mainPanel = new MainPanel(points, speed);
		btnPanel = new ButtonsPanel(speed);
		add(mainPanel, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);
		addListeners();
	}

	/**
	 * This function sets up the panel to the initial configuration
	 */
	private void resetMainPanel() {
		mainPanel.getGraphics().clearRect(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
		mainPanel.repaint();
		mainPanel.setIteration(0);
		mainPanel.getIterations().clear();
	}
	/**
	 * This function add Listeners to the components of the interface.
	 */
	private void addListeners() {
		/**
		 * Internal anonymous class
		 */
		btnPanel.getInitBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.createPoints();
				solution = mainPanel.getPoints();
				mainPanel.paintPoints();
				quickHull.run(mainPanel.getPoints());
				mainPanel.setIterations(quickHull.getEvolution());
			}
		});
		/**
		 * Internal anonymous class
		 */
		btnPanel.getRunBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					mainPanel.paintIterations();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		/**
		 * Internal anonymous class
		 */
		btnPanel.getStepBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					mainPanel.paintSingleIteration();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		/**
		 * Internal anonymous class
		 */
		btnPanel.getClearBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetMainPanel();
			}
		});
		/**
		 * Internal anonymous class
		 */
		btnPanel.getPauseBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean mode = (mainPanel.isPainting() == true) ? false : true;
				mainPanel.setPainting(mode);
			}
		});
		/**
		 * Internal anonymous class
		 */
		btnPanel.getSpeedSlider().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int speed = btnPanel.getSpeedSlider().getValue();
				System.out.println("Speed in MyApplet: " + speed);
				mainPanel.setSpeed(speed);
				System.out.println("Speed in MainPanel: " + mainPanel.getSpeed());
			}
		});
	}
}
