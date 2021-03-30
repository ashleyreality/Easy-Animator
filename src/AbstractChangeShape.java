/**
 * Use this class to change a shape.
 */
public abstract class AbstractChangeShape {
  private int changeBegin;
  private int changeEnd;
  private double from;
  private double to;


  /**
   * Generate a shape change.
   *
   * @param changeBegin - the time in ticks from which the change begins
   * @param changeEnd - the time in ticks when the change ends
   * @param from - location, size, or color
   * @param to - location, size, or color
   */
  public AbstractChangeShape(int changeBegin, int changeEnd, double from, double to) {
    this.changeBegin = changeBegin;
    this.changeEnd = changeEnd;
    this.from = from;
    this.to = to;
  }
}
