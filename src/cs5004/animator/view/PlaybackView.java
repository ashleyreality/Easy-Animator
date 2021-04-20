package cs5004.animator.view;

import cs5004.animator.model.IAnimationModel;

public class PlaybackView extends VisualView {

  /**
   * The PlaybackView constructor takes in a model and the specified speed.
   *
   * @param model the model that the view follows for the animation, an IAnimation
   * @param speed the speed of the animation, an int
   */
  public PlaybackView(IAnimationModel model, int speed) {
    super(model, speed);
  }
}
