public class ScaleShape extends AbstractChangeShape {
  int oldParamA;
  int oldParamB;
  int newParamA;
  int newParamB;

  /**
   * Generate a shape change.
   *
   * @param shape - the shape to be changed
   * @param changeBegin - the time in ticks from which the change begins
   * @param changeEnd   - the time in ticks when the change ends
   */
  public ScaleShape(IShape shape, int changeBegin, int changeEnd, int oldParamA, int oldParamB,
                    int newParamA, int newParamB) {
    super(shape, changeBegin, changeEnd);
    this.oldParamA = oldParamA;
    this.oldParamB = oldParamB;
    this.newParamA = newParamA;
    this.newParamB = newParamB;
  }
}
