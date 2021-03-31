public class ScaleShape extends ChangeShape {
  int oldParamA;
  int oldParamB;
  int newParamA;
  int newParamB;

  /**
   * Generate a shape change.
   *
   * @param shape       - the shape to be changed
   * @param changeBegin - the time in ticks from which the change begins
   * @param changeEnd   - the time in ticks when the change ends
   */
  public ScaleShape(Shape shape, int changeBegin, int changeEnd, int oldParamA, int oldParamB,
                    int newParamA, int newParamB) {
    super(shape, changeBegin, changeEnd);
    this.oldParamA = oldParamA;
    this.oldParamB = oldParamB;
    this.newParamA = newParamA;
    this.newParamB = newParamB;
  }

  public String toString() {
    // "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
    // fixme -- need to find a way to get old params AND new params?
    return ("Shape " + shape.getName() + " scales from ");
  }
}