import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;
import cs5004.animator.model.MoveShape;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ScaleShape;

import static org.junit.Assert.assertEquals;

/**
 * ___________________________________ CLASS: EventTest() ________________________________________.
 * This class contains the unit tests for the Events.
 */
public class EventTest {
  private IShape ellipse;
  private IShape rect;
  private IEvent colorEvent;
  private IEvent scaleEvent;
  private IEvent moveEvent;

  @Before
  public void setUp() {
    // make some shapes
    // make some events to test their getters and setters

    //________________________________________ Oval Objects _______________________________________.

    this.ellipse = new Ellipse("oval1",
            120, 60,
            0, 0, 1,
            500.0, 100.0);

    //_____________________________________ Rectangle Objects _____________________________________.

    this.rect = new Rectangle("rect1",
            50, 100,
            1, 0, 0,
            200.0, 200.0);

    //________________________________________ Oval Events ________________________________________.

    this.colorEvent = new ChangeColor(ellipse,
            0, 0, 1,
            100, 25, 3);

    this.scaleEvent = new ScaleShape(ellipse,
            120.0, 60.0,
            10.1, 30.9);

    //_____________________________________ Rectangle Events ______________________________________.

    this.moveEvent = new MoveShape(rect,
            200.0, 200.0,
            50.0, 100.0);
  }

  @Test
  public void testValidConstructor() {
    // test normal examples
    // test larger numbers and with rectangle

    //________________________________________ Oval Events ________________________________________.
    this.colorEvent = new ChangeColor(ellipse,
            0, 0, 1,
            100, 25, 3);

    assertEquals("oval1 changes color from (0,0,1) to (100,25,3) from time t=0 to t=1",
            colorEvent.toString());

    this.scaleEvent = new ScaleShape(ellipse,
            120.0, 60.0,
            10.1, 30.90123);

    assertEquals("oval1 changes height from 60.0 to 30.90123 and "
                    + "changes width from 120.0 to 10.1 from time t=0 to t=1",
            scaleEvent.toString());

    this.moveEvent = new MoveShape(ellipse,
            500.0, 100.0,
            14.5, 78.432);

    assertEquals("oval1 moves from (500.0,100.0) to (14.5,78.4) from time t=0 to t=1",
            moveEvent.toString());

    //_____________________________________ Rectangle Events ______________________________________.
    IEvent colorCheck = new ChangeColor(rect,
            1, 0, 0,
            255, 255, 255);

    assertEquals("rect1 changes color from (1,0,0) to (255,255,255) from time t=0 to t=1",
            colorCheck.toString());

    IEvent scaleCheck = new ScaleShape(rect,
            50.0, 100.0,
            1784357921834.28349, 9872345.8300);

    assertEquals("rect1 changes height from 100.0 to 9872345.83 and changes "
                    + "width from 50.0 to 1.7843579218342834E12 from time t=0 to t=1",
            scaleCheck.toString());

    IEvent moveCheck = new MoveShape(rect,
            200.0, 200.0,
            754732847528.00042, 8247582);

    assertEquals("rect1 moves from (200.0,200.0) to "
                    + "(754732847528.0,8247582.0) from time t=0 to t=1",
            moveCheck.toString());

    // ____________________ Negative Move ____________________
    IEvent negMoveCheck = new MoveShape(rect,
            200.0, 200.0,
            -234753489.346, -2);

    assertEquals("rect1 moves from (200.0,200.0) to "
                    + "(-234753489.3,-2.0) from time t=0 to t=1",
            negMoveCheck.toString());

    // ____________________ Test With Zeros ____________________
    IEvent zeroColorCheck = new ChangeColor(rect,
            1, 0, 0,
            0, 0, 0);

    assertEquals("rect1 changes color from (1,0,0) to (0,0,0) from time t=0 to t=1",
            zeroColorCheck.toString());

    IEvent zeroMoveCheck = new MoveShape(rect,
            200.0, 200.0,
            0, 0);
    assertEquals("rect1 moves from (200.0,200.0) to "
            + "(0.0,0.0) from time t=0 to t=1", zeroMoveCheck.toString());
  }


  // ____________________________________ EXCEPTIONS: Oval ________________________________________.

  // Original Oval RGB: 0, 0, 1
  // New Oval RGB: -5, 200, 200
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRed() {
    new ChangeColor(ellipse,
            0, 0, 1,
            -5, 200, 200);
  }

