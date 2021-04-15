package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.IOException;
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
  // include more methods here
  // we may need to have a get state method
  // we may need to have an output method that handles different types of outputs - need to handle
  // BOTH System.out and .txt output
  // if nothing on -out, automatically should be System.out

  /**
   * Constructs a text view.
   *
   * @param model the AnimationModel instance you want to create a view for
   * @param out   out where the text view is being written to
   * @throws FileNotFoundException if the out is not found
   */
  public TextView(IAnimationModel model, Appendable out) throws IOException {
    // sort by appear time
    Comparator<IShape> sortByAppear = Comparator.comparingInt(IShape::getAppear);
    // Add the shapes to the StringBuilder, sb
    List<IShape> s = model.getShapeMap().keySet().stream().sorted(sortByAppear)
            .collect(Collectors.toList());
    // for each shape add it's create string to out
    for (IShape l : s) {
      // we need the color toString to output color words (red, blue, green)
      out.append(l.createString());
      out.append("\n");
    }
    out.append("\n");
    // for each shape add it's appear/disappear string to out
    for (IShape l : s) {
      out.append(l.appearString());
      out.append("\n");
    }
    out.append("\n");
    // Sort the events in terms of begin & end time
    Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);
    // Create a list of sorted events
    List<IEvent> t = model.getShapeMap().values().stream().flatMap(Collection::stream)
            .sorted(sortByEventBegin).collect(Collectors.toList());
    // Add the changes/events strings to out
    for (IEvent a : t) {
      out.append(a.toString());
      out.append("\n");
    }
    // it should come out in the console rather than be written to a file now.
  }
}
