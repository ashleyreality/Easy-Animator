package cs5004.animator.controller.commands;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

public class StartAnimation implements AnimationCommand {
  @Override
  public void go(IAnimationModel model, IView view) {
    System.out.println("Animation start command received");
      //Do whatever
      view.drawShapes(0);
      //fixme maybe need a thread to keep track of the actions
  }
}
