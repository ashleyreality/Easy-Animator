package cs5004.animator.controller.commands;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;

public class SaveAsSVG implements AnimationCommand {
  @Override
  public void go(IAnimationModel model) {
    // do something
    System.out.println("Save as SVG command received");
  }
}
