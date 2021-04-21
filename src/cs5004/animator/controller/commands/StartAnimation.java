package cs5004.animator.controller.commands;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;

public class StartAnimation implements AnimationCommand {
  @Override
  public void go(IAnimationModel model) {
    System.out.println("Animation start command received");
  }
}
