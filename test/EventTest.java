import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class contains the unit tests for the Events.
 */
public class EventTest {
  private IShape oval;
  private IShape rect;
  private IEvent colorEvent;
  private IEvent scaleEvent;
  private IEvent moveEvent;

  /*
  things to test:
  constructor
  getters and setters for event begin
  and event end
  and toStrings
   */
  @Before
  public void setUp() {
    // make some shapes
    this.oval = new Oval("oval1", 120, 60, 0,
            0, 1, 500.0, 100.0);
    this.rect = new Rectangle("rect1", 50, 100, 1,
            0, 0, 200.0, 200.0);
    // make some events to test their getters and setters
    this.colorEvent = new ChangeColor(oval, 0, 1, 0);
    this.scaleEvent = new ScaleShape(oval, 60.0, 30.0);
    this.moveEvent = new MoveShape(rect, 50.0, 100.0);
  }

  @Test
  public void testValidConstructor() {
    // test normal examples
    //this.colorEvent = new ChangeColor(oval, 100, 25, 3);
    //assertEquals("Shape oval1 changes color from (0,0,1) to (100,25,3) from t=0 to t=0",
      //      colorEvent.toString());
    this.scaleEvent = new ScaleShape(oval, 10.09, 30.90123);
    assertEquals("Shape oval1 scales from Width: 120.0, Height: 60.0 to Width: 10.1, "
            + "Height: 30.9 from t=0 to t=0", scaleEvent.toString());
    this.moveEvent = new MoveShape(oval, 14.5, 78.432);
    assertEquals("Shape oval1 moves from (500.0,100.0) to (14.5,78.4) from t=0 to t=0",
            moveEvent.toString());
    // test larger numbers and with rectangle
    IEvent colorCheck = new ChangeColor(rect, 255, 255, 255);
    assertEquals("Shape rect1 changes color from (1,0,0) to (255,255,255) from t=0 to t=0",
            colorCheck.toString());
    IEvent scaleCheck = new ScaleShape(rect, 1784357921834.28349, 9872345.8300);
    assertEquals("Shape rect1 scales from Width: 50.0, Height: 100.0 to "
            + "Width: 1784357921834.3, Height: 9872345.8 from t=0 to t=0", scaleCheck.toString());
    IEvent moveCheck = new MoveShape(rect, 754732847528.00042, 8247582);
    assertEquals("Shape rect1 moves from (200.0,200.0) to "
            + "(754732847528.0,8247582.0) from t=0 to t=0", moveCheck.toString());
    // negative move
    IEvent negMoveCheck = new MoveShape(rect, -234753489.346, -2);
    assertEquals("Shape rect1 moves from (200.0,200.0) to "
            + "(-234753489.3,-2.0) from t=0 to t=0", negMoveCheck.toString());
    // test with zeros
    IEvent zeroColorCheck = new ChangeColor(rect, 0, 0, 0);
    assertEquals("Shape rect1 changes color from (1,0,0) to (0,0,0) from t=0 to t=0",
            zeroColorCheck.toString());
    IEvent zeroMoveCheck = new MoveShape(rect, 0, 0);
    assertEquals("Shape rect1 moves from (200.0,200.0) to "
            + "(0.0,0.0) from t=0 to t=0", zeroMoveCheck.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidth() {
    new ScaleShape(rect, 0, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeight() {
    new ScaleShape(rect, 10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    new ScaleShape(rect, -192, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {
    new ScaleShape(rect, 10, -1.3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRed() {
    new ChangeColor(oval, -5, 200, 200);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeGreen() {
    new ChangeColor(oval, 200, -200, 200);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeBlue() {
    new ChangeColor(oval, 200, 200, 200);
  }

}

