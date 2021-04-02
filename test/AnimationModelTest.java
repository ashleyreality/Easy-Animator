import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is the AnimationModelTest class. It tests the AnimationModel interface.
 */
public class AnimationModelTest {
  private IAnimationModel testAnimation;

  IShape R;
  IShape C;

  IEvent move1;
  IEvent move2;
  IEvent move3;

  IEvent size1;

  IEvent colorChange1;

  @Before
  public void setUp() {
    // create the animation model
    testAnimation = new AnimationModelImpl();

    // create the shapes
    R = new Rectangle(50.0, 100.0, 1,0,0,200.0,200.0);
    C = new Oval(60.0, 30.0, 0, 0, 1, 500.0, 100.0);

    // create the moves
    move1 = new MoveShape(R, 300.0, 300.0);
    move2 = new MoveShape(C, 500.0, 400.0);
    move3 = new MoveShape(R, 200.0, 200.0);

    // create the size change
    size1 = new ScaleShape(R, 25.0, 100.0);

    // create the color change
    colorChange1 = new ChangeColor(C, 0, 1, 0);

  }

  @Test
  public void testAddShape() {
    // add the shapes to the animation
    testAnimation.addShape(R, 1, 100);
    testAnimation.addShape(C, 6, 100);

    assertEquals("", testAnimation.toString());
  }

  @Test
  public void testAddMove() {

    testAnimation.addEvent(move1, 10, 50);
    testAnimation.addEvent(move2, 20, 70);
    testAnimation.addEvent(move3, 70, 100);

    assertEquals("", testAnimation.toString());
  }

  @Test
  public void testAddSizeChange() {
    testAnimation.addEvent(size1, 51, 70);

    assertEquals("", testAnimation.toString());
  }

  @Test
  public void testColorChange() {
    testAnimation.addEvent(colorChange1, 50, 80);

    assertEquals("", testAnimation.toString());
  }

  @Test
  public void testEverything() {
    testAddShape();
    testAddMove();
    testColorChange();
    testAddSizeChange();

    assertEquals("\n" +
                    "Shapes:\n" +
                    "Name: R\n" +
                    "Type: rectangle\n" +
                    "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, " +
                    "Color: (1.0,0.0,0.0)\n" +
                    "Appears at t=1\n" +
                    "Disappears at t=100\n" +
                    " \n" +
                    "Name: C\n" +
                    "Type: oval\n" +
                    "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, " +
                    "Color: (0.0,0.0,1.0)\n" +
                    "Appears at t=6\n" +
                    "Disappears at t=100\n" +
                    " \n" +
                    "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n" +
                    "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n" +
                    "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) " +
                    "from t=50 to t=80\n" +
                    "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, " +
                    "Height: 100.0 from t=51 to t=70\n" +
                    "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100"
            , testAnimation.toString());
  }
}
