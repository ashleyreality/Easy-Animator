import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Use this class to change a shape.
 */
public abstract class AbstractCreateShape implements IShape {
  private Color color;
  private Point2D location;

  /**
   * Generate a shape change.
   *
   * @param color - the color of the shape
   * @param location - the shape's location as a Point2D
   */
  public AbstractCreateShape(Color color, Point2D location) {
    this.color = color;
    this.location = location;
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
   */
  public void changeColor() {

  }
}
