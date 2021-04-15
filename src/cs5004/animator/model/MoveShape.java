package cs5004.animator.model;

/**
 * _____________________________ CONCRETE CLASS: MoveShape ______________________________________.
 * The MoveShape class extends ChangeShape. Use it to move a shape from one location to another.
 */
public class MoveShape extends AbstractEvent {

  private final Point2D from;
  private final Point2D to;

  /**
   * ________________________________ CONSTRUCTOR: MoveShape() ____________________________________.
   * Generates a shape move event.
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
      throw new IllegalArgumentException("The new location can't be the same.");
    }

    this.from = new Point2D(xBefore, yBefore);
    this.to = new Point2D(xAfter, yAfter);
  }

  /**
   * _________________________________ METHOD: toString() _________________________________________.
   * Returns a string describing this event's attributes.
   *
   * @return a string that describes the location move of a shape
   */
  @Override
  public String toString() {
    // Example output: Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50

    return this.shape.getName()
            + " moves from " + this.from.toString() + " to " + this.to.toString() + " from time t="
            + this.getEventBegin() + " to t=" + this.getEventEnd();
  }

  /**
   * _________________________________ METHOD: getEventType() _____________________________________.
   * Gets the type of this event.
   *
   * @return an enum storing the event's type
   */
  public EventType getEventType() {
    return EventType.MOVE;
  }

  @Override
  public String getBefore() {
    return this.from.toString();
  }

  @Override
  public String getAfter() {
    return this.to.toString();
  }

  public Point2D getFrom() {
    return from;
  }

  public Point2D getTo() {
    return to;
  }

  public void applyEvent(IShape shape, int tick) {
    shape.setLocation(tweening(tick, this.from.getX(), this.to.getX()),
            tweening(tick, this.from.getY(), this.to.getY()));
  }
}
