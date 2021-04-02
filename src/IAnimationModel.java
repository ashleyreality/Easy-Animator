import java.awt.geom.Point2D;

/**
 * _______________________________ INTERFACE: AnimationModel {} ___________________________________.
 * This is the AnimationModel interface. It represents an animation. It includes all the methods an
 * animation model should offer. The client/end-user will use this interface. The client will see
 * and rely on anything that is public so once it is in the interface it is locked in and can not
 * be changed.
 */
public interface IAnimationModel {

  /**
   * _____________________________________ METHOD: addShape() _____________________________________.
   * The addShape() method adds a shape with the given parameters.
   *
   * @param shape     - the shape, an IShape
   * @param appear    - the tick when the shape appears, an int
   * @param disappear - the tick when the shape disappears, an int
   */
  void addShape(IShape shape, int appear, int disappear);

  /**
   * _____________________________________ METHOD: addEvent() _____________________________________.
   * The addEvent() method adds an event/shape change with the given parameters.
   *
   * @param event         - the changes being done to the shape
   * @param eventBegin    - the start of when the changes to the shape occur
   * @param eventEnd      - the end of the changes to the shape
   */
  void addEvent(IEvent event, int eventBegin, int eventEnd);
}
