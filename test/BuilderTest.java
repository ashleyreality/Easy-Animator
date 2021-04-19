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

/**
 * This class tests the model builder of the Easy Animator.
 */
public class BuilderTest {
  IAnimationModel model;
  Readable file;
  AnimationBuilder<IAnimationModel> build;

  @Before
  public void setUp() {
    this.model = new AnimationModelImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void canvasNegativeDimensions() {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles" +
            "\\canvasNeg.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeHeight() {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles" +
            "\\negativeHeight.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColor() {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles" +
            "\\invalidColor.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
  }

  @Test(expected = IllegalArgumentException.class)
  public void motionWithoutShape() {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles" +
            "\\motionWithoutShape.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
  }
  @Test(expected = IllegalArgumentException.class)
  public void sameNameShape() {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles" +
            "\\sameNameShape.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidShape() {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles" +
            "\\hexagon.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
  }
}
