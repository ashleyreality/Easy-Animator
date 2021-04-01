
import java.awt.geom.Point2D;

/**
 * This interface describes the methods required to create a Shape class.
 */
public interface Shape {

  String getName();

  /**
   * Returns the current color of a shape.
   *
   * @return a Color associated with a shape
   */
  Color getColor();

  /**
   * Returns the current location of a shape.
   *
   * @return a Point2D location
   */
  Point2D getLocation();

  int getAppear();
  int getDisappear();

  double getParam1();
  double getParam2();
}
