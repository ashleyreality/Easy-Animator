package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;

/**
 * This class represents a text view. The text view outputs a text description of the animation
 * to an appendable.
 */
public class TextView implements IView {
  private IAnimationModel model;
  private PrintWriter out;
  private StringBuilder sb;
  /**
   * Constructs a text view, given a model and a string representing the file to be written to.
   *
   * @param model the AnimationModel instance you want to create a view for
   * @param outString name of the file to write to
   * @throws FileNotFoundException if the out is not found
   */
  public TextView(IAnimationModel model, String outString) throws IOException {
    this.model = model;

    try {
      this.out = new PrintWriter(outString);

    } catch (IOException e) {
      System.out.println("File was not found. Defaulting to System.out");
      this.out = new PrintWriter(System.out);
    }
    createView();
  }

  /**
   * @param model
   * @throws IOException
   */
  public TextView(IAnimationModel model) throws IOException {
      this.model = model;
      this.out = new PrintWriter(System.out);;
      createView();
  }

  /**
   * @throws IOException
   */
  private void createView() throws IOException {
    sb = new StringBuilder();
    // sort by appear time
    Comparator<IShape> sortByAppear = Comparator.comparingInt(IShape::getAppear);
    List<IShape> s = model.getShapeMap().keySet().stream().sorted(sortByAppear)
            .collect(Collectors.toList());

    // for each shape, add its create string to out
    for (IShape l : s) {
      sb.append(l.createString());
      sb.append("\n");
    }
    sb.append("\n");

    // for each shape, add its appear/disappear string to out
    for (IShape l : s) {
      sb.append(l.appearString());
      sb.append("\n");
    }
    sb.append("\n");

    // Sort the events in terms of begin & end time
    Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);

    // Create a list of sorted events
    List<IEvent> t = model.getShapeMap().values().stream().flatMap(Collection::stream)
            .sorted(sortByEventBegin).collect(Collectors.toList());

    // Add the changes/events strings to out
    for (IEvent a : t) {
      sb.append(a.toString());
      sb.append("\n");
    }
    out.print(sb.toString());
    out.close();
  }

  /**
   * @return
   */
  public String getState() {
    return sb.toString();
  }
}


