import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * __________________INTERFACE IMPLEMENTATION CLASS: AnimationModelImpl {} ________________________.
 * This is the AnimationModelImpl class. It implements the method declarations of the AnimationModel
 * interface.
 */
public class AnimationModelImpl implements IAnimationModel {
  private static final Scanner object = new Scanner(System.in);
  private List<IShape> shapes;
  private List<AbstractEvent> changes;

  // Notes:
  // HW 7 -- we will need to impl getShapesAtTick() where we are given a tick, & we have to determine
  // what the screen looks like at that tick. Our data should show us what that description.
  // All the change methods mutate the shape list and add to an "event list" (which is kind of what
  // changes is)
  // Reconsider storing appear and disappear in shapes - might run into issues later

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
   * The addShape() method adds a shape with the given parameters.
   *
   * @param shape     - the shape, an IShape
   * @param appear    - the tick when the shape appears, an int
   * @param disappear - the tick when the shape disappears, an int
   */
  @Override
  public void addShape(IShape shape, int appear, int disappear) {
    // send information to correct Shape class
    // names must be unique -- throw an error if name is not unique
    // add return to shapes list
    // Aya

    // (Each "Shape" has a name, color, location, appear, disappear)
    // For each element/index in the list of shapes
    for (int index = 0; index < shapes.size(); index++) {
      // If duplicate Shape names exist in the list of shapes
      if (nameMatch()) {
        // Throw an error
        throw new IllegalArgumentException("Each shape name must be unique.");
        // If no duplicate Shape names exist in the list of shapes...
        // Append the Shape to the list
      } else {
        // Get the element for each index in the list of "Shapes"
        //shapes.add(object.next());
        IShape eachShape = shapes.get(index);
        // Append the "Shape" to the list of "Shapes"
        shapes.add(eachShape);
      }
    }
  }

  /**
   * ____________________________________ METHOD: nameMatch() _____________________________________.
   * This is a helper method that iterates through the ArrayList of IShapes, shapes, and checks
   * whether two shapes have the same name.
   * @return true if the ArrayList of Shapes contains duplicate Shape names
   */
  private boolean nameMatch() {
    // Create a new list of type String
    List<String> newList = new ArrayList<String>();
    // For each "Shape" that exists in the list of shapes of type "Shape"
    // (each "Shape" has a name, color, location, appear, disappear)
    for (IShape eachShape : shapes) {
      // Iterate through the list of "Shapes"
      // Get the name of the IShape & append it to the new list of Strings
      newList.add(eachShape.getName());
      // If a given name in the list of "Shapes" DOES match any of the names in the list
      // of Strings, return true
      for (String eachName : newList) {
        return eachShape.getName().equals(eachName);
      }
    }
    return false;
  }

  /**
   *
   * @param event the change of the shape
   * @param eventBegin
   * @param eventEnd
   */
  @Override
  public void addEvent(IEvent event, int eventBegin, int eventEnd) {

  }

  /**
   * _________________________________ METHOD: addColorChange() ___________________________________.
   *
   * @param name        - the name of the shape, a String
   * @param newColor    - the new color of the shape, an enumeration
   * @param startChange - the *** tick *** at which the color of the shape changes, an int
   * @param endChange   - the *** tick *** at which the color of the shape, an int
   */
  @Override
  public void addColorChange(String name, Color newColor, int startChange, int endChange) {
    // get shape from list

    // send information to ChangeColor
    // add return to changes list
    // Aya
  }

  /**
   * _________________________________ METHOD: addSizeChange() ____________________________________.
   *
   * @param name        - the name of the shape, a String
   * @param newWidth    - the new width of the shape, a double
   * @param newHeight   - the new height of the shape, a double
   * @param startChange - the *** tick *** at which the color of the shape changes, an int
   * @param endChange   - the *** tick *** at which the color of the shape, an int
   */
  @Override
  public void addSizeChange(String name, double newWidth, double newHeight, int startChange, int endChange) {
    // get shape from list
    IShape shape = null;
    for (IShape s : shapes) {
      if (s.getName().equals(name)) {
        shape = s;
      }
    }
    if (shape == null) {
      throw new IllegalArgumentException("Shape not found in animation!");
    }

    // send information to ScaleShape
    ScaleShape scaled = new ScaleShape(shape, startChange, endChange, shape.getWidth(), newWidth,
            shape.getHeight(), newHeight);

    // add return to changes list
    changes.add(scaled);
  }

  /**
   * ____________________________________ METHOD: addMove() _______________________________________.
   *
   * @param name        - the name of the shape, a String
   * @param moveTo      - the coordinates of where the shape is moving to, a Point2D
   * @param startChange - the *** tick *** at which the color of the shape changes, an int
   * @param endChange   - the *** tick *** at which the color of the shape, an int
   */
  @Override
  public void addMove(String name, Point2D moveTo, int startChange, int endChange) {
    // get shape from list
    IShape shape = null;
    for (IShape s : shapes) {
      if (s.getName().equals(name)) {
        shape = s;
      }
    }
    if (shape == null) {
      throw new IllegalArgumentException("Shape not found in animation!");
    }
    // send information to MoveShape
    MoveShape moved = new MoveShape(shape, startChange, endChange, shape.getLocation(), moveTo);

    // add return to changes list
    changes.add(moved);
  }

  /**
   * ____________________________________ METHOD: toString() ______________________________________.
   *
   * @return the String form of the lists declared and instantiated within this class.
   */
  @Override
  public String toString() {

    System.out.println(shapes);
    // new StringBuilder
    StringBuilder sb = new StringBuilder();

    // add the shapes to sb
    sb.append("Shapes:\n");
    for (IShape shape : shapes) {
      sb.append(shape.toString()).append("\n");
    }

    // add the changes to sb
    for (AbstractEvent change : changes) {
      sb.append(change.toString()).append("\n");
    }

    return sb.toString();
  }


  /**
   * This method getShapesAtTick() should be a stub. It does not get implemented in this model. This
   * method will be implemented in the controller, instead. "The return type will be some form of a
   * collection of shapes, as hinted by your... description that it "gets all the shapes." -
   * Freifeld* * Look ups by ticks in real time. Consider a map instead... a navigable map, the tree
   * map class in java implements the navigable map
   */
  public String getShapesAtTick(int tick) {
    return null; // this is a stub & will be impl later.

//    List<String> tickShapes = shapes.stream()
//            .filter(n -> n.getAppear() < tick && tick < n.getDisappear())
//            .map(Object::toString)
//            .collect(Collectors.toList());
//
//    // create new list (shapesAtTick)
//    // iterate through the list of shapes
//    // for each shape, check if appear < tick < disappear
//    // this is a filter
//    // if true, add shape to new list
//    changes.stream().filter(n -> n.getChangeBegin() < tick && tick < n.getChangeEnd())
//            .forEach(ChangeShape::change);
//    // iterate through the list of changes
//    // for each shape, check if startChange < tick < endChange
//    // if true, apply change to shape
//    // return list
//    // Jen
//    return tickShapes.toString();
//  }
  }
}
