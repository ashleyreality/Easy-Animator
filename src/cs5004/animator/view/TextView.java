package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;

public class TextView implements IView {
  private PrintWriter file;

  public TextView(IAnimationModel model, String fileName) throws FileNotFoundException {
    file = new PrintWriter(fileName);
    // New StringBuilder (from AnimationModelImpl)
    StringBuilder sb = new StringBuilder();

    //sort by appear time
    Comparator<IShape> sortByAppear = Comparator.comparingInt(IShape::getAppear);
    // Add the shapes to the StringBuilder, sb
    List<IShape> s = model.getShapeMap().keySet().stream().sorted(sortByAppear)
            .collect(Collectors.toList());

    for (IShape l : s) {
      // we need the color toString to output color words (red, blue, green)
      sb.append(l.createString());

      // Should we just change the toString methods in the Shape classes?
      // will probably be easier to just change the toString...
      sb.append("\n");
    }
    sb.append("\n");
    for (IShape l : s) {
      sb.append(l.appearString());
      // Should we just change the toString methods in the Shape classes?
      sb.append("\n");
    }
    sb.append("\n");

    // Sort the events in terms of begin & end time
    Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);
    // Create a list of sorted events
    List<IEvent> t = model.getShapeMap().values().stream().flatMap(Collection::stream)
            .sorted(sortByEventBegin).collect(Collectors.toList());

    // Add the changes/events to sb
    for (IEvent a : t) {
      sb.append(a.toString());
      sb.append("\n");
    }

    file.print(sb.toString());
    file.close();
  }
}
