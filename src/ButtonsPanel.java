/**
* Subject: 	Programación de Aplicaciones Interactivas
* Practice: P11 QuickHull
* E-mail:   alu0100881677@ull.edu.es 
* Date:     20/4/2017
* @author 	Guillermo Esquivel González
* @version 	1.0.0
*/
import java.awt.*;
import javax.swing.*;

/**
 * This class configure a panel with the necessary buttons for control the application
 * @author Guille
 *
 */
public class ButtonsPanel extends JPanel {
	//private attributes of the class
	private JButton initBtn;
	private JButton runBtn;
	private JButton pauseBtn;
	private JButton clearBtn;
	private JButton stepBtn;
	private JSlider speedSlider;
	private JLabel	speedLabel;
	private final String INIT = "Init";
	private final String RUN = "Run";
	private final String PAUSE = "Pause";
	private final String CLEAR = "Clear";
	private final String STEP = "Step";
	private final String SPEED = "Speed";
	private final int BTN_WIDTH = 80;
	private final int BTN_HEIGHT = 30;
	private final int MIN_SPEED = 100;
	private final int MAX_SPEED = 800;
	
	/**
	 * Constructor of the class
	 * @param speed
	 */
	public ButtonsPanel(final int speed) {
		setLayout(new FlowLayout());
		initBtn = new JButton(INIT);
		addButton(initBtn);
		runBtn = new JButton(RUN);
		addButton(runBtn);
		pauseBtn = new JButton(PAUSE);
		addButton(pauseBtn);
		stepBtn = new JButton(STEP);
		addButton(stepBtn);
		clearBtn = new JButton(CLEAR);
		addButton(clearBtn);
		setSlider(speed);
	}
	
	/**
	 * set up a button and add it to the panel
	 * @param btn
	 */
	private void addButton(JButton btn) {
		btn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
		add(btn);
	}
	
	/**
	 * configures the slider and add it to the panel further a label
	 * @param speed
	 */
	private void setSlider(final int speed) {
		speedLabel = new JLabel(SPEED);
		speedSlider = new JSlider(MIN_SPEED, MAX_SPEED,
			((MAX_SPEED + MIN_SPEED) / 2));
		//speedTextField = new JTextField(String.valueOf(speed));
		//speedTextField.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
		add(speedLabel);
		add(speedSlider);
		//add(speedTextField);
	}


	/**
	 * @return the initBtn
	 */
	public JButton getInitBtn() {
		return initBtn;
	}

	/**
	 * @param initBtn
	 *          the initBtn to set
	 */
	public void setInitBtn(JButton initBtn) {
		this.initBtn = initBtn;
	}

	/**
	 * @return the runBtn
	 */
	public JButton getRunBtn() {
		return runBtn;
	}

	/**
	 * @param runBtn
	 *          the runBtn to set
	 */
	public void setRunBtn(JButton runBtn) {
		this.runBtn = runBtn;
	}

	/**
	 * @return the pauseBtn
	 */
	public JButton getPauseBtn() {
		return pauseBtn;
	}

	/**
	 * @param pauseBtn
	 *          the pauseBtn to set
	 */
	public void setPauseBtn(JButton pauseBtn) {
		this.pauseBtn = pauseBtn;
	}

	/**
	 * @return the stepBtn
	 */
	public JButton getStepBtn() {
		return stepBtn;
	}

	/**
	 * @param stepBtn
	 *          the stepBtn to set
	 */
	public void setStepBtn(JButton stepBtn) {
		this.stepBtn = stepBtn;
	}

	/**
	 * @return the clearBtn
	 */
	public JButton getClearBtn() {
		return clearBtn;
	}

	/**
	 * @param clearBtn
	 *          the clearBtn to set
	 */
	public void setClearBtn(JButton clearBtn) {
		this.clearBtn = clearBtn;
	}

	/**
	 * @return the speedSlider
	 */
	public JSlider getSpeedSlider() {
		return speedSlider;
	}

	/**
	 * @param speedSlider
	 *          the speedSlider to set
	 */
	public void setSpeedSlider(JSlider speedSlider) {
		this.speedSlider = speedSlider;
	}
}
