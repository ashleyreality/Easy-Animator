package cs5004.animator.view;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import cs5004.animator.model.EventType;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;
import cs5004.animator.model.MoveShape;
import cs5004.animator.model.ScaleShape;
import cs5004.animator.model.ShapeType;


/**
 * This class represents an SVG view. The SVG view outputs an svg display to the end-user of
 * the animation.
 */
public class SVGView implements IView {
  private final PrintStream out;
  private final IAnimationModel model;
  private final int speed;
  private final StringBuilder sb;

  /**
   * Constructs an SVG view.
   *
   * @param model     - the model holding the animation data
   * @param outString - a string describing the out file. Defaults to System.out
   * @param speed     - speed in ticks
   * @throws IOException if file is not found
   */
  public SVGView(IAnimationModel model, String outString, int speed) throws IOException {
    this.model = model;
    this.speed = speed;
    this.sb = new StringBuilder();

    if (outString.equals("System.out")) {
      this.out = new PrintStream(System.out);
    } else {
      this.out = new PrintStream(outString);
    }
  }

  /**
   * _______________________________ METHOD: buildTheSVGString() __________________________________.
   * Build the SVGView String.
   *
   */
  private void buildTheSVGString() {
    // Add the initial XML String to the StringBuilder
    String str = "<svg width=\"" + model.getBoundsWidth() + "\" height=\"" + model.getBoundsHeight()
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n";
    sb.append(str);

    // Add the shape XML to StringBuilder along with its list of events
    for (IShape shape : model.getShapeMap().keySet()) {
      if (shape.getColor() == null || shape.getLocation() == null || shape.getHeight() == 0
              || shape.getWidth() == 0) {
        throw new IllegalArgumentException("Shape parameters not set.");
      }
      // If the shape is a rectangle
      if (shape.getType() == ShapeType.RECTANGLE) {
        // Add initial <rect> tag with attributes
        sb.append(addRectangle(shape));
        // Put the shape's events in a list
        List<IEvent> eventList = new ArrayList<>(model.getShapeMap().get(shape));
        // Add events for that shape
        for (IEvent event : eventList) {
          addEvents(shape, event);
        }
        // Add closing </rect>
        sb.append("</rect>\n\n");
      }

      // If the shape is an ellipse
      if (shape.getType() == ShapeType.ELLIPSE) {
        // Add initial <ellipse> tag with attributes
        sb.append(addEllipse(shape));
        // Put the shape's events in a list
        List<IEvent> eventList = new ArrayList<>(model.getShapeMap().get(shape));
        // Add events for that shape
        for (IEvent event : eventList) {
          addEvents(shape, event);
        }
        // Add closing </ellipse>
        sb.append("</ellipse>\n\n");
      }
    }

    // Add closing </svg>
    sb.append("</svg>");

  }


  /**
   * Creates an SVG view.
   */
  public void createView() {
    buildTheSVGString();
    out.print(sb.toString());
    out.close();
  }

  /**
   * Adds a shape's events to the StringBuilder.
   *
   * @param shp - the shape
   * @param e   - the associated event
   */
  private void addEvents(IShape shp, IEvent e) {
    if (e.getEventType().equals(EventType.MOVE)) {
      sb.append(addMove(shp, (MoveShape) e));
    }
    if (e.getEventType().equals(EventType.SCALE)) {
      sb.append(addScale(shp, (ScaleShape) e));
    }
    if (e.getEventType().equals(EventType.RECOLOR)) {
      sb.append(addColorChange(shp, e));
    }
  }

  /**
   * Returns an SVG format string describing an IShape rectangle.
   *
   * @param shp - the shape
   * @return the SVG formatted string
   */
  private String addRectangle(IShape shp) {
    int xLocation = (int) (shp.getLocation().getX() - model.getBoundsX());
    int yLocation = (int) (shp.getLocation().getY() - model.getBoundsY());
    return "<rect id=\"" + shp.getName() + "\" x=\"" + xLocation + "\" y=\""
            + yLocation + "\" width=\"" + shp.getWidth() + "\" height=\""
            + shp.getHeight() + "\" fill=\"rgb" + shp.getColor() + "\" visibility="
            + "\"visible\" >\n\n";
  }

