import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the AnimationModelImpl class. It implements the method declarations of the AnimationModel
 * interface.
 */
public class AnimationModelImpl implements AnimationModel {
  private List<Shape> shapes;
  private List<ChangeShape> changes;

  /*
  all the change methods mutate the shape list
  and add to an "event list" (which is kind of what changes is)

  reconsider storing appear and disappear in shapes - might run into issues later
   */

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
   * @param name      - the name of the shape, a String
   * @param color     - the color of the shape, an enumeration
   * @param type      - the type of shape, an enumeration
   * @param location  - the location of the shape, a Point2D
   * @param width    - the width or X radius of the shape, an int
   * @param height    - the height or Y radius of the shape, an int
   * @param appear    - the tick when the shape appears, an int
   * @param disappear - the tick when the shape disappears, an int
   */
  @Override
  public void addShape(String name, Color color, ShapeType type, Point2D location, double width,
                       double height, int appear, int disappear) {
    // send information to correct Shape class
    // names must be unique -- throw an error if name is not unique
    // add return to shapes list
    // Aya

    for (int index = 0; index < shapes.size(); index++) {
      // for each item in the list of shapes, if a name exists more than once is true
      if (nameMatch()) {
        // throw an error
        throw new IllegalArgumentException("Each shape name must be unique.");
      } else if (shapes.get(index) == ShapeType.OVAL) {
        shapes.add(ShapeType.OVAL);
      } else if (shapes.get(index) == ShapeType.RECTANGLE) {
        shapes.add(ShapeType.OVAL);
      }
    }
  }

  /**
   * ____________________________________ METHOD: nameMatch() _____________________________________.
   * @return true if the ArrayList of shapes contains duplicates
   */
  private boolean nameMatch() {
    for (Shape eachShape : shapes) {
      return eachShape.equals(shapes.get(0));
  }
    return false;
  }


  /**
   * _________________________________ METHOD: addColorChange() ___________________________________.
   * @param name        - the name of the shape, a String
   * @param newColor    - the new color of the shape, an enumeration
   * @param startChange - the *** tick *** at which the color of the shape changes.
   * @param endChange   - the *** tick *** at which the color of the shape
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
   * @param name        - the name of the shape, a String
   * @param newWidth    - the new width of the shape, a double
   * @param newHeight   - the new height of the shape, a double
   * @param startChange - the *** tick *** at which the color of the shape changes.
   * @param endChange   - the *** tick *** at which the color of the shape
   */
  @Override
  public void addSizeChange(String name, double newWidth, double newHeight, int startChange, int endChange) {
    // get shape from list
    Shape shape = null;
    for (Shape s : shapes) {
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
   * ____________________________________ METHOD: addMove_() ______________________________________.
   * @param name        - the name of the shape, a String
   * @param moveTo      - the coordinates of where the shape is moving to, a Point2D
   * @param startChange - the *** tick *** at which the color of the shape changes.
   * @param endChange   - the *** tick *** at which the color of the shape
   */
  @Override
  public void addMove(String name, Point2D moveTo, int startChange, int endChange) {
    // get shape from list
    Shape shape = null;
    for (Shape s : shapes) {
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
   * @return the String form of the lists declared and instantiated within this class.
   */
  @Override
  public String toString() {

    System.out.println(shapes);
    // new StringBuilder
    StringBuilder sb = new StringBuilder();

    // add the shapes to sb
    sb.append("Shapes:\n");
    for (Shape shape : shapes) {
      sb.append(shape.toString()).append("\n");
    }

    // add the changes to sb
    for (ChangeShape change : changes) {
      sb.append(change.toString()).append("\n");
    }

    return sb.toString();
  }


  //  /**
//   * This method getShapesAtTick() should be a stub. It does not get implemented in this model.
//   * This method will be implemented in the controller, instead.
//   * "The return type will be some form of a collection of shapes, as hinted by your...
//   * description that it "gets all the shapes." - Freifeld
//   */
//  @Override
//  public String getShapesAtTick(int tick) {
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
