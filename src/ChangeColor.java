/**
 * _____________________________ CONCRETE CLASS: ChangeColor ______________________________________.
 * The ChangeColor class extends AbstractEvent. It changes the color of a shape.
 */
public class ChangeColor extends AbstractEvent {
  private Color from;
  private Color to;

  /**
   * ________________________________ CONSTRUCTOR: ChangeColor() __________________________________.
   *  The ChangeColor() constructor instantiates the declared fields. Generates a color change.
   *
   * @param shape the shape to be changed
   * @param fromR the red value of the original color
   * @param fromG the green value of the original color
   * @param fromB the blue value of the original color
   * @param toR   the red value of the new color
   * @param toG   the green value of the new color
   * @param toB   the blue value of the new color
   */
  public ChangeColor(IShape shape, int fromR, int fromG, int fromB, int toR, int toG, int toB) {
    super(shape);
    if (fromR < 0 || fromR > 255 || fromG < 0 || fromG > 255 || fromB < 0 || fromB > 255
            ||toR < 0 || toR > 255 || toG < 0 || toG > 255 || toB < 0 || toB > 255 ) {
      throw new IllegalArgumentException("RGB color values must be between 0 and 255.");
    }

    this.from = new Color(fromR, fromG, fromB);
    this.to = new Color(toR, toG, toB);
  }

  /**
   * _________________________________ METHOD: toString() _________________________________________.
   * This is a toString() override.
   *
   * @return a string that describes the color change of a shape
   */
  @Override
  public String toString() {
    // Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80
    return "Shape " + this.getShape().getName()
            + " changes color from " + this.from.toString()
            + " to " + this.to.toString()
            + " from t=" + this.getEventBegin() + " to t=" + this.getEventEnd();
  }

  public EventType getEventType() {
    return EventType.RECOLOR;
  }
}
