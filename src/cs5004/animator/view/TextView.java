package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;

public class TextView implements IView {
  private PrintWriter file;

  public TextView(IAnimationModel model, String fileName) throws FileNotFoundException {
    file = new PrintWriter(fileName);
// New StringBuilder
    StringBuilder sb = new StringBuilder();

    // Add the shapes to the StringBuilder, sb
    sb.append("Shapes:\n");
    List<String> s = model.getShapeMap().keySet().stream().map(Object::toString)
            .collect(Collectors.toList());
    for (String l : s) {
      sb.append(l);
      sb.append("\n");
    }

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
