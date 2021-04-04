import org.junit.Before;
import org.junit.Test;

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

    assertEquals("""
            Name: oval1
            Type: oval
            Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0,0,1)
            Appears at t=0
            Disappears at t=0
            """, oval.toString());

    // huge numbers
    IShape oval2 = new Oval("oval2",
            12000.001, 602828.6,
            240, 120, 221,
            54637.559, 928736.021);

    assertEquals("""
            Name: oval2
            Type: oval
            Center: (54637.6,928736.0), X radius: 6000.0005, Y radius: 301414.3, Color: (240,120,221)
            Appears at t=0
            Disappears at t=0
            """, oval2.toString());

    // zeroes

    IShape oval3 = new Oval("oval3",
            1, 1,
            0, 0, 0,
            0.0, 0.0);

    assertEquals("""
            Name: oval3
            Type: oval
            Center: (0.0,0.0), X radius: 0.5, Y radius: 0.5, Color: (0,0,0)
            Appears at t=0
            Disappears at t=0
            """, oval3.toString());

    //_____________________________________ Rectangle Objects _____________________________________.

    this.rect = new Rectangle("rect1",
            50, 100,
            1, 0, 0,
            200.0, 200.0);

    assertEquals("""
            Name: rect1
            Type: rectangle
            Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1,0,0)
            Appears at t=0
            Disappears at t=0
            """, rect.toString());

    // huge numbers
    IShape rect2 = new Rectangle("rect2",
            12000.001, 602828.6,
            240, 120, 221,
            54637.559, 928736.021);

    assertEquals("""
            Name: rect2
            Type: rectangle
            Min corner: (54637.6,928736.0), Width: 12000.001, Height: 602828.6, Color: (240,120,221)
            Appears at t=0
            Disappears at t=0
            """, rect2.toString());

    // zeroes

    IShape rect3 = new Rectangle("rect3",
            1, 1,
            0, 0, 0,
            0.0, 0.0);

    assertEquals("""
            Name: rect3
            Type: rectangle
            Min corner: (0.0,0.0), Width: 1.0, Height: 1.0, Color: (0,0,0)
            Appears at t=0
            Disappears at t=0
            """, rect3.toString());

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
            1, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeightRect() {
    IShape rect3 = new Rectangle("rect3",
            1, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthRect() {
    IShape rect3 = new Rectangle("rect3",
            1, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeightRect() {
    IShape rect3 = new Rectangle("rect3",
            1, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColorRect() {
    IShape rect3 = new Rectangle("rect3",
            1, 1,
            0, 0, 0,
            0.0, 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArgRect() {
    IShape rect3 = new Rectangle("rect3",
            1, 1,
            0, 0, 0,
            0.0, 0.0);
  }


  @Test
  public void getName() {

  }

  @Test
  public void setName() {
  }

  @Test
  public void getColor() {
  }

  @Test
  public void setColor() {
  }

  @Test
  public void getLocation() {
  }

  @Test
  public void setLocation() {
  }

  @Test
  public void getAppear() {
  }

  @Test
  public void setAppear() {
  }

  @Test
  public void getDisappear() {
  }

  @Test
  public void setDisappear() {
  }

  @Test
  public void getWidth() {
  }

  @Test
  public void setWidth() {
    // test for negative
  }

  @Test
  public void getHeight() {
  }

  @Test
  public void setHeight() {
    // test for negative
  }

  @Test
  public void testToString() {
  }
}
