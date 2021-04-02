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
   * @param eventBegin - the time in ticks when the event begins
   * @param eventEnd - the time in ticks when the event ends
   * @param from - the Point2D you are moving the shape from
   * @param to - the Point2D you are moving the shape to
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
