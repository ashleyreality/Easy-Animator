import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.processing.FilerException;
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
import static org.junit.Assert.assertNull;

/**
 * The TextViewTest class tests the text view of the Easy Animator.
 */
public class TextViewTest {
  IAnimationModel model;
  Readable file;
  AnimationBuilder<IAnimationModel> build;

  // make sure we're testing to make sure the file is created correctly
  // test for all exceptions
  // test for empty animation

  @Before
  public void setUp() throws Exception {
    this.model = new AnimationModelImpl();
  }

  @Test
  public void testState() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\smalldemo.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out", 1);
    IView textView = newView.create();
    assertEquals("Create (255,0,0) rectangle R with corner at (200.0,200.0), width 50.0 and "
                    + "height 100.0\n"
                    + "Create (0,0,255) ellipse C with center at (440.0,70.0), radius 60.0 and 30.0\n"
                    + "\n"
                    + "R appears at time t=1 and disappears at time t=100\n"
                    + "C appears at time t=6 and disappears at time t=100\n"
                    + "\n"
                    + "R moves from (200.0,200.0) to (300.0,300.0) from time t=10 to t=50\n"
                    + "R changes width from 50.0 to 25.0 from time t=51 to t=70\n"
                    + "R moves from (300.0,300.0) to (200.0,200.0) from time t=70 to t=100\n"
                    + "C moves from (440.0,70.0) to (440.0,250.0) from time t=20 to t=50\n"
                    + "C moves from (440.0,250.0) to (440.0,370.0) from time t=50 to t=70\n"
                    + "C changes color from (0,0,255) to (0,170,85) from time t=50 to t=70\n"
                    + "C changes color from (0,170,85) to (0,255,0) from time t=70 to t=80\n",
            textView.getViewState());
  }

  @Test
  public void testState2() throws IOException {
    String inputName = "/Users/abrown/Northeastern/CS5004/Homeworks/HW6/Easy-Animator/src/cs5004/"
            + "animator/toh-5.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out", 1);
    IView textView = newView.create();
    assertEquals("Create (127,185,246) rectangle disk1 with corner at (190.0,168.0), " +
                    "width 20.0 and height 18.0\n" +
                    "Create (102,162,48) rectangle disk2 with corner at (178.0,186.0), " +
                    "width 42.0 and height 18.0\n" +
                    "Create (157,206,118) rectangle disk3 with corner at (167.0,204.0), " +
                    "width 65.0 and height 18.0\n" +
                    "Create (250,125,210) rectangle disk4 with corner at (156.0,222.0), " +
                    "width 87.0 and height 18.0\n" +
                    "Create (56,214,194) rectangle disk5 with corner at (145.0,240.0), " +
                    "width 110.0 and height 18.0\n" +
                    "\n" +
                    "disk1 appears at time t=1 and disappears at time t=1166\n" +
                    "disk2 appears at time t=1 and disappears at time t=1166\n" +
                    "disk3 appears at time t=1 and disappears at time t=1166\n" +
                    "disk4 appears at time t=1 and disappears at time t=1166\n" +
                    "disk5 appears at time t=1 and disappears at time t=1166\n" +
                    "\n" +
                    "disk1 moves from (190.0,168.0) to (190.0,50.0) from time t=25 to t=35\n" +
                    "disk1 moves from (190.0,50.0) to (490.0,50.0) from time t=36 to t=46\n" +
                    "disk1 moves from (490.0,50.0) to (490.0,240.0) from time t=47 to t=57\n" +
                    "disk2 moves from (178.0,186.0) to (178.0,50.0) from time t=57 to t=67\n" +
                    "disk2 moves from (178.0,50.0) to (328.0,50.0) from time t=68 to t=78\n" +
                    "disk2 moves from (328.0,50.0) to (328.0,240.0) from time t=79 to t=89\n" +
                    "disk1 moves from (490.0,240.0) to (490.0,50.0) from time t=89 to t=99\n" +
                    "disk1 moves from (490.0,50.0) to (340.0,50.0) from time t=100 to t=110\n" +
                    "disk1 moves from (340.0,50.0) to (340.0,222.0) from time t=111 to t=121\n" +
                    "disk3 moves from (167.0,204.0) to (167.0,50.0) from time t=121 to t=131\n" +
                    "disk3 moves from (167.0,50.0) to (467.0,50.0) from time t=132 to t=142\n" +
                    "disk3 moves from (467.0,50.0) to (467.0,240.0) from time t=143 to t=153\n" +
                    "disk1 moves from (340.0,222.0) to (340.0,50.0) from time t=153 to t=163\n" +
                    "disk1 moves from (340.0,50.0) to (190.0,50.0) from time t=164 to t=174\n" +
                    "disk1 moves from (190.0,50.0) to (190.0,204.0) from time t=175 to t=185\n" +
                    "disk2 moves from (328.0,240.0) to (328.0,50.0) from time t=185 to t=195\n" +
                    "disk2 moves from (328.0,50.0) to (478.0,50.0) from time t=196 to t=206\n" +
                    "disk2 moves from (478.0,50.0) to (478.0,222.0) from time t=207 to t=217\n" +
                    "disk1 moves from (190.0,204.0) to (190.0,50.0) from time t=217 to t=227\n" +
                    "disk1 moves from (190.0,50.0) to (490.0,50.0) from time t=228 to t=238\n" +
                    "disk1 moves from (490.0,50.0) to (490.0,204.0) from time t=239 to t=249\n" +
                    "disk4 moves from (156.0,222.0) to (156.0,50.0) from time t=249 to t=259\n" +
                    "disk4 moves from (156.0,50.0) to (306.0,50.0) from time t=260 to t=270\n" +
                    "disk4 moves from (306.0,50.0) to (306.0,240.0) from time t=271 to t=281\n" +
                    "disk1 moves from (490.0,204.0) to (490.0,50.0) from time t=281 to t=291\n" +
                    "disk1 moves from (490.0,50.0) to (340.0,50.0) from time t=292 to t=302\n" +
                    "disk1 moves from (340.0,50.0) to (340.0,222.0) from time t=303 to t=313\n" +
                    "disk2 moves from (478.0,222.0) to (478.0,50.0) from time t=313 to t=323\n" +
                    "disk2 moves from (478.0,50.0) to (178.0,50.0) from time t=324 to t=334\n" +
                    "disk2 moves from (178.0,50.0) to (178.0,222.0) from time t=335 to t=345\n" +
                    "disk1 moves from (340.0,222.0) to (340.0,50.0) from time t=345 to t=355\n" +
                    "disk1 moves from (340.0,50.0) to (190.0,50.0) from time t=356 to t=366\n" +
                    "disk1 moves from (190.0,50.0) to (190.0,204.0) from time t=367 to t=377\n" +
                    "disk3 moves from (467.0,240.0) to (467.0,50.0) from time t=377 to t=387\n" +
                    "disk3 moves from (467.0,50.0) to (317.0,50.0) from time t=388 to t=398\n" +
                    "disk3 moves from (317.0,50.0) to (317.0,222.0) from time t=399 to t=409\n" +
                    "disk1 moves from (190.0,204.0) to (190.0,50.0) from time t=409 to t=419\n" +
                    "disk1 moves from (190.0,50.0) to (490.0,50.0) from time t=420 to t=430\n" +
                    "disk1 moves from (490.0,50.0) to (490.0,240.0) from time t=431 to t=441\n" +
                    "disk2 moves from (178.0,222.0) to (178.0,50.0) from time t=441 to t=451\n" +
                    "disk2 moves from (178.0,50.0) to (328.0,50.0) from time t=452 to t=462\n" +
                    "disk2 moves from (328.0,50.0) to (328.0,204.0) from time t=463 to t=473\n" +
                    "disk1 moves from (490.0,240.0) to (490.0,50.0) from time t=473 to t=483\n" +
                    "disk1 moves from (490.0,50.0) to (340.0,50.0) from time t=484 to t=494\n" +
                    "disk1 moves from (340.0,50.0) to (340.0,186.0) from time t=495 to t=505\n" +
                    "disk5 moves from (145.0,240.0) to (145.0,50.0) from time t=505 to t=515\n" +
                    "disk5 moves from (145.0,50.0) to (445.0,50.0) from time t=516 to t=526\n" +
                    "disk5 moves from (445.0,50.0) to (445.0,240.0) from time t=527 to t=537\n" +
                    "disk1 moves from (340.0,186.0) to (340.0,50.0) from time t=537 to t=547\n" +
                    "disk5 changes color from (56,214,194) to (0,255,0) from time t=537 to t=545\n" +
                    "disk1 moves from (340.0,50.0) to (190.0,50.0) from time t=548 to t=558\n" +
                    "disk1 moves from (190.0,50.0) to (190.0,240.0) from time t=559 to t=569\n" +
                    "disk2 moves from (328.0,204.0) to (328.0,50.0) from time t=569 to t=579\n" +
                    "disk2 moves from (328.0,50.0) to (478.0,50.0) from time t=580 to t=590\n" +
                    "disk2 moves from (478.0,50.0) to (478.0,222.0) from time t=591 to t=601\n" +
                    "disk1 moves from (190.0,240.0) to (190.0,50.0) from time t=601 to t=611\n" +
                    "disk1 moves from (190.0,50.0) to (490.0,50.0) from time t=612 to t=622\n" +
                    "disk1 moves from (490.0,50.0) to (490.0,204.0) from time t=623 to t=633\n" +
                    "disk3 moves from (317.0,222.0) to (317.0,50.0) from time t=633 to t=643\n" +
                    "disk3 moves from (317.0,50.0) to (167.0,50.0) from time t=644 to t=654\n" +
                    "disk3 moves from (167.0,50.0) to (167.0,240.0) from time t=655 to t=665\n" +
                    "disk1 moves from (490.0,204.0) to (490.0,50.0) from time t=665 to t=675\n" +
                    "disk1 moves from (490.0,50.0) to (340.0,50.0) from time t=676 to t=686\n" +
                    "disk1 moves from (340.0,50.0) to (340.0,222.0) from time t=687 to t=697\n" +
                    "disk2 moves from (478.0,222.0) to (478.0,50.0) from time t=697 to t=707\n" +
                    "disk2 moves from (478.0,50.0) to (178.0,50.0) from time t=708 to t=718\n" +
                    "disk2 moves from (178.0,50.0) to (178.0,222.0) from time t=719 to t=729\n" +
                    "disk1 moves from (340.0,222.0) to (340.0,50.0) from time t=729 to t=739\n" +
                    "disk1 moves from (340.0,50.0) to (190.0,50.0) from time t=740 to t=750\n" +
                    "disk1 moves from (190.0,50.0) to (190.0,204.0) from time t=751 to t=761\n" +
                    "disk4 moves from (306.0,240.0) to (306.0,50.0) from time t=761 to t=771\n" +
                    "disk4 moves from (306.0,50.0) to (456.0,50.0) from time t=772 to t=782\n" +
                    "disk4 moves from (456.0,50.0) to (456.0,222.0) from time t=783 to t=793\n" +
                    "disk1 moves from (190.0,204.0) to (190.0,50.0) from time t=793 to t=803\n" +
                    "disk4 changes color from (250,125,210) to (0,255,0) from time t=793 to " +
                    "t=801\n" +
                    "disk1 moves from (190.0,50.0) to (490.0,50.0) from time t=804 to t=814\n" +
                    "disk1 moves from (490.0,50.0) to (490.0,204.0) from time t=815 to t=825\n" +
                    "disk2 moves from (178.0,222.0) to (178.0,50.0) from time t=825 to t=835\n" +
                    "disk2 moves from (178.0,50.0) to (328.0,50.0) from time t=836 to t=846\n" +
                    "disk2 moves from (328.0,50.0) to (328.0,240.0) from time t=847 to t=857\n" +
                    "disk1 moves from (490.0,204.0) to (490.0,50.0) from time t=857 to t=867\n" +
                    "disk1 moves from (490.0,50.0) to (340.0,50.0) from time t=868 to t=878\n" +
                    "disk1 moves from (340.0,50.0) to (340.0,222.0) from time t=879 to t=889\n" +
                    "disk3 moves from (167.0,240.0) to (167.0,50.0) from time t=889 to t=899\n" +
                    "disk3 moves from (167.0,50.0) to (467.0,50.0) from time t=900 to t=910\n" +
                    "disk3 moves from (467.0,50.0) to (467.0,204.0) from time t=911 to t=921\n" +
                    "disk1 moves from (340.0,222.0) to (340.0,50.0) from time t=921 to t=931\n" +
                    "disk3 changes color from (157,206,118) to (0,255,0) from time t=921 to " +
                    "t=929\n" +
                    "disk1 moves from (340.0,50.0) to (190.0,50.0) from time t=932 to t=942\n" +
                    "disk1 moves from (190.0,50.0) to (190.0,240.0) from time t=943 to t=953\n" +
                    "disk2 moves from (328.0,240.0) to (328.0,50.0) from time t=953 to t=963\n" +
                    "disk2 moves from (328.0,50.0) to (478.0,50.0) from time t=964 to t=974\n" +
                    "disk2 moves from (478.0,50.0) to (478.0,186.0) from time t=975 to t=985\n" +
                    "disk1 moves from (190.0,240.0) to (190.0,50.0) from time t=985 to t=995\n" +
                    "disk2 changes color from (102,162,48) to (0,255,0) from time t=985 to " +
                    "t=993\n" +
                    "disk1 moves from (190.0,50.0) to (490.0,50.0) from time t=996 to t=1006\n" +
                    "disk1 moves from (490.0,50.0) to (490.0,168.0) from time t=1007 to t=1017\n" +
                    "disk1 changes color from (127,185,246) to (0,255,0) from time t=1017 to " +
                    "t=1025\n",
            textView.getViewState());
  }


  @Test
  public void testNoInFile() throws FileNotFoundException {
    String inputName = "";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    assertNull(this.file);
  }

  @Test
  public void testEmptyOutFile() {
    String inputName = "/Users/abrown/Northeastern/CS5004/Homeworks/HW6/Easy-Animator/src/cs5004/"
            + "animator/smalldemo.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "", 1);
    assertEquals(System.out, newView.getOutputName());
  }

  // it returns an empty string for now, but should it throw an error?
  @Test
  public void empty() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\empty.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out", 1);
    IView textView = newView.create();
    assertEquals("\n\n", textView.getViewState());
  }

  @Test
  public void canvasOnly() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\canvasOnly.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out", 1);
    IView textView = newView.create();
    assertEquals("\n\n",
            textView.getViewState());
  }

  // fixme should also be throwing an error if we put in a negative parameter for canvas dimensions
  @Test(expected = IllegalArgumentException.class)
  public void canvasNegativeDimensions() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\canvasNeg.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeHeight() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\negativeHeight.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out", 1);
    newView.create();
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColor() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\invalidColor.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    //ViewFactory newView = new ViewFactory("text", model, "System.out", 1);
    //newView.create();
  }

  //fixme this doesn't work because everything in the shapes are null, so the toString can't be called
  // is that fine?
  @Test
  public void shapeWithoutMotion() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\shapeWithoutMotion.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out", 1);
    IView textView = newView.create();
    assertEquals("should just be the declared shapes here and maybe with some bad appear and disappear info",
            textView.getViewState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void motionWithoutShape() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\motionWithoutShape.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out", 1);
    IView textView = newView.create();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sameNameShape() throws IOException {
    String inputName = "C:\\Users\\jenrw\\IdeaProjects\\Easy-Animator\\test\\testFiles\\sameNameShape.txt";
    JFrame frame = AnimatorHelper.jFrameStart();
    this.file = AnimatorHelper.fileExceptions(inputName, frame);
    this.build = new Builder(model);
    model = AnimationReader.parseFile(file, build);
    ViewFactory newView = new ViewFactory("text", model, "System.out", 1);
    IView textView = newView.create();
  }

//ideas to test
  /*
  multiple changes in one motion line


  empty file
  just canvas dimensions
  bad numbers, ex. negative height, width, color out of range
  shape without motion
  motion without shape
  negative dimensions for canvas
  multiple shapes with same name
   */


}
