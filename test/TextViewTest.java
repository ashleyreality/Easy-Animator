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
            + "animator/smalldemotext.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
  }

  @Test
  public void testState() throws IOException {
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out");
    newView.create();
  }
}
