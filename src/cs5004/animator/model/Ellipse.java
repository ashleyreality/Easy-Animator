package cs5004.animator.model;

/**
 * _____________________________________ CLASS: Ellipse {} ________________________________________.
 * The Ellipse class extends AbstractShape and implements the method declarations that are unique to
 * the Ellipse class. It creates and stores information about an Ellipse shape.
 */
public class Ellipse extends AbstractShape {
  // radiusX, from the center point to the outer edge of the circle along the X-axis/width
  private double radiusX = 0;
  // radiusY, from the center point to the outer edge of the circle along the Y-axis/height
  private double radiusY = 0;

  /**
   * _____________________________ FIRST CONSTRUCTOR: Ellipse() ___________________________________.
   * Creates an ellipse with the given width, height, color parameters, and location coordinates.
   *
   * @param name   name of the ellipse
   * @param width  width of the ellipse (x)
   * @param height height of the ellipse (y)
   * @param red    red value of ellipse
   * @param green  green value of ellipse
   * @param blue   blue value of ellipse
   * @param x      x coordinate of ellipse
   * @param y      y coordinate of ellipse
   */
  public Ellipse(String name, double width, double height,
                 int red, int green, int blue, double x, double y) {
    super(name, width, height, red, green, blue, x, y);

    this.radiusX = width / 2;
    this.width = this.radiusX * 2;

    this.radiusY = height / 2;
    this.height = this.radiusY * 2;
  }

  /**
   * _____________________________ SECOND CONSTRUCTOR: Ellipse() __________________________________.
   * Creates a shape with the given name. Further attributes are instantiated with the first event
   * called on the shape.
   *
   * @param name name of the shape
   * @param type the type of shape
   * @throws IllegalArgumentException if name is null
   */
  public Ellipse(String name, String type) {
    super(name, type);
  }

  /**
   * ___________________________________ METHOD: toString() _______________________________________.
   * Returns a String with the name, location, horizontal radius, vertical radius, color, and appear
   * and disappear times of the ellipse.
   *
   * @return a String displaying the ellipse's attributes
   */
  @Override
  public String toString() {
    return "Name: " + this.name
            + "\nType: ellipse"
            + "\nCenter: " + this.location.toString()
            + ", X radius: " + this.radiusX + ", Y radius: "
            + this.radiusY + ", Color: " + this.color.toString()
            + "\nAppears at t=" + this.appear
            + "\nDisappears at t=" + this.disappear + "\n";
  }

  /**
   * _________________________________ METHOD: createString() _____________________________________.
   * Returns a String with the name of the color of the ellipse, the name of the ellipse, the center
   * location of the ellipse on the canvas, the horizontal radius and the vertical radius.
   * @return the string for the text output, a String
   */
  @Override
  public String createString() {
    return "Create " + this.color.toString() + " ellipse " + this.name
            + " with center at " + this.location.toString() + ", radius " + this.radiusX
            + " and " + this.radiusY;
  }

  /**
   * ___________________________________ METHOD: setWidth() _______________________________________.
   * This is a setter override from the AbstractShape that sets the width of the Ellipse.
   */
  @Override
  public void setWidth(double width) {
    if (width <= 0) {
      throw new IllegalArgumentException("Width must be positive.");
    }
    this.radiusX = width / 2;
  }

  /**
   * ___________________________________ METHOD: setHeight() ______________________________________.
   * This is a setter override from the AbstractShape that sets the height of the Ellipse.
   */
  @Override
  public void setHeight(double height) {
    if (height <= 0) {
      throw new IllegalArgumentException("Height must be positive.");
    }
    this.radiusY = height / 2;
  }

}
