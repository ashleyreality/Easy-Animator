package cs5004.animator.view;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs5004.animator.model.IAnimationModel;

/**
 * This class represents a visual view. The visual view outputs a visual display to the end-user of
 * the animation. It extends JFrame JFC/Swing component architecture.
 *
 */
public class VisualView extends JFrame implements IView, ActionListener {
  protected IAnimationModel model;
  protected int speed;
  protected AnimationPanel animationPanel;
  protected int tick;
  protected Timer timer;

  /**
   * The VisualView constructor takes in a model and the specified speed.
   * @param model the model that the view follows for the animation, an IAnimation
   * @param speed the speed of the animation, an int
   */
  public VisualView(IAnimationModel model, int speed) {
    super();
    this.model = model;
    this.speed = speed;
    // Instantiate AnimationPanel with the tick at 0 to be updated later
    animationPanel = new AnimationPanel(model, 0);
  }

  /**
   * Creates a visual view.
   */
  public void createView() {
    newPanel(animationPanel);
    scrollPanel(animationPanel);
    this.pack();
    this.setVisible(true);
    drawShapes(0);
  }

  /**
   * Creates a panel.
   * @param animationPanel an animation panel
   */
  protected void newPanel(AnimationPanel animationPanel) {
    animationPanel.setPreferredSize(new Dimension(model.getBoundsWidth(), model.getBoundsHeight()));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(animationPanel);
  }

  /**
   * Creates a scroll pane.
   *
   * @param animationPanel an animation panel
   */
  protected void scrollPanel(AnimationPanel animationPanel) {
    // Create a Scroll Pane
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(scrollPane);
  }

  /**
   * Draws the shapes at a given tick.
   *
   * @param fromTick the current tick
   */
  public void drawShapes(int fromTick) {
    int lastShapeTime = model.getEndTick();
    // For the duration of the animation, where the end of the animation is calculated via
    // lastShapeTime, run through each tick from 0 through lastShapeTime
    for (int tick = fromTick; tick <= lastShapeTime; tick++) {

      // Get the current epoch timestamp in milliseconds precision
      long startTime = System.currentTimeMillis();

      // Update the tick being passed into AnimationPanel
      animationPanel.setTick(tick);
      // Draw the shape(s) at this time using the paintComponent method inside of repaint(), which
      // is overridden in animationPanel
      animationPanel.repaint();
      // System.out.println("draw frame " + tick);

      // Get the current epoch timestamp in milliseconds precision
      double endTime = System.currentTimeMillis();

      // Calculate the duration between epoch times
      double duration = endTime - startTime;

      // Calculate the desired time relative to the speed provided by the user on the command line
      // The user provides "speed" which is the ticks/sec, or in other words, frames/sec, which is
      // the frame rate
      int desiredTime = 1000 / speed;

      // Calculate the remaining sleep time
      double remainingSleepTime = desiredTime - duration;
      // System.out.println(remainingSleepTime);

      // Set the sleep time using the remaining sleep time
      try {
        Thread.sleep((long) remainingSleepTime);
      } catch (InterruptedException e) {
        // Do nothing
      }
    }
  }

  /**
   * Gets the timer of the animation.
   * @return the timer, a Timer
   */
  public Timer getTimer() {
    return timer;
  }

  /**
   * Sets the tick in the animation Timer.
   * @param tick a tick/frame rate, an int
   */
  @Override
  public void setTick(int tick) {
    this.tick = tick;
  }

  /**
   * Gets the loop checkbox from the aniimation.
   * @return a loop checkbox, a JCheckBox
   */
  @Override
  public JCheckBox getLoopCheckbox() {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
  }

  /**
   * Gets a text representation of the view state. Does not apply to visual view.
   *
   * @throws UnsupportedOperationException because there is no view state for the visual view
   */
  @Override
  public String getViewState() {
    throw new UnsupportedOperationException("No view state for visual");
  }

