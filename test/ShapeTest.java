import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * ___________________________________ METHOD: ShapeTest() ________________________________________.
 */
public class ShapeTest {
  private IShape rectangle1;

  @Before
  public void setUp() {
    IShape rectangle1 = new Rectangle("rectangle1", 1, 1, 0, 0, 0, 30.0, 75.0);

  }
}
