/**
 * _____________________________ CONCRETE CLASS: MoveShape ______________________________________.
 * The MoveShape class extends ChangeShape. Use it to move a shape from one location to another.
 */
public class MoveShape extends AbstractEvent {
  private Point2D from;
  private Point2D to;

  /**
   * ________________________________ CONSTRUCTOR: MoveShape() ____________________________________.
   * The MoveShape() constructor instantiates the declared fields. Generates a shape move.
   *
   * @param shape   - the shape you want to move
   * @param xBefore - a double, the previous x location
   * @param yBefore - a double, the previous y location
   * @param xAfter  - a double, the new x location
   * @param yAfter  - a double, the new y location
   */
  public MoveShape(IShape shape, double xBefore, double yBefore, double xAfter, double yAfter) {
    super(shape);
    if (xBefore == xAfter && yBefore == yAfter) {
      throw new IllegalArgumentException("The location can not be the same.");
    }

    this.from = new Point2D(xBefore, yBefore);
    this.to = new Point2D(xAfter, yAfter);
  }

  /**
   * _________________________________ METHOD: toString() _________________________________________.
   * This is a toString() override.
   *
   * @return a string that describes the location move of a shape
   */
  @Override
  public String toString() {
    // Example output: Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50

    return "Shape " + this.shape.getName()
            + " moves from " + this.from.toString() + " to " + this.to.toString() + " from t="
            + this.getEventBegin() + " to t=" + this.getEventEnd();
  }

  public EventType getEventType() {
    return EventType.MOVE;
  }
}
