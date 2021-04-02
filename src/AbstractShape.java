import java.awt.geom.Point2D;

/**
 * Use this class to change a shape.
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected Color color;
  protected Point2D location;

  protected int appear;
  protected int disappear;

  /**
   * Generate a shape change.
   *
   * @param color - the color of the shape
   * @param location - the shape's location as a Point2D
   */
  public AbstractShape(String name, Color color, Point2D location, int appear, int disappear) {
    this.name = name;
    this.color = color;
    this.location = location;
    this.appear = appear;
    this.disappear = disappear;
  }

  @Override
  public String getName() { return this.name; }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public Point2D getLocation() {
    return this.location;
  }

  /**
   * Changes the color associated with a Shape.
   *
   * @param color - the color to change the shape to
   */
  public void changeColor(Color color) {
    this.color = color;
  }

  public int getAppear() {
    return appear;
  }

  public int getDisappear() {
    return disappear;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void setLocation(Point2D location) {
    this.location = location;
  }

  public void setAppear(int appear) {
    this.appear = appear;
  }

  public void setDisappear(int disappear) {
    this.disappear = disappear;
  }

// fixme -- what do y'all think about the toStrings for shapes? We could leave them here and the
  // text would be slightly different than shown -- or we could dynamically get the text? :(
  // or we could leave them as shown in the subclasses, but that is repeated code. lmk what you think
  @Override
  public String toString() {
    return "Name: " + this.getName()
            + "\nType: Oval"
            + "\n Location: " + this.getLocation() + ", Param 1: " + this.getWidth() + ", Param 2: "
            + this.getHeight() + ", Color: " + this.getColor()
            + "\nAppears at t=" + this.getAppear()
            + "\nDisappears at t=" + this.getDisappear() + "\n";
  }
}
