import java.awt.geom.Point2D;

/**
 * This is the AnimationModel interface. It represents an animation. It includes all the methods an
 * animation model should offer.
 */
public interface AnimationModel {

//  /**
//   * Gets a string with the name, type, location, size, and color of the shapes at that tick.
//   *
//   * @param tick - the frame in the animation
//   * @return a string representation of the shapes at the given tick
//   */
//  String getShapesAtTick(int tick);

  /**
   * _____________________________________ METHOD: addShape() _____________________________________.
   * The addShape() method creates a shape with the given parameters.
   *
   * @param name      - the name of the shape, a String
   * @param color     - the color of the shape, an enumeration
   * @param type      - the type of shape, an enumeration
   * @param location  - the location of the shape, a Point2D
   * @param width    - the width or X radius of the shape, an int
   * @param height    - the height or Y radius of the shape, an int
   * @param appear    - the tick when the shape appears, an int
   * @param disappear - the tick when the shape disappears, an int
   */
  void addShape(String name, Color color, ShapeType type, Point2D location, double width,
                double height, int appear, int disappear);


  /**
   * _________________________________ METHOD: addColorChange() ___________________________________.
   * The addColorChange() method *** mutates? *** the color of a given shape.
   *
   * @param name        - the name of the shape, a String
   * @param newColor    - the new color of the shape, an enumeration
   * @param startChange - the *** tick *** at which the color of the shape changes.
   * @param endChange   - the *** tick *** at which the color of the shape
   */
  void addColorChange(String name, Color newColor, int startChange, int endChange);

  /**
   * _________________________________ METHOD: addSizeChange() ___________________________________.
   * The addSizeChange() method *** mutates? *** the size of a given shape.
   *
   * @param name        - the name of the shape, a String
   * @param newWidth    - the new width of the shape, a double
   * @param newHeight   - the new height of the shape, a double
   * @param startChange - the *** tick *** at which the color of the shape changes.
   * @param endChange   - the *** tick *** at which the color of the shape
   */
  void addSizeChange(String name, double newWidth, double newHeight, int startChange,
                     int endChange);

  /**
   * ____________________________________ METHOD: addMove() _______________________________________.
   * The addMove() method *** mutates? *** the location of a given shape.
   *
   * @param name        - the name of the shape, a String
   * @param moveTo      - the coordinates of where the shape is moving to, a Point2D
   * @param startChange - the *** tick *** at which the color of the shape changes.
   * @param endChange   - the *** tick *** at which the color of the shape
   */
  void addMove(String name, Point2D moveTo, int startChange, int endChange);
}
