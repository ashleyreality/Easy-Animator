/**
 * The IEvent interface creates a shape change event.
 */
public interface IEvent {

  /**
   * The change method creates a changed shape event.
   *
   * @param shape - the shape you want to change
   * @throws IllegalArgumentException if the shape does not exist
   */
  public void change(IShape shape) throws IllegalArgumentException;
}
