package cs5004.animator.model;

import java.util.Objects;

/**
 * ________________________________ CONCRETE CLASS: Point2D _______________________________________.
 * Prof. Park's Point2D class. This class represents a 2D point. This point is denoted in Cartesian
 * coordinates as (x,y).
 */
public class Point2D {
  private final double x;
  private final double y;

  /**
   * _________________________________ CONSTRUCTOR: Point2D() _____________________________________.
   * Constructs a 2d point with the given coordinates.
   *
   * @param x the x-coordinate of this point
   * @param y the y-coordinate of this point
   */
  public Point2D(double x, double y) {

    //    if (x < 0 || y < 0) {
    //      throw new IllegalArgumentException
    //      ("The x and y coordinates can not be negative values.");
    //    }

    this.x = x;
    this.y = y;
  }

  /**
   * ___________________________________ METHOD: getX() ___________________________________________.
   * Outputs the x-coordinate of this point.
   *
   * @return x-coordinate of this point
   */
  public double getX() {
    return x;
  }

  /**
   * ___________________________________ METHOD: getY() ___________________________________________.
   * Outputs the y-coordinate of this point.
   *
   * @return y-coordinate of this point
   */
  public double getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point2D point2D = (Point2D) o;
    return Double.compare(point2D.x, x) == 0 && Double.compare(point2D.y, y) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  /**
   * ___________________________________ METHOD: toString() _______________________________________.
   * This is the toString() method override. It outputs a String with the location of the shape.
   *
   * @return a String of the shape's location
   */
  public String toString() {
    return String.format("(%.1f,%.1f)", this.x, this.y);
  }
}