package cs5004.animator.controller;

import cs5004.animator.model.IAnimationModel;

/**
 * Represents a Controller for a simple animation. Executes shapes and events using the model;
 * conveys that information to the views.
 */
public interface IAnimationController {

  /**
   * Execute a single animation, given an AnimationModel. When the animation ends, so does the
   * playAnimation method.
   *
   * @param model - a non-null AnimationModel
   */
  public void playAnimation(IAnimationModel model);
}
