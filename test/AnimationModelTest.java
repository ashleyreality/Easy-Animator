import org.junit.Before;
import org.junit.Test;

import cs5004.animation.model.AnimationModelImpl;
import cs5004.animation.model.ChangeColor;
import cs5004.animation.model.IAnimationModel;
import cs5004.animation.model.IEvent;
import cs5004.animation.model.IShape;
import cs5004.animation.model.MoveShape;
import cs5004.animation.model.Oval;
import cs5004.animation.model.Rectangle;
import cs5004.animation.model.ScaleShape;

import static org.junit.Assert.assertEquals;

/**
 * The AnimationModelTest class tests the AnimationModel interface.
 */
public class AnimationModelTest {

  private IAnimationModel testAnimation;

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
    // Instantiate the animation model
    testAnimation = new AnimationModelImpl();

    // Instantiate the IShapes
    r = new Rectangle("R",
            50.0, 100.0,
            1, 0, 0,
            200.0, 200.0);

    c = new Oval("C",
            120.0, 60.0,
            0, 0, 1,
            500.0, 100.0);
  }

  // _________________________________ EXCEPTIONS: Rectangle ______________________________________.
  @Test(expected = IllegalArgumentException.class)
  public void sameShapeName() {

    testAnimation.addShape(r, 1, 100);

    // new shape with same name
    IShape f = new Rectangle("R",
            2, 4,
            3, 50, 10,
            30.0, 75.0);

    testAnimation.addShape(f, 30, 80);
  }

  // ___________________________ EXCEPTIONS: Duplicate shape ______________________________________.
  @Test(expected = IllegalArgumentException.class)
  public void testAddSameShapeTwice() {
    testAnimation.addShape(c, 1, 100);
    testAnimation.addShape(c, 50, 70);
  }

  // ___________________________ EXCEPTIONS: Appear and Disappear _________________________________.
  @Test(expected = IllegalArgumentException.class)
  public void testAppearEqualsDisappear() {
    testAnimation.addShape(c, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisappearAt0() {
    testAnimation.addShape(r, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAppearAfterDisappear() {
    testAnimation.addShape(r, 13, 1);
  }

  // ___________________________ EXCEPTIONS: Null tests ______________________________________.
  @Test(expected = IllegalArgumentException.class)
  public void testNullShape() {
    testAnimation.addShape(null, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullShapeEvent() {
    testAnimation.addShape(c, 1, 100);
    move1 = new MoveShape(c, 500.0, 100.0, 300.0, 300.0);
    testAnimation.addEvent(null, move1, 20, 45);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullEvent() {
    testAnimation.addShape(c, 1, 100);
    testAnimation.addEvent(c, null, 29, 85);
  }

  // _____________________________________ AddShape() Test ________________________________________.
  @Test
  public void testAddShape() {
    // add the "R" & "C" shapes to the animation
    testAnimation.addShape(r, 1, 100);
    testAnimation.addShape(c, 6, 100);

    assertEquals("Shapes:\n"
                    + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n\n", testAnimation.toString());
  }

  // ____________________________ EXCEPTIONS: Event without Shape _________________________________.

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveEventWithoutAddShape() {
    move1 = new MoveShape(r, 200.0, 200.0, 300.0, 300.0);
    testAnimation.addEvent(r, move1, 10, 50);
  }

  // _____________________________ EXCEPTIONS: Begin and End ______________________________________.
  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveEndBeforeBegin() {
    testAnimation.addShape(r, 1, 100);
    size1 = new ScaleShape(r, 50.0, 100.0, 25.0, 100.0);
    testAnimation.addEvent(r, size1, 90, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBeforeAppear() {
    testAnimation.addShape(c, 30, 100);
    colorChange1 = new ChangeColor(c,
            0, 0, 1,
            0, 1, 0);
    testAnimation.addEvent(r, size1, 20, 45);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEventBeforeAppear() {
    testAnimation.addShape(c, 30, 100);
    colorChange1 = new ChangeColor(c,
            0, 0, 1,
            0, 1, 0);
    testAnimation.addEvent(r, size1, 0, 29);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAfterAppear() {
    testAnimation.addShape(c, 30, 100);
    colorChange1 = new ChangeColor(c,
            0, 0, 1,
            0, 1, 0);
    testAnimation.addEvent(r, size1, 31, 166);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEventAfterDisappear() {
    testAnimation.addShape(c, 30, 100);
    colorChange1 = new ChangeColor(c,
            0, 0, 1,
            0, 1, 0);
    testAnimation.addEvent(r, size1, 101, 166);
  }

  // ____________ EXCEPTIONS: Add Same Event types With Overlapping tick range  ___________________.
  @Test(expected = IllegalArgumentException.class)
  public void testScaleOverlap() {
    testAnimation.addShape(r, 1, 100);
    size1 = new ScaleShape(r, 50.0, 100.0, 25.0, 100.0);
    size2 = new ScaleShape(r, 25.0, 100.0, 70.0, 1000.0);
    testAnimation.addEvent(r, size1, 90, 100);
    testAnimation.addEvent(r, size2, 89, 95);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMoveOverlap() {
    testAnimation.addShape(r, 1, 100);
    move1 = new MoveShape(r, 200.0, 200.0, 300.0, 300.0);
    move2 = new MoveShape(r, 300.0, 300.0, 200.0, 200.0);
    testAnimation.addEvent(r, move1, 60, 100);
    testAnimation.addEvent(r, move2, 89, 95);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorOverlap() {
    testAnimation.addShape(c, 1, 100);
    colorChange1 = new ChangeColor(c,
            0, 0, 1,
            0, 1, 0);
    colorChange2 = new ChangeColor(c,
            0, 1, 0,
            25, 1, 0);
    testAnimation.addEvent(c, colorChange1, 20, 45);
    testAnimation.addEvent(c, colorChange2, 36, 95);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDuplicateEvent() {
    testAnimation.addShape(c, 1, 100);
    colorChange1 = new ChangeColor(c,
            0, 0, 1,
            0, 1, 0);
    testAnimation.addEvent(c, colorChange1, 20, 45);
    testAnimation.addEvent(c, colorChange1, 36, 95);
  }

  // _________________________ Overlapping Different Changes Test _______________________________.
  @Test
  public void testDifferentChangeTypeOverlap() {
    testAnimation.addShape(c, 1, 100);
    move1 = new MoveShape(c, 500.0, 100.0, 300.0, 300.0);
    size1 = new ScaleShape(c, 120.0, 60.0, 85.0, 10.67);
    colorChange1 = new ChangeColor(c,
            0, 0, 1,
            0, 1, 0);
    testAnimation.addEvent(c, move1, 20, 45);
    testAnimation.addEvent(c, size1, 28, 88);
    testAnimation.addEvent(c, colorChange1, 36, 95);
    assertEquals("Shapes:\n"
            + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape C moves from (500.0,100.0) to (300.0,300.0) from t=20 to t=45\n"
                    + "Shape C scales from Width: 120.0, Height: 60.0 to Width: 85.0, Height: 10.7"
            + " from t=28 to t=88\nShape C changes color from (0,0,1) to (0,1,0) from t=36 "
                    + "to t=95\n",
            testAnimation.toString());
  }

  // ______________________________________ AddMove() Test ________________________________________.
  @Test
  public void testAddMove() {
    testAnimation.addShape(r, 1, 100);
    testAnimation.addShape(c, 6, 100);

    // Instantiate the moves
    move1 = new MoveShape(r, 200.0, 200.0, 300.0, 300.0);
    move2 = new MoveShape(c, 500.0, 100.0, 500.0, 400.0);
    move3 = new MoveShape(r, 300.0, 300.0, 200.0, 200.0);

    testAnimation.addEvent(r, move1, 10, 50);
    testAnimation.addEvent(c, move2, 20, 70);
    testAnimation.addEvent(r, move3, 70, 100);

    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
            + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n",
            testAnimation.toString());
  }

  // __________________________________ AddSizeChange() Test ______________________________________.
  @Test
  public void testAddSizeChange() {
    testAnimation.addShape(r, 1, 100);
    // Instantiate the size change
    size1 = new ScaleShape(r, 50.0, 100.0, 25.0, 100.0);
    size2 = new ScaleShape(r, 25.0, 100.0, 25.0, 13.0);
    testAnimation.addEvent(r, size1, 51, 70);
    testAnimation.addEvent(r, size2, 71, 73);

    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0"
                    + " from t=51 to t=70\nShape R scales from Width: 25.0, Height: 100.0 to Width:"
                    + " 25.0, Height: 13.0 from t=71 to t=73\n",
             testAnimation.toString());
  }

  // ____________________________________ ColorChange() Test ______________________________________.
  @Test
  public void testColorChange() {
    testAnimation.addShape(c, 6, 100);
    // Instantiate the color change
    colorChange1 = new ChangeColor(c,
            0, 0, 1,
            0, 1, 0);
    colorChange2 = new ChangeColor(c,
            0, 1, 0,
            25, 1, 0);
    testAnimation.addEvent(c, colorChange1, 50, 80);
    testAnimation.addEvent(c, colorChange2, 81, 90);
    assertEquals("Shapes:\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape C changes color from (0,0,1) to (0,1,0) from t=50 to t=80\n"
            + "Shape C changes color from (0,1,0) to (25,1,0) from t=81 to t=90\n",
            testAnimation.toString());
  }

  // _____________________________________ Test Everything ________________________________________.
  @Test
  public void testEverything() {
    testAnimation.addShape(r, 1, 100);
    testAnimation.addShape(c, 6, 100);

    move1 = new MoveShape(r, 200.0, 200.0, 300.0, 300.0);
    move2 = new MoveShape(c, 500.0, 100.0, 500.0, 400.0);
    move3 = new MoveShape(r, 300.0, 300.0, 200.0, 200.0);

    size1 = new ScaleShape(r, 50.0, 100.0, 25.0, 100.0);

    colorChange1 = new ChangeColor(c, 0, 0, 1, 0, 1, 0);

    testAnimation.addEvent(r, move1, 10, 50);
    testAnimation.addEvent(c, move2, 20, 70);
    testAnimation.addEvent(c, colorChange1, 50, 80);
    testAnimation.addEvent(r, size1, 51, 70);
    testAnimation.addEvent(r, move3, 70, 100);

    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
                    + "Shape C changes color from (0,0,1) to (0,1,0) from t=50 to t=80\n"
                    + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0"
                    + " from t=51 to t=70\nShape R moves from (300.0,300.0) to (200.0,200.0) from"
                    + " t=70 to t=100\n",
             testAnimation.toString());
  }
}
