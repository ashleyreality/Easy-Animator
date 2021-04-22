package cs5004.animator.controller.commands;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

public class SaveAsSVG implements AnimationCommand {
  @Override
  public void go(IAnimationModel model, IView view) {
    // do something
    System.out.println("Save as SVG command received");
  }
}
