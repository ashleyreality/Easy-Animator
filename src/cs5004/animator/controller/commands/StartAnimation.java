package cs5004.animator.controller.commands;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

/**
 * Starts the animation.
 */
public class StartAnimation implements AnimationCommand {

  /**
   * _____________________________ METHOD OVERRIDE: start() _______________________________________.
   * This is an override of the start() method from the AnimationCommand interface. It performs the
   * command, being starting the animation.
   *
   * @param model the IAnimationModel instance containing the animation data.
   * @param view  the type of view specified, an IView
   */
  @Override
  public void start(IAnimationModel model, IView view) {
    view.play();
  }
}
