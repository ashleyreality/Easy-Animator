import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import javax.swing.*;

import cs5004.animator.controller.AnimatorController;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * This class contains the tests for the controller.
 */
public class ControllerTest {
  private IAnimationModel testAnimation;
  public AnimatorController controller;

  // IShapes: Rectangle named R & Oval named C
  IShape r;
  IShape c;

  // IEvents for IShape moves
  IEvent move1;
  IEvent move2;
  IEvent move3;

  // IEvents for IShape size changes
  IEvent size1;
  IEvent size2;

  // IEvents for IShape color changes
  IEvent colorChange1;
  IEvent colorChange2;

  @Before
  public void setUp() {
    controller = new AnimatorController(new String[]{"-in C:\\Users\\jenrw\\IdeaProjects\\"
            + "Easy-Animator\\test\\testFiles\\smalldemo.txt -view playback -out test.txt "
            + "-speed 1"});
  }

  @Test
  public void ConstructorStartTest() {
    controller = new AnimatorController(new String[]{"-in C:\\Users\\jenrw\\IdeaProjects\\"
            + "Easy-Animator\\test\\testFiles\\smalldemo.txt -view playback -out test.txt "
            + "-speed 1"});
    controller.start();
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255,0,0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: ellipse\n"
                    + "Center: (440.0,70.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,255)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "R moves from (200.0,200.0) to (300.0,300.0) from time t=10 to t=50\n"
                    + "C moves from (440.0,70.0) to (440.0,250.0) from time t=20 to t=50\n"
                    + "C moves from (440.0,250.0) to (440.0,370.0) from time t=50 to t=70\n"
                    + "C changes color from (0,0,255) to (0,170,85) from time t=50 to t=70\n"
                    + "R changes width from 50.0 to 25.0 from time t=51 to t=70\n"
                    + "R moves from (300.0,300.0) to (200.0,200.0) from time t=70 to t=100\n"
                    + "C changes color from (0,170,85) to (0,255,0) from time t=70 to t=80\n",
            controller.getModel().toString());
  }

  @Test (expected = NullPointerException.class)
  public void noFileTest() {
    controller = new AnimatorController(new String[]{"-in none -view playback -out test.txt "
            + "-speed 1"});
    controller.start();
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidViewTest() {
    controller = new AnimatorController(new String[]{"-in C:\\Users\\jenrw\\IdeaProjects\\"
            + "Easy-Animator\\test\\testFiles\\smalldemo.txt -view test -out test.txt "
            + "-speed 1"});
    controller.start();
  }

  @Test (expected = IllegalArgumentException.class)
  public void negativeSpeedTest() {
    controller = new AnimatorController(new String[]{"-in C:\\Users\\jenrw\\IdeaProjects\\"
            + "Easy-Animator\\test\\testFiles\\smalldemo.txt -view visual -out test.txt "
            + "-speed -1"});
    controller.start();
  }
}
