/**
 * ___________________________________ INTERFACE: IShape {} _______________________________________.
 * The Shape interface declares the methods all types of shapes should support.
 */
public interface IShape extends Comparable<IShape> {

  /**
   * ____________________________________ METHOD: getName() _______________________________________.
   *
   * @return the unique name of the shape
   */
  String getName();

  /**
   * ____________________________________ METHOD: setName() _______________________________________.
   *
   * @param name - the unique name given to the shape
   */
  void setName(String name);

  /**
   * ___________________________________ METHOD: getColor() _______________________________________.
   *
   * @return the color associated with the given shape
   */
  Color getColor();

  /**
   * ___________________________________ METHOD: setColor() _______________________________________.
   *
   * @param red   - the integer value of the red color, an int
   * @param green - the integer value of the green color, an int
   * @param blue  - the integer value of the blue color, an int
   */
  void setColor(int red, int green, int blue);

  /**
   * __________________________________ METHOD: getLocation() _____________________________________.
   *
   * @return the Point2D location of the given shape
   */
  Point2D getLocation();

  /**
   * __________________________________ METHOD: getLocation() _____________________________________.
   *
   * @param x - the x value of the shape's location, a double
   * @param y - the y value of the shape's location, a double
   */
  void setLocation(double x, double y);

  /**
   * ___________________________________ METHOD: getAppear() ______________________________________.
   *
   * @return the tick/time value of when the given shape appears, an int
   */
  int getAppear();

  /**
   * ___________________________________ METHOD: setAppear() ______________________________________.
   *
   * @param appear - the time in ticks when the Shape is set to appear, an int
   */
  void setAppear(int appear);

  /**
   * _________________________________ METHOD: getDisappear() _____________________________________.
   *
   * @return the tick value of when the given shape disappears, an int
   */
  int getDisappear();

  /**
   * ___________________________________ METHOD: setDisappear() ______________________________________.
   *
   * @param disappear - the time in ticks when the Shape is set to disappear, an int
   */
  void setDisappear(int disappear);

  /**
   * ____________________________________ METHOD: getWidth() ______________________________________.
   * The width is the horizontal length of a rectangle and the horizontal diameter of an oval.
   *
   * @return the width value of the given shape, a double
   */
  double getWidth();

  /**
   * ____________________________________ METHOD: setWidth() ______________________________________.
   * The width is the horizontal length of a rectangle and the horizontal diameter of an oval.
   *
   * @param width - the width the Shape is being set to, a double
   * @throws IllegalArgumentException if the width being passed in is negative
   */
  void setWidth(double width);

  /**
   * ____________________________________ METHOD: getHeight() _____________________________________.
   * The height is the vertical length of a rectangle, or the vertical length of an oval.
   *
   * @return the height value of the given shape, a double
   */
  double getHeight();

  /**
   * ____________________________________ METHOD: setHeight() _____________________________________.
   * The height is the vertical length of a rectangle, or the vertical length of an oval.
   *
   * @param height - the height the Shape is being set to, a double
   * @throws IllegalArgumentException if the height being passed in is negative
   */
  void setHeight(double height);

}
