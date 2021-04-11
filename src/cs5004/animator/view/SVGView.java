package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;

public class SVGView implements IView {
  private PrintWriter file;
  private IAnimationModel model;

  public SVGView(IAnimationModel model, String fileName) throws FileNotFoundException {
    this.file = new PrintWriter(fileName);
    this.model = model;

    // create StringBuilder
    StringBuilder sb = new StringBuilder();

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
        String shapeStr = "<rect id=\"" + shp.getName() + "x=\"" + shp.getLocation().getX() +"\" y=\""
        + shp.getLocation().getY() +"\" width=\"" + shp.getWidth() + "\" height=\""
                + shp.getHeight() + "\" fill=rgb\"" + shp.getColor() + "\" visibility=\""
        + "\"visible\" >\n";
        sb.append(shapeStr);

        // put the shape's events in a list, sorted by event time
        Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);

        List<IEvent> t = model.getShapeMap().get(shp).stream().sorted(sortByEventBegin)
                .collect(Collectors.toList());

        // add events for that shape
        for (IEvent e : t) {
          int duration = e.getEventEnd() - e.getEventBegin();
          String eventStr = "<animate attributeType=\"xml\" begin=\"" + e.getEventBegin() +"\" "
                  + "\"dur=" + duration + "\" attributeName=\"" + shp.getType() + "\" from="
                  + e.getBefore() + "\" to=\"" + e.getAfter() + "\" fill=\"freeze\" />";
        }

        // add closing </rect>
        sb.append("</rect>\n");
      }

      // if ellipse
      if (shp.getType().equalsIgnoreCase("ellipse")) {
        // add initial <ellipse> tag with attributes
        String shapeStr = "<ellipse id=\"" + shp.getName() + "cx=\"" + shp.getLocation().getX()
                +"\" cy=\"" + shp.getLocation().getY() +"\" rx=\"" + shp.getWidth()/2 + "\" ry=\""
                + shp.getHeight()/2 + "\" fill=rgb\"" + shp.getColor() + "\" visibility=\""
                + "\"visible\" >";
        sb.append(shapeStr);

        // put the shape's events in a list, sorted by event time
        Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);

        // add events for that shape

        // add closing </ellipse>
      }
    }

    // add closing </svg>
    sb.append("</svg>");

    // print the sb to the file and close the file
    file.print(sb.toString());
    file.close();

  }
}
