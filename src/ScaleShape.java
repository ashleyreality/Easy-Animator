/**
 * The ScaleShape class extends ChangeShape. Use it to scale a shape from one size to another size.
 */
public class ScaleShape extends AbstractEvent {
  protected double widthBefore;
  protected double widthAfter;
  protected double heightBefore;
  protected double heightAfter;
  protected IShape shape;


  /**
   * Generates a shape change.
   *
   * @param shape
   * @param eventBegin
   * @param eventEnd
   * @param widthBefore
   * @param widthAfter
   * @param heightBefore
   * @param heightAfter
   */
  public ScaleShape(IShape shape, int eventBegin, int eventEnd, double widthBefore,
                    double widthAfter, double heightBefore, double heightAfter) {
    super(shape, eventBegin, eventEnd);
    this.widthBefore = widthBefore;
    this.widthAfter = widthAfter;
    this.heightBefore = heightBefore;
    this.heightAfter = heightAfter;
  }

  @Override
  public String toString() {
    // "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70
    return ("Shape " + this.getShape().getName()
            + " scales from Width: " + this.widthBefore
            + ", Height: " + this.heightBefore
            + " to Width: " + this.widthAfter + ", Height: " + this.heightAfter)
            + " from t=" + this.eventBegin + " to t=" + this.eventEnd;
  }
}