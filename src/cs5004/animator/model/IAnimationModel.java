package cs5004.animator.model;

import java.util.List;
import java.util.Map;

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
   * _____________________________________ METHOD: addShape() _____________________________________.
   * The addShape() method adds a shape with the given parameters.
   *
   * @param shape     - the shape, an IShape
   */
  void addShape(IShape shape);

  /**
   * _____________________________________ METHOD: addEvent() _____________________________________.
   * The addEvent() method adds an event/shape change with the given parameters.
   *
   * @param event         - the changes being done to the shape
   * @param eventBegin    - the start of when the changes to the shape occur
   * @param eventEnd      - the end of the changes to the shape
   */
  void addEvent(IShape shape, IEvent event, int eventBegin, int eventEnd);

  /**
   * _________________________________ METHOD: getShapeMap() ______________________________________.
   * This is a stub for now. Might be useful later.

   * @return the map of shapes and their associated event lists
   */
  Map<IShape, List<IEvent>> getShapeMap();

  /**
   * ________________________________ METHOD: getEventList() ______________________________________.
   * This is a stub for now. Might be useful later.
   * @param shape - the shape to which the event list is tied, an IShape
   * @return the list of events
   */
  List<IEvent> getEventList(IShape shape);

  /**
   * _______________________________ METHOD: getShapesAtTick() ____________________________________.
   *
   * @param tick the frame
   * @return TBD
   */
  List<IShape> getShapesAtTick(int tick);

  /**
   * ___________________________________ METHOD: getShape() _______________________________________.
   *
   * @param name the name of the shape, a String
   * @return the shape, an IShape
   */
  IShape getShape(String name);

  void addBounds(int x, int y, int width, int height);

  int getBoundsX();

  int getBoundsY();

  int getBoundsHeight();

  int getBoundsWidth();

  /**
   * Gets the last tick in the model.
   *
   * @return the ending tick for the animation
   */
  int getEndTick();

  /**
   * Sets the last tick in the model.
   *
   * @param endTick the latest tick in the animation
   */
  void setEndTick(int endTick);
}
