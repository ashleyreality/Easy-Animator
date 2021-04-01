import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnimationModelImpl implements AnimationModel {
  private List<Shape> shapes;
  private List<ChangeShape> changes;

  public AnimationModelImpl() {
    shapes = new ArrayList<>();
    changes = new ArrayList<>();
  }

  @Override
  public String getShapesAtTick(int tick) {
    List<String> tickShapes = shapes.stream()
            .filter(n -> n.getAppear() < tick && tick < n.getDisappear()).map(Object::toString)
            .collect(Collectors.toList());

    // create new list (shapesAtTick)
    // iterate through the list of shapes
    // for each shape, check if appear < tick < disappear
    // this is a filter
    // if true, add shape to new list
    changes.stream().filter(n->n.getChangeBegin()<tick&&tick< n.getChangeEnd()).forEach(ChangeShape::change);
    // iterate through the list of changes
    // for each shape, check if startChange < tick < endChange
    // if true, apply change to shape
    // return list
    // Jen
    return tickShapes.toString();
  }

  @Override
  public void addShape(String name, Color color, ShapeType type, Point2D location, double param1,
                       double param2, int appear, int disappear) {
    // send information to correct Shape class
    // names must be unique -- throw an error if name is not unique
    // add return to shapes list
    // Aya
  }

  @Override
  public void addColorChange(Shape shape, Color newColor, int startChange, int endChange) {
    // send information to ChangeColor
    // add return to changes list
    // Aya
  }

  @Override
  public void addSizeChange(Shape shape, double newParam1, double newParam2, int startChange, int endChange) {
    // send information to ScaleShape
    ScaleShape scaled = new ScaleShape(shape, startChange, endChange, shape.getParam1(), newParam1,
            shape.getParam2(), newParam2);

    // add return to changes list
    changes.add(scaled);
  }

  @Override
  public void addMove(Shape shape, Point2D moveTo, int startChange, int endChange) {
    // send information to MoveShape
    MoveShape moved = new MoveShape(shape, startChange, endChange, shape.getLocation(), moveTo);

    // add return to changes list
    changes.add(moved);
  }

  @Override
  public String toString() {
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

}
