
import java.awt.geom.Point2D;

/**
 * This class creates and stores information about a Rectangle shape.
 */
public class Rectangle extends AbstractCreateShape {
  private double width;
  private double height;

  /**
   * Generates a Rectangle shape.
   *  @param color    - the color of the Rectangle
   * @param location - the rectangle's location
   * @param width    - int, a width
   * @param height   - int, a height
   */
  public Rectangle(Color color, Point2D location, int appear, int disappear, double width,
                   double height) {

    super(color, location, appear, disappear);
    width = this.width;
    height = this.height;
  }
}
