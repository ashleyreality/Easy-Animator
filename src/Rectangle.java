
import java.awt.geom.Point2D;

/**
 * This class creates and stores information about a Rectangle shape.
 */
public class Rectangle extends CreateShape {
  private double width;
  private double height;

  /**
   * Generates a Rectangle shape.
   *  @param color    - the color of the Rectangle
   * @param location - the rectangle's location
   * @param width    - int, a width
   * @param height   - int, a height
   */
  public Rectangle(String name, Color color, Point2D location, int appear, int disappear, double width,
                   double height) {

    super(name, color, location, appear, disappear);
    width = this.width;
    height = this.height;
  }

  public double getParam1() {
    return width;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getParam2() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  // fixme -- see note in superclass
  @Override
  public String toString() {
    return "Name: " // super get name?
            + "\nType: Rectangle"
            + "\nMin corner: " + this.getLocation() + ", Width: " + this.getParam1() + ", Height: "
            + this.getParam2() + ", Color: " // super get color?
            + "\nAppears at t=" + this.getAppear()
            + "\nDisappears at t=" + this.getDisappear() + "\n";
  }
}
