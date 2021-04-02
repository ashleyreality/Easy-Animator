/**
 * The Color class defines colors that can be assigned to Shapes.
 */
public class Color {
  private double red;
  private double green;
  private double blue;

  /**
   * Creates a color using the given red, green and blue values.
   *
   * @param red the red value for this color
   * @param green the green value for this color
   * @param blue the blue value for this color
   * @throws IllegalArgumentException when a color value is not between 0 and 255
   */
  public Color(double red, double green, double blue) {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Color values must be between 0 and 255.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  // RED, GREEN, BLUE integer values
  // Use AWT color instead of this enumeration!
  // OR have a class that just holds the 3 colors
  // 0 to 255
}
