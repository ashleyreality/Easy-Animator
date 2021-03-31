/**
 * This is the Color enumeration. It defines
 */
public enum Color {
  R, G, B;

  public int IntegerColor() {
    switch (this) {
      case R:
        //Shape.getColor(0); //??
        //break;
        return 0;
      case G:
        //Shape.getColor(1); //??
        //break;
        return 1;
      case B:
        //Shape.getColor(2); //??
        //break;
        return 2;
      default:
        throw new IllegalArgumentException("This should never happen.");
    }
  }
}
