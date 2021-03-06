package cs5004.animator.model;

/**
 * _________________________________ ABSTRACT: AbstractEvent ______________________________________.
 * This is the AbstractEvent abstract class. It makes changes to a shape.
 */
public abstract class AbstractEvent implements IEvent {
  protected IShape shape;
  protected int eventBegin;
  protected int eventEnd;

  /**
   * ______________________________ CONSTRUCTOR: AbstractEvent() __________________________________.
   * The AbstractEvent() constructor instantiates the declared fields. Constructs a shape change
   * event.
   *
   * @param shape - the shape you want to change
   */
  public AbstractEvent(IShape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape can't be null");
    }
    this.shape = shape;
    // event end can not be 0 or same as event begin
    this.eventEnd = 1;
  }

  /**
   * ____________________________________ METHOD: getShape() ______________________________________.
   * Gets the shape being used in this change.
   *
   * @return the IShape being changed
   */
  public IShape getShape() {
    return shape;
  }

  /**
   * _________________________________ METHOD: setEventBegin() ____________________________________.
   * Sets the start time of the shape change.
   *
   * @param begin the start time of the change
   * @throws IllegalArgumentException if start tick is negative
   */
  public void setEventBegin(int begin) {
    if (begin < 0) {
      throw new IllegalArgumentException("Event begin tick can't be negative");
    }
    this.eventBegin = begin;
  }

  /**
   * ___________________________________ METHOD: setEventEnd() ____________________________________.
   * Sets the end time of the shape change.
   *
   * @param end the end time of the change
   * @throws IllegalArgumentException if end tick is negative or less than begin
   */
  public void setEventEnd(int end) {
    if (end <= 0 || end < this.eventBegin) {
      throw new IllegalArgumentException("Event end tick can't be negative or before the "
              + "event begins");
    }
    this.eventEnd = end;
  }

  /**
   * _________________________________ METHOD: getEventBegin() ____________________________________.
   * Get the start time of the shape change.
   *
   * @return the time for when the change started
   */
  public int getEventBegin() {
    return eventBegin;
  }

  /**
   * _________________________________ METHOD: getEventEnd() ______________________________________.
   * Get the end time of the shape change.
   *
   * @return the time for when the change stopped
   */
  public int getEventEnd() {
    return eventEnd;
  }

  /**
   * ____________________________________ METHOD: change() ________________________________________.
   *
   * @param shape - the shape you want to change
   * @throws IllegalArgumentException if the shape does not exist
   */
  public void change(IShape shape) throws IllegalArgumentException {
    // this method is a stub for now, until we have more information about how changes will work
  }


  // linear interp
  protected double tweening(int tick, double fromValue, double toValue) {

    double valueDelta = toValue - fromValue;
    double timeDelta  = this.eventEnd - this.eventBegin;

    double interp = (tick - this.eventBegin) / timeDelta;
    // Number between 0 and 1: 1 means finished, 0 means begin, 0.5 means halfway, etc.
    // Clamp between 0 and 1, in case tick is outside bounds of the event
    interp = Math.max(Math.min(interp, 1), 0);
    return (interp * valueDelta) + fromValue;
  }
}
