import java.awt.geom.Point2D;

public class MoveShape extends AbstractChangeShape {
  private Point2D from;
  private Point2D to;

  public MoveShape(IShape shape, int changeBegin, int changeEnd, Point2D from, Point2D to) {
    super(shape, changeBegin, changeEnd);
    this.from = from;
    this.to = to;
  }
}
