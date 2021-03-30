public class MoveShape extends AbstractChangeShape {
  private double from;
  private double to;

  public MoveShape(IShape shape, int changeBegin, int changeEnd, double from, double to) {
    super(shape, changeBegin, changeEnd);
    this.from = from;
    this.to = to;
  }
}
