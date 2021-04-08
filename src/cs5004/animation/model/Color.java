package cs5004.animation.model;

import java.util.Objects;

/**
 * _________________________________ CONCRETE CLASS: Color ________________________________________.
 * The Color class defines colors that can be assigned to Shapes.
 */
public class Color {
  private final int red;
  private final int green;
  private final int blue;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Color color = (Color) o;
    return red == color.red && green == color.green && blue == color.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
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
