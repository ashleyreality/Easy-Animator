/**
 * Use this class to change a shape.
 */
public abstract class ChangeShape {
  protected int changeBegin;
  protected int changeEnd;
  protected Shape shape;


  /**
   * Generate a shape change.
   *
   * @param shape - the shape to be changed
   * @param changeBegin - the time in ticks from which the change begins
   * @param changeEnd - the time in ticks when the change ends
   */
  public ChangeShape(Shape shape, int changeBegin, int changeEnd) {
    this.changeBegin = changeBegin;
    this.changeEnd = changeEnd;
    this.shape = shape;
  }

  public int getChangeBegin() {
    return changeBegin;
  }
  public int getChangeEnd() {
    return changeEnd;
  }
}
