import java.awt.*;
import java.awt.geom.Point2D;

public class AbstractCreateShape implements IShape {
  private Color color;
  private Point2D location;

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
