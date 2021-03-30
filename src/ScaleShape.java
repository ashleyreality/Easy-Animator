public class ScaleShape extends AbstractChangeShape {
  /**
   * Generate a shape change.
   *
   * @param shape - the shape to be changed
   * @param changeBegin - the time in ticks from which the change begins
   * @param changeEnd   - the time in ticks when the change ends
   */
  public ScaleShape(IShape shape, int changeBegin, int changeEnd) {
    super(shape, changeBegin, changeEnd);
  }
}
