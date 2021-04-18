package cs5004.animator.model;

/**
 * _______________________________ ABSTRACT: AbstractShape ___________________________________.
 * The AbstractShape class implements the method declarations of the IShape
 * interface for the methods that are shared by some shapes.
 */
public abstract class AbstractShape implements IShape {
  // possibly set to invalid/null values & check if null, then set to a certain value
  protected Color color;
  protected Point2D location;
  protected double width;
  protected double height;
  protected String name;
  protected int appear = -1;
  protected int disappear = -1;
  protected ShapeType type;


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

  /**
   * _________________________SECOND CONSTRUCTOR: AbstractShape() _________________________________.
   * Creates a shape with the given name. Further attributes are instantiated with the first event
   * called on the shape.
   *
   * @param name   name of the shape
   * @param type the type of shape
   * @throws IllegalArgumentException if name is null
   */
  public AbstractShape(String name, ShapeType type) {
    if (name == null) {
      throw new IllegalArgumentException("Name can't be null");
    }
    this.name = name;
    this.type = type;
  }


  /**
   * Creates a copy of a shape.
   *
   * @param name the name of the shape
   * @param width the shape's width
   * @param height the shape's height
   * @param color the shape's color
   * @param location the shape's location
   * @param appear the time the shape appears
   * @param disappear the time the shape disappears
   * @param type the type of the shape
   */
  public AbstractShape(String name, double width, double height,
                       Color color, Point2D location, int appear, int disappear, ShapeType type) {
    this.name = name;
    this.width = width;
    this.height = height;
    this.color = color;
    this.location = location;
    // do we need to copy these?
    this.appear = appear;
    this.disappear = disappear;
    this.type = type;
  }

  /**
   * ___________________________________ METHOD: getType() _______________________________________.
   * This is a getter that gets the type of the shape.
   * @return the type of the shape, a String
   */
  public ShapeType getType() {
    return type;
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
   * Sets the width of the shape.
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
   * _________________________________ METHOD: appearString() _____________________________________.
   * This returns the text output.
   * @return the text output found in the out file, a String
   */
  @Override
  public String appearString() {
    return this.getName() + " appears at time t=" + this.appear
            + " and disappears at time t=" + this.disappear;
  }

}
