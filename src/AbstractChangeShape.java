/**
 * Use this class to change a shape.
 */
public abstract class AbstractChangeShape {
  private int changeBegin;
  private int changeEnd;
  private int shape;


  /**
   * Generate a shape change.
   *
   * @param shape - the shape to be changed
   * @param changeBegin - the time in ticks from which the change begins
   * @param changeEnd - the time in ticks when the change ends
   */
  public AbstractChangeShape(IShape shape, int changeBegin, int changeEnd) {
    this.changeBegin = changeBegin;
    this.changeEnd = changeEnd;
  }

}
