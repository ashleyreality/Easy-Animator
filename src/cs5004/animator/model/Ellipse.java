package cs5004.animator.model;

/**
 * _____________________________________ CLASS: Ellipse {} ___________________________________________.
 * The Ellipse class extends AbstractShape and implements the method declarations that are unique to
 * the Ellipse class. It creates and stores information about an Ellipse shape.
 */
public class Ellipse extends AbstractShape {
  // radiusX, from the center point to the outer edge of the circle along the X-axis/width
  private double radiusX = 0;
  // radiusY, from the center point to the outer edge of the circle along the Y-axis/height
  private double radiusY = 0;

  /**
   * __________________________________ CONSTRUCTOR: Ellipse() _______________________________________.
   * The Ellipse() constructor creates an ellipse with the given width, height, color parameters, and
   * location coordinates.
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
   * _________________________SECOND CONSTRUCTOR: Ellipse() _________________________________.
   * Creates a shape with the given name. Further attributes are instantiated with the first event
   * called on the shape.
   *
   * @param name   name of the shape
   * @throws IllegalArgumentException if name is null
   */
  public Ellipse(String name) {
    super(name);
  }

  /**
   * ___________________________________ METHOD: toString() _______________________________________.
   * Returns a String with the name, location, horizontal radius, vertical radius, color, and
   * appear and disappear times of the ellipse.
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
//  @Override
//  public void setWidth(double width) {
//    if (width <= 0) {
//      throw new IllegalArgumentException("Width must be positive.");
//    }
//    this.width = this.radiusX * 2;
//  }

  /**
   * ___________________________________ METHOD: setHeight() ______________________________________.
   * This is a setter override from the AbstractShape that sets the height of the Ellipse.
   */
//  @Override
//  public void setHeight(double height) {
//    if (height <= 0) {
//      throw new IllegalArgumentException("Height must be positive.");
//    }
//    this.height = this.radiusY * 2;
//  }

}
