import java.awt.geom.Point2D;

/**
 * This is the Shape interface. It declares the methods required to create a Shape class.
 */
public interface IShape {

  String getName();

  /**
   * ___________________________________ METHOD: getColor() _______________________________________.
   * The getColor() method outputs the current color of a given shape.
   *
   * @return a Color associated with the given shape
   */
  Color getColor();

  /**
   * __________________________________ METHOD: getLocation() _____________________________________.
   * The getLocation() method outputs the current location of a given shape.
   *
   * @return a Point2D location of the given shape
   */
  Point2D getLocation();

  /**
   * ___________________________________ METHOD: getAppear() ______________________________________.
   * The getAppear() method outputs the time/tick of when a given shape appears.
   *
   * @return the tick value of when the given shape appears, an int
   */
  int getAppear();

  /**
   * _________________________________ METHOD: getDisappear() _____________________________________.
   * The getDisappear() method outputs the time/tick of when a given shape disappears.
   *
   * @return the tick value of when the given shape disappears, an int
   */
  int getDisappear();

  /**
   * ____________________________________ METHOD: getWidth() ______________________________________.
   * The getWidth() method outputs the width of a given shape.
   * width = horizontal length of rectangle = horizontal diameter of oval
   *
   * @return the width value of the given shape, a double
   */
  double getWidth();

  /**
   * ____________________________________ METHOD: getHeight() ______________________________________.
   * The getHeight() method outputs the height of a given shape.
   * height = vertical length of rectangle = vertical length of oval
   *
   * @return the height value of the given shape, a double
   */
  double getHeight();
}
