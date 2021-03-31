public class ScaleShape extends ChangeShape {
  protected int widthBefore;
  protected int widthAfter;
  protected int heightBefore;
  protected int heightAfter;

  /**
   * Generate a shape change.
   *
   * @param shape       - the shape to be changed
   * @param changeBegin - the time in ticks from which the change begins
   * @param changeEnd   - the time in ticks when the change ends
   */
  public ScaleShape(Shape shape, int changeBegin, int changeEnd, int widthBefore, int widthAfter,
                    int heightBefore, int heightAfter) {
    super(shape, changeBegin, changeEnd);
    this.widthBefore = widthBefore;
    this.widthAfter = widthAfter;
    this.heightBefore = heightBefore;
    this.heightAfter = heightAfter;
  }

  public String toString() {
    // "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, " "Height: 100.0 from t=51 to t=70
    // fixme -- need to find a way to get old params AND new params?
    return ("Shape " + CreateShape.getName()
            + " scales from Width: " + this.widthBefore
            + ", Height: " + this.heightBefore
            + " to Width: " + this.widthAfter);
  }
}