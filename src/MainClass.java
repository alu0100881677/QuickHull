/**
* Subject: 	Programación de Aplicaciones Interactivas
* Practice: P11 QuickHull
* E-mail:   alu0100881677@ull.edu.es 
* Date:     20/4/2017
* @author 	Guillermo Esquivel González
* @version 	1.0.0
*/
import java.awt.BorderLayout;
import javax.swing.JFrame;


/**
 * Enable our program to run standalone by adding a main method.
 * @author Guille
 *
 */
public class MainClass {
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 786;
	private final static int ARGS = 2;
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != ARGS)
			System.err.println("Usage: $java MainClass <numOfPoint> <speed>");
		else {
			JFrame frame = new JFrame("MyApplet");
			Aplication applet = new Aplication(Integer.parseInt(args[0]),
				Integer.parseInt(args[1]));
			frame.add(applet, BorderLayout.CENTER);
			frame.setSize(WIDTH, HEIGHT);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}
}
