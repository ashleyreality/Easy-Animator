import java.awt.*;

/**
 * The MoveShape class extends ChangeShape. Use it to move a shape from one location to another.
 */
public class MoveShape extends AbstractEvent {
  private Point2D from;
  private Point2D to;

  /**
   * Generates a shape move.
   *
   * @param shape - the shape you want to move
   * @param x - a double, the new x location
   * @param y - a double, the new y location
   */
  public MoveShape(IShape shape, double x, double y) {
    super(shape);
    this.from = shape.getLocation();
    this.to = new Point2D(x, y);
  }

  @Override
  public String toString() {
  // Example output: Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50

    return "Shape" + this.shape.getName()
      + " moves from (" + this.from + ") to (" + this.to + ") from t=" +  this.getEventBegin()
    + " to t=" + this.getEventEnd();
  }
}
