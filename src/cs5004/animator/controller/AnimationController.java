package cs5004.animator.controller;

import cs5004.animator.model.IAnimationModel;

/**
 * A controller for the Animation Model. The controller receives all its inputs from a Readable
 * object and transmits all outputs to an Appendable object. The Appendable object is provided by a
 * view.
 */
public class AnimationController implements IAnimationController {
  private IAnimationModel model;
  final Readable in;
  final Appendable out;

  public AnimationController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void playAnimation(IAnimationModel model) {

  }
}
