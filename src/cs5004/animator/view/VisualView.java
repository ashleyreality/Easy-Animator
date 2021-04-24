package cs5004.animator.view;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.*;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.controller.commands.Loop;
import cs5004.animator.model.IAnimationModel;

/**
 * This class represents a visual view. The visual view outputs a visual display to the end-user of
 * the animation. It extends JFrame JFC/Swing component architecture.
 *
 */
public class VisualView extends JFrame implements IView, ActionListener {
  protected final IAnimationModel model;
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
    // Set the layout of the JFrame container to a BorderLayout
    //this.setLayout(new BorderLayout());

    newPanel(animationPanel);

    scrollPanel(animationPanel);

    this.pack();
    this.setVisible(true);

    drawShapes(0);
  }

  protected void newPanel(AnimationPanel animationPanel) {
    animationPanel.setPreferredSize(new Dimension(model.getBoundsWidth(), model.getBoundsHeight()));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(animationPanel);
  }

  protected void scrollPanel(AnimationPanel animationPanel) {
    // Create a Scroll Pane
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(scrollPane);
  }

  public void drawShapes(int fromTick) {
    // timer makes the program multi-threaded, so the program will keep running
    // this is making the program "sleep" so it doesn't draw
    // use the swing timer -- give the timer an action listener and implement action performed

    // i think passing in speed is wrong here -- in this context it is the amount of time
    // in between times the timer goes off and calls actionPerformed, in milliseconds
    // get the disappear time of the last shape in the sorted treemap
    int lastShapeTime = model.getEndTick();
    // For the duration of the animation, where the end of the animation is calculated via
    // lastShapeTime, run through each tick from 0 through lastShapeTime
    for (int tick = fromTick; tick <= lastShapeTime; tick++) {
      //System.out.println(tick);

      // Get the current epoch timestamp in milliseconds precision
      long startTime = System.currentTimeMillis();

      // Update the tick being passed into AnimationPanel
      animationPanel.setTick(tick);
      // Draw the shape(s) at this time using the paintComponent method inside of repaint(), which
      // is overridden in animationPanel
      animationPanel.repaint();
      System.out.println("draw frame " + tick);

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

  public Timer getTimer() {
    return timer;
  }

  @Override
  public void setTick(int tick) {
    this.tick = tick;
  }

  @Override
  public JCheckBox getLoopCheckbox() {
    throw new UnsupportedOperationException("This doesn't apply to the visual view");
  }

  /**
   * Show an error message.
   *
   * @param error the text of the error message
   */
  // fixme - do we use this anywhere? we can delete if not?
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
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

  @Override
  public void setStartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");
  }

  @Override
  public void setStopButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  @Override
  public void setRestartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  @Override
  public void setFastButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  @Override
  public void setSlowButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  @Override
  public void setLoopButtonListener(ItemListener itemEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  @Override
  public void setLoadButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  @Override
  public void setSaveTextButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");

  }

  @Override
  public void setSaveSVGButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("No need for button listener in visual view");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    throw new UnsupportedOperationException("Not supported in visual view");
  }

  public void loop() {
    int lastShapeTime = model.getEndTick();
    if (tick > lastShapeTime) {
      tick = 0;
      timer.restart();
    }
  }

  public void noLoop() {
    int lastShapeTime = model.getEndTick();
    if (tick > lastShapeTime) {
      timer.stop();
    }
  }

  @Override
  public void setSpeed(int speed) {
    throw new UnsupportedOperationException("Visual view does not support this");
  }

  @Override
  public int getSpeed() {
    throw new UnsupportedOperationException("Visual view does not support this");
  }

}


