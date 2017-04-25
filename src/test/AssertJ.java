package test;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import 
import static org.assertj.core.api.Assertions.assertThat;
import java.awt.Dimension;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Test;

public class AssertJ {
	  private Aplication frame;

	  
	  @Before
	  public void initialize() {
	    applet = new GUI();
	    JFrame window = new JFrame();
	    window.setSize(800, 600);
	    window.add(applet);
	    applet.init();
	    applet.start();
	    window.setVisible(true);
	    frame = new FrameFixture(window);
	  }

	  //Test for lanzar button
	  @Test
	  public void testLanzar() {
	    frame.button("lanzar").click();
	    assertEquals(applet.getTimer().isRunning(),true);
	  }
	  
	  //Test for pausa button
	  @Test
	  public void testPausa() {
	    frame.button("pausa").click();
	    assertEquals(applet.getTimer().isRunning(),false);
	  }
	  
	  //Test for borrar button
	  @Test
	  public void testBorrar() {
	    frame.button("borrar").click();
	    assertEquals(applet.getShootCounter(),0);
	  }
	  
	  //Test for speed slider
	  @Test
	  public void testVel() {
	    frame.slider("slider_Velocidad").slideTo(55);
	    int aux = (int)applet.getControl().getVel();
	    assertEquals(aux,55);

	  }
	  
	  //Test for degree slider
	  @Test
	  public void testAng() {
	    frame.slider("slider_Ángulo").slideTo(49);
	    int aux = (int)applet.getControl().getAngulo();
	    assertEquals(aux,49);

	  }
	  
	  //Test for height slider
	  @Test
	  public void testAlt() {
	    frame.slider("slider_Altura").slideTo(20);
	    int aux = (int)applet.getControl().getAltura();
	    assertEquals(aux,20);

	  }
	 
	  @After
	  public void clear() {
	    frame.cleanUp();
	  }
	}