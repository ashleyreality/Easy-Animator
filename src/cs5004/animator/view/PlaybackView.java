package cs5004.animator.view;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.Actions;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class PlaybackView extends VisualView {

  private JPanel playbackAnimationFrame;
  private ButtonGroup buttonGroup;
  private GridBagLayout playbackLayout;
  private GridBagConstraints layoutConstraints;

  private AnimationPanel animationPanel;
  private JToggleButton startAnimation;
  private JToggleButton stopButton;
  private JToggleButton restartAnimation;
  private JButton fasterButton;
  private JButton slowerButton;
  private JTextArea speedNumber;
  private JLabel speedLabel;
  private JCheckBox loopCheckbox;

  private JButton saveAsText;
  private JButton saveAsSVG;
  private JButton loadFile;


  public PlaybackView(IAnimationModel model, int speed) {
    super(model, speed);
  }

  @Override
  public void createView() {
    // create outer container
    playbackAnimationFrame = new JPanel();
    playbackAnimationFrame.setBorder(BorderFactory.createTitledBorder("Playback Animation"));

    // create "GridBag" layout for outer container - this holds the jpanels
    playbackLayout = new GridBagLayout();
    layoutConstraints = new GridBagConstraints();
    playbackAnimationFrame.setLayout(playbackLayout);

    // group for buttons -- may not be needed?
    buttonGroup = new ButtonGroup();

    // add animation
    createAnimation();

    // add other components (buttons, text areas, checkboxes, etc) to frame
    startButton();
    stopButton();
    restartButton();
    fastButton();
    slowButton();
    speedArea();
    loopCheckbox();
    loadButton();
    saveTextButton();
    saveSVGButton();

    // add scroll pane
    JScrollPane scrollAnimation = new JScrollPane(playbackAnimationFrame);
    setContentPane(scrollAnimation);

    // finish up JFrame
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  private void createAnimation() {
    animationPanel = new AnimationPanel(model, 0);

    animationPanel.setPreferredSize(new Dimension((model.getBoundsWidth()), model.getBoundsHeight()));
    GridBagLayout gbAnimation = new GridBagLayout();
    GridBagConstraints gbcAnimation = new GridBagConstraints();

    animationPanel.setLayout(gbAnimation);
    setItemFields(animationPanel, 4, 0, 16, 17, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    playbackAnimationFrame.add(animationPanel);

    // add animation
    int lastShapeTime = model.getEndTick();

    for (int tick = 0; tick < lastShapeTime; tick++) {
      // Get the current epoch timestamp in milliseconds precision
      long startTime = System.currentTimeMillis();

      // Update the tick being passed into AnimationPanel
      animationPanel.setTick(tick);

      // Repaint
      animationPanel.repaint();

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
    buttonGroup.add(startAnimation);
    setItemFields(startAnimation, 1, 1, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);

    // set up event listener
    startAnimation.setActionCommand(Actions.START.name());

    // add button to frame
    playbackAnimationFrame.add(startAnimation);
  }

  @Override
  public void setStartButtonListener(ActionListener actionEvent) {
    startAnimation.addActionListener(actionEvent);
  }

  private void stopButton() {
    stopButton = new JToggleButton("Stop");
    buttonGroup.add(stopButton);
    setItemFields(stopButton, 1, 3, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);

    stopButton.setActionCommand(Actions.STOP.name());
    playbackAnimationFrame.add(stopButton);
  }



  private void restartButton() {
    restartAnimation = new JToggleButton("Start over");
    buttonGroup.add(restartAnimation);
    setItemFields(restartAnimation, 1, 4, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    playbackAnimationFrame.add(restartAnimation);
  }

  private void fastButton() {
    fasterButton = new JButton("Faster");
    setItemFields(fasterButton, 1, 9, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    playbackAnimationFrame.add(fasterButton);
  }

  private void slowButton() {
    slowerButton = new JButton("Slower");
    setItemFields(slowerButton, 1, 10, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    playbackAnimationFrame.add(slowerButton);
  }

  private void speedArea() {
    // text area where speed will be displayed
    speedNumber = new JTextArea();
    setItemFields(speedNumber, 1, 8, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.SOUTH);
    playbackAnimationFrame.add(speedNumber);

    // label for text area
    speedLabel = new JLabel("Current speed");
    setItemFields(speedLabel, 1, 7, 1, 1, GridBagConstraints.HORIZONTAL,
            1, 1, GridBagConstraints.SOUTH);
    playbackAnimationFrame.add(speedLabel);
  }

  private void loopCheckbox() {
    loopCheckbox = new JCheckBox("  Loop?");
    loopCheckbox.setSelected(true);
    setItemFields(loopCheckbox, 1, 14, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    playbackAnimationFrame.add(loopCheckbox);
  }

  private void loadButton() {
    loadFile = new JButton("Load file");
    setItemFields(loadFile, 11, 17, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    playbackAnimationFrame.add(loadFile);
  }

  private void saveTextButton() {
    saveAsText = new JButton("Save as text");
    setItemFields(saveAsText, 14, 17, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    playbackAnimationFrame.add(saveAsText);
  }

  private void saveSVGButton() {
    saveAsSVG = new JButton("Save as SVG");
    setItemFields(saveAsSVG, 17, 17, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    playbackAnimationFrame.add(saveAsSVG);
  }

  private void setItemFields(JComponent item, int gridX, int gridY, int gridWidth,
                             int gridHeight, int fill, int weightX, int weightY,
                             int anchor) {
    layoutConstraints.gridx = gridX;
    layoutConstraints.gridy = gridY;
    layoutConstraints.gridwidth = gridWidth;
    layoutConstraints.gridheight = gridHeight;
    layoutConstraints.fill = fill;
    layoutConstraints.weightx = weightX;
    layoutConstraints.weighty = weightY;
    layoutConstraints.anchor = anchor;
    playbackLayout.setConstraints(item, layoutConstraints);

  }


}