  /**
   * Returns an SVG format string describing an IShape ellipse.
   *
   * @param shp - the shape
   * @return the SVG formatted string
   */
  private String addEllipse(IShape shp) {
    int xLocation = (int) (shp.getLocation().getX() - model.getBoundsX());
    int yLocation = (int) (shp.getLocation().getY() - model.getBoundsY());
    return "<ellipse id=\"" + shp.getName() + "\" cx=\"" + xLocation
            + "\" cy=\"" + yLocation + "\" rx=\"" + shp.getWidth() / 2 + "\" ry=\""
            + shp.getHeight() / 2 + "\" fill=\"rgb" + shp.getColor() + "\" visibility="
            + "\"visible\" >\n\n";
  }

  /**
   * Returns an SVG formatted string describing a move event.
   *
   * @param shp - the shape that will move
   * @param e   - the move event
   * @return the SVG formatted string
   */
  private String addMove(IShape shp, MoveShape e) {
    float duration = (e.getEventEnd() - e.getEventBegin()) / (float) speed;
    int xFromLocation = (int) e.getFrom().getX() - model.getBoundsX();
    int xToLocation = (int) e.getTo().getX() - model.getBoundsX();
    int yFromLocation = (int) e.getFrom().getY() - model.getBoundsY();
    int yToLocation = (int) e.getTo().getY() - model.getBoundsY();

    if (shp.getType() == ShapeType.RECTANGLE) {
      return "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 / speed + "ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"x\" from=\""
              + xFromLocation + "\" to=\"" + xToLocation + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 / speed + "ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"y\" from=\""
              + yFromLocation + "\" to=\"" + yToLocation + "\" fill=\"freeze\" />\n";
    }
    if (shp.getType() == ShapeType.ELLIPSE) {
      return "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 / speed + "ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"cx\" from=\""
              + xFromLocation + "\" to=\"" + xToLocation + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 / speed + "ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"cy\" from=\""
              + yFromLocation + "\" to=\"" + yToLocation + "\" fill=\"freeze\" />\n";
    }
    return "";
  }

  /**
   * Returns an SVG formatted string describing a scale shape event.
   *
   * @param shp - the shape that will scale
   * @param e   - the scale event describing the change
   * @return the SVG formatted string
   */
  private String addScale(IShape shp, ScaleShape e) {
    float duration = (e.getEventEnd() - e.getEventBegin()) / (float) speed;
    if (shp.getType() == ShapeType.RECTANGLE) {
      return "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 / speed + "ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"height\" from=\""
              + e.getBefore() + "\" to=\"" + e.getAfter() + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 / speed + "ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"width\" from=\""
              + e.getWidthBefore() + "\" to=\"" + e.getWidthAfter() + "\" fill=\"freeze\" />\n";
    }
    if (shp.getType() == ShapeType.ELLIPSE) {
      return "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 / speed + "ms\" "
              + "dur\"=" + duration * 100 + "ms\" attributeName=\"height\" from=\""
              + e.getBefore() + "\" to=\"" + e.getAfter() + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 / speed + "ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"width\" from=\""
              + e.getWidthBefore() + "\" to=\"" + e.getWidthAfter() + "\" fill=\"freeze\" />\n";
    }
    return "";
  }

  /**
   * Returns an SVG formatted  string describing a color change event.
   *
   * @param shp - the shape that will change color
   * @param e   - the color event describing the change
   * @return the SVG formatted string
   */
  private String addColorChange(IShape shp, IEvent e) {
    float duration = ((e.getEventEnd() - e.getEventBegin()) / (float) speed);
    return "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 / speed + "ms\""
            + " dur=\"" + duration * 100 + "ms\" attributeName=\"fill\" from=\"rgb"
            + e.getBefore() + "\" to=\"rgb" + e.getAfter() + "\" fill=\"freeze\" />\n";
  }

  /**
   * Get the state of the current SVG view.
   *
   * @return a string describing the current SVG view
   */
  @Override
  public String getViewState() {
    buildTheSVGString();
    return sb.toString();
  }
}
