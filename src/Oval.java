/**
 * _____________________________________ CLASS: Oval {} ___________________________________________.
 * This is the Oval class. It extends the AbstractShape abstract class and implements the method
 * declarations that are unique to the Oval class. It creates and stores information about an Oval
 * shape.
 */
public class Oval extends AbstractShape {
  // radiusX, from the center point to the outer edge of the circle along the X-axis/width
  private double radiusX;
  // radiusY, from the center point to the outer edge of the circle along the Y-axis/height
  private double radiusY;

  /**
   * __________________________________ CONSTRUCTOR: Oval() _______________________________________.
   * This is the Oval() constructor. It creates an oval with the given width and height, color
   * parameters, and location coordinates.
   *
   * @param name   name of the oval
   * @param width  width of the oval (x)
   * @param height height of the oval (y)
   * @param red    red value of oval
   * @param green  green value of oval
   * @param blue   blue value of oval
   * @param x      x coordinate of oval
   * @param y      y coordinate of oval
   */
  public Oval(String name, double width, double height,
              int red, int green, int blue, double x, double y) {
    super(name, width, height, red, green, blue, x, y);
    this.radiusX = width / 2;
    this.radiusY = height / 2;
  }

  private Oval(String name, double width, double height,
                   Color color, Point2D location) {
    super(name, width, height, color, location);
    this.radiusX = width / 2;
    this.radiusY = height / 2;
  }

  /**
   * ___________________________________ METHOD: toString() _______________________________________.
   * This is the toString() method override. It outputs a String with the name, location,
   * horizontal radius, vertical radius, color, and appear and disappear time of the oval.
   *
   * @return a String of the oval attributes
   */
  @Override
  public String toString() {
    return "Name: " + this.name
            + "\nType: oval"
            + "\nCenter: " + this.location.toString() + ", X radius: " + this.radiusX + ", Y radius: "
            + this.radiusY + ", Color: " + this.color.toString()
            + "\nAppears at t=" + this.appear
            + "\nDisappears at t=" + this.disappear + "\n";
  }

//  @Override
//  public IShape copy() {
//    return new Oval(this.name,this.width,this.height,this.color,this.location);
//  }
}
