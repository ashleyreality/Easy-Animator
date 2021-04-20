package cs5004.animator.view;

import cs5004.animator.model.IAnimationModel;

import javax.swing.*;

import java.awt.*;

public class PlaybackView extends VisualView {

  static JFrame frame;

  private JPanel PlaybackAnimation;
  private ButtonGroup rbgPlaybackAnimation;
  private GridBagLayout gbPlaybackAnimation;
  private GridBagConstraints gbcPlaybackAnimation;

  private AnimationPanel Animation;
  private JToggleButton startAnimation;
  private JToggleButton resumeButton;
  private JToggleButton restartAnimation;
  private JButton fasterButton;
  private JButton slowerButton;
  private JTextArea Speed;
  private JLabel speedLabel;
  private JCheckBox loopCheckbox;


  public PlaybackView(IAnimationModel model, int speed) {
    super(model, speed);

    // create outer container
    PlaybackAnimation = new JPanel();
    PlaybackAnimation.setBorder(BorderFactory.createTitledBorder("Playback Animation"));

    // group for buttons -- may not be needed?
    rbgPlaybackAnimation = new ButtonGroup();

    // create gridbag layout for outer container - this holds the jpanels
    gbPlaybackAnimation = new GridBagLayout();
    gbcPlaybackAnimation = new GridBagConstraints();
    PlaybackAnimation.setLayout(gbPlaybackAnimation);

    // add animation
    createAnimation();

    // add other components (buttons, text areas, checkboxes, etc) to frame
    startButton();
    resumeButton();
    restartButton();
    fastButton();
    slowButton();
    speedArea();
    loopCheckbox();

    // add scroll pane
    JScrollPane scpPlaybackAnimation = new JScrollPane(PlaybackAnimation);
    setContentPane(scpPlaybackAnimation);

    // finish up JFrame
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  private void createAnimation() {
    Animation = new AnimationPanel(model, 0);

    Animation.setPreferredSize(new Dimension((model.getBoundsWidth()), model.getBoundsHeight()));
    GridBagLayout gbAnimation = new GridBagLayout();
    GridBagConstraints gbcAnimation = new GridBagConstraints();

    Animation.setLayout(gbAnimation);
    setItemFields(Animation, 4, 0, 16, 17, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    PlaybackAnimation.add(Animation);

    // add animation
    int lastShapeTime = model.getEndTick();

    for (int tick = 0; tick < lastShapeTime; tick++) {
      // Get the current epoch timestamp in milliseconds precision
      long startTime = System.currentTimeMillis();

      // Update the tick being passed into AnimationPanel
      Animation.setTick(tick);

      // Repaint
      Animation.repaint();

      // Get the current epoch timestamp in milliseconds precision
      double endTime = System.currentTimeMillis();

      // Calculate the duration between epoch times
      double duration = endTime - startTime;

      // Calculate frame rate
      int desiredTime = 1000 / speed;

      // Calculate the remaining sleep time
      double remainingSleepTime = desiredTime - duration;

      // Set the sleep time using the remaining sleep time
      try {
        Thread.sleep((long) remainingSleepTime);
      } catch (InterruptedException e) {
        // Do nothing
      }
    }
  }

  private void startButton() {
    startAnimation = new JToggleButton("Start");
    startAnimation.setSelected(true);
    rbgPlaybackAnimation.add(startAnimation);
    setItemFields(startAnimation, 1, 1, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    PlaybackAnimation.add(startAnimation);
  }

  private void resumeButton() {
    resumeButton = new JToggleButton("Resume");
    rbgPlaybackAnimation.add(resumeButton);
    setItemFields(resumeButton, 1, 3, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    PlaybackAnimation.add(resumeButton);
  }

  private void restartButton() {
    restartAnimation = new JToggleButton("Restart");
    rbgPlaybackAnimation.add(restartAnimation);
    setItemFields(restartAnimation, 1, 4, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    PlaybackAnimation.add(restartAnimation);
  }

  private void fastButton() {
    fasterButton = new JButton("Faster");
    setItemFields(fasterButton, 1, 9, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    PlaybackAnimation.add(fasterButton);
  }

  private void slowButton() {
    slowerButton = new JButton("Slower");
    setItemFields(slowerButton, 1, 10, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    PlaybackAnimation.add(slowerButton);
  }

  private void speedArea() {
    // text area where speed will be displayed
    Speed = new JTextArea();
    setItemFields(Speed, 1, 8, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.SOUTH);
    PlaybackAnimation.add(Speed);

    // label for text area
    speedLabel = new JLabel("Current speed");
    setItemFields(speedLabel, 1, 7, 1, 1, GridBagConstraints.HORIZONTAL,
            1, 1, GridBagConstraints.SOUTH);
    PlaybackAnimation.add(speedLabel);
  }

  private void loopCheckbox() {
    loopCheckbox = new JCheckBox("  Loop?");
    loopCheckbox.setSelected(true);
    setItemFields(loopCheckbox, 1, 14, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    PlaybackAnimation.add(loopCheckbox);
  }

  private void setItemFields(JComponent item, int gridX, int gridY, int gridWidth,
                             int gridHeight, int fill, int weightX, int weightY,
                             int anchor) {
    gbcPlaybackAnimation.gridx = gridX;
    gbcPlaybackAnimation.gridy = gridY;
    gbcPlaybackAnimation.gridwidth = gridWidth;
    gbcPlaybackAnimation.gridheight = gridHeight;
    gbcPlaybackAnimation.fill = fill;
    gbcPlaybackAnimation.weightx = weightX;
    gbcPlaybackAnimation.weighty = weightY;
    gbcPlaybackAnimation.anchor = anchor;
    gbPlaybackAnimation.setConstraints(item, gbcPlaybackAnimation);

  }
}
