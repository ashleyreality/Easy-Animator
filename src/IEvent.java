/**
 * The IEvent interface creates a shape change event.
 */
public interface IEvent {

  public void setEventBegin(int begin);

  public void setEventEnd(int end);

  public int getEventBegin();

  public int getEventEnd();

  /**
   * The change method creates a changed shape event.
   *
   * @param shape - the shape you want to change
   * @throws IllegalArgumentException if the shape does not exist
   */
  public void change(IShape shape) throws IllegalArgumentException;
}
