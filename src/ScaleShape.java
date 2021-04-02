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
   * @param shape - the shape to be scaled
   * @param widthAfter - the new width
   * @param heightAfter - the new height
   */
  public ScaleShape(IShape shape, double widthAfter, double heightAfter) {
    super(shape);
    this.widthBefore = shape.getWidth();
    this.widthAfter = widthAfter;
    this.heightBefore = shape.getHeight();
    this.heightAfter = heightAfter;
  }

  @Override
  public String toString() {
    // "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70
    return ("Shape " + this.getShape().getName()
            + " scales from Width: " + this.widthBefore
            + ", Height: " + this.heightBefore
            + " to Width: " + this.widthAfter + ", Height: " + this.heightAfter)
            + " from t=" + this.getEventBegin() + " to t=" + this.getEventEnd();
  }
}