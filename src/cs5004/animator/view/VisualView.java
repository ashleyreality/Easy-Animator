package cs5004.animator.view;

import java.awt.*;
import javax.swing.*;
import cs5004.animator.model.IAnimationModel;

/**
 * This class represents a visual view. The visual view outputs a visual display to the end-user of
 * the animation. It extends JFrame JFC/Swing component architecture.
 *
 */
public class VisualView extends JFrame implements IView {
  private AnimationPanel animationPanel;
  private IAnimationModel model;
  private int speed;

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
   *
   */
  public void createView() {
    // Get the ticks in the file by the file's created AnimationModel which has its own shapeMap
    // IEvent has getEventBegin()
    // IShape has getAppear()

    // Set the layout of the JPanel container to BorderLayout which helps layout the components
    // and animation panel; set the size; make it visible
    this.setLayout(new BorderLayout());

    // Instantiate AnimationPanel with the tick at 0 to be updated later
    animationPanel = new AnimationPanel(model, 0);
    animationPanel.setPreferredSize(new Dimension(model.getBoundsWidth(), model.getBoundsHeight()));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(animationPanel, BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);

    // get the disappear time of the last shape in the sorted treemap
    int lastShapeTime = model.getShapeMap().lastKey().getDisappear();

    // For the duration of the animation where the end of the animation is calculated via
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
      // the framerate
      int desiredTime = 1000 / speed;

      // Calculate the remaining sleep time
      double remainingSleepTime = desiredTime - duration;
      // System.out.println(remainingSleepTime);

      // Set the sleep time using the remaining sleep time
      try {
        Thread.sleep((long) remainingSleepTime);
      }
      catch(InterruptedException e)
      {
        // Do nothing
      }

      // FixMe use the speed provided by the user to update the timer (instead of 100) - DONE
      // FixMe the ellipse hasn't shown up
      // FixMe the width is negative at some point -- why?
    }

    // Create a Scroll Pane
    JTextArea textArea = new JTextArea(5, 30);
    JScrollPane scrollPane = new JScrollPane(textArea);

    // Add the Scroll Pane to to the frame's content pane, placing it in center of border layout
    this.add(scrollPane, BorderLayout.CENTER);
  }

  public void refresh() { this.repaint(); }

  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }


  @Override
  public String getViewState() {
    return null;
  }
}



// specify the speed
// create a timer & specify the action listener to be used
// actionPerformed in the listener is getShapesAtTick, then draw shapes

// Timer timer = new Timer(speed * 1000, this);
// timer.start();

// create shapes
// https://docs.oracle.com/javase/8/docs/api/java/awt/geom/Rectangle2D.Double.html
// https://docs.oracle.com/javase/8/docs/api/java/awt/geom/Ellipse2D.Double.html
// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html#fill-java.awt.Shape-
// or: fill(new Rectangle(x, y, w, h);


// add components to the panel

// finish up JFrame