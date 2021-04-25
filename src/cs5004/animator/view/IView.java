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

  void setStartButtonListener(ActionListener actionEvent);

  void setStopButtonListener(ActionListener actionEvent);

  void setRestartButtonListener(ActionListener actionEvent);

  void setFastButtonListener(ActionListener actionEvent);

  void setSlowButtonListener(ActionListener actionEvent);

  void setLoopButtonListener(ItemListener itemEvent);

  void setLoadButtonListener(ActionListener actionEvent);

  void setSaveTextButtonListener(ActionListener actionEvent);

  void setSaveSVGButtonListener(ActionListener actionEvent);

  void drawShapes(int fromTick);

  Timer getTimer();

  void setTick(int tick);

  JCheckBox getLoopCheckbox();

  void loop();

  void noLoop();

  void setSpeed(int speed);

  int getSpeed();

  void play();

  void printView();

  IAnimationModel getModel();

  void setModel(IAnimationModel model);

  void clearModel();
}
