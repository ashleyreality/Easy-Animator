import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * This class creates and stores information about an Oval shape.
 */
public class CreateOval extends AbstractCreateShape {
  private int radius1;
  private int radius2;

  /** Generates an Oval shape.
   *
   * @param color - the color of the Oval
   * @param location - the Oval's location
   * @param radius1 - the first radius of the Oval
   * @param radius2 - the second radius of the Oval
   */
  public CreateOval(Color color, Point2D location, int radius1, int radius2) {
    super(color, location);
    radius1 = this.radius1;
    radius2 = this.radius2;
  }
}
