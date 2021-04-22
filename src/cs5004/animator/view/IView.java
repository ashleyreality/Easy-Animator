package cs5004.animator.view;

import java.awt.event.ActionListener;

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

  void setLoopButtonListener(ActionListener actionEvent);

  void setLoadButtonListener(ActionListener actionEvent);

  void setSaveTextButtonListener(ActionListener actionEvent);

  void setSaveSVGButtonListener(ActionListener actionEvent);

  void drawShapes(int fromTick);
}
