package cs5004.animator.controller.commands;

import java.io.IOException;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

/**
 * This is the SpeedUp class. It implements the AnimationCommand interface which contains methods
 * common to all animation commands.
 */
public class SpeedUp implements AnimationCommand {

  private final int speed;

  public SpeedUp(IView view) {
    this.speed = view.getSpeed();
  }

  @Override
  public void go(IAnimationModel model, IView view) {
    System.out.println("Speed up command received");
    //view.setSpeed(view.getSpeed() + 1);
    setUpdatedSpeed(view);
    System.out.println("The speed is now: " + speed);
    //System.out.println("speed + " + view.getSpeed());
    //view.getTimer().setDelay(1000 / view.getSpeed());
    setTimerDelay(view);
  }

  private void setUpdatedSpeed(IView view) {
    view.setSpeed(speed + 1);
  }

  private void setTimerDelay(IView view) {
    view.getTimer().setDelay(1000/speed);
  }

  public int getUpdatedSpeed() {
    return this.speed;
  }

}
