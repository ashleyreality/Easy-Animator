package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.Timer;

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
  private IAnimationModel model;
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
    printView();
    System.exit(0);
  }

  /**
   * Prints the requested view to an out file.
   */
  public void printView() {
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

  /**
   * _____________________________ METHOD: setStartButtonListener() _______________________________.
   * Creates a listener for the start button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setStartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * ______________________________ METHOD: setStopButtonListener() _______________________________.
   * Creates a listener for the stop button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setStopButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");

  }

  /**
   * ____________________________ METHOD: setRestartButtonListener() ______________________________.
   * Creates a listener for the restart button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setRestartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");

  }

  /**
   * _______________________________ METHOD: setFastButtonListener() ______________________________.
   * Creates a listener for the "faster" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setFastButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * ______________________________ METHOD: setSlowButtonListener() _______________________________.
   * Creates a listener for the "slower" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSlowButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * ______________________________ METHOD: setLoopButtonListener() _______________________________.
   * Creates a listener for the "loop" checkbox.
   *
   * @param itemEvent the event, an ItemListener
   */
  @Override
  public void setLoopButtonListener(ItemListener itemEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * ______________________________ METHOD: setLoadButtonListener() _______________________________.
   * Creates a listener for the "load" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setLoadButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * ____________________________ METHOD: setSaveTextButtonListener() _____________________________.
   * Creates a listener for the "save as text" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSaveTextButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * _____________________________ METHOD: setSaveSVGButtonListener() _____________________________.
   * Creates a listener for the "save as svg" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSaveSVGButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * Draws the shapes at a given tick.
   *
   * @param fromTick the current tick
   */
  @Override
  public void drawShapes(int fromTick) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * Gets the timer of the animation.
   * @return the timer, a Timer
   */
  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * Sets the tick in the animation Timer.
   * @param tick a tick/frame rate, an int
   */
  @Override
  public void setTick(int tick) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * Gets the loop checkbox from the aniimation.
   * @return a loop checkbox, a JCheckBox
   */
  @Override
  public JCheckBox getLoopCheckbox() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * ____________________________________ METHOD OVERRIDE: loop() _________________________________.
   * Loops the animation to play the animation.
   */
  @Override
  public void loop() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * __________________________________ METHOD OVERRIDE: noLoop() _________________________________.
   * Does not loop the animation after the last tick.
   */
  @Override
  public void noLoop() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * _____________________________________ METHOD: setSpeed() _____________________________________.
   * Sets the speed of the animation.
   *
   * @param speed the speed to set the animation to, an int
   */
  @Override
  public void setSpeed(int speed) {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   /**
   * _____________________________________ METHOD: getSpeed() _____________________________________.
   * Gets the speed of the animation.
   *
   * @return the speed of the animation, an int
   */
  @Override
  public int getSpeed() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * _________________________________ METHOD OVERRIDE: play() ____________________________________.
   * This is the play() method override from the IView interface. It instantiates a new Timer object
   * with a time delay defined by the user's speed selection and the view being the listener.
   */
  @Override
  public void play() {
    throw new UnsupportedOperationException("The SVG view does not use this.");
  }

  /**
   * ________________________________ METHOD OVERRIDE: getModel() _________________________________.
   * Gets the current model.
   *
   * @return the model, an IAnimationModel
   */
  @Override
  public IAnimationModel getModel() {
    return this.model;
  }

  /**
   * ________________________________ METHOD OVERRIDE: setModel() _________________________________.
   * Sets the model.
   *
   * @param model the model, an IAnimationModel
   */
  @Override
  public void setModel(IAnimationModel model) {
    this.model = model;
  }

  /**
   * ______________________________ METHOD OVERRIDE: clearModel() _________________________________.
   * Clears the model.
   */
  @Override
  public void clearModel() {
    // no op
  }
}
