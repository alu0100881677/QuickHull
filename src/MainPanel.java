/**
* Subject: 	Programación de Aplicaciones Interactivas
* Practice: P11 QuickHull
* E-mail:   alu0100881677@ull.edu.es 
* Date:     20/4/2017
* @author 	Guillermo Esquivel González
* @version 	1.0.0
*/

//import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Point;

/**
 *
 */
public class MainPanel extends JPanel {
	private int	numOfPoints;
	private ArrayList<Point> points;
	private ArrayList<Point> solution;
	private ArrayList<ArrayList<Point>> iterations;
	private int	speed;
	private final int	WIDTH	= 5;
	private boolean step;
	private  boolean painting;
	private final int MAX_SPEED = 3000;
	private int iteration = 0;
	
	
	/**
	 * 
	 * @return
	 */
	public int getIteration() {
		return iteration;
	}
	/**
	 * 
	 * @param iteration
	 */
	public void setIteration(int iteration) {
		this.iteration = iteration;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<Point>> getIterations() {
		return iterations;
	}
	
	/**
	 * Constructor of the class
	 * @param pts
	 * @param spd
	 */
	public MainPanel( int pts,  int spd) {
		numOfPoints = pts;
		points = new ArrayList<Point>();
		iterations = new ArrayList<>();
		speed = spd;
		painting = true;
		step = false;
		buildPanel();
	}
	
	/**
	 * Construct the panel
	 */
	private void buildPanel() {
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	/**
	 * Create the points
	 */
	public void createPoints() {
		points = null;
		points = new ArrayList<Point>();
		for (int i = 0; i < getNumOfPoints(); i++) {
			int coordX = new Random().nextInt(getWidth());
			int coordY = new Random().nextInt(getHeight());
			points.add(new Point(coordX, coordY));
		}
	}

	/**
	 * Paints the points
	 */
	public void paintPoints() {
		Graphics g = getGraphics();
		g.setColor(Color.BLUE);
		for (Point point : points)
			g.fillOval(point.x, point.y, WIDTH, WIDTH);
	}
	/**
	 * Paints all the steps of the solution
	 * @throws InterruptedException
	 */
	public void paintIterations() throws InterruptedException{
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int i = iteration;
				while(i < iterations.size()){
					System.out.println(isPainting());
					if(isPainting()){
						Graphics g = getGraphics();
						g.clearRect(0, 0, getWidth(), getHeight());
						paintPoints();
						try {
							Thread.sleep(200);
							paintLines(iterations.get(i));
							Thread.sleep(speed);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						i++;
						iteration = i;
					}
				}
			}
		});
		thread.start();
	}
	
	/**
	 * Paints a single step of the execution of the program
	 * @throws InterruptedException
	 */
	public void paintSingleIteration() throws InterruptedException{
		if(iteration < iterations.size()){
			Graphics g = getGraphics();
			g.clearRect(0, 0, getWidth(), getHeight());
			paintPoints();
			Thread.sleep(200);
			paintLines(iterations.get(iteration));
			Thread.sleep(speed);
			iteration++;
		}
	}

	/**
	 * Paints the line of the solution
	 *
	 * @param solution
	 * @throws InterruptedException
	 */
	public void paintLines(ArrayList<Point> solution) throws InterruptedException {
		//Thread thread = new Thread(new Runnable() {
			//@Override
			//public void run() {
				Graphics g = getGraphics();
				g.setColor(Color.RED);
				int index = 0;
				while (index < solution.size() - 1) {
					//if (isPainting()) {
						g.drawLine(solution.get(index).x +1, solution.get(index).y+1, solution.get(index + 1).x+1, solution.get(index + 1).y+1);
						g.drawLine(solution.get(index).x, solution.get(index).y,	solution.get(index + 1).x, solution.get(index + 1).y);
						g.drawLine(solution.get(index).x-1, solution.get(index).y-1,	solution.get(index + 1).x-1, solution.get(index + 1).y-1);
						index++;
						if (isStep())
							try {
								Thread.sleep(speed);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						g.drawLine(solution.get(solution.size() - 1).x+1, solution.get(solution.size() - 1).y+1, solution.get(0).x+1, solution.get(0).y+1);
						g.drawLine(solution.get(solution.size() - 1).x, solution.get(solution.size() - 1).y, solution.get(0).x, solution.get(0).y);
						g.drawLine(solution.get(solution.size() - 1).x-1, solution.get(solution.size() - 1).y-1, solution.get(0).x-1, solution.get(0).y-1);
					//}
				}
			//}
		//});
		//thread.start();
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = Math.abs((MAX_SPEED - speed));
	}

	/**
	 * @return the numOfPoints
	 */
	public int getNumOfPoints() {
		return numOfPoints;
	}

	/**
	 * @param numOfPoints
	 */
	public void setNumOfPoints(int numOfPoints) {
		this.numOfPoints = numOfPoints;
		points = null;
		points = new ArrayList<Point>();
	}

	/**
	 * @param points
	 */
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	/**
	 * @return the points
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}

	/**
	 * @return the solution
	 */
	public ArrayList<Point> getSolution() {
		return solution;
	}

	/**
	 * @param solution
	 */
	public void setSolution(ArrayList<Point> solution) {
		this.solution = solution;
	}

	/**
	 * @return the painting
	 */
	public boolean isPainting() {
		return painting;
	}

	/**
	 * @param painting
	 */
	public void setPainting(boolean painting) {
		this.painting = painting;
	}

	/**
	 * @return the step
	 */
	public boolean isStep() {
		return step;
	}
	
	public void setIterations(ArrayList<ArrayList<Point>> iterations){
		this.iterations = iterations;
	}

	/**
	 * @param step
	 */
	public void setStep(boolean step) {
		this.step = step;
	}
}
