/**
 * This is the ScaleShape class. It extends the abstract class ChangeShape.
 */
public class ScaleShape extends AbstractEvent {
  protected double widthBefore;
  protected double widthAfter;
  protected double heightBefore;
  protected double heightAfter;
  protected IShape shape;

  // Note:
  // width = horizontal length of rectangle = horizontal diameter of oval
  // height = vertical length of rectangle = vertical length of oval

  /**
   * _______________________________ CONSTRUCTOR: ScaleShape() ____________________________________.
   * The ScaleShape() constructor generates a shape change.
   *
   * @param shape       - the shape to be changed
   * @param changeBegin - the time in ticks from which the change begins
   * @param changeEnd   - the time in ticks when the change ends
   */
  public ScaleShape(IShape shape, int changeBegin, int changeEnd, double widthBefore,
                    double widthAfter, double heightBefore, double heightAfter) {
    super(shape, changeBegin, changeEnd);
    this.widthBefore = widthBefore;
    this.widthAfter = widthAfter;
    this.heightBefore = heightBefore;
    this.heightAfter = heightAfter;
  }

  /**
   * ____________________________________ METHOD: toString() ______________________________________.
   * The toString() method override.
   *
   * @return
   */
  public String toString() {
    // "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70
    // fixme -- how to call super method to get shape name?
    return ("Shape " + "CreateShape.getName()"
            + " scales from Width: " + this.widthBefore
            + ", Height: " + this.heightBefore
            + " to Width: " + this.widthAfter + ", Height: " + this.heightAfter);
  }
}