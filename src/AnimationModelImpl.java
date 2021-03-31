import java.awt.geom.Point2D;

public class AnimationModelImpl implements AnimationModel {
  @Override
  public String getShapesAtTick(int tick) {
    return null;
  }

  @Override
  public void createShape(String name, Color color, Shape type, Point2D location, int appear, int disappear) {

  }

  @Override
  public void addShape(Shape shape) {

  }

  @Override
  public void addColorChange(Shape shape, Color newColor, int startChange, int endChange) {

  }

  @Override
  public void addSizeChange(Shape shape, double newParam1, double newParam2, int startChange, int endChange) {

  }

  @Override
  public void addMove(Shape shape, Point2D moveTo, int startChange, int endChange) {

  }
}
