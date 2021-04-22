package cs5004.animator.controller.commands;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

public class RestartAnimation implements AnimationCommand {
  @Override
  public void go(IAnimationModel model, IView view) {
    // do something
    System.out.println("Restart command received");
  }
}
