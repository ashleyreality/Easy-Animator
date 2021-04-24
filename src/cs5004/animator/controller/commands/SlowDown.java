package cs5004.animator.controller.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

public class SlowDown implements AnimationCommand, ActionListener {

  private int speed;

  public SlowDown(IView view) {
    this.speed = view.getSpeed();

    if (speed < 1) {
      // set it to 1? 0 speed is just pausing at some point so 1 makes sense for minimal speed
      this.speed = 1;
    }
  }

  @Override
  public void go(IAnimationModel model, IView view) {
    System.out.println("Slow down command received");
    //view.setSpeed(view.getSpeed() - 1);
    setUpdatedSpeed(view);
    //System.out.println("speed - " + view.getSpeed());
    //view.getTimer().setDelay(1000 / view.getSpeed());
    System.out.println("The speed is now: " + speed);
    setTimerDelay(view);
  }

  private void setUpdatedSpeed(IView view) {
    view.setSpeed(speed - 1);
  }

  private void setTimerDelay(IView view) {
    view.getTimer().setDelay(1000/speed);
  }

  public int getUpdatedSpeed() {
    return this.speed;
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("Slow down button clicked!");

  }
}
