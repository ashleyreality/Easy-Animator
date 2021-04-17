package cs5004.animator.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

/**
 * __________________________________ CLASS: ViewFactory {} _______________________________________.
 */
public class ViewFactory {
  private String outputView;
  private IAnimationModel model;
  private String outputName;
  private int speed;

  /**
   * _____________________________ CONSTRUCTOR: ViewFactory() _____________________________________.
   * @param outputView the type of animation view being requested, a String
   * @param model the model of the animation, a model
   * @param outputName the text file output, a String
   * @param speed the speed
   */
  public ViewFactory(String outputView, IAnimationModel model, String outputName, String speed) {
    this.outputView = outputView;
    this.model = model;
    this.outputName = outputName;
    this.speed = Integer.parseInt(speed);
  }

  /**
   * @param outputView
   * @param model
   * @param outputName
   */
  public ViewFactory(String outputView, IAnimationModel model, String outputName) {
    this.outputView = outputView;
    this.model = model;
    this.outputName = outputName;
    this.speed = 1;
  }

  /**
   * ____________________________________ METHOD: create() ________________________________________.
   * @return a new object of the view that the caller requested/passed in
   * @throws FileNotFoundException
   */
  public IView create() throws IOException {
    // need to understand what parameters are needed for views
    // there is an example of how a factory works at:
    // https://github.ccs.neu.edu/kbagley/cs5004flipped-sp2021/blob/master/Lecture_9_AbstractFactoryPattern/Factory.java

    // if view type == svg
    // return new svg view


    if (outputView.equalsIgnoreCase("svg")) {
      // pass in the print writer here to be consistent with the text view, i guess?
      return new SVGView(model, this.outputName, speed);
    } else if (outputView.equalsIgnoreCase("text")) {
      // for the text view, we will use appendable to just print it out in the console
      // depending on the appendable we pass in, we could do a text file or other appendable types
      return new TextView(model, this.outputName);
    } else if (outputView.equalsIgnoreCase("visual")) {
      return new VisualView(model, speed);
    } else {
      throw new IllegalArgumentException("View type does not exist.");
    }

  }

}
