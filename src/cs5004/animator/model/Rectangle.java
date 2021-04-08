package cs5004.animator.model;

/**
 * __________________________________ CLASS: Rectangle {} _________________________________________.
 * The Rectangle class extends AbstractShape and implements method declarations that are unique
 * to the Rectangle class. It creates and stores information about a Rectangle shape.
 */
public class Rectangle extends AbstractShape {

  /**
   * _____________________________ CONSTRUCTOR: Rectangle() _______________________________________.
   * Constructs a rectangle with the given width and height, color parameters, and location
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

  /**
   * ___________________________________ METHOD: toString() _______________________________________.
   * Returns a String with the name, location, width, height, color, and appear and disappear times
   * of the rectangle.
   *
   * @return a String displaying the rectangle's attributes
   */
  @Override
  public String toString() {
    return "Name: " + this.name
            + "\nType: rectangle"
            + "\nMin corner: " + this.location.toString() + ", Width: " + this.width + ", Height: "
            + this.height + ", Color: " + this.color.toString()
            + "\nAppears at t=" + this.appear
            + "\nDisappears at t=" + this.disappear + "\n";
  }
}
