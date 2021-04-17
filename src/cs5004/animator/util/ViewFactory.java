package cs5004.animator.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

/**
 * __________________________________ CLASS: ViewFactory {} _______________________________________.
 * ViewFactory takes data from the user and sends it to the correct view.
 */
public class ViewFactory {
  private String outputView;
  private IAnimationModel model;
  private String outputName;
  private int speed;

  /**
   * _____________________________ CONSTRUCTOR: ViewFactory() _____________________________________.
   * Constructs a ViewFactory.
   *
   * @param outputView the type of animation view being requested, a String
   * @param model the model containing the animation data
   * @param outputName the text file output, a String
   * @param speed the speed in ticks
   */
  public ViewFactory(String outputView, IAnimationModel model, String outputName, int speed) {
    this.outputView = outputView;
    this.model = model;
    this.outputName = outputName;
    this.speed = speed;
  }

  /**
   * ____________________________________ METHOD: create() ________________________________________.
   * Creates the requested view.
   *
   * @return a view object
   * @throws FileNotFoundException if the file is not found
   * @throws IllegalArgumentException if the view type is incorrect
   */
  public IView create() throws IOException {

    if (outputView.equalsIgnoreCase("svg")) {
      return new SVGView(model, this.outputName, speed);
    } else if (outputView.equalsIgnoreCase("text")) {
      return new TextView(model, this.outputName);
    } else if (outputView.equalsIgnoreCase("visual")) {
      return new VisualView(model, speed);
    } else {
      throw new IllegalArgumentException("View type does not exist.");
    }
  }

  public String getOutputName() {
    return outputName;
  }

  public int getSpeed() {
    return speed;
  }
}
