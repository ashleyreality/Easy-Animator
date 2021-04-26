import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.util.Timer;

import cs5004.animator.controller.AnimatorController;
import cs5004.animator.view.IView;
import cs5004.animator.view.PlaybackView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class contains the tests for the controller.
 */
public class ControllerTest {
  private IView view;
  private AnimatorController controller;
  private String smalldemo;

  @Before
  public void setUp() {
    controller = new AnimatorController(new String[]{"-in C:\\Users\\jenrw\\IdeaProjects\\"
            + "Easy-Animator\\test\\testFiles\\smalldemo.txt -view playback -out test.txt "
            + "-speed 1"});
    controller.start();
    view = controller.getView();
    smalldemo = "Shapes:\n"
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
            + "C changes color from (0,170,85) to (0,255,0) from time t=70 to t=80\n";
  }

  @Test
  public void ConstructorStartTest() {
    controller = new AnimatorController(new String[]{"-in C:\\Users\\jenrw\\IdeaProjects\\"
            + "Easy-Animator\\test\\testFiles\\smalldemo.txt -view playback -out test.txt "
            + "-speed 1"});
    controller.start();
    assertEquals(smalldemo,
            controller.getModel().toString());
  }

  @Test(expected = NullPointerException.class)
  public void noFileTest() {
    controller = new AnimatorController(new String[]{"-in none -view playback -out test.txt "
            + "-speed 1"});
    controller.start();
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidViewTest() {
    controller = new AnimatorController(new String[]{"-in C:\\Users\\jenrw\\IdeaProjects\\"
            + "Easy-Animator\\test\\testFiles\\smalldemo.txt -view test -out test.txt "
            + "-speed 1"});
    controller.start();
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeSpeedTest() {
    controller = new AnimatorController(new String[]{"-in C:\\Users\\jenrw\\IdeaProjects\\"
            + "Easy-Animator\\test\\testFiles\\smalldemo.txt -view visual -out test.txt "
            + "-speed -1"});
    controller.start();
  }

  @Test
  public void actionPerformedTest() {
    // start
    ActionEvent start = new ActionEvent(view, 1, "START");
    controller.actionPerformed(start);
    assertTrue(view.getTimer().isRunning());
    // stop
    ActionEvent stop = new ActionEvent(view, 1, "STOP");
    controller.actionPerformed(stop);
    assertFalse(view.getTimer().isRunning());
    // speed up and down
    assertEquals(1, view.getSpeed());
    ActionEvent fast = new ActionEvent(view, 1, "FAST");
    controller.actionPerformed(fast);
    assertEquals(2, view.getSpeed());
    ActionEvent slow = new ActionEvent(view, 1, "SLOW");
    controller.actionPerformed(slow);
    assertEquals(1, view.getSpeed());
    // try to make speed 0, but should stay at 1
    controller.actionPerformed(slow);
    assertEquals(1, view.getSpeed());
    // speed changes while running
    controller.actionPerformed(start);
    assertEquals(1, view.getSpeed());
    controller.actionPerformed(fast);
    assertEquals(2, view.getSpeed());
    controller.actionPerformed(slow);
    assertEquals(1, view.getSpeed());
    controller.actionPerformed(stop);
    // restart
    assertFalse(view.getTimer().isRunning());
    ActionEvent restart = new ActionEvent(view, 1, "RESTART");
    controller.actionPerformed(restart);
    assertTrue(view.getTimer().isRunning());
    // load
    ActionEvent load = new ActionEvent(view, 1, "LOAD");
    controller.actionPerformed(load);
    // load small demo
    assertEquals(smalldemo, view.getModel().toString());
    // save as text
    ActionEvent saveText = new ActionEvent(view, 1, "SAVETEXT");
    controller.actionPerformed(saveText);
    // load small demo
    ActionEvent saveSVG = new ActionEvent(view, 1, "SAVETEXT");
    controller.actionPerformed(saveSVG);
  }
}
