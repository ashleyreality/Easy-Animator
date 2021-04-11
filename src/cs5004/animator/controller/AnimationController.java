package cs5004.animator.controller;

import cs5004.animator.model.IAnimationModel;

/**
 * _________________________________ CLASS: AnimationController {} ________________________________.
 * A controller for the Animation Model. The controller receives all its inputs from a Readable
 * object and transmits all outputs to an Appendable object. The Appendable object is provided by a
 * view.
 */
public class AnimationController implements IAnimationController {
  private IAnimationModel model;
  final Readable in;
  final Appendable out;
  private String[] args;

  /**
   * ____________________________ CONSTRUCTOR: AnimationController() ______________________________.
   *
   * @param args the String array being built, an Array
   * @param in   a source of characters made available to callers, a Readable
   * @param out  an object to which character sequences and values can be appended, an Appendable
   */
  public AnimationController(String[] args, Readable in, Appendable out) {
    this.args = args;
    this.in = in;
    this.out = out;
  }

  /**
   * _______________________________ METHOD: playAnimation() ______________________________________.
   *
   * @param model - a non-null AnimationModel
   */
  @Override
  public void playAnimation(IAnimationModel model) {
  }
}
