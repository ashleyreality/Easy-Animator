/**
 * This interface represents an animation. It includes all the methods an animation model should
 * offer.
 */
public interface AnimationModel {
  /**
   * Gets a string with the name, type, location, size, and color of the shapes at that tick.
   * @param tick the frame in the animation
   * @return a string representation of the shapes at the given tick
   */
  public String getShapesAtTick(int tick);

  public void createShape();
}
