import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;
import cs5004.animator.model.MoveShape;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ScaleShape;

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

    c = new Ellipse("C",
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
            + "Type: ellipse\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n"
            + "\n", testAnimation.toString());
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
                    + "Type: ellipse\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "C moves from (500.0,100.0) to (300.0,300.0) from time t=20 to t=45\n"
                    + "C changes height from 60.0 to 10.67 and changes width from 120.0 to 85.0"
                    + " from time t=28 to t=88\nC changes color from (0,0,1) to (0,1,0) from " +
                    "time t=36 "
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
                    + "Type: ellipse\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "R moves from (200.0,200.0) to (300.0,300.0) from time t=10 to t=50\n"
                    + "C moves from (500.0,100.0) to (500.0,400.0) from time t=20 to t=70\n"
                    + "R moves from (300.0,300.0) to (200.0,200.0) from time t=70 to t=100\n",
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
                    + "R changes width from 50.0 to 25.0 from time t=51 to t=70"
                    + "\nR changes height from 100.0 to 13.0 from time t=71 to t=73\n",
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
                    + "Type: ellipse\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "C changes color from (0,0,1) to (0,1,0) from time t=50 to t=80\n"
                    + "C changes color from (0,1,0) to (25,1,0) from time t=81 to t=90\n",
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
                    + "Type: ellipse\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n"
                    + "\n"
                    + "R moves from (200.0,200.0) to (300.0,300.0) from time t=10 to t=50\n"
                    + "C moves from (500.0,100.0) to (500.0,400.0) from time t=20 to t=70\n"
                    + "C changes color from (0,0,1) to (0,1,0) from time t=50 to t=80\n"
                    + "R changes width from 50.0 to 25.0 from time t=51 to t=70"
                    + "\nR moves from (300.0,300.0) to (200.0,200.0) from time"
                    + " t=70 to t=100\n",
            testAnimation.toString());
  }

  @Test
  public void testGetShapesAtTick() {
    // empty
    assertEquals("[]", testAnimation.getShapesAtTick(1).toString());
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

    // one shape
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "]", testAnimation.getShapesAtTick(1).toString());

    // move
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (202.5,202.5), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + ", Name: C\n"
            + "Type: ellipse\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n"
            + "]", testAnimation.getShapesAtTick(11).toString());

    // scale change
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (300.0,300.0), Width: 31.578947368421055, "
            + "Height: 100.0, Color: (1,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + ", Name: C\n"
            + "Type: ellipse\n"
            + "Center: (500.0,370.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n"
            + "]", testAnimation.getShapesAtTick(65).toString());

    //color change
    assertEquals("[Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (300.0,300.0), Width: 43.421052631578945, Height: 100.0, " +
            "Color: (1,0,0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + ", Name: C\n"
            + "Type: ellipse\n"
            + "Center: (500.0,316.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n"
            + "]", testAnimation.getShapesAtTick(56).toString());
    assertEquals("[]", testAnimation.getShapesAtTick(500).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTicks() {
    testAnimation.getShapesAtTick(-1);
  }
}
