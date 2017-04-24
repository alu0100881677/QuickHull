/**
* Subject: 	Programación de Aplicaciones Interactivas
* Practice: P11 QuickHull
* E-mail:   alu0100881677@ull.edu.es 
* Date:     20/4/2017
* @author 	Guillermo Esquivel González
* @version 	1.0.0
*/
import javax.swing.JApplet;

/**
 * This class allows to run the application as an applet
 */
public class MyApplet extends JApplet {
	/**
	 * 
	 */
	public void init() {
		int points = Integer.parseInt(getParameter("POINTS"));
		int speed = Integer.parseInt(getParameter("SPEED"));
		Aplication aplication = new Aplication(points, speed);
		add(aplication);
	}
}
