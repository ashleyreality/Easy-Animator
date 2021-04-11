package cs5004.animator.model;

/**
 * ______________________________ CONCRETE CLASS: ScaleShape ______________________________________.
 * The ScaleShape class extends ChangeShape. Use it to scale a shape from one size to another size.
 */
public class ScaleShape extends AbstractEvent {
  private double widthBefore;
  private double widthAfter;
  private double heightBefore;
  private double heightAfter;
  private IShape shape;

  /**
   * _______________________________ CONSTRUCTOR: ScaleShape() ____________________________________.
   * Generates a scaled shape event.
   *
   * @param shape         - the shape to be scaled
   * @param widthBefore   - the previous width of the shape
   * @param heightBefore  - the previous height of the shape
   * @param widthAfter    - the new width of the shape
   * @param heightAfter   - the new height of the shape
   */
  public ScaleShape(IShape shape,
                    double widthBefore, double heightBefore,
                    double widthAfter, double heightAfter) {
    super(shape);
    if (widthBefore == widthAfter && heightBefore == heightAfter) {
      throw new IllegalArgumentException("The new scale can not be the same.");
    }
    if (widthAfter <= 0 || heightAfter <= 0 || widthBefore <= 0 || heightBefore <= 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }


    this.widthBefore = widthBefore;
    this.widthAfter = widthAfter;
    this.heightBefore = heightBefore;
    this.heightAfter = heightAfter;
    this.shape = shape;
  }

  /**
   * _________________________________ METHOD: toString() _________________________________________.
   * Returns a string describing this event's attributes.
   *
   * @return a string that describes the size of a shape
   */
  @Override
  public String toString() {

    if (this.widthBefore == this.widthAfter) {
      return this.shape.getName() + " changes height from " + this.heightBefore + " to "
              + this.heightAfter + " from time t=" + this.getEventBegin() + " to t="
              + this.getEventEnd();
    }
    else if (this.heightBefore == this.heightAfter) {
      return this.shape.getName() + " changes width from " + this.widthBefore + " to "
              + this.widthAfter + " from time t=" + this.getEventBegin() + " to t="
              + this.getEventEnd();
    }
    else {
      return this.shape.getName() + " changes height from " + this.heightBefore + " to "
              + this.heightAfter + " and changes width from " + this.widthBefore + " to "
              + this.widthAfter + " from time t=" + this.getEventBegin() + " to t="
              + this.getEventEnd();
    }
  }


  /**
   * _________________________________ METHOD: getEventType() _____________________________________.
   * Gets the type of this event.
   *
   * @return an enum storing the event's type
   */
  public EventType getEventType() {
    return EventType.SCALE;
  }

  @Override
  public String getBefore() {
    return null;
  }

  @Override
  public String getAfter() {
    return null;
  }
}