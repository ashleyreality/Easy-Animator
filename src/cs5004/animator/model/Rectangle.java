package cs5004.animator.model;

/**
 * __________________________________ CLASS: Rectangle {} _________________________________________.
 * The Rectangle class extends AbstractShape and implements method declarations that are unique to
 * the Rectangle class. It creates and stores information about a Rectangle shape.
 */
public class Rectangle extends AbstractShape {
  private final ShapeType type = ShapeType.RECTANGLE;

  /**
   * _____________________________ FIRST CONSTRUCTOR: Rectangle() _________________________________.
   * Creates a rectangle with the given width, height, color parameters, and location coordinates.
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
  public Rectangle(String name,
                   double width, double height,
                   int red, int green, int blue,
                   double x, double y) {
    super(name, width, height, red, green, blue, x, y);
  }

  /**
   * _____________________________ SECOND CONSTRUCTOR: Rectangle() ________________________________.
   * Creates a shape with the given name. Further attributes are instantiated with the first event
   * called on the shape.
   *
   * @param name name of the shape
   * @throws IllegalArgumentException if name is null
   */
  public Rectangle(String name) {
    super(name);
  }

  // for copy
  public Rectangle(String name, double width, double height,
                   Color color, Point2D location, int appear, int disappear) {
    super(name, width, height, color, location, appear, disappear);
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
            + "\nType: " + this.type.toString()
            + "\nMin corner: " + this.location.toString() + ", Width: " + this.width + ", Height: "
            + this.height + ", Color: " + this.color.toString()
            + "\nAppears at t=" + this.appear
            + "\nDisappears at t=" + this.disappear + "\n";
  }

  /**
   * _________________________________ METHOD: createString() _____________________________________.
   * Returns a String with the name of the color of the rectangle, the name of the rectangle, the
   * top-left corner location of the rectangle on the canvas, the width and the height.
   *
   * @return the string for the text output, a String
   */
  @Override
  public String createString() {
    return "Create " + this.color.toString() + " " + this.type.toString() + " " + this.name
            + " with corner at " + this.location.toString() + ", width " + this.width
            + " and height " + this.height;
  }

  /**
   * ___________________________________ METHOD: getType() _______________________________________.
   * This is a getter that gets the type of the shape.
   * @return the type of the shape, a String
   */
  public ShapeType getType() {
    return type;
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.name, this.width, this.height, this.color,
            this.location, this.appear, this.disappear);
  }

}
