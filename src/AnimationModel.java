import java.awt.geom.Point2D;

/**
 * This interface represents an animation. It includes all the methods an animation model should
 * offer.
 */
public interface AnimationModel {

  /**
   * Gets a string with the name, type, location, size, and color of the shapes at that tick.
   *
   * @param tick - the frame in the animation
   * @return a string representation of the shapes at the given tick
   */
  String getShapesAtTick(int tick);

  /**
   * Creates a shape with the given parameters.
   *
   * @param name - the name of the shape
   * @param color - the color of the shape
   * @param type - the type of shape
   * @param location - the location of the shape
   * @param appear - the tick when the shape appears
   * @param disappear - the tick when the shape disappears
   */
  void addShape(String name, Color color, Shape type,
                          Point2D location, int appear, int disappear);


  void addColorChange(Shape shape, Color newColor, int startChange, int endChange);

  void addSizeChange(Shape shape, double newParam1, double newParam2, int startChange, int endChange);

  void addMove(Shape shape, Point2D moveTo, int startChange, int endChange);


}
