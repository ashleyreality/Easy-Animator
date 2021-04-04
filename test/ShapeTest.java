import org.junit.Before;
import org.junit.Test;

import cs5004.animationmodel.Color;
import cs5004.animationmodel.IShape;
import cs5004.animationmodel.Oval;
import cs5004.animationmodel.Point2D;
import cs5004.animationmodel.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * ___________________________________ CLASS: ShapeTest() ________________________________________.
 * This class contains unit tests for Shapes.
 */
public class ShapeTest {
  private IShape rect;
  private IShape oval;

  @Before
  public void setUp() {
    //________________________________________ Oval Objects _______________________________________.

    this.oval = new Oval("oval1",
            120, 60,
            0, 0, 1,
            500.0, 100.0);

    //_____________________________________ Rectangle Objects _____________________________________.

    this.rect = new Rectangle("rect1",
            50, 100,
            1, 0, 0,
            200.0, 200.0);
  }

  @Test
  public void testValidConstructor() {

    //________________________________________ Oval Objects _______________________________________.

    this.oval = new Oval("oval1",
            120, 60,
            0, 0, 1,
            500.0, 100.0);

    assertEquals("Name: oval1\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
                    + "Appears at t=0\n"
            + "Disappears at t=0\n",
             oval.toString());

    // huge numbers
    IShape oval2 = new Oval("oval2",
            12000.001, 602828.6,
            240, 120, 221,
            54637.559, 928736.021);

    assertEquals("Name: oval2\n"
            + "Type: oval\n"
            + "Center: (54637.6,928736.0), X radius: 6000.0005, Y radius: 301414.3, Color:"
            + " (240,120,221)\n"
            + "Appears at t=0\n"
            + "Disappears at t=0\n", oval2.toString());

    // zeroes

    IShape oval3 = new Oval("oval3",
            1, 1,
            0, 0, 0,
            0.0, 0.0);

    assertEquals("Name: oval3\n"
                    + "Type: oval\n"
                    + "Center: (0.0,0.0), X radius: 0.5, Y radius: 0.5, Color: (0,0,0)\n"
                    + "Appears at t=0\n"
            + "Disappears at t=0\n", oval3.toString());

    //_____________________________________ Rectangle Objects _____________________________________.

    this.rect = new Rectangle("rect1",
            50, 100,
            1, 0, 0,
            200.0, 200.0);

    assertEquals("Name: rect1\n"
            + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=0\n"
            + "Disappears at t=0\n", rect.toString());

    // huge numbers
    IShape rect2 = new Rectangle("rect2",
            12000.001, 602828.6,
            240, 120, 221,
            54637.559, 928736.021);

    assertEquals("Name: rect2\n"
                    + "Type: rectangle\n"
                    + "Min corner: (54637.6,928736.0), Width: 12000.001, Height: 602828.6, Color: "
            + "(240,120,221)\nAppears at t=0\n"
            + "Disappears at t=0\n", rect2.toString());

    // zeroes

    IShape rect3 = new Rectangle("rect3",
            1, 1,
            0, 0, 0,
            0.0, 0.0);

    assertEquals("Name: rect3\n"
            + "Type: rectangle\n"
                    + "Min corner: (0.0,0.0), Width: 1.0, Height: 1.0, Color: (0,0,0)\n"
                    + "Appears at t=0\n"
            + "Disappears at t=0\n", rect3.toString());

  }

  // ____________________________________ EXCEPTIONS: Oval ________________________________________.

  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidthOval() {
    IShape oval3 = new Oval("oval3",
            0, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeightOval() {
    IShape oval3 = new Oval("oval3",
            1, 0,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthOval() {
    IShape oval3 = new Oval("oval3",
            -1, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightOval() {
    IShape oval3 = new Oval("oval3",
            1, -1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColorOval() {
    IShape oval3 = new Oval("oval3",
            1, 1,
            -10, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArgOval() {
    IShape oval3 = new Oval(null,
            1, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  // _________________________________ EXCEPTIONS: Rectangle ______________________________________.

  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidthRect() {
    IShape rect3 = new Rectangle("rect3",
            0, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeightRect() {
    IShape rect3 = new Rectangle("rect3",
            1, 0,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthRect() {
    IShape rect3 = new Rectangle("rect3",
            -1, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightRect() {
    IShape rect3 = new Rectangle("rect3",
            1, -1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColorRect() {
    IShape rect3 = new Rectangle("rect3",
            1, 1,
            -90, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArgRect() {
    IShape rect3 = new Rectangle(null,
            1, 1,
            0, 0, 0,
            0.0, 0.0);
  }


  @Test
  public void getName() {
    assertEquals("rect1", rect.getName());
    assertEquals("oval1", oval.getName());

  }

  @Test
  public void setName() {
    oval.setName("Gudetama");
    rect.setName("Ikura");
    assertEquals("Ikura", rect.getName());
    assertEquals("Gudetama", oval.getName());
  }

  @Test
  public void getColor() {
    Color color1 = new Color(0,0,1);
    Color color2 = new Color(1,0,0);
    assertEquals(color1, oval.getColor());
    assertEquals(color2, rect.getColor());
  }

  @Test
  public void setColor() {
    Color color1 = new Color(2,2,2);
    Color color2 = new Color(100,0,100);
    rect.setColor(2, 2,2);
    oval.setColor(100,0,100);
    assertEquals(color1, rect.getColor());
    assertEquals(color2, oval.getColor());
  }

  @Test
  public void getLocation() {
    Point2D loc1 = new Point2D(200.0,200.0);
    Point2D loc2 = new Point2D(500.0,100.0);
    assertEquals(loc1, rect.getLocation());
    assertEquals(loc2, oval.getLocation());
  }

  @Test
  public void setLocation() {
    Point2D loc1 = new Point2D(0,0);
    Point2D loc2 = new Point2D(0,0);
    rect.setLocation(0,0);
    oval.setLocation(0,0);
    assertEquals(loc1, rect.getLocation());
    assertEquals(loc2, oval.getLocation());
  }

  @Test
  public void getAppear() {
    assertEquals(0, rect.getAppear());
    assertEquals(0, oval.getAppear());
  }

  @Test
  public void setAppear() {
    rect.setAppear(100);
    oval.setAppear(10000);
    assertEquals(100, rect.getAppear());
    assertEquals(10000, oval.getAppear());
  }

  @Test
  public void getDisappear() {
    assertEquals(0, rect.getDisappear());
    assertEquals(0, oval.getDisappear());
  }

  @Test
  public void setDisappear() {
    rect.setDisappear(100);
    oval.setDisappear(10000);
    assertEquals(100, rect.getDisappear());
    assertEquals(10000, oval.getDisappear());
  }

  @Test
  public void getWidth() {
    assertEquals(50.0, rect.getWidth(), 0.1);
    assertEquals(120.0, oval.getWidth(), 0.1);

  }

  @Test(expected = IllegalArgumentException.class)
  public void setNegativeWidth() {
    oval.setWidth(-100);
  }

  @Test
  public void setWidth() {
    rect.setWidth(100);
    oval.setWidth(7362);
    assertEquals(100, rect.getWidth(), 0.01);
    assertEquals(7362, oval.getWidth(),0.01);
  }

  @Test
  public void getHeight() {
    assertEquals(100.0, rect.getHeight(), 0.01);
    assertEquals(60.0, oval.getHeight(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setNegativeHeight() {
    oval.setHeight(-100);
  }

  @Test
  public void setHeight() {
    rect.setHeight(500);
    oval.setHeight(7654.22);
    assertEquals(500, rect.getHeight(), 0.01);
    assertEquals(7654.22, oval.getHeight(), 0.01);
  }

  @Test
  public void testCompareTo() {
    assertEquals(-1, rect.compareTo(oval));
    assertEquals(1, oval.compareTo(rect));
  }

  @Test
  public void testToString() {
    assertEquals("Name: rect1\n"
            + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=0\n"
            + "Disappears at t=0\n", rect.toString());
    assertEquals("Name: oval1\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
            + "Appears at t=0\n"
            + "Disappears at t=0\n", oval.toString());
  }
}
