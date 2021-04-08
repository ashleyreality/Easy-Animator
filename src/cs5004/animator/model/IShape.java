package cs5004.animator.model;

/**
 * ___________________________________ INTERFACE: IShape {} _______________________________________.
 * The Shape interface declares the methods all types of shapes should support.
 */
public interface IShape extends Comparable<IShape> {

  /**
   * ____________________________________ METHOD: getName() _______________________________________.
   * Returns the shape name.
   *
   * @return the unique name of the shape
   */
  String getName();

  /**
   * ____________________________________ METHOD: setName() _______________________________________.
   * Sets the shape name.
   *
   * @param name - the unique name given to the shape
   */
  void setName(String name);

  /**
   * ___________________________________ METHOD: getColor() _______________________________________.
   * Gets the shape's color.
   *
   * @return the color associated with the given shape
   */
  Color getColor();

  /**
   * ___________________________________ METHOD: setColor() _______________________________________.
   * Sets the shape's color.
   *
   * @param red   - the integer value of the red color, an int
   * @param green - the integer value of the green color, an int
   * @param blue  - the integer value of the blue color, an int
   */
  void setColor(int red, int green, int blue);

  /**
   * __________________________________ METHOD: getLocation() _____________________________________.
   * Gets the shape's location.
   *
   * @return the Point2D location of the given shape
   */
  Point2D getLocation();

  /**
   * __________________________________ METHOD: setLocation() _____________________________________.
   * Sets the shape's location.
   *
   * @param x - the x value of the shape's location, a double
   * @param y - the y value of the shape's location, a double
   */
  void setLocation(double x, double y);

  /**
   * ___________________________________ METHOD: getAppear() ______________________________________.
   * Gets the shape's appear time.
   *
   * @return the tick/time value of when the given shape appears, an int
   */
  int getAppear();

  /**
   * ___________________________________ METHOD: setAppear() ______________________________________.
   * Sets the shape's appear time.
   *
   * @param appear - the time in ticks when the Shape is set to appear, an int
   */
  void setAppear(int appear);

  /**
   * _________________________________ METHOD: getDisappear() _____________________________________.
   * Gets the shape's disappear time.
   *
   * @return the tick value of when the given shape disappears, an int
   */
  int getDisappear();

  /**
   * ___________________________________ METHOD: setDisappear() __________________________________.
   * Sets the shape's disappear time.
   *
   * @param disappear - the time in ticks when the Shape is set to disappear, an int
   */
  void setDisappear(int disappear);

  /**
   * ____________________________________ METHOD: getWidth() ______________________________________.
   * Gets the shape's width. The width is the horizontal length of a rectangle and the horizontal
   * diameter of an oval.
   *
   * @return the width value of the given shape, a double
   */
  double getWidth();

  /**
   * ____________________________________ METHOD: setWidth() ______________________________________.
   * Sets the shape's width. The width is the horizontal length of a rectangle and the horizontal
   * diameter of an oval.
   *
   * @param width - the width the Shape is being set to, a double
   * @throws IllegalArgumentException if the width being passed in is negative
   */
  void setWidth(double width);

  /**
   * ____________________________________ METHOD: getHeight() _____________________________________.
   * Gets the shape's height. The height is the vertical length of a rectangle, or the vertical
   * length of an oval.
   *
   * @return the height value of the given shape, a double
   */
  double getHeight();

  /**
   * ____________________________________ METHOD: setHeight() _____________________________________.
   * Sets the shape's height. The height is the vertical length of a rectangle, or the vertical
   * length of an oval.
   *
   * @param height - the height the Shape is being set to, a double
   * @throws IllegalArgumentException if the height being passed in is negative
   */
  void setHeight(double height);

}
