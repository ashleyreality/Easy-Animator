package cs5004.animationmodel;

import java.util.List;
import java.util.NavigableMap;

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
  void addEvent(IShape shape, IEvent event, int eventBegin, int eventEnd);

  /**
   * _________________________________ METHOD: getShapeMap() ______________________________________.
   * @param shape - the shape to which the event list is tied, an IShape
   * @return the map of shapes and their associated event lists
   */
  NavigableMap<IShape, List<IEvent>> getShapeMap(IShape shape);

  /**
   * ________________________________ METHOD: getEventList() ______________________________________.
   * @param shape - the shape to which the event list is tied, an IShape
   * @return the list of events
   */
  List<IEvent> getEventList(IShape shape);
}
