package cs5004.animator.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import cs5004.animator.model.IAnimationModel;

/**
 * This class represents a visual view. The visual view outputs a visual display to the end-user of
 * the animation. It extends JFrame JFC/Swing component architecture.
 *
 */
public class VisualView extends JFrame implements IView {
  protected final IAnimationModel model;
  protected final int speed;

  /**
   * The VisualView constructor takes in a model and the specified speed.
   * @param model the model that the view follows for the animation, an IAnimation
   * @param speed the speed of the animation, an int
   */
  public VisualView(IAnimationModel model, int speed) {
    super();
    this.model = model;
    this.speed = speed;
  }

  /**
   * Creates a visual view.
   */
  public void createView() {
    // Set the layout of the JFrame container to a BorderLayout
    //this.setLayout(new BorderLayout());

    // Instantiate AnimationPanel with the tick at 0 to be updated later
    AnimationPanel animationPanel = new AnimationPanel(model, 0);
    animationPanel.setPreferredSize(new Dimension(model.getBoundsWidth(), model.getBoundsHeight()));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(animationPanel);

    // Create a Scroll Pane
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(scrollPane);

    this.pack();
    this.setVisible(true);

    // get the disappear time of the last shape in the sorted treemap
    int lastShapeTime = model.getEndTick();
    // For the duration of the animation, where the end of the animation is calculated via
    // lastShapeTime, run through each tick from 0 through lastShapeTime
    for (int tick = 0; tick < lastShapeTime; tick++) {
      //System.out.println(tick);

      // Get the current epoch timestamp in milliseconds precision
      long startTime = System.currentTimeMillis();

      // Update the tick being passed into AnimationPanel
      animationPanel.setTick(tick);

      // Draw the shape(s) at this time using the paintComponent method inside of repaint(), which
      // is overridden in animationPanel
      animationPanel.repaint();

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
}