  // Original Oval RGB: 0, 0, 1
  // New Oval RGB: 200, -200, 200
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeGreen() {
    new ChangeColor(ellipse,
            0, 0, 1,
            200, -200, 200);
  }

  // Original Oval RGB: 0, 0, 1
  // New Oval RGB: 200, 200, 200
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeBlue() {
    new ChangeColor(ellipse,
            0, 0, 1,
            200, 200, -200);
  }

  // _________________________________ EXCEPTIONS: Rectangle ______________________________________.

  // Original Rectangle Width, Height: 50.0, 100.0
  // New Rectangle Width, Height: 0, 5
  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidth() {
    new ScaleShape(rect,
            50.0, 100.0,
            0, 5);
  }

  // Original Rectangle Width, Height: 50.0, 100.0
  // New Rectangle Width, Height: 10, 0
  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeight() {
    new ScaleShape(rect,
            50.0, 100.0,
            10, 0);
  }

  // Original Rectangle Width, Height: 50.0, 100.0
  // New Rectangle Width, Height: -192, 5
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    new ScaleShape(rect,
            50.0, 100.0,
            -192, 5);
  }

  // Original Rectangle Width, Height: 50.0, 100.0
  // New Rectangle Width, Height: 10, -1.3
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {
    new ScaleShape(rect,
            50.0, 100.0,
            10, -1.3);
  }

  // null tests
  @Test(expected = IllegalArgumentException.class)
  public void nullScaleShape() {
    new ScaleShape(null,
            50.0, 100.0,
            10, 13);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullColorShape() {
    new ChangeColor(null,
            0, 0, 1,
            200, 200, 200);
  }


  @Test(expected = IllegalArgumentException.class)
  public void nullMoveShape() {
    new MoveShape(null,
            64880.0, 130.0,
            10, -1.3);
  }

  // change to the same
  @Test(expected = IllegalArgumentException.class)
  public void noColorChange() {
    new ChangeColor(ellipse,
            0, 0, 1,
            0, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void noSizeChange() {
    new ScaleShape(rect,
            50.0, 100.0,
            50.0, 100.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void noMoveChange() {
    new MoveShape(rect,
            200.0, 200.0,
            200.0, 200.0);
  }

  // set event begin
  // valid positive begin int
  @Test
  public void testEventBeginSetter() {
    assertEquals(0, this.colorEvent.getEventBegin());
    this.colorEvent.setEventBegin(0);
    assertEquals(0, this.colorEvent.getEventBegin());
    this.moveEvent.setEventBegin(50);
    assertEquals(50, this.moveEvent.getEventBegin());
    this.scaleEvent.setEventBegin(700);
    assertEquals(700, this.scaleEvent.getEventBegin());
  }

  // negative begin int
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeEventBeginSetter() {
    this.colorEvent.setEventBegin(-10);
  }

  // set event end
  // valid end, set begin first
  @Test
  public void testEventEndSetter() {
    assertEquals(1, this.colorEvent.getEventEnd());
    this.colorEvent.setEventBegin(0);
    this.colorEvent.setEventEnd(2);
    assertEquals(2, this.colorEvent.getEventEnd());
    this.moveEvent.setEventBegin(50);
    this.moveEvent.setEventEnd(200);
    assertEquals(200, this.moveEvent.getEventEnd());
    this.scaleEvent.setEventBegin(700);
    this.scaleEvent.setEventEnd(87435);
    assertEquals(87435, this.scaleEvent.getEventEnd());
  }

  // negative end
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeEventEndSetter() {
    this.colorEvent.setEventEnd(-100);
  }

  // 0 end
  @Test(expected = IllegalArgumentException.class)
  public void testZeroEventEndSetter() {
    this.moveEvent.setEventEnd(0);
  }

  // set end before (lower) than begin
  @Test(expected = IllegalArgumentException.class)
  public void testEarlyEventEndSetter() {
    this.moveEvent.setEventBegin(80);
    this.moveEvent.setEventEnd(79);
  }

  @Test
  public void testToString() {
    assertEquals("oval1 changes color from (0,0,1) to (100,25,3) from time t=0 to t=1",
            colorEvent.toString());
    assertEquals("rect1 moves from (200.0,200.0) to (50.0,100.0) from time t=0 to t=1",
            moveEvent.toString());
    assertEquals("oval1 changes height from 60.0 to 30.9 and changes "
            + "width from 120.0 to 10.1 from time t=0 to t=1", scaleEvent.toString());
  }

}

