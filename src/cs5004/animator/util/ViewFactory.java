package cs5004.animator.util;

import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

public class ViewFactory {
  // This creates a new view
  // a factory function gives you an option, and it returns to you the right type of object
  // the arguments the client gives the ViewFactory will have it return a certain type of view


  // This class should have a single static method that takes in a String name for a view
  // and constructs an instance of the appropriate concrete view. --AB

  public IView create(String viewType) {
    // need to understand what parameters are needed for views
    // there is an example of how a factory works at:
    // https://github.ccs.neu.edu/kbagley/cs5004flipped-sp2021/blob/master/Lecture_9_AbstractFactoryPattern/Factory.java

    // if view type == svg
    // return new svg view

    if (viewType.equals("svg")) {
      return new SVGView();
    } else if (viewType.equals("text")) {
      return new TextView();
    } else if (viewType.equals("visual")) {
      return new VisualView();
    } else {
      throw new IllegalArgumentException("View type does not exist.");
    }

  }
}
