package cs5004.animator.model;

/**
 * ___________________________________ INTERFACE: IEvent {} _______________________________________.
 * The IEvent interface creates a shape change event.
 */
public interface IEvent {

  /**
   * _________________________________ METHOD: setEventBegin() ____________________________________.
   * Sets the start time of the shape change.
   *
   * @param begin the start time of the change
   * @throws IllegalArgumentException if start tick is negative
   */
  void setEventBegin(int begin);

  /**
   * ___________________________________ METHOD: setEventEnd() ____________________________________.
   * Sets the end time of the shape change.
   *
   * @param end the end time of the change
   * @throws IllegalArgumentException if end tick is negative or less than begin
   */
  void setEventEnd(int end);

  /**
   * _________________________________ METHOD: getEventBegin() ____________________________________.
   * Gets the start time of the shape change.
   *
   * @return the time for when the change started
   */
  int getEventBegin();

  /**
   * _________________________________ METHOD: getEventEnd() ______________________________________.
   * Gets the end time of the shape change.
   *
   * @return the time for when the change stopped
   */
  int getEventEnd();

  /**
   * _________________________________ METHOD: getEventType() _____________________________________.
   * Gets the type of the event.
   *
   * @return the event type enum
   */
  EventType getEventType();

  String getBefore();

  String getAfter();

  //  /**
  //   * The change method creates a changed shape event.
  //   *
  //   * @param shape - the shape you want to change
  //   * @throws IllegalArgumentException if the shape does not exist
  //   */
  //public void change(IShape shape) throws IllegalArgumentException;
}
