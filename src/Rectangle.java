
import java.awt.geom.Point2D;

/**
 * This class creates and stores information about a Rectangle shape.
 */
public class Rectangle extends AbstractShape {
  /**
   * Creates a rectangle with the given width and height, color parameters, and location
   * coordinates.
   *
   * @param name   name of the rectangle
   * @param width  width of the rectangle (x)
   * @param height height of the rectangle (y)
   * @param red    red value of rectangle
   * @param green  green value of rectangle
   * @param blue   blue value of rectangle
   * @param x      x coordinate of rectangle
   * @param y      y coordinate of rectangle
   */
  public Rectangle(String name, double width, double height,
                   int red, int green, int blue, double x, double y) {
    super(name, width, height, red, green, blue, x, y);
  }

  @Override
  public String toString() {
    return "Name: " + this.name
            + "\nType: Rectangle"
            + "\nMin corner: " + this.location.toString() + ", Width: " + this.width + ", Height: "
            + this.height + ", Color: " + this.color.toString()
            + "\nAppears at t=" + this.appear
            + "\nDisappears at t=" + this.disappear + "\n";
  }
}
