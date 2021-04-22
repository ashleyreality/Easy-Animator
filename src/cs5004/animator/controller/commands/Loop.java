package cs5004.animator.controller.commands;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

public class Loop implements AnimationCommand {
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
