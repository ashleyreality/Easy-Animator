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
   */
  public AbstractEvent(IShape shape) {
    this.shape = shape;
  }

  /**
   * Gets the shape being used in this change.
   *
   * @return an IShape being changed
   */
  public IShape getShape() { return shape; }

  public void setEventBegin(int begin) { this.eventBegin = begin; }

  public void setEventEnd(int end) { this.eventBegin = end; }

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
