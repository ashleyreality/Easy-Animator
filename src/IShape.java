/**
 * This is the Shape interface. It declares the methods all types of shapes should support.
 */
public interface IShape {

  String getName();

  void setName(String name);

  /**
   * ___________________________________ METHOD: getColor() _______________________________________.
   * The getColor() method outputs the current color of a given shape.
   *
   * @return a Color associated with the given shape
   */
  Color getColor();

  void setColor(int red, int green, int blue);

  /**
   * __________________________________ METHOD: getLocation() _____________________________________.
   * The getLocation() method outputs the current location of a given shape.
   *
   * @return a Point2D location of the given shape
   */
  Point2D getLocation();

  /**
   * Sets an object's location.
   *
   * @param x - the x value of the location
   * @param y - the y value of the location
   */
  void setLocation(double x, double y);

  /**
   * ___________________________________ METHOD: getAppear() ______________________________________.
   * The getAppear() method outputs the time/tick of when a given shape appears.
   *
   * @return the tick value of when the given shape appears, an int
   */
  int getAppear();

  /**
   * Sets the time a Shape appears.
   *
   * @param appear - the time in ticks when the Shape appears
   */
  void setAppear(int appear);

  /**
   * _________________________________ METHOD: getDisappear() _____________________________________.
   * The getDisappear() method outputs the time/tick of when a given shape disappears.
   *
   * @return the tick value of when the given shape disappears, an int
   */
  int getDisappear();

  /**
   * Sets the time a Shape disappears.
   *
   * @param disappear - the time in ticks when a Shape disappears
   */
  void setDisappear(int disappear);

  /**
   * ____________________________________ METHOD: getWidth() ______________________________________.
   * The getWidth() method outputs the width of a given shape. The width is the horizontal length of
   * a rectangle and the horizontal diameter of an oval.
   *
   * @return the width value of the given shape, a double
   */
  double getWidth();

  /**
   * Sets the width of a given shape. The width is the horizontal length of
   * a rectangle and the horizontal diameter of an oval.
   *
   * @param width - a double, the width of a Shape
   */
  void setWidth(double width);

  /**
   * ____________________________________ METHOD: getHeight() ______________________________________.
   * The getHeight() method outputs the height of a given shape. The height is the vertical length
   * of a rectangle, or the vertical length of an oval.
   *
   * @return the height value of the given shape, a double
   */
  double getHeight();

  /**
   * Sets the height of a given shape. The height is the vertical length
   * of a rectangle, or the vertical length of an oval.
   *
   * @param height - the height value of the given shape, a double
   */
  void setHeight(double height);
}
