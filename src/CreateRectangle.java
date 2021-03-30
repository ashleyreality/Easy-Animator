import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * This class creates and stores information about a Rectangle shape.
 */
public class CreateRectangle extends AbstractCreateShape {
  private int width;
  private int height;

  /**
   * Generates a Rectangle shape.
   *
   * @param color - the color of the Rectangle
   * @param location - the rectangle's location
   * @param width - int, a width
   * @param height - int, a height
   */
  public CreateRectangle(Color color, Point2D location, int width, int height) {

    super(color, location);
    width = this.width;
    height = this.height;
  }
}
