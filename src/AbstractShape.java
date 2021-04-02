/**
 * __________________________ ABSTRACT CLASS: AnimationModelImpl {} _______________________________.
 * This is the AnimationModelImpl class. It implements the methods declarations of the IShape
 * interface for the methods that are shared by some shapes.
 */
public abstract class AbstractShape implements IShape {
  protected Color color;
  protected Point2D location;
  protected double width;
  protected double height;

  protected String name;
  protected int appear;
  protected int disappear;

  /**
   * _____________________________ CONSTRUCTOR: AbstractShape() ___________________________________.
   * Creates a shape with the given width and height, color parameters, and location coordinates .
   *
   * @param name   name of the shape
   * @param width  width of the shape (x)
   * @param height height of the shape (y)
   * @param red    red value of shape
   * @param green  green value of shape
   * @param blue   blue value of shape
   * @param x      x coordinate of shape
   * @param y      y coordinate of shape
   * @throws IllegalArgumentException if width or height are negative
   */
  public AbstractShape(String name, double width, double height,
                       int red, int green, int blue, double x, double y) {
    this.color = new Color(red, green, blue);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }
    this.name = name;
    this.location = new Point2D(x, y);
    this.width = width;
    this.height = height;
  }

  /**
   * ___________________________________ METHOD: getWidth() _______________________________________.
   * This is a getter that gets the width of the shape.
   * @return the width of the shape, a double
   */
  @Override
  public double getWidth() {
    return width;
  }

  /**
   * ___________________________________ METHOD: setWidth() _______________________________________.
   * This is a setter that sets the width of the shape.
   */
  @Override
  public void setWidth(double width) {
    if (width < 0) {
      throw new IllegalArgumentException("Width must be positive.");
    }
    this.width = width;
  }

  /**
   * ___________________________________ METHOD: getHeight() _______________________________________.
   * This is a getter that gets the height of the shape.
   * @return the height of the shape, a double
   */
  @Override
  public double getHeight() {
    return height;
  }

  /**
   * ___________________________________ METHOD: setHeight() _______________________________________.
   * This is a setter that sets the height of the shape.
   */
  @Override
  public void setHeight(double height) {
    if (height < 0) {
      throw new IllegalArgumentException("Height must be positive.");
    }
    this.height = height;
  }

  /**
   * ___________________________________ METHOD: getName() _______________________________________.
   * This is a getter that gets the name of the shape.
   * @return the name of the shape, a String
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * ___________________________________ METHOD: setName() _______________________________________.
   * This is a setter that sets the name of the shape.
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * ___________________________________ METHOD: getColor() _______________________________________.
   * This is a getter that gets the color of the shape.
   * @return the color of the shape, a Color
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * ___________________________________ METHOD: setColor() _______________________________________.
   * This is a setter that sets the color of the shape.
   */
  @Override
  public void setColor(int red, int green, int blue) {
    this.color = new Color(red, green, blue);
  }

  /**
   * _________________________________ METHOD: getLocation() ______________________________________.
   * This is a getter that gets the location of the shape.
   * @return the location of the shape, a Point2D
   */
  @Override
  public Point2D getLocation() {
    return this.location;
  }

  /**
   * _________________________________ METHOD: setLocation() ______________________________________.
   * This is a setter that sets the location of the shape.
   */
  @Override
  public void setLocation(double x, double y) {
    this.location = new Point2D(x, y);
  }

  /**
   * _________________________________ METHOD: getAppear() ______________________________________.
   * This is a getter that gets the appear time of the shape.
   * @return the appear time of the shape, an int
   */
  @Override
  public int getAppear() {
    return appear;
  }

  /**
   * _________________________________ METHOD: setAppear() ______________________________________.
   * This is a setter that sets the appear time of the shape.
   */
  @Override
  public void setAppear(int appear) {
    this.appear = appear;
  }

  /**
   * _________________________________ METHOD: getDisappear() ______________________________________.
   * This is a getter that gets the disappear time of the shape.
   * @return the disappear time of the shape, an int
   */
  @Override
  public int getDisappear() {
    return disappear;
  }

  /**
   * _________________________________ METHOD: setDisappear() ______________________________________.
   * This is a setter that sets the disappear time of the shape.
   */
  @Override
  public void setDisappear(int disappear) {
    this.disappear = disappear;
  }

}
