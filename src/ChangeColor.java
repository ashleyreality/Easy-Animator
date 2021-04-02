/**
 * The ChangeColor class extends ChangeShape. Use it to change the color of a shape.
 */
public class ChangeColor extends AbstractEvent {
  private Color from;
  private Color to;

  /**
   * Generate a shape change.
   *
   * @param shape - the shape to be changed
   * @param R - the red value of the new color
   * @param G - the green value of the new color
   * @param B - the blue value of the new color
   */

  public ChangeColor(IShape shape, int R, int G, int B) {
    super(shape);
    this.from = shape.getColor();
    this.to = new Color(R, G, B);

    shape.setColor(R, G, B);
  }

  @Override
  public String toString() {
    // Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80
    return "Shape " + this.getShape().getName()
            + " changes color from " + this.from.toString()
            + " to " + this.to.toString()
            + " from t=" + this.getEventBegin() + " to t=" + this.getEventEnd();
  }
}
