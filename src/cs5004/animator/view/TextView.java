package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;

/**
 * This class represents a text view. The text view outputs a text description of the animation to
 * an appendable.
 */
public class TextView implements IView {
  private IAnimationModel model;
  private PrintStream out;
  private StringBuilder sb;

  /**
   * Constructs a text view, given a model and a string representing the file to be written to.
   *
   * @param model     the AnimationModel instance you want to create a view for
   * @param outString name of the file to write to
   * @throws FileNotFoundException if the out is not found
   */
  public TextView(IAnimationModel model, String outString) throws IOException {
    this.model = model;
    this.sb = new StringBuilder();

    if (outString.equals("System.out")) {
      this.out = new PrintStream(System.out);
    } else {
      this.out = new PrintStream(outString);
    }
  }


  /**
   * _______________________________ METHOD: buildTheTextString() _________________________________.
   * Build the TextView String.
   */
  private void buildTheTextString() {
    List<IShape> shapeList = new ArrayList<>(model.getShapeMap().keySet());

    // For each shape, add its IShape createString() to the shapeList ArrayList
    for (IShape shape : shapeList) {
      if (shape.getColor() == null || shape.getLocation() == null || shape.getHeight() == 0
              || shape.getWidth() == 0) {
        throw new IllegalArgumentException("Shape parameters not set.");
      }
      sb.append(shape.createString());
      sb.append("\n");
    }
    sb.append("\n");

    // For each shape, add its appear/disappear string the shapeList ArrayList
    for (IShape shape : shapeList) {
      sb.append(shape.appearString());
      sb.append("\n");
    }
    sb.append("\n");
    // Sort the events in terms of begin time
    Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);

    // Create a list of sorted events
    List<IEvent> eventList = model.getShapeMap().values().stream().flatMap(Collection::stream)
            .sorted(sortByEventBegin).collect(Collectors.toList());

    // Add the changes/events strings to out
    for (IEvent event : eventList) {
      sb.append(event.toString());
      sb.append("\n");
    }
  }

  /**
   * Creates a text view.
   */
  public void createView() {

    buildTheTextString();
    out.print(sb.toString());
    out.close();

  }

  /**
   * Gets the state of the view.
   *
   * @return a string representation of the current state of the view
   */
  public String getViewState() {
    buildTheTextString();
    return sb.toString();
  }
}


