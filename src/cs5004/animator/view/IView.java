package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.Timer;


import cs5004.animator.model.IAnimationModel;

/**
 * The IView interface describes methods common to all views.
 */
public interface IView {

  /**
   * Creates a view of the specified type.
   */
  void createView();

  /**
   * Gets a string representation of the current state of the view.
   *
   * @return a string representing the view state
   */
  String getViewState();

  /**
   * Listener for the start button.
   *
   * @param actionEvent the start button is clicked
   */
  void setStartButtonListener(ActionListener actionEvent);

  /**
   * Listener for the stop button.
   *
   * @param actionEvent the stop button is clicked
   */
  void setStopButtonListener(ActionListener actionEvent);

  /**
   * Listener for the start over button.
   *
   * @param actionEvent the restart button is clicked
   */
  void setRestartButtonListener(ActionListener actionEvent);

  /**
   * Listener for the faster button.
   *
   * @param actionEvent the faster button is clicked
   */
  void setFastButtonListener(ActionListener actionEvent);

  /**
   * Listener for the slower button.
   *
   * @param actionEvent the slower button is clicked
   */
  void setSlowButtonListener(ActionListener actionEvent);

  /**
   * Listener for the loop checkbox.
   *
   * @param itemEvent look check box is checked or unchecked
   */
  void setLoopButtonListener(ItemListener itemEvent);

  /**
   * Listener for the load button.
   *
   * @param actionEvent the load button is clicked
   */
  void setLoadButtonListener(ActionListener actionEvent);

  /**
   * Listener for the save as text button.
   *
   * @param actionEvent the save as text button is clicked
   */
  void setSaveTextButtonListener(ActionListener actionEvent);

  /**
   * Listener for the save as SVG button.
   *
   * @param actionEvent the save as SVG button is clicked
   */
  void setSaveSVGButtonListener(ActionListener actionEvent);

  /**
   * Draws the shapes from the given tick.
   *
   * @param fromTick the tick to start drawing shapes from
   */
  void drawShapes(int fromTick);

  /**
   * Gets the timer for the view.
   *
   * @return the timer for the playback
   */
  Timer getTimer();

  /**
   * Sets the tick the animation is on.
   *
   * @param tick the tick to set the animation to
   */
  void setTick(int tick);

  /**
   * Gets the loop checkbox.
   *
   * @return the loop checkbox
   */
  JCheckBox getLoopCheckbox();

  /**
   * Makes the animation loop.
   */
  void loop();

  /**
   * Makes the animation not loop.
   */
  void noLoop();

  /**
   * Sets the speed for the playback.
   *
   * @param speed the speed to set the playback to
   */
  void setSpeed(int speed);

  /**
   * Gets the speed of the animation.
   *
   * @return the speed of the animation
   */
  int getSpeed();

  /**
   * Starts the timer so the animation plays.
   */
  void play();

  /**
   * Prints the view without exiting the program.
   */
  void printView();

  /**
   * Gets the model in the view.
   *
   * @return the model in the view
   */
  IAnimationModel getModel();

  /**
   * Sets the model in the view.
   *
   * @param model the model to add to the view
   */
  void setModel(IAnimationModel model);

  /**
   * Removes the current model from the view.
   */
  void clearModel();
}
