package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.EventType;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;
import cs5004.animator.model.MoveShape;
import cs5004.animator.model.ScaleShape;


/**
 * This class represents an svg view. The svg view outputs an svg display to the end-user of
 * the animation.
 */
public class SVGView implements IView {
  private PrintWriter file;
  private IAnimationModel model;

  public SVGView(IAnimationModel model, PrintWriter file, int speed) throws IOException {
    this.file = file;
    this.model = model;

    // add initial xml to file
    String str = "<svg width=\"" + model.getBoundsWidth() + "\" height=\"" + model.getBoundsHeight()
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n";
    this.file.append(str);

    // sort shapes by appear time and create a sorted list of them
    Comparator<IShape> sortByAppear = Comparator.comparingInt(IShape::getAppear);
    List<IShape> s = model.getShapeMap().keySet().stream().sorted(sortByAppear)
            .collect(Collectors.toList());

    // add shape xml to file along with its list of events
    // will need to sort individual event lists by start time bc events must go inside shape tags
    for (IShape shp : s) {
      // if rect
      if (shp.getType().equalsIgnoreCase("rectangle")) {
        // add initial <rect> tag with attributes
        this.file.append(addRectangle(shp));

        // put the shape's events in a list, sorted by event time
        Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);
        List<IEvent> t = model.getShapeMap().get(shp).stream().sorted(sortByEventBegin)
                .collect(Collectors.toList());

        // add events for that shape
        for (IEvent e : t) {
          addEvents(shp, e);
        }
        // add closing </rect>
        this.file.append("</rect>\n\n");
      }

      // if ellipse
      if (shp.getType().equalsIgnoreCase("ellipse")) {
        // add initial <ellipse> tag with attributes
        this.file.append(addEllipse(shp));

        // put the shape's events in a list, sorted by event time
        Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);
        List<IEvent> t = model.getShapeMap().get(shp).stream().sorted(sortByEventBegin)
                .collect(Collectors.toList());
        // add events for that shape
        for (IEvent e : t) {
          addEvents(shp, e);
        }
        // add closing </ellipse>
        this.file.append("</ellipse>\n\n");
      }
    }

    // add closing </svg>
    this.file.append("</svg>");

    // close the file
    this.file.close();

  }

  private void addEvents(IShape shp, IEvent e) {
    if (e.getEventType().equals(EventType.MOVE)) {
      file.append(addMove(shp, (MoveShape) e));
    }
    if (e.getEventType().equals(EventType.SCALE)) {
      file.append(addScale(shp, (ScaleShape) e));
    }
    if (e.getEventType().equals(EventType.RECOLOR)) {
      file.append(addColorChange(shp, e));
    }
  }

  private String addRectangle(IShape shp) {
    int xLocation = (int) (shp.getLocation().getX() - model.getBoundsX());
    int yLocation = (int) (shp.getLocation().getY() - model.getBoundsY());
    return "<rect id=\"" + shp.getName() + "\" x=\"" + xLocation + "\" y=\""
            + yLocation + "\" width=\"" + shp.getWidth() + "\" height=\""
            + shp.getHeight() + "\" fill=\"rgb" + shp.getColor() + "\" visibility="
            + "\"visible\" >\n\n";
  }

  private String addEllipse(IShape shp) {
    int xLocation = (int) (shp.getLocation().getX() - model.getBoundsX());
    int yLocation = (int) (shp.getLocation().getY() - model.getBoundsY());
    return "<ellipse id=\"" + shp.getName() + "\" cx=\"" + xLocation
            + "\" cy=\"" + yLocation + "\" rx=\"" + shp.getWidth() / 2 + "\" ry=\""
            + shp.getHeight() / 2 + "\" fill=\"rgb" + shp.getColor() + "\" visibility="
            + "\"visible\" >\n\n";
  }

  private String addMove(IShape shp, MoveShape e) {
    int duration = e.getEventEnd() - e.getEventBegin();
    int xFromLocation = (int) e.getFrom().getX() - model.getBoundsX();
    int xToLocation = (int) e.getTo().getX() - model.getBoundsX();
    int yFromLocation = (int) e.getFrom().getY() - model.getBoundsY();
    int yToLocation = (int) e.getTo().getY() - model.getBoundsY();

    if (shp.getType().equalsIgnoreCase("rectangle")) {

      return "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 + "ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"x\" from=\""
              + xFromLocation + "\" to=\"" + xToLocation + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 + "ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"y\" from=\""
              + yFromLocation + "\" to=\"" + yToLocation + "\" fill=\"freeze\" />\n";
    }
    if (shp.getType().equalsIgnoreCase("ellipse")) {
      return "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 + "ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"cx\" from=\""
              + xFromLocation + "\" to=\"" + xToLocation + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 + "ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"cy\" from=\""
              + yFromLocation + "\" to=\"" + yToLocation + "\" fill=\"freeze\" />\n";
    }
    return "";
  }

  private String addScale(IShape shp, ScaleShape e) {
    int duration = e.getEventEnd() - e.getEventBegin();
    if (shp.getType().equalsIgnoreCase("rectangle")) {
      return "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 + "ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"height\" from=\""
              + e.getBefore() + "\" to=\"" + e.getAfter() + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 + "ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"width\" from=\""
              + e.getWidthBefore() + "\" to=\"" + e.getWidthAfter() + "\" fill=\"freeze\" />\n";
    }
    if (shp.getType().equalsIgnoreCase("ellipse")) {
      return "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 + "ms\" "
              + "dur\"=" + duration * 100 + "ms\" attributeName=\"height\" from=\""
              + e.getBefore() + "\" to=\"" + e.getAfter() + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 + "ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"width\" from=\""
              + e.getWidthBefore() + "\" to=\"" + e.getWidthAfter() + "\" fill=\"freeze\" />\n";
    }
    return "";
  }

  private String addColorChange(IShape shp, IEvent e) {
    int duration = e.getEventEnd() - e.getEventBegin();
    return "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 + "ms\" "
            + "dur=\"" + duration * 100 + "ms\" attributeName=\"fill\" from=\"rgb"
            + e.getBefore() + "\" to=\"rgb" + e.getAfter() + "\" fill=\"freeze\" />\n";
  }
}
