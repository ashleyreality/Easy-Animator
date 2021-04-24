package cs5004.animator.controller.commands;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

public class SlowDown implements AnimationCommand {
  @Override
  public void go(IAnimationModel model, IView view) {
    System.out.println("Slow down command received");
    view.setSpeed(view.getSpeed() - 1);
    if (view.getSpeed() < 1) {
      // set it to 1? 0 speed is just pausing at some point so 1 makes sense for minimal speed
      view.setSpeed(1);
    }
    System.out.println("speed - " +view.getSpeed());
    view.play();
  }
}
