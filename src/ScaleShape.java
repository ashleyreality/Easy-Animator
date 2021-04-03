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
   * Generates a scaled shape.
   *
   * @param shape       - the shape to be scaled
   *                    ADD
   * @param widthAfter  - the new width
   * @param heightAfter - the new height
   */
  public ScaleShape(IShape shape, double widthBefore, double heightBefore,
                    double widthAfter, double heightAfter) {
    super(shape);
    if (widthAfter <= 0 || heightAfter <= 0 || widthBefore <= 0 || heightBefore <= 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }
    this.widthBefore = widthBefore;
    this.widthAfter = widthAfter;
    this.heightBefore = heightBefore;
    this.heightAfter = heightAfter;
  }

  @Override
  public String toString() {
    // "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70
    return "Shape " + this.getShape().getName()
            + " scales from Width: " + String.format("%.1f", this.widthBefore)
            + ", Height: " + String.format("%.1f", this.heightBefore)
            + " to Width: " + String.format("%.1f", this.widthAfter) + ", Height: "
            + String.format("%.1f", this.heightAfter)
            + " from t=" + this.getEventBegin() + " to t=" + this.getEventEnd();
  }
}