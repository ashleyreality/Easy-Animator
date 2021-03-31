
public class ChangeColor extends ChangeShape {
  private Color from;
  private Color to;

  /**
   * Generate a shape change.
   *
   * @param shape - the shape to be changed
   * @param changeBegin - the time in ticks from which the change begins
   * @param changeEnd   - the time in ticks when the change ends
   * @param from        - color to be changed from
   * @param to          - color to be changed to
   */

  public ChangeColor(Shape shape, int changeBegin, int changeEnd, Color from, Color to) {
    super(shape, changeBegin, changeEnd);
    this.from = from;
    this.to = to;
  }
}
