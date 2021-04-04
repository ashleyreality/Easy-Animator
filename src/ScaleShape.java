import java.util.List;
import java.util.NavigableMap;

/**
 * ______________________________ CONCRETE CLASS: ScaleShape ______________________________________.
 * The ScaleShape class extends ChangeShape. Use it to scale a shape from one size to another size.
 */
public class ScaleShape extends AbstractEvent {
  protected double widthBefore;
  protected double widthAfter;
  protected double heightBefore;
  protected double heightAfter;
  protected IShape shape;

  /**
   * _______________________________ CONSTRUCTOR: ScaleShape() ____________________________________.
   * The ScaleShape() constructor instantiates the declared fields. Generates a scaled shape.
   *
   * @param shape         - the shape to be scaled
   * @param widthBefore   - the previous width of the shape
   * @param heightBefore  - the previous height of the shape
   * @param widthAfter    - the new width of the shape
   * @param heightAfter   - the new height of the shape
   */
  public ScaleShape(IShape shape,
                    double widthBefore, double heightBefore,
                    double widthAfter, double heightAfter) {
    super(shape);

    if (widthAfter <= 0 || heightAfter <= 0 || widthBefore <= 0 || heightBefore <= 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }

    //    NavigableMap<IShape, List<IEvent>> shapeMap = shape.getShapeMap(shape);
    //    List<IEvent> eventList = shapeMap.getEventList(shape);
    //
    //    for (IEvent otherEvent : eventList) {
    //      if (event.equals(otherEvent)) {
    //        if (eventBegin <= otherEvent.getEventBegin()
    //                && otherEvent.getEventBegin() <= eventEnd
    //                || otherEvent.getEventBegin() <= eventBegin
    //                && eventEnd <= otherEvent.getEventEnd()
    //                || eventBegin <= otherEvent.getEventEnd()
    //                && otherEvent.getEventEnd() <= eventEnd) {
    //          throw new IllegalArgumentException("The shape can not have a change of the same type " +
    //                  "overlap in terms of event time.");
    //        }
    //      }
    //    }

    this.widthBefore = widthBefore;
    this.widthAfter = widthAfter;
    this.heightBefore = heightBefore;
    this.heightAfter = heightAfter;
  }

  /**
   * _________________________________ METHOD: toString() _________________________________________.
   * This is a toString() override.
   *
   * @return a string that describes the size of a shape
   */
  @Override
  public String toString() {
    // "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70
    return "Shape " + this.getShape().getName()
            + " scales from Width: " + String.format("%.1f", this.widthBefore)
            + ", Height: " + String.format("%.1f", this.heightBefore)
            + " to Width: " + String.format("%.1f", this.widthAfter) + ", Height: "
            + String.format("%.1f", this.heightAfter)
            + " from t=" + this.getEventBegin() + " to t=" + this.getEventEnd();
  }

  public EventType getEventType() {
    return EventType.SCALE;
  }
}