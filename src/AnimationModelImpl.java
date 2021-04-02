import java.util.ArrayList;
import java.util.List;

/**
 * __________________INTERFACE IMPLEMENTATION CLASS: AnimationModelImpl {} ________________________.
 * This is the AnimationModelImpl class. It implements the method declarations of the AnimationModel
 * interface.
 */
public class AnimationModelImpl implements IAnimationModel {
  private List<IShape> shapes;
  private List<IEvent> changes;



  /**
   * ___________________________ CONSTRUCTOR: AnimationModelImpl() ________________________________.
   * The AnimationModelImpl() constructor instantiates the declared fields.
   */
  public AnimationModelImpl() {
    shapes = new ArrayList<>();
    changes = new ArrayList<>();
  }



  /**
   * ____________________________________ METHOD: addShape() ______________________________________.
   * The addShape() method adds a new "Shape" to the ArrayList of type "Shape", shapes, with the
   * given parameters.
   *
   * @param shape     - the shape (has a name, color, location, appear, disappear), an IShape
   * @param appear    - the tick when the shape appears, an int
   * @param disappear - the tick when the shape disappears, an int
   * @throws IllegalArgumentException if appear time is greater than disappear time, or is negative
   * @throws IllegalArgumentException if a duplicate of the Shape name already exists in the list
   */
  @Override
  public void addShape(IShape shape, int appear, int disappear) {
    if (appear > disappear || appear < 0) {
      throw new IllegalArgumentException("The appear time must be greater than the disappear"
              + " time, and neither can be a negative integer value.");
    }

    if (nameMatch(shape)) {
      throw new IllegalArgumentException("Each shape name must be unique.");
    }

    // Pass in the provided appear time into the setter setAppear() and the provided disappear time
    // into the setter setDisappear() methods which exist in the provided shape instance of IShape.
    shape.setAppear(appear);
    shape.setDisappear(disappear);

    // Append the provided shape instance of IShape to the ArrayList of IShape types, shapes.
    shapes.add(shape);
  }



  /**
   * ____________________________________ METHOD: nameMatch() _____________________________________.
   * This is a helper method that iterates through the ArrayList of "Shapes", shapes, and checks
   * whether two shapes have the same name.
   * @return true if the ArrayList of Shapes contains duplicate Shape names
   */
  private boolean nameMatch(IShape shape) {
    // Create a new list of type String
    List<String> names = new ArrayList<>();
    // For each "Shape" that exists in the list of shapes
    for (IShape s : shapes) {
      // Iterate through the list of "Shapes"
      // Get the name of the shape & append it to the new list of Strings
      names.add(s.getName());
    // If a given name in the list of "Shapes" DOES match the provided shape name
    // of Strings, return true
    for (String eachName : names) {
      return shape.getName().equals(eachName);
      }
    }
    return false;
  }



  /**
   * ____________________________________ METHOD: addEvent() _____________________________________.
   * The addEvent() method adds a new "Event"/change to the ArrayList of type "Event", changes,
   * with the given parameters.
   * An "Event" has a: "Shape", eventBegin, eventEnd
   *
   * @param event       - the change made to the shape, an IEvent
   * @param eventBegin  - the time in ticks when the event begins, an int
   * @param eventEnd    - the time in ticks when the event ends, an int
   */
  @Override
  public void addEvent(IEvent event, int eventBegin, int eventEnd) {

    // Pass in the provided eventBegin time into the setter setEventBegin() and the provided
    // eventEnd time into the setter setEventEnd() time which exist in the provided event instance
    // of IEvent.
    event.setEventBegin(eventBegin);
    event.setEventEnd(eventEnd);

    // Append the provided event instance of IEvent to the ArrayList of IEvent types, changes.
    changes.add(event);
  }



  /**
   * ____________________________________ METHOD: toString() ______________________________________.
   *
   * @return the String form of the lists declared and instantiated within this class.
   */
  @Override
  public String toString() {
    // new StringBuilder
    StringBuilder sb = new StringBuilder();

    // add the shapes to sb
    sb.append("Shapes:\n");
    for (IShape shape : shapes) {
      sb.append(shape.toString()).append("\n");
    }

    // add the changes to sb
    for (IEvent change : changes) {
      sb.append(change.toString()).append("\n");
    }

    return sb.toString();
  }



  /**
   * _________________________________ STUB: getShapesAtTick() ____________________________________.
   * This method getShapesAtTick() is a stub. It does not get implemented in this model. This
   * method will be implemented in the controller, instead. "The return type will be some form of a
   * collection of shapes, as hinted by your... description that it "gets all the shapes." -
   * Freifeld* * Look ups by ticks in real time. Consider a map instead... a navigable map, the tree
   * map class in java implements the navigable map
   */
  public String getShapesAtTick(int tick) {
    return null; // this is a stub & will be impl later.
    /*
        List<String> tickShapes = shapes.stream()
                .filter(n -> n.getAppear() < tick && tick < n.getDisappear())
                .map(Object::toString)
                .collect(Collectors.toList());

        // create new list (shapesAtTick)
        // iterate through the list of shapes
        // for each shape, check if appear < tick < disappear
        // this is a filter
        // if true, add shape to new list
        changes.stream().filter(n -> n.getChangeBegin() < tick && tick < n.getChangeEnd())
                .forEach(ChangeShape::change);
        // iterate through the list of changes
        // for each shape, check if startChange < tick < endChange
        // if true, apply change to shape
        // return list
        // Jen
        return tickShapes.toString();
      }
     */
  }
}
