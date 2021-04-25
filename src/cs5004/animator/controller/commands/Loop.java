package cs5004.animator.controller.commands;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

/**
 * _____________________________________ CLASS: Loop {} ___________________________________________.
 * This is the Loop class which implements the AnimationCommand interface, which contains methods
 * common to all animation commands. The Loop receives the command to via a checkbox to enable or
 * disable looping-- enabling looping should cause the animation to automatically restart once it
 * reaches the end of the animation in the playback window.
 */
public class Loop implements AnimationCommand {

  /**
   * _____________________________ METHOD OVERRIDE: go() __________________________________________.
   * This is an override of the go() method from the AnimationCommand interface. It performs the
   * command, being loading a loop.
   *
   * @param model the IAnimationModel instance containing the animation data.
   * @param view  the type of view specified, an IView
   */
  @Override
  public void go(IAnimationModel model, IView view) {
    if (view.getLoopCheckbox().isSelected()) {
      view.loop();
    }
    if (!view.getLoopCheckbox().isSelected()) {
      view.noLoop();
    }
  }
}
