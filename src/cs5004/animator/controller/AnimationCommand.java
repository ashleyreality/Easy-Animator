package cs5004.animator.controller;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

/**
 * The AnimationCommand interface contains methods common to all animation commands.
 */
public interface AnimationCommand {

  /**
   * Perform the command.
   *
   * @param model the IAnimationModel instance containing the animation data.
   * @param view
   */
  void go(IAnimationModel model, IView view);
}
