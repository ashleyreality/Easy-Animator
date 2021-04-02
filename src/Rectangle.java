/**
 * __________________________________ CLASS: Rectangle {} _________________________________________.
 * This is the Rectangle class. It extends the AbstractShape abstract class and implements the
 * method declarations that are unique to the Rectangle class. It creates and stores information
 * about a Rectangle shape.
 */
public class Rectangle extends AbstractShape {

  /**
   * __________________________________ CONSTRUCTOR: Rectangle() _______________________________________.
   * This is the Rectangle() constructor. It creates a rectangle with the given width and height,
   * color parameters, and location coordinates.
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
   * This is the toString() method override. It outputs a String with the name, location,
   * horizontal radius, vertical radius, color, and appear and disappear time of the rectangle.
   *
   * @return a String of the rectangle attributes
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
