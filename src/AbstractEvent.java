/**
 * Use this class to change a shape.
 */
public abstract class AbstractEvent implements IEvent {
  protected IShape shape;
  protected int eventBegin;
  protected int eventEnd;

  /**
   * Constructs a shape change event.
   *
   * @param shape - the shape you want to change
   * @param eventBegin - the time in ticks when the event begins
   * @param eventEnd - the time in ticks when the event ends
   */
  public AbstractEvent(IShape shape, int eventBegin, int eventEnd) {
    this.shape = shape;
    this.eventBegin = eventBegin;
    this.eventEnd = eventEnd;
  }

  /**
   * Gets the shape being used in this change.
   *
   * @return an IShape being changed
   */
  public IShape getShape() { return shape; }

  public int getEventBegin() {
    return eventBegin;
  }

  public int getEventEnd() {
    return eventEnd;
  }

  public void change(IShape shape) throws IllegalArgumentException {
    // this method is a stub for now, until we have more information about how changes will work
  }
}
