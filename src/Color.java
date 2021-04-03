/**
 * _________________________________ CONCRETE CLASS: Color ________________________________________.
 * The Color class defines colors that can be assigned to Shapes.
 */
public class Color {
  private int red;
  private int green;
  private int blue;

  /**
   * __________________________________ CONSTRUCTOR: Color() ______________________________________.
   * Creates a color using the given red, green and blue integer values at & between 0 and 255.
   *
   * @param red   the red value for this color
   * @param green the green value for this color
   * @param blue  the blue value for this color
   * @throws IllegalArgumentException when a color value is not between 0 and 255
   */
  public Color(int red, int green, int blue) {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Color values must be between 0 and 255.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * _________________________________ METHOD: toString() _________________________________________.
   * This is a toString() override.
   *
   * @return a string that describes the color RGB values of a shape
   */
  public String toString() {
    return "(" + this.red + "," + this.green + "," + this.blue + ")";
  }
}
