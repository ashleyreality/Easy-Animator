/**
 * This class includes the code shared by some shapes.
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
   * Creates a shape with the given width and height, color parameters, and location coordinates .
   *
   * @param width  width of the shape (x)
   * @param height height of the shape (y)
   * @param red    red value of shape
   * @param green  green value of shape
   * @param blue   blue value of shape
   * @param x      x coordinate of shape
   * @param y      y coordinate of shape
   */
  public AbstractShape(double width, double height,
                       int red, int green, int blue, double x, double y) {
    this.color = new Color(red, green, blue);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }
    this.location = new Point2D(x, y);
    this.width = width;
    this.height = height;
  }

  @Override
  public double getWidth() {
    return width;
  }

  @Override
  public void setWidth(double width) {
    this.width = width;
  }

  @Override
  public double getHeight() {
    return height;
  }

  @Override
  public void setHeight(double height) {
    this.height = height;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setColor(int red, int green, int blue) {
    this.color = new Color(red, green, blue);
  }

  @Override
  public Point2D getLocation() {
    return this.location;
  }

  @Override
  public void setLocation(double x, double y) {
    this.location = new Point2D(x, y);
  }

  @Override
  public int getAppear() {
    return appear;
  }

  @Override
  public void setAppear(int appear) {
    this.appear = appear;
  }

  @Override
  public int getDisappear() {
    return disappear;
  }

  @Override
  public void setDisappear(int disappear) {
    this.disappear = disappear;
  }

}
