package cs5004.animator.controller.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

/**
 * ___________________________________ CLASS: SlowDown {} _________________________________________.
 * This is the SlowDown class. It implements the AnimationCommand interface which contains methods
 * common to all animation commands. The SlowDown receives the command to decrease the speed of the
 * animation per the user button click in the animation playback window.
 */
public class SlowDown implements AnimationCommand, ActionListener {
  private int speed;


  /**
   * _________________________________ CONSTRUCTOR: SlowDown ______________________________________.
   * This constructs the SpeedUp() object. If the SlowDown button is clicked to speed of
   * "1 tick/second", it will not decrement lower than this value.
   *
   * @param view the animation view, an IView
   */
  public SlowDown(IView view) {
    this.speed = view.getSpeed();

    if (speed < 1) {
      // set it to 1? 0 speed is just pausing at some point so 1 makes sense for minimal speed
      this.speed = 1;
    }
  }

  /**
   * _________________________________ METHOD OVERRIDE: go() ______________________________________.
   * This is an override of the go() method from the AnimationCommand interface. It performs the
   * command, being slowing down the speed of the animation.
   *
   * @param model the IAnimationModel instance containing the animation data.
   * @param view the type of view specified, an IView
   */
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

  /**
   * _______________________________ METHOD: setUpdatedSpeed() ____________________________________.
   * This helper method updates the speed of the view.
   *
   * @param view the type of view specified, an IView
   */
  private void setUpdatedSpeed(IView view) {
    view.setSpeed(speed - 1);
  }

  /**
   * ________________________________ METHOD: setTimerDelay() _____________________________________.
   * This helper method sets the delay of the timer.
   *
   * @param view the type of view specified, an IView
   */
  private void setTimerDelay(IView view) {
    view.getTimer().setDelay(1000/speed);
  }

  public int getUpdatedSpeed() {
    return this.speed;
  }

  /**
   * ________________________________ METHOD: actionPerformed() ___________________________________.
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("Slow down button clicked!");

  }
}
