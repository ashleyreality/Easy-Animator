package cs5004.animator.model;

/**
 * This enum represents the type of Shape.
 */
public enum ShapeType {
  RECTANGLE, ELLIPSE;

  @Override
  public String toString() {
    switch (this) {
      case RECTANGLE:
        return "rectangle";
      case ELLIPSE:
        return "ellipse";
      default:
        throw new IllegalArgumentException("no shape");
    }
  }
}