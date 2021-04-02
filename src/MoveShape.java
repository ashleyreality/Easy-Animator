/**
 * The MoveShape class extends ChangeShape. Use it to move a shape from one location to another.
 */
public class MoveShape extends AbstractEvent {
  private Point2D from;
  private Point2D to;

  /**
   * Generates a shape move.
   *
   * @param shape
   * @param eventBegin
   * @param eventEnd
   * @param from
   * @param to
   */
  public MoveShape(IShape shape, int eventBegin, int eventEnd, Point2D from, Point2D to) {
    super(shape, eventBegin, eventEnd);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
  // Example output: Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50

    return "Shape" + this.shape.getName()
      + " moves from (" + this.from + ") to (" + this.to + ") from t=" + this.eventBegin
    + " to t=" + this.eventEnd;
  }
}
