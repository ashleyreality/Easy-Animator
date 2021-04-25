package cs5004.animator.controller.commands;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

/**
 * Restarts the animation.
 */
public class RestartAnimation implements AnimationCommand {

  /**
   /**
   * _____________________________ METHOD OVERRIDE: go() __________________________________________.
   * This is an override of the go() method from the AnimationCommand interface. It performs the
   * command, being restarting the animation.
   *
   * @param model the IAnimationModel instance containing the animation data.
   * @param view  the type of view specified, an IView
   */
  @Override
  public void start(IAnimationModel model, IView view) {
    view.setTick(0);
    view.getTimer().restart();
  }
}
