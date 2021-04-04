/**
 * _______________________________ ABSTRACT: AnimationModelImpl ___________________________________.
 * The AnimationModelImpl class implements the method declarations of the IShape
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
   * Creates a shape with the given width and height, color parameters, and location coordinates.
   *
   * @param name   name of the shape
   * @param width  width of the shape (x)
   * @param height height of the shape (y)
   * @param red    red value of shape
   * @param green  green value of shape
   * @param blue   blue value of shape
   * @param x      x coordinate of shape
   * @param y      y coordinate of shape
   * @throws IllegalArgumentException if width or height are negative or if name is null
   */
  public AbstractShape(String name, double width, double height,
                       int red, int green, int blue, double x, double y) {
    this.color = new Color(red, green, blue);
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }
    if (name == null) {
      throw new IllegalArgumentException("Name can't be null");
    }
    this.name = name;
    this.location = new Point2D(x, y);
    this.width = width;
    this.height = height;
  }

  /*
   * _____________________________ CONSTRUCTOR: AbstractShape() ___________________________________.
   * Creates a clone of the shape.
   * @param name      name of the shape
   * @param width     width of the shape (x)
   * @param height    height of the shape (y)
   * @param color     color of the shape
   * @param location  location of the shape
   */
  /* protected AbstractShape(String name, double width, double height,
                   Color color, Point2D location) {
    this.name = name;
    this.width = width;
    this.height = height;
    this.color = color;
    this.location = location;
  }*/

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
    if (width <= 0) {
      throw new IllegalArgumentException("Width must be positive.");
    }
    this.width = width;
  }

  /**
   * ___________________________________ METHOD: getHeight() ______________________________________.
   * Gets the height of the shape.
   *
   * @return the height of the shape, a double
   */
  @Override
  public double getHeight() {
    return height;
  }

  /**
   * ___________________________________ METHOD: setHeight() ______________________________________.
   * Sets the height of the shape.
   */
  @Override
  public void setHeight(double height) {
    if (height <= 0) {
      throw new IllegalArgumentException("Height must be positive.");
    }
    this.height = height;
  }

  /**
   * ___________________________________ METHOD: getName() _______________________________________.
   * Gets the name of the shape.
   *
   * @return the name of the shape, a String
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * ___________________________________ METHOD: setName() _______________________________________.
   * Sets the name of the shape.
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * ___________________________________ METHOD: getColor() _______________________________________.
   * Gets the color of the shape.
   *
   * @return the color of the shape, a Color
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * ___________________________________ METHOD: setColor() _______________________________________.
   * Sets the color of the shape.
   */
  @Override
  public void setColor(int red, int green, int blue) {
    this.color = new Color(red, green, blue);
  }

  /**
   * _________________________________ METHOD: getLocation() ______________________________________.
   * Gets the location of the shape.
   *
   * @return the location of the shape, a Point2D
   */
  @Override
  public Point2D getLocation() {
    return this.location;
  }

  /**
   * _________________________________ METHOD: setLocation() ______________________________________.
   * Sets the location of the shape.
   */
  @Override
  public void setLocation(double x, double y) {
    this.location = new Point2D(x, y);
  }

  /**
   * _________________________________ METHOD: getAppear() ______________________________________.
   * Gets the appear time of the shape.
   *
   * @return the appear time of the shape, an int
   */
  @Override
  public int getAppear() {
    return appear;
  }

  /**
   * _________________________________ METHOD: setAppear() ______________________________________.
   * Sets the appear time of the shape.
   */
  @Override
  public void setAppear(int appear) {
    this.appear = appear;
  }

  /**
   * _________________________________ METHOD: getDisappear() _____________________________________.
   * Gets the disappear time of the shape.
   *
   * @return the disappear time of the shape, an int
   */
  @Override
  public int getDisappear() {
    return disappear;
  }

  /**
   * _________________________________ METHOD: setDisappear() _____________________________________.
   * Sets the disappear time of the shape.
   */
  @Override
  public void setDisappear(int disappear) {
    this.disappear = disappear;
  }

  /**
   * ___________________________________ METHOD: compareTo() ______________________________________.
   * This is an override of the compareTo() method of the Comparable interface. It sorts the shape
   * objects by width.
   */
  @Override
  public int compareTo(IShape o) {
    if (this.width > o.getWidth()) {
      return 1;
    } else if (this.width < o.getWidth()) {
      return -1;
    }
    return 0;
  }

}
