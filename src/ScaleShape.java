public class ScaleShape extends ChangeShape {
  protected double widthBefore;
  protected double widthAfter;
  protected double heightBefore;
  protected double heightAfter;
  protected Shape shape;

  /**
   * Generate a shape change.
   *
   * @param shape       - the shape to be changed
   * @param changeBegin - the time in ticks from which the change begins
   * @param changeEnd   - the time in ticks when the change ends
   */
  public ScaleShape(Shape shape, int changeBegin, int changeEnd, double widthBefore, double widthAfter,
                    double heightBefore, double heightAfter) {
    super(shape, changeBegin, changeEnd);
    this.widthBefore = widthBefore;
    this.widthAfter = widthAfter;
    this.heightBefore = heightBefore;
    this.heightAfter = heightAfter;

  }

  public String toString() {
    // "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70
    // fixme -- how to call super method to get shape name?
    return ("Shape " + "CreateShape.getName()"
            + " scales from Width: " + this.widthBefore
            + ", Height: " + this.heightBefore
            + " to Width: " + this.widthAfter + ", Height: " + this.heightAfter);
  }
}