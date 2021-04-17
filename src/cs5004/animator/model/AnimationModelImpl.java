package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * _______________________ INTERFACE IMPLEMENTATION: AnimationModelImpl ___________________________.
 * The AnimationModelImpl class implements the method declarations of the AnimationModel
 * interface.
 */
public class AnimationModelImpl implements IAnimationModel {
  private NavigableMap<IShape, List<IEvent>> shapeMap;
  private int boundsX;
  private int boundsY;
  private int boundsWidth;
  private int boundsHeight;

  /**
   * ___________________________ CONSTRUCTOR: AnimationModelImpl() ________________________________.
   * The AnimationModelImpl() constructor instantiates the declared field. A TreeMap stores a key
   * Shape and its associated value, a list of Events.
   */
  public AnimationModelImpl() {
    shapeMap = new TreeMap<>();
  }

  /**
   * ____________________________________ METHOD: addShape() ______________________________________.
   * The addShape() method adds a new "Shape" and its given parameters to the animation.
   *
   * @param shape     - the shape (has a name, color, location, appear, disappear), an IShape
   * @param appear    - the tick when the shape appears, an int
   * @param disappear - the tick when the shape disappears, an int
   * @throws IllegalArgumentException if the shape is null
   * @throws IllegalArgumentException if appear time is greater than disappear time, or is negative
   * @throws IllegalArgumentException if a duplicate of the Shape name already exists in the list
   */
  @Override
  public void addShape(IShape shape, int appear, int disappear) {

    shapeNullException(shape);

    if (appear >= disappear || appear < 0) {
      throw new IllegalArgumentException("The appear time must be greater than the disappear"
              + " time, and neither can be a negative integer value.");
    }

    if (nameMatch(shape)) {
      throw new IllegalArgumentException("Each shape name must be unique.");
    }

    // Send appear and disappear times to the shape
    shape.setAppear(appear);
    shape.setDisappear(disappear);

    // Append the shape to the list along with an empty list of changes
    shapeMap.put(shape, new ArrayList<>());
  }

  /**
   * ____________________________________ METHOD: addShape() ______________________________________.
   * The addShape() method adds a new "Shape" and its given parameters to the animation.
   *
   * @param shape - the shape (has a name, color, location, appear, disappear), an IShape
   * @throws IllegalArgumentException if the shape is null
   * @throws IllegalArgumentException if a duplicate of the Shape name already exists in the list
   */

  @Override
  public void addShape(IShape shape) {
    shapeNullException(shape);

    if (nameMatch(shape)) {
      throw new IllegalArgumentException("Each shape name must be unique.");
    }

    shapeMap.put(shape, new ArrayList<>());
  }

  /**
   * ____________________________________ METHOD: nameMatch() _____________________________________.
   * This is a helper method that iterates through the ArrayList of "Shapes", shapes, and checks
   * whether two shapes have the same name.
   *
   * @return true if the ArrayList of Shapes contains duplicate Shape names
   * @throws IllegalArgumentException if the shape is null
   */
  private boolean nameMatch(IShape shape) {
    // check to make sure shape isn't null
    shapeNullException(shape);

    // For each key/shape in the map of shapes, return true if
    // the shape's name equals the new shape's name
    for (IShape s : shapeMap.keySet()) {
      if (s.getName().equals(shape.getName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * ____________________________________ METHOD: addEvent() _____________________________________.
   * The addEvent() method adds a new "Event"/change to the ArrayList of type "Event", changes, with
   * the given parameters. An "Event" has a:
   *
   * @param event      - the change made to the shape (has a "Shape", eventBegin, eventEnd), an
   *                   IEvent
   * @param eventBegin - the time in ticks when the event begins, an int
   * @param eventEnd   - the time in ticks when the event ends, an int
   * @throws IllegalArgumentException if the shape is null
   * @throws IllegalArgumentException if event start time is greater than its end, or is negative
   */
  @Override
  public void addEvent(IShape shape, IEvent event, int eventBegin, int eventEnd) {
    // check to make sure shape isn't null
    shapeNullException(shape);

    // check to make sure event isn't null
    if (event == null) {
      throw new IllegalArgumentException("Event can't be null.");
    }

    // check to make sure event time makes sense
    if (eventEnd <= eventBegin || eventBegin < 0) {
      throw new IllegalArgumentException("The event begin time must be greater than the event end"
              + " time, and neither can be a negative integer value.");
    }

    if (eventBegin < shape.getAppear() || eventBegin > shape.getDisappear()
            || eventEnd < shape.getAppear() || eventEnd > shape.getDisappear()) {
      throw new IllegalArgumentException("The event can't begin before the shape appears or"
              + " after it disappears. The event can't end before the shape appears or after"
              + " it disappears. Try to add the event again.\n The shape appears at "
              + shape.getAppear() + " and disappears at " + shape.getDisappear() + "\n The "
              + " event begins at " + eventBegin + "and ends at " + eventEnd);
    }

    // Iterate through the event list
    // Check for matches that exist in the list for the type of change being passed into addEvent()
    // If there is a match...
    // Check that the other change of the same type is not between this change's event times
    // If it is...
    // Throw an exception
    // If it is not...
    // Add this change to the event list

    // A list of shapes in the shapeMap
    Set<IShape> shapeSet = shapeMap.keySet();
    List<IShape> shapeList = new ArrayList<>(shapeSet);

    // A list of events for the provided shape
    List<IEvent> eventList = shapeMap.get(shape);
    if (shapeMap.get(shape) == null) {
      throw new IllegalArgumentException("The shape is not in the list.");
    }

    // I dont think event names need to be unique -  discuss today
    /*for (IEvent otherEvent : eventList) {
      if (event.equals(otherEvent)) {
        throw new IllegalArgumentException("Each event name must be unique.");
      }
    }*/

    // Need to add a check for:
    // If event TYPE is the same
    // B/c if event type is the same then it falls under this exception

    // for each shape that's already in the shape list
    for (IShape otherShape : shapeList) {
      // if the shape we passed in has the same name as the shape in the list
      if (shape.getName().equals(otherShape.getName())) {
        // for each event that's in the list of events
        for (IEvent otherEvent : eventList) {
          // if the type of the event we passed in is the same type as the event in the list
          if (event.getEventType() == otherEvent.getEventType()
                  // AND this event begins before the other event begins
                  && (eventBegin < otherEvent.getEventBegin()
                  // AND the other event begins before this event ends
                  && otherEvent.getEventBegin() < eventEnd
                  // OR the other event begins before this event begins
                  || otherEvent.getEventBegin() < eventBegin
                  // AND this event ends before the other event ends
                  && eventEnd < otherEvent.getEventEnd()
                  // OR this event begins before the other event ends
                  || eventBegin < otherEvent.getEventEnd()
                  // AND the other event ends before this event ens
                  && otherEvent.getEventEnd() < eventEnd)) {
            //|| otherEvent.getEventBegin() <= eventBegin
            //&& otherEvent.getEventEnd() <= eventEnd) {
            throw new IllegalArgumentException("Events of the same type can't happen at the same \n"
                    + "time. Try again with a different event time. Event type was \n"
                    + event.getEventType() + ", event begin was " + eventBegin + ", and event \n"
                    + "end was " + eventEnd + ". Other event begin was \n"
                    + otherEvent.getEventBegin() + " and other event end was " +
                    otherEvent.getEventEnd());
          }

        }
      }
    }
    // Send the begin and end times to the event
    event.setEventBegin(eventBegin);
    event.setEventEnd(eventEnd);
    // Add the event to the shape it acts on
    eventList.add(event);
  }


  /**
   * _________________________________ METHOD: getShapeMap() ______________________________________.
   *
   * @return the map of shapes and their associated event lists
   */
  @Override
  public NavigableMap<IShape, List<IEvent>> getShapeMap() {
    // A list of events for the provided shape
    return this.shapeMap;
  }

  /**
   * _________________________________ METHOD: getShape() ______________________________________.
   *
   * @param name - the name of the shape you want to get
   * @return the IShape associated with that name
   */

  public IShape getShape(String name) {
    IShape n = null;
    for (IShape s : shapeMap.keySet()) {
      if (s.getName().equals(name)) {
        n = s;
      }
    }
    //if (n == null) {
    //  throw new IllegalArgumentException("Shape name not present in the list of shapes");
    //}
    return n;
  }


  /**
   * ________________________________ METHOD: addBounds() ______________________________________.
   * Add the bounds of the animation's canvas.
   *
   * @param x the x location
   * @param y the y location
   * @param width the width of the canvas
   * @param height the height of the canvas
   */
  public void addBounds(int x, int y, int width, int height) {
    this.boundsX = x;
    this.boundsY = y;
    this.boundsWidth = width;
    this.boundsHeight = height;
  }

  /**
   * ________________________________ METHOD: getBoundsX() ______________________________________.
   * Gets the boundsX location.
   *
   * @return the boundsX location
   */
  public int getBoundsX() {
    return boundsX;
  }

  /**
   * ________________________________ METHOD: getBoundsY() ______________________________________.
   * Get the boundsY location.
   *
   * @return the boundsY location
   */
  public int getBoundsY() {
    return boundsY;
  }

  /**
   * ________________________________ METHOD: getBoundsWidth() ____________________________________.
   * Get the width of the canvas.
   *
   * @return the width of the canvas
   */
  public int getBoundsWidth() {
    return boundsWidth;
  }

  /**
   * ________________________________ METHOD: getBoundsHeight() ___________________________________.
   * Get the height of the canvas.
   *
   * @return the height of the canvas
   */
  public int getBoundsHeight() {
    return boundsHeight;
  }

  /**
   * ________________________________ METHOD: getEventList() ______________________________________.
   * Get the event list.
   *
   * @param shape the shape to which the event list is tied, an IShape
   * @return the list of events
   */
  @Override
  public List<IEvent> getEventList(IShape shape) {
    // A list of events for the provided shape
    return shapeMap.get(shape);
  }


  /**
   * ______________________________ METHOD: shapeNullException() __________________________________.
   * Throws a null exception.
   *
   * @param shape the shape being checked
   * @throws IllegalArgumentException if the shape is null
   */
  private void shapeNullException(IShape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape can't be null.");
    }
  }


  /**
   * ___________________________________ METHOD: toString() ______________________________________.
   * Returns a string representation of the model.
   *
   * @return the String form of the lists declared and instantiated within this class.
   */
  @Override
  public String toString() {

    // New StringBuilder
    StringBuilder sb = new StringBuilder();

    // Add the shapes to the StringBuilder, sb
    sb.append("Shapes:\n");
    List<String> s = shapeMap.keySet().stream().map(Object::toString).collect(Collectors.toList());
    for (String l : s) {
      sb.append(l);
      sb.append("\n");
    }

    // Sort the events in terms of begin & end time
    Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);

    // Create a list of sorted events
    List<IEvent> t = shapeMap.values().stream().flatMap(Collection::stream)
            .sorted(sortByEventBegin).collect(Collectors.toList());

    // Add the changes/events to sb
    for (IEvent a : t) {
      sb.append(a.toString());
      sb.append("\n");
    }

    return sb.toString();
  }


  /**
   * ______________________________ METHOD: getShapesAtTick() ____________________________________.
   * Returns a list of shapes and their states at a given tick in the animation.
   *
   * @return a list of shapes at a given tick
   */
  public List<IShape> getShapesAtTick(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("No negative ticks.");
    }
    // note from TA: consider doing this in order of transformations possibly,
    // rather than order of shapes
    List<IShape> shapesAtTick = new ArrayList<>();
    for (IShape shape : shapeMap.keySet()) {
      if (shape.getAppear() <= tick && shape.getDisappear() >= tick) {
        IShape newShape = shape.copy();
        shapesAtTick.add(newShape);
        for (IEvent event : this.getEventList(shape)) {
          if (event.getEventBegin() <= tick) {
            event.applyEvent(newShape, tick);
            // apply any event that has occurred in the past, even if not currently active
            // so it applies previous animation to the current state/shape's events
            // rather than skipping rows
          }
        }
      }
    }
    return shapesAtTick;
  }

}

