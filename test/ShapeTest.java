import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.Color;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * ___________________________________ CLASS: ShapeTest() ________________________________________.
 * This class contains unit tests for Shapes.
 */
public class ShapeTest {
  private IShape rect;
  private IShape ellipse;

  @Before
  public void setUp() {
    //________________________________________ Ellipse Objects _______________________________________.

    this.ellipse = new Ellipse("ellipse1",
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

    //________________________________________ Ellipse Objects _______________________________________.

    this.ellipse = new Ellipse("ellipse1",
            120, 60,
            0, 0, 1,
            500.0, 100.0);

    assertEquals("Name: ellipse1\n"
                    + "Type: ellipse\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
            // -1 shows that the appear and disappear times have not been updated for the builder
                    + "Appears at t=-1\n"
            + "Disappears at t=-1\n",
             ellipse.toString());

    // huge numbers
    IShape ellipse2 = new Ellipse("ellipse2",
            12000.001, 602828.6,
            240, 120, 221,
            54637.559, 928736.021);

    assertEquals("Name: ellipse2\n"
            + "Type: ellipse\n"
            + "Center: (54637.6,928736.0), X radius: 6000.0005, Y radius: 301414.3, Color:"
            + " (240,120,221)\n"
            + "Appears at t=-1\n"
            + "Disappears at t=-1\n", ellipse2.toString());

    // zeroes

    IShape ellipse3 = new Ellipse("ellipse3",
            1, 1,
            0, 0, 0,
            0.0, 0.0);

    assertEquals("Name: ellipse3\n"
                    + "Type: ellipse\n"
                    + "Center: (0.0,0.0), X radius: 0.5, Y radius: 0.5, Color: (0,0,0)\n"
                    + "Appears at t=-1\n"
            + "Disappears at t=-1\n", ellipse3.toString());

    //_____________________________________ Rectangle Objects _____________________________________.

    this.rect = new Rectangle("rect1",
            50, 100,
            1, 0, 0,
            200.0, 200.0);

    assertEquals("Name: rect1\n"
            + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=-1\n"
            + "Disappears at t=-1\n", rect.toString());

    // huge numbers
    IShape rect2 = new Rectangle("rect2",
            12000.001, 602828.6,
            240, 120, 221,
            54637.559, 928736.021);

    assertEquals("Name: rect2\n"
                    + "Type: rectangle\n"
                    + "Min corner: (54637.6,928736.0), Width: 12000.001, Height: 602828.6, Color: "
            + "(240,120,221)\nAppears at t=-1\n"
            + "Disappears at t=-1\n", rect2.toString());

    // zeroes

    IShape rect3 = new Rectangle("rect3",
            1, 1,
            0, 0, 0,
            0.0, 0.0);

    assertEquals("Name: rect3\n"
            + "Type: rectangle\n"
                    + "Min corner: (0.0,0.0), Width: 1.0, Height: 1.0, Color: (0,0,0)\n"
                    + "Appears at t=-1\n"
            + "Disappears at t=-1\n", rect3.toString());

  }

  // _________________________________ EXCEPTIONS: Ellipse ________________________________________.

  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidthEllipse() {
    IShape ellipse3 = new Ellipse("ellipse3",
            0, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeightEllipse() {
    IShape ellipse3 = new Ellipse("ellipse3",
            1, 0,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthEllipse() {
    IShape ellipse3 = new Ellipse("ellipse3",
            -1, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightEllipse() {
    IShape ellipse3 = new Ellipse("ellipse3",
            1, -1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColorEllipse() {
    IShape ellipse3 = new Ellipse("ellipse3",
            1, 1,
            -10, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArgEllipse() {
    IShape ellipse3 = new Ellipse(null,
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
    assertEquals("ellipse1", ellipse.getName());

  }

  @Test
  public void setName() {
    ellipse.setName("Gudetama");
    rect.setName("Ikura");
    assertEquals("Ikura", rect.getName());
    assertEquals("Gudetama", ellipse.getName());
  }

  @Test
  public void getColor() {
    Color color1 = new Color(0,0,1);
    Color color2 = new Color(1,0,0);
    assertEquals(color1, ellipse.getColor());
    assertEquals(color2, rect.getColor());
  }

  @Test
  public void setColor() {
    Color color1 = new Color(2,2,2);
    Color color2 = new Color(100,0,100);
    rect.setColor(2, 2,2);
    ellipse.setColor(100,0,100);
    assertEquals(color1, rect.getColor());
    assertEquals(color2, ellipse.getColor());
  }

  @Test
  public void getLocation() {
    Point2D loc1 = new Point2D(200.0,200.0);
    Point2D loc2 = new Point2D(500.0,100.0);
    assertEquals(loc1, rect.getLocation());
    assertEquals(loc2, ellipse.getLocation());
  }

  @Test
  public void setLocation() {
    Point2D loc1 = new Point2D(0,0);
    Point2D loc2 = new Point2D(0,0);
    rect.setLocation(0,0);
    ellipse.setLocation(0,0);
    assertEquals(loc1, rect.getLocation());
    assertEquals(loc2, ellipse.getLocation());
  }

  @Test
  public void getAppear() {
    assertEquals(-1, rect.getAppear());
    assertEquals(-1, ellipse.getAppear());
  }

  @Test
  public void setAppear() {
    rect.setAppear(100);
    ellipse.setAppear(10000);
    assertEquals(100, rect.getAppear());
    assertEquals(10000, ellipse.getAppear());
  }

  @Test
  public void getDisappear() {
    assertEquals(-1, rect.getDisappear());
    assertEquals(-1, ellipse.getDisappear());
  }

  @Test
  public void setDisappear() {
    rect.setDisappear(100);
    ellipse.setDisappear(10000);
    assertEquals(100, rect.getDisappear());
    assertEquals(10000, ellipse.getDisappear());
  }

  @Test
  public void getWidth() {
    assertEquals(50.0, rect.getWidth(), 0.1);
    assertEquals(120.0, ellipse.getWidth(), 0.1);

  }

  @Test(expected = IllegalArgumentException.class)
  public void setNegativeWidth() {
    ellipse.setWidth(-100);
  }

  @Test
  public void setWidth() {
    rect.setWidth(100);
    ellipse.setWidth(7362);
    assertEquals(100, rect.getWidth(), 0.01);
    assertEquals(7362, ellipse.getWidth(),0.01);
  }

  @Test
  public void getHeight() {
    assertEquals(100.0, rect.getHeight(), 0.01);
    assertEquals(60.0, ellipse.getHeight(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setNegativeHeight() {
    ellipse.setHeight(-100);
  }

  @Test
  public void setHeight() {
    rect.setHeight(500);
    ellipse.setHeight(7654.22);
    assertEquals(500, rect.getHeight(), 0.01);
    assertEquals(7654.22, ellipse.getHeight(), 0.01);
  }

  // this test is useless now
  @Test
  public void testCompareTo() {
    assertEquals(13, rect.compareTo(ellipse));
    assertEquals(-13, ellipse.compareTo(rect));
  }

  @Test
  public void testToString() {
    assertEquals("Name: rect1\n"
            + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)\n"
                    + "Appears at t=-1\n"
            + "Disappears at t=-1\n", rect.toString());
    assertEquals("Name: ellipse1\n"
            + "Type: ellipse\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)\n"
            + "Appears at t=-1\n"
            + "Disappears at t=-1\n", ellipse.toString());
  }
}
