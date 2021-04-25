package cs5004.animator.controller.commands;

import java.io.IOException;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.TextView;

/**
 * Saves the current file as a text output.
 */
public class SaveAsText implements AnimationCommand {


  /**
   * Saves the current file as a text output.
   *
   * @param model the IAnimationModel instance containing the animation data.
   * @param view  the type of view specified, an IView
   * @throws IOException if there is a problem saving the file.
   */
  @Override
  public void start(IAnimationModel model, IView view) throws IOException {
    // do something
    System.out.println("Save as text file command received");
    IView text = new TextView(model, "text.txt");
    text.printView();
  }
}
