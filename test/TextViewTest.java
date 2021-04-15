import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import javax.swing.*;

import cs5004.animator.AnimatorHelper;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.util.ViewFactory;
import cs5004.animator.view.IView;

import static org.junit.Assert.assertEquals;

public class TextViewTest {
  IAnimationModel model;
  Readable file;
  AnimationBuilder<IAnimationModel> build;
  // use method to get state -- need to write this

  // call reader parse file method
  // use assert equals

  // make sure we're testing to make sure the file is created correctly
  // test for all exceptions

  @Before
  public void setUp() throws Exception {
    this.model = new AnimationModelImpl();
    String inputName = "/Users/abrown/Northeastern/CS5004/Homeworks/HW6/Easy-Animator/src/cs5004/"
            + "animator/smalldemo.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
  }

  @Test
  public void testState() throws IOException {
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out");
    IView textView = newView.create();
    assertEquals("Create (255,0,0) rectangle R with corner at (200.0,200.0), width 50.0 and "
            + "height 100.0\n"
            + "Create (0,0,255) ellipse C with center at (440.0,70.0), radius 60.0 and 30.0\n"
            + "\n"
            + "R appears at time t=1 and disappears at time t=100\n"
            + "C appears at time t=6 and disappears at time t=100\n"
            + "\n"
            + "R moves from (200.0,200.0) to (300.0,300.0) from time t=10 to t=50\n"
            + "C moves from (440.0,70.0) to (440.0,250.0) from time t=20 to t=50\n"
            + "C moves from (440.0,250.0) to (440.0,370.0) from time t=50 to t=70\n"
            + "R changes width from 50.0 to 25.0 from time t=51 to t=70\n"
            + "C changes color from (0,170,85) to (0,255,0) from time t=70 to t=80\n"
            + "R moves from (300.0,300.0) to (200.0,200.0) from time t=70 to t=100\n", textView.getViewState());
  }
}
