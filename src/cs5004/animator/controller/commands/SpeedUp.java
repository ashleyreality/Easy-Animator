package cs5004.animator.controller.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

/**
 * ____________________________________ CLASS: SpeedUp {} _________________________________________.
 * This is the SpeedUp class. It implements the AnimationCommand interface which contains methods
 * common to all animation commands. The SpeedUp receives the command to increase the speed of the
 * animation per the user button click in the animation playback window.
 */
public class SpeedUp implements AnimationCommand, ActionListener {
  private final int speed;
  private IView view;

  /**
   * __________________________________ CONSTRUCTOR: SpeedUp ______________________________________.
   * This constructs the SpeedUp() object.
   *
   * @param view the animation view, an IView
   */
  public SpeedUp(IView view) {
    this.speed = view.getSpeed();
    this.view = view;
  }

  /**
   * _____________________________ METHOD OVERRIDE: start() ______________________________________.
   * This is an override of the start() method from the AnimationCommand interface. It performs the
   * command, being speeding up the speed of the animation.
   *
   * @param model the IAnimationModel instance containing the animation data.
   * @param view  the type of view specified, an IView
   */
  @Override
  public void start(IAnimationModel model, IView view) {

    view.setSpeed(speed);

    System.out.println("Speed up command received");
    //view.setSpeed(view.getSpeed() + 1);
    setUpdatedSpeed(view);
    System.out.println("The speed is now: " + speed);
    //System.out.println("speed + " + view.getSpeed());
    //view.getTimer().setDelay(1000 / view.getSpeed());
    setTimerDelay(view);
  }

  /**
   * _______________________________ METHOD: setUpdatedSpeed() ____________________________________.
   * This helper method updates the speed of the view.
   *
   * @param view the type of view specified, an IView
   */
  private void setUpdatedSpeed(IView view) {
    view.setSpeed(speed + 1);
  }

  /**
   * ________________________________ METHOD: setTimerDelay() _____________________________________.
   * This helper method sets the delay of the timer.
   *
   * @param view the type of view specified, an IView
   */
  private void setTimerDelay(IView view) {
    view.getTimer().setDelay(1000 / speed);
  }

  /**
   * ________________________________ METHOD: getUpdatedSpeed() ___________________________________.
   * This getter method gets the speed of this SpeedUp object.
   *
   * @return the current speed, an int
   */
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
    System.out.println("Speed up button clicked!");
    view.setSpeed(speed);
  }
}
