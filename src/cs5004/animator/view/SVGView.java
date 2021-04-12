package cs5004.animator.view;

import java.io.FileNotFoundException;
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

public class SVGView implements IView {
  private PrintWriter file;
  private IAnimationModel model;
  private StringBuilder sb;

  public SVGView(IAnimationModel model, String fileName) throws FileNotFoundException {
    this.file = new PrintWriter(fileName);
    this.model = model;

    // create StringBuilder
    sb = new StringBuilder();

    // add initial xml to sb
    String str = "<svg width=\"" + model.getBoundsWidth() + "\" height=\"" + model.getBoundsHeight()
            + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n";
    sb.append(str);

    // sort shapes by appear time and create a sorted list of them
    Comparator<IShape> sortByAppear = Comparator.comparingInt(IShape::getAppear);
    List<IShape> s = model.getShapeMap().keySet().stream().sorted(sortByAppear)
            .collect(Collectors.toList());

    // add shape xml to sb along with its list of events
    // will need to sort individual event lists by start time bc events must go inside shape tags
    for (IShape shp : s) {
      // if rect
      if (shp.getType().equalsIgnoreCase("rectangle")) {
        // add initial <rect> tag with attributes
        sb.append(addRectangle(shp));

        // put the shape's events in a list, sorted by event time
        Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);
        List<IEvent> t = model.getShapeMap().get(shp).stream().sorted(sortByEventBegin)
                .collect(Collectors.toList());

        // add events for that shape
        for (IEvent e : t) {
          addEvents(shp, e);
        }
        // add closing </rect>
        sb.append("</rect>\n\n");
      }

      // if ellipse
      if (shp.getType().equalsIgnoreCase("ellipse")) {
        // add initial <ellipse> tag with attributes
        sb.append(addEllipse(shp));

        // put the shape's events in a list, sorted by event time
        Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);
        List<IEvent> t = model.getShapeMap().get(shp).stream().sorted(sortByEventBegin)
                .collect(Collectors.toList());
        // add events for that shape
        for (IEvent e : t) {
          addEvents(shp, e);
        }
        // add closing </ellipse>
        sb.append("</ellipse>\n\n");
      }
    }

    // add closing </svg>
    sb.append("</svg>");

    // print the sb to the file and close the file
    file.print(sb.toString());
    file.close();

  }

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

  private String addRectangle(IShape shp) {
    int xLocation = (int) (shp.getLocation().getX() - model.getBoundsX());
    int yLocation = (int) (shp.getLocation().getY() - model.getBoundsY());
    String shapeStr = "<rect id=\"" + shp.getName() + "\" x=\"" + xLocation +"\" y=\""
            + yLocation +"\" width=\"" + shp.getWidth() + "\" height=\""
            + shp.getHeight() + "\" fill=\"rgb" + shp.getColor() + "\" visibility="
            + "\"visible\" >\n\n";
    return shapeStr;
  }

  private String addEllipse(IShape shp) {
    int xLocation = (int) (shp.getLocation().getX() - model.getBoundsX());
    int yLocation = (int) (shp.getLocation().getY() - model.getBoundsY());
    String shapeStr = "<ellipse id=\"" + shp.getName() + "\" cx=\"" + xLocation
            +"\" cy=\"" + yLocation +"\" rx=\"" + shp.getWidth() / 2 + "\" ry=\""
            + shp.getHeight() / 2 + "\" fill=\"rgb" + shp.getColor() + "\" visibility="
            + "\"visible\" >\n\n";
    return shapeStr;
  }

  private String addMove(IShape shp, MoveShape e) {
    int duration = e.getEventEnd() - e.getEventBegin();
    int xFromLocation = (int) e.getFrom().getX() - model.getBoundsX();
    int xToLocation = (int) e.getTo().getX() - model.getBoundsX();
    int yFromLocation = (int) e.getFrom().getY() - model.getBoundsY();
    int yToLocation = (int) e.getTo().getY() - model.getBoundsY();

    if (shp.getType().equalsIgnoreCase("rectangle")) {
      String eventStr = "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 +"ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"x\" from=\""
              + xFromLocation + "\" to=\"" + xToLocation + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 +"ms\""
              + " dur=\"" + duration * 100 + "ms\" attributeName=\"y\" from=\""
              + yFromLocation + "\" to=\"" + yToLocation + "\" fill=\"freeze\" />\n";

      return eventStr;
    }
    if (shp.getType().equalsIgnoreCase("ellipse")) {
      String eventStr = "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 +"ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"cx\" from=\""
              + xFromLocation + "\" to=\"" + xToLocation + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 +"ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"cy\" from=\""
              + yFromLocation + "\" to=\"" + yToLocation + "\" fill=\"freeze\" />\n";
      return eventStr;
    }
    return "";
  }

  private String addScale(IShape shp, ScaleShape e) {
    int duration = e.getEventEnd() - e.getEventBegin();
    if (shp.getType().equalsIgnoreCase("rectangle")) {
      String eventStr = "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 +"ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"height\" from=\""
              + e.getBefore() + "\" to=\"" + e.getAfter() + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 +"ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"width\" from=\""
              + e.getWidthBefore() + "\" to=\"" + e.getWidthAfter() + "\" fill=\"freeze\" />\n";
      return eventStr;
    }
    if (shp.getType().equalsIgnoreCase("ellipse")) {
      String eventStr = "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 +"ms\" "
              + "dur\"=" + duration * 100 + "ms\" attributeName=\"height\" from=\""
              + e.getBefore() + "\" to=\"" + e.getAfter() + "\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 +"ms\" "
              + "dur=\"" + duration * 100 + "ms\" attributeName=\"width\" from=\""
              + e.getWidthBefore() + "\" to=\"" + e.getWidthAfter() + "\" fill=\"freeze\" />\n";
      return eventStr;
    }
    return "";
  }

  private String addColorChange(IShape shp, IEvent e) {
    int duration = e.getEventEnd() - e.getEventBegin();
    String eventStr = "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() * 100 +"ms\" "
            + "dur=\"" + duration * 100 + "ms\" attributeName=\"fill\" from=\"rgb"
            + e.getBefore() + "\" to=\"rgb" + e.getAfter() + "\" fill=\"freeze\" />\n";;
    return eventStr;
  }
}
