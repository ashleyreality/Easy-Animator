/**
 * The ChangeColor class extends ChangeShape. Use it to change the color of a shape.
 */
public class ChangeColor extends AbstractEvent {
  private Color from;
  private Color to;

  /**
   * Generate a shape change.
   *
   * @param shape - the shape to be changed
   * @param eventBegin - the time in ticks when the change begins
   * @param eventEnd   - the time in ticks when the change ends
   * @param from        - color to be changed from
   * @param to          - color to be changed to
   */

  public ChangeColor(IShape shape, int eventBegin, int eventEnd, Color from, Color to) {
    super(shape, eventBegin, eventEnd);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    // Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80
    return "Shape " + this.getShape().getName()
            + " changes color from (" + this.from
            + ") to (" + this.to
            + " from t=" + this.eventBegin + " to t=" + this.eventEnd;
  }
}
