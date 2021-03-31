import java.awt.geom.Point2D;

public class MoveShape extends ChangeShape {
  private Point2D from;
  private Point2D to;

  public MoveShape(Shape shape, int changeBegin, int changeEnd, Point2D from, Point2D to) {
    super(shape, changeBegin, changeEnd);
    this.from = from;
    this.to = to;
  }

  public String toString() {
  // Example output: Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50
    // fixme -- need to figure out how to represent this given the super issue -- getters?
    return "Shape" //shape name
      + " moves from (" + this.from + ") to (" + this.to + ") from t=" //changeBegin
    + " to t="; //changeEnd
  }
}
