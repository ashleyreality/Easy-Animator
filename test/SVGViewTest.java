import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import javax.swing.JFrame;

import cs5004.animator.AnimatorHelper;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.util.ViewFactory;
import cs5004.animator.view.IView;

import static org.junit.Assert.assertEquals;

/**
 * The SVGViewTest class tests the EasyAnimator SVG View.
 */
public class SVGViewTest {
  private IAnimationModel model;
  Readable file;
  AnimationBuilder<IAnimationModel> build;

  // make sure we're testing to make sure the file is created correctly
  // test for all exceptions

  @Before
  public void setUp() throws Exception {
    this.model = new AnimationModelImpl();
  }

  @Test
  public void testState() throws IOException {
    String inputName = "/Users/abrown/Northeastern/CS5004/Homeworks/HW6/Easy-Animator/src/cs5004/"
            + "animator/smalldemo.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("svg", model, "System.out", 1);
    IView svgView = newView.create();
    assertEquals("<svg width=\"360\" height=\"360\" version=\"1.1\" " +
                    "xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "\n" +
                    "<rect id=\"R\" x=\"0\" y=\"130\" width=\"50.0\" height=\"100.0\" " +
                    "fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n" +
                    "\n" +
                    "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"x\" from=\"0\" to=\"100\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"y\" from=\"130\" to=\"230\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\" " +
                    "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\" " +
                    "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\" " +
                    "attributeName=\"x\" from=\"100\" to=\"0\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\" " +
                    "attributeName=\"y\" from=\"230\" to=\"130\" fill=\"freeze\" />\n" +
                    "</rect>\n" +
                    "\n" +
                    "<ellipse id=\"C\" cx=\"240\" cy=\"0\" rx=\"60.0\" ry=\"30.0\" " +
                    "fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n" +
                    "\n" +
                    "<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" " +
                    "attributeName=\"cx\" from=\"240\" to=\"240\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"3000ms\" " +
                    "attributeName=\"cy\" from=\"0\" to=\"180\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" " +
                    "attributeName=\"cx\" from=\"240\" to=\"240\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"2000ms\" " +
                    "attributeName=\"cy\" from=\"180\" to=\"300\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"1000ms\" " +
                    "attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" " +
                    "fill=\"freeze\" />\n" +
                    "</ellipse>\n" +
                    "\n" +
                    "</svg>",
            svgView.getViewState());
  }

  @Test
  public void empty() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\empty.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("svg", model, "test.svg", 1);
    IView textView = newView.create();
    assertEquals("<svg width=\"0\" height=\"0\" version=\"1.1\" xmlns=\""
            + "http://www.w3.org/2000/svg\">\n\n</svg>", textView.getViewState());
  }

  @Test
  public void canvasOnly() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\canvasOnly.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("svg", model, "test.svg", 1);
    IView textView = newView.create();
    assertEquals("<svg width=\"360\" height=\"360\" version=\"1.1\" xmlns=\""
                    + "http://www.w3.org/2000/svg\">\n\n</svg>",
            textView.getViewState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shapeWithoutMotion() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\shapeWithoutMotion.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("svg", model, "test.svg", 1);
    IView textView = newView.create();
    textView.getViewState();
  }
}