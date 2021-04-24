package cs5004.animator.view;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.controller.commands.Loop;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.Actions;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;

public class PlaybackView extends VisualView {

  private JPanel playbackAnimationFrame;
  private ButtonGroup buttonGroup;
  private GridBagLayout playbackLayout;
  private GridBagConstraints layoutConstraints;

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

    // create "GridBag" layout for outer container - this holds the jPanels in the right place
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
    this.add(scrollAnimation);
    setContentPane(scrollAnimation);

    // finish up JFrame
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();


    frameAtTick(0);
    //drawShapes(0);
  }

  private void frameAtTick(int tick) {
    animationPanel.setTick(tick);
    // Draw the shape(s) at this time using the paintComponent method inside of repaint(), which
    // is overridden in animationPanel
    this.repaint();

  }

  @Override
  public void play() {
    timer = new Timer(1000 / speed, this);
    timer.setInitialDelay(100);
    timer.start();
  }

  private void createAnimation() {
    animationPanel.setPreferredSize(new Dimension((model.getBoundsWidth()), model.getBoundsHeight()));
    GridBagLayout gbAnimation = new GridBagLayout();
    GridBagConstraints gbcAnimation = new GridBagConstraints();

    animationPanel.setLayout(gbAnimation);
    setItemFields(animationPanel, 4, 0, 16, 17, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);
    playbackAnimationFrame.add(animationPanel);

    setVisible(true);
    //super.drawShapes(animationPanel);
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

  @Override
  public void setStopButtonListener(ActionListener actionEvent) {
    stopButton.addActionListener(actionEvent);
  }

  private void restartButton() {
    restartAnimation = new JToggleButton("Start over");
    buttonGroup.add(restartAnimation);
    setItemFields(restartAnimation, 1, 4, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);

    restartAnimation.setActionCommand(Actions.RESTART.name());
    playbackAnimationFrame.add(restartAnimation);
  }

  @Override
  public void setRestartButtonListener(ActionListener actionEvent) {
    restartAnimation.addActionListener(actionEvent);
  }

  private void fastButton() {
    fasterButton = new JButton("Faster");
    setItemFields(fasterButton, 1, 9, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);

    fasterButton.setActionCommand(Actions.FAST.name());
    playbackAnimationFrame.add(fasterButton);
  }

  @Override
  public void setFastButtonListener(ActionListener actionEvent) {
    fasterButton.addActionListener(actionEvent);
  }

  private void slowButton() {
    slowerButton = new JButton("Slower");
    setItemFields(slowerButton, 1, 10, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);

    slowerButton.setActionCommand(Actions.SLOW.name());
    playbackAnimationFrame.add(slowerButton);
  }

  @Override
  public void setSlowButtonListener(ActionListener actionEvent) {
    slowerButton.addActionListener(actionEvent);
  }

  private void speedArea() {
    // text area where speed will be displayed
    speedNumber = new JTextArea(String.valueOf(speed));
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

    setItemFields(loopCheckbox, 1, 14, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);

    loopCheckbox.setActionCommand(Actions.LOOP.name());
    playbackAnimationFrame.add(loopCheckbox);
  }

  public JCheckBox getLoopCheckbox() { return loopCheckbox; }


  public void setLoopButtonListener(ItemListener itemEvent) {
    loopCheckbox.addItemListener(itemEvent);
  }

  private void loadButton() {
    loadFile = new JButton("Load file");
    setItemFields(loadFile, 11, 17, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);

    loadFile.setActionCommand(Actions.LOAD.name());
    playbackAnimationFrame.add(loadFile);
  }

  @Override
  public void setLoadButtonListener(ActionListener actionEvent) {
    loadFile.addActionListener(actionEvent);
  }

  private void saveTextButton() {
    saveAsText = new JButton("Save as text");
    setItemFields(saveAsText, 14, 17, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);

    saveAsText.setActionCommand(Actions.SAVETEXT.name());
    playbackAnimationFrame.add(saveAsText);
  }

  @Override
  public void setSaveTextButtonListener(ActionListener actionEvent) {
    saveAsText.addActionListener(actionEvent);
  }

  private void saveSVGButton() {
    saveAsSVG = new JButton("Save as SVG");
    setItemFields(saveAsSVG, 17, 17, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);

    saveAsSVG.setActionCommand(Actions.SAVESVG.name());
    playbackAnimationFrame.add(saveAsSVG);
  }

  @Override
  public void setSaveSVGButtonListener(ActionListener actionEvent) {
    saveAsSVG.addActionListener(actionEvent);
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

  @Override
  public void setSpeed(int speed) {
    this.speed =speed;
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    animationPanel.setTick(tick);
    animationPanel.repaint();
    System.out.println("draw frame " + tick);
    tick++;
    noLoop();
    AnimationCommand loop = new Loop();
    try {
      loop.go(model, this);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  @Override
  public void loop() {
    int lastShapeTime = model.getEndTick();
    if (tick > lastShapeTime) {
      tick = 0;
      timer.restart();
    }
  }

  @Override
  public void noLoop() {
    int lastShapeTime = model.getEndTick();
    if (tick > lastShapeTime) {
      timer.stop();
    }
  }

  /*
  public void newText() throws IOException {
    // we can figure out if we want to name the outstring something good later
    new TextView(model, "text.txt");
  }

  public void newSVG() throws IOException {
    new SVGView(model, "svg.txt", speed);
  }*/

}