  /**
   * _____________________________ METHOD: setStartButtonListener() _______________________________.
   * Creates a listener for the start button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setStartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");
  }

  /**
   * ______________________________ METHOD: setStopButtonListener() _______________________________.
   * Creates a listener for the stop button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setStopButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  /**
   * ____________________________ METHOD: setRestartButtonListener() ______________________________.
   * Creates a listener for the restart button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setRestartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  /**
   * _______________________________ METHOD: setFastButtonListener() ______________________________.
   * Creates a listener for the "faster" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setFastButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  /**
   * ______________________________ METHOD: setSlowButtonListener() _______________________________.
   * Creates a listener for the "slower" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSlowButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  /**
   * ______________________________ METHOD: setLoopButtonListener() _______________________________.
   * Creates a listener for the "loop" checkbox.
   *
   * @param itemEvent the event, an ItemListener
   */
  @Override
  public void setLoopButtonListener(ItemListener itemEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  /**
   * ______________________________ METHOD: setLoadButtonListener() _______________________________.
   * Creates a listener for the "load" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setLoadButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  /**
   * ____________________________ METHOD: setSaveTextButtonListener() _____________________________.
   * Creates a listener for the "save as text" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSaveTextButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  /**
   * _____________________________ METHOD: setSaveSVGButtonListener() _____________________________.
   * Creates a listener for the "save as svg" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSaveSVGButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");
  }

  /**
   * _____________________________ METHOD OVERRIDE: actionPerformed() _____________________________.
   * Repaints the animation to play the animation.
   *
   * @param e the event, an ActionEvent
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    throw new UnsupportedOperationException("Not supported in visual view");
  }

  /**
   * ____________________________________ METHOD OVERRIDE: loop() _________________________________.
   * Loops the animation to play the animation.
   */
  @Override
  public void loop() {
    throw new UnsupportedOperationException("Not supported in visual view");
  }

  /**
   * __________________________________ METHOD OVERRIDE: noLoop() _________________________________.
   * Does not loop the animation after the last tick.
   */
  @Override
  public void noLoop() {
    throw new UnsupportedOperationException("Not supported in visual view");
  }

  /**
   * _____________________________________ METHOD: setSpeed() _____________________________________.
   * Sets the speed of the animation.
   *
   * @param speed the speed to set the animation to, an int
   */
  @Override
  public void setSpeed(int speed) {
    throw new UnsupportedOperationException("Visual view does not support this");
  }

  /**
   * _____________________________________ METHOD: getSpeed() _____________________________________.
   * Gets the speed of the animation.
   *
   * @return the speed of the animation, an int
   */
  @Override
  public int getSpeed() {
    throw new UnsupportedOperationException("Visual view does not support this");
  }

  /**
   * _________________________________ METHOD OVERRIDE: play() ____________________________________.
   * This is the play() method override from the IView interface. It instantiates a new Timer object
   * with a time delay defined by the user's speed selection and the view being the listener.
   */
  @Override
  public void play() {
    throw new UnsupportedOperationException("Visual view does not support this");
  }

  /**
   * Prints the current view to an out file.
   */
  @Override
  public void printView() {
    throw new UnsupportedOperationException("Visual view does not support this");
  }

  /**
   * ________________________________ METHOD OVERRIDE: getModel() _________________________________.
   * Gets the current model.
   *
   * @return the model, an IAnimationModel
   */
  @Override
  public IAnimationModel getModel() {
    return this.model;
  }

  /**
   * ________________________________ METHOD OVERRIDE: setModel() _________________________________.
   * Sets the model.
   *
   * @param model the model, an IAnimationModel
   */
  @Override
  public void setModel(IAnimationModel model) {
    this.model = model;
  }

  /**
   * ______________________________ METHOD OVERRIDE: clearModel() _________________________________.
   * Clears the model.
   */
  @Override
  public void clearModel() {
    // no op
  }

}


