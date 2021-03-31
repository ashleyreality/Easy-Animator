import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Use this class to change a shape.
 */
public class AbstractCreateShape implements IShape {
  private Color color;
  private Point2D location;
  private int appear;
  private int disappear;

  /**
   * Generate a shape change.
   *
   * @param color - the color of the shape
   * @param location - the shape's location as a Point2D
   */
  public AbstractCreateShape(Color color, Point2D location, int appear, int disappear) {
    this.color = color;
    this.location = location;
    this.appear = appear;
    this.disappear = disappear;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public Point2D getLocation() {
    return this.location;
  }



  /**
   * Changes the color associated with a Shape.
   *
   * @param color - the color to change the shape to
   */
  public void changeColor(Color color) {
    this.color = color;
  }
}
