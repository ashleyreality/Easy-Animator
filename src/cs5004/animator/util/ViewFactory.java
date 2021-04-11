package cs5004.animator.util;

import java.io.FileNotFoundException;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

/**
 * __________________________________ CLASS: ViewFactory {} _______________________________________.
 */
public class ViewFactory {
  // make these fields for now
  private String viewType;
  private IAnimationModel model;
  private String out;
  private String speed;
  // This creates a new view
  // a factory function gives you an option, and it returns to you the right type of object
  // the arguments the client gives the ViewFactory will have it return a certain type of view


  // This class should have a single static method that takes in a String name for a view
  // and constructs an instance of the appropriate concrete view. --AB

  // Question: Is it bad/good design to pass around the model to different constructors?

  /**
   * _________________________________ CONSTRUCTOR: Builder() _____________________________________.
   * @param viewType the type of animation view being requested, a String
   * @param model the model of the animation, a model
   * @param out the text file output, a String
   * @param speed the speed
   */
  public ViewFactory(String viewType, IAnimationModel model, String out, String speed) {
    this.viewType = viewType;
    this.model = model;
    this.out = out;
    this.speed = speed;

    // What is "out" here? The output text file name? If so, maybe we should name it more specifically

    // passing to the view the things that it needs
    // viewType is the view type --> send it to create so it knows which view

    // speed is only used for visual
  }

  /**
   * ____________________________________ METHOD: create() ________________________________________.
   * @return a new object of the view that the caller requested/passed in
   * @throws FileNotFoundException
   */
  public IView create() throws FileNotFoundException {
    // need to understand what parameters are needed for views
    // there is an example of how a factory works at:
    // https://github.ccs.neu.edu/kbagley/cs5004flipped-sp2021/blob/master/Lecture_9_AbstractFactoryPattern/Factory.java

    // if view type == svg
    // return new svg view

    if (viewType.equalsIgnoreCase("svg")) {
      return new SVGView(model);
    } else if (viewType.equalsIgnoreCase("text")) {
      return new TextView(model,this.out);
    } else if (viewType.equalsIgnoreCase("visual")) {
      return new VisualView(model);
    } else {
      throw new IllegalArgumentException("View type does not exist.");
    }

  }
}
