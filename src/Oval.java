
/**
 * This class creates and stores information about an Oval shape.
 */
public class Oval extends AbstractShape {
  // radiusX, from the center point to the outer edge of the circle along the X-axis/width
  private double radiusX;
  // radiusY, from the center point to the outer edge of the circle along the Y-axis/height
  private double radiusY;

  /**
   * Creates an oval with the given width and height, color parameters, and location coordinates.
   *
   * @param width  width of the oval (x)
   * @param height height of the oval (y)
   * @param red    red value of oval
   * @param green  green value of oval
   * @param blue   blue value of oval
   * @param x      x coordinate of oval
   * @param y      y coordinate of oval
   */
  public Oval(double width, double height,
              int red, int green, int blue, double x, double y) {
    super(width, height, red, green, blue, x, y);
    this.radiusX = width / 2;
    this.radiusY = height / 2;
  }

  @Override
  public String toString() {
    return "Name: " + this.name
            + "\nType: Oval"
            + "\nCenter: " + this.location.toString() + ", X radius: " + this.radiusX + ", Y radius: "
            + this.radiusY + ", Color: " + this.color.toString()
            + "\nAppears at t=" + this.appear
            + "\nDisappears at t=" + this.disappear + "\n";
  }
}
