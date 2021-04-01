import java.awt.geom.Point2D;

/**
 * This class creates and stores information about an Oval shape.
 */
public class Oval extends CreateShape {
  // radiusX, from the center point to the outer edge of the circle along the X-axis/width
  private double radiusX;
  // radiusY, from the center point to the outer edge of the circle along the Y-axis/height
  private double radiusY;

  /**
   * Generates an Oval shape.
   *
   * @param color    - the color of the Oval
   * @param location - the Oval's location
   * @param radius1  - the first radius of the Oval
   * @param radius2  - the second radius of the Oval
   */
  public Oval(String name, Color color, Point2D location, int appear, int disappear, double radius1,
              double radius2) {
    super(name, color, location, appear, disappear);
    radius1 = this.radiusX;
    radius2 = this.radiusY;
  }

  public double getParam1() {
    return radiusX;
  }

  public void setRadiusX(double radiusX) {
    this.radiusX = radiusX;
  }

  public double getParam2() {
    return radiusY;
  }

  public void setRadiusY(double radiusY) {
    this.radiusY = radiusY;
  }

  // fixme -- see note in superclass
  @Override
  public String toString() {
    return "Name: " // super get name?
          + "\nType: Oval"
          + "\n Center: " + this.getLocation() + ", X radius: " + this.getParam1() + ", Y radius: "
          + this.getParam2() + ", Color: " // super get color?
          + "\nAppears at t=" + this.getAppear()
            + "\nDisappears at t=" + this.getDisappear() + "\n";
  }
}
