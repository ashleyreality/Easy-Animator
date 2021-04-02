/**
 * Use this class to change a shape.
 */
public abstract class AbstractEvent implements IEvent {
  protected IShape shape;
  protected int eventBegin;
  protected int eventEnd;

  public AbstractEvent(IShape shape, int eventBegin, int eventEnd) {
    this.shape = shape;
    this.eventBegin = eventBegin;
    this.eventEnd = eventEnd;
  }

  public IShape getShape() { return shape; }

  public int getEventBegin() {
    return eventBegin;
  }

  public int getEventEnd() {
    return eventEnd;
  }

  public void change() {
    // this method is a stub for now, until we have more information about how changes will work
  }
}
