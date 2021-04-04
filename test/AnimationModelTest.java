import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is the AnimationModelTest class. It tests the AnimationModel interface.
 */
public class AnimationModelTest {

  private IAnimationModel testAnimation;

  // IShapes: Rectangle named R & Oval named C
  IShape R;
  IShape C;

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
    R = new Rectangle("R",
            50.0, 100.0,
            1, 0, 0,
            200.0, 200.0);

    C = new Oval("C",
            120.0, 60.0,
            0, 0, 1,
            500.0, 100.0);
  }

  // _________________________________ EXCEPTIONS: Rectangle ______________________________________.
  @Test(expected = IllegalArgumentException.class)
  public void sameShapeName() {

    testAnimation.addShape(R, 1, 100);

    // new shape with same name
    IShape F = new Rectangle("R",
            2, 4,
            3, 50, 10,
            30.0, 75.0);

    testAnimation.addShape(F, 30, 80);
  }

  // ___________________________ EXCEPTIONS: Duplicate shape ______________________________________.
  @Test(expected = IllegalArgumentException.class)
  public void testAddSameShapeTwice() {
    testAnimation.addShape(C, 1, 100);
    testAnimation.addShape(C, 50, 70);
  }

  // ___________________________ EXCEPTIONS: Appear and Disappear _________________________________.
  @Test(expected = IllegalArgumentException.class)
  public void testAppearEqualsDisappear() {
    testAnimation.addShape(C, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisappearAt0() {
    testAnimation.addShape(R, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAppearAfterDisappear() {
    testAnimation.addShape(R, 13, 1);
  }

  // _____________________________________ AddShape() Test ________________________________________.
  @Test
  public void testAddShape() {
    // add the "R" & "C" shapes to the animation
    testAnimation.addShape(R, 1, 100);
    testAnimation.addShape(C, 6, 100);

    assertEquals("""
            Shapes:
            Name: R
            Type: rectangle
            Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)
            Appears at t=1
            Disappears at t=100

            Name: C
            Type: oval
            Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)
            Appears at t=6
            Disappears at t=100

            """, testAnimation.toString());
  }

  // ____________________________ EXCEPTIONS: Event without Shape _________________________________.

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveEventWithoutAddShape() {
    move1 = new MoveShape(R, 200.0, 200.0, 300.0, 300.0);
    testAnimation.addEvent(R, move1, 10, 50);
  }

  // _____________________________ EXCEPTIONS: Begin and End ______________________________________.
  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveEndBeforeBegin() {
    testAnimation.addShape(R, 1, 100);
    size1 = new ScaleShape(R, 50.0, 100.0, 25.0, 100.0);
    testAnimation.addEvent(R, size1, 90, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBeforeAppear() {
    testAnimation.addShape(C, 30, 100);
    colorChange1 = new ChangeColor(C,
            0, 0, 1,
            0, 1, 0);
    testAnimation.addEvent(R, size1, 20, 45);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEventBeforeAppear() {
    testAnimation.addShape(C, 30, 100);
    colorChange1 = new ChangeColor(C,
            0, 0, 1,
            0, 1, 0);
    testAnimation.addEvent(R, size1, 0, 29);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAfterAppear() {
    testAnimation.addShape(C, 30, 100);
    colorChange1 = new ChangeColor(C,
            0, 0, 1,
            0, 1, 0);
    testAnimation.addEvent(R, size1, 31, 166);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEventAfterDisappear() {
    testAnimation.addShape(C, 30, 100);
    colorChange1 = new ChangeColor(C,
            0, 0, 1,
            0, 1, 0);
    testAnimation.addEvent(R, size1, 101, 166);
  }

  // ____________ EXCEPTIONS: Add Same Event types With Overlapping tick range  ___________________.
  @Test(expected = IllegalArgumentException.class)
  public void testScaleOverlap() {
    testAnimation.addShape(R, 1, 100);
    size1 = new ScaleShape(R, 50.0, 100.0, 25.0, 100.0);
    size2 = new ScaleShape(R, 25.0, 100.0, 70.0, 1000.0);
    testAnimation.addEvent(R, size1, 90, 100);
    testAnimation.addEvent(R, size2, 89, 95);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMoveOverlap() {
    testAnimation.addShape(R, 1, 100);
    move1 = new MoveShape(R, 200.0, 200.0, 300.0, 300.0);
    move2 = new MoveShape(R, 300.0, 300.0, 200.0, 200.0);
    testAnimation.addEvent(R, move1, 60, 100);
    testAnimation.addEvent(R, move2, 89, 95);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorOverlap() {
    testAnimation.addShape(C, 1, 100);
    colorChange1 = new ChangeColor(C,
            0, 0, 1,
            0, 1, 0);
    colorChange2 = new ChangeColor(C,
            0, 1, 0,
            25, 1, 0);
    testAnimation.addEvent(C, colorChange1, 20, 45);
    testAnimation.addEvent(C, colorChange2, 36, 95);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDuplicateEvent() {
    testAnimation.addShape(C, 1, 100);
    colorChange1 = new ChangeColor(C,
            0, 0, 1,
            0, 1, 0);
    testAnimation.addEvent(C, colorChange1, 20, 45);
    testAnimation.addEvent(C, colorChange1, 36, 95);
  }

  // ______________________________________ AddMove() Test ________________________________________.
  @Test
  public void testAddMove() {
    testAnimation.addShape(R, 1, 100);
    testAnimation.addShape(C, 6, 100);

    // Instantiate the moves
    move1 = new MoveShape(R, 200.0, 200.0, 300.0, 300.0);
    move2 = new MoveShape(C, 500.0, 100.0, 500.0, 400.0);
    move3 = new MoveShape(R, 300.0, 300.0, 200.0, 200.0);

    testAnimation.addEvent(R, move1, 10, 50);
    testAnimation.addEvent(C, move2, 20, 70);
    testAnimation.addEvent(R, move3, 70, 100);

    assertEquals("""
                    Shapes:
                    Name: R
                    Type: rectangle
                    Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)
                    Appears at t=1
                    Disappears at t=100
                                        
                    Name: C
                    Type: oval
                    Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)
                    Appears at t=6
                    Disappears at t=100
                                       
                    Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50
                    Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70
                    Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100
                    """,
            testAnimation.toString());
  }

  // __________________________________ AddSizeChange() Test ______________________________________.
  @Test
  public void testAddSizeChange() {
    testAnimation.addShape(R, 1, 100);
    // Instantiate the size change
    size1 = new ScaleShape(R, 50.0, 100.0, 25.0, 100.0);
    size2 = new ScaleShape(R, 25.0, 100.0, 25.0, 13.0);
    testAnimation.addEvent(R, size1, 51, 70);
    testAnimation.addEvent(R, size2, 71, 73);

    assertEquals("""
            Shapes:
            Name: R
            Type: rectangle
            Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)
            Appears at t=1
            Disappears at t=100
                        
            Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70
            Shape R scales from Width: 25.0, Height: 100.0 to Width: 25.0, Height: 13.0 from t=71 to t=73
            """, testAnimation.toString());
  }

  // ____________________________________ ColorChange() Test ______________________________________.
  @Test
  public void testColorChange() {
    testAnimation.addShape(C, 6, 100);
    // Instantiate the color change
    colorChange1 = new ChangeColor(C,
            0, 0, 1,
            0, 1, 0);
    colorChange2 = new ChangeColor(C,
            0, 1, 0,
            25, 1, 0);
    testAnimation.addEvent(C, colorChange1, 50, 80);
    testAnimation.addEvent(C, colorChange2, 81, 90);
    assertEquals("""
                    Shapes:
                    Name: C
                    Type: oval
                    Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)
                    Appears at t=6
                    Disappears at t=100
                                        
                    Shape C changes color from (0,0,1) to (0,1,0) from t=50 to t=80
                    Shape C changes color from (0,1,0) to (25,1,0) from t=81 to t=90
                    """,
            testAnimation.toString());
  }

  // _____________________________________ Test Everything ________________________________________.
  @Test
  public void testEverything() {
    testAnimation.addShape(R, 1, 100);
    testAnimation.addShape(C, 6, 100);

    move1 = new MoveShape(R, 200.0, 200.0, 300.0, 300.0);
    move2 = new MoveShape(C, 500.0, 100.0, 500.0, 400.0);
    move3 = new MoveShape(R, 300.0, 300.0, 200.0, 200.0);

    size1 = new ScaleShape(R, 50.0, 100.0, 25.0, 100.0);

    colorChange1 = new ChangeColor(C, 0, 0, 1, 0, 1, 0);

    testAnimation.addEvent(R, move1, 10, 50);
    testAnimation.addEvent(C, move2, 20, 70);
    testAnimation.addEvent(C, colorChange1, 50, 80);
    testAnimation.addEvent(R, size1, 51, 70);
    testAnimation.addEvent(R, move3, 70, 100);

    assertEquals("""
                    Shapes:
                    Name: R
                    Type: rectangle
                    Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)
                    Appears at t=1
                    Disappears at t=100

                    Name: C
                    Type: oval
                    Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)
                    Appears at t=6
                    Disappears at t=100

                    Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50
                    Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70
                    Shape C changes color from (0,0,1) to (0,1,0) from t=50 to t=80
                    Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70
                    Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100
                    """
            , testAnimation.toString());
  }
}
