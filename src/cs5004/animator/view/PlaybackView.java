package cs5004.animator.view;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.controller.commands.Loop;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.Actions;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.JComponent;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;

/**
 * ____________________________________ CLASS: PlaybackView() _____________________________________.
 * This class represents a PlayBack view. The PlayBack view outputs a window that allows the user to
 * start and stop the animation, load an animation, slow down and speed up the animation, and save
 * the animation as a text or svg, loop the animation and start it over as desired.
 */
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
  private JButton saveAsText;
  private JButton saveAsSVG;
  private JButton loadFile;

  private JLabel speedNumber;
  private JLabel speedLabel;

  private JCheckBox loopCheckbox;

  /**
   * ________________________________ CONSTRUCTOR: PlayBackView() _________________________________.
   * This is the PlayBackView constructor. It takes in the animation model and its initial speed.
   *
   * @param model the animation model, an IAnimationModel
   * @param speed the initial speed of the animation, an int
   */
  public PlaybackView(IAnimationModel model, int speed) {
    super(model, speed);
  }

  /**
   * ___________________________________ METHOD: createView() _____________________________________.
   * This is the createView() method. It instantiates a Java Swing JPanel() object and constrains
   * the layout.
   */
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

    // New Speed Label
    speedLabel = new JLabel();
    // New Speed Number
    speedNumber = new JLabel();
    //this.speed = 1;
    playbackAnimationFrame.add(this.speedLabel);
    playbackAnimationFrame.add(this.speedNumber);

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
  }

  /**
   * ___________________________________ METHOD: frameAtTick() ____________________________________.
   * This is the frameAtTick() method which sets the tick/frame using the AnimationPanel, the JPanel
   * container class and repaints the animation using Java Swing/AWT API.
   *
   * @param tick the tick you'd like to set the current tick to
   */
  private void frameAtTick(int tick) {
    animationPanel.setTick(tick);
    // Draw the shape(s) at this time using the paintComponent method inside of repaint(), which
    // is overridden in animationPanel
    this.repaint();
  }

  /**
   * _________________________________ METHOD OVERRIDE: play() ____________________________________.
   * This is the play() method override from the IView interface. It instantiates a new Timer object
   * with a time delay defined by the user's speed selection and the view being the listener.
   */
  @Override
  public void play() {
    timer = new Timer(1000 / speed, this);
    timer.setInitialDelay(100);
    timer.start();
  }

  /**
   * _________________________________ METHOD: createAnimation() __________________________________.
   * This is the createAnimation() method which sets the preferred size of the animation panel and
   * constrains the layout of it.
   */
  private void createAnimation() {
    animationPanel.setPreferredSize(new Dimension((model.getBoundsWidth()),
            model.getBoundsHeight()));
    GridBagLayout gbAnimation = new GridBagLayout();

    animationPanel.setLayout(gbAnimation);
    setItemFields(animationPanel, 4, 0, 16, 17,
            GridBagConstraints.BOTH, 1, 0, GridBagConstraints.NORTH);
    playbackAnimationFrame.add(animationPanel);

    setVisible(true);
  }

  /**
   * ___________________________________ METHOD: startButton() ____________________________________.
   * This is the startButton() method which instantiates a new JToggleButton, which creates an
   * unselected toggle button with the specified text, "Start".
   */
  private void startButton() {
    startAnimation = new JToggleButton("Start");
    startAnimation.setSelected(true);
    buttonGroup.add(startAnimation);
    setItemFields(startAnimation, 1, 1, 1, 1,
            GridBagConstraints.BOTH, 1, 0, GridBagConstraints.NORTH);

    // set up event listener
    startAnimation.setActionCommand(Actions.START.name());

    // add button to frame
    playbackAnimationFrame.add(startAnimation);
  }

  /**
   * _____________________________ METHOD: setStartButtonListener() _______________________________.
   * Creates a listener for the start button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setStartButtonListener(ActionListener actionEvent) {
    startAnimation.addActionListener(actionEvent);
  }

  /**
   * __________________________________ METHOD: stopButton() ______________________________________.
   * Creates a JToggleButton for stopping/pausing the animation.
   */
  private void stopButton() {
    stopButton = new JToggleButton("Stop");
    buttonGroup.add(stopButton);
    setItemFields(stopButton, 1, 3, 1, 1,
            GridBagConstraints.BOTH, 1, 0, GridBagConstraints.NORTH);

    stopButton.setActionCommand(Actions.STOP.name());
    playbackAnimationFrame.add(stopButton);
  }

  /**
   * ______________________________ METHOD: setStopButtonListener() _______________________________.
   * Creates a listener for the stop button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setStopButtonListener(ActionListener actionEvent) {
    stopButton.addActionListener(actionEvent);
  }

  /**
   * ________________________________ METHOD: restartButton() _____________________________________.
   * Creates a JToggleButton for restarting the animation.
   */
  private void restartButton() {
    restartAnimation = new JToggleButton("Start over");
    buttonGroup.add(restartAnimation);
    setItemFields(restartAnimation, 1, 4, 1, 1,
            GridBagConstraints.BOTH, 1, 0, GridBagConstraints.NORTH);

    restartAnimation.setActionCommand(Actions.RESTART.name());
    playbackAnimationFrame.add(restartAnimation);
  }

  /**
   * ____________________________ METHOD: setRestartButtonListener() ______________________________.
   * Creates a listener for the restart button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setRestartButtonListener(ActionListener actionEvent) {
    restartAnimation.addActionListener(actionEvent);
  }

  /**
   * __________________________________ METHOD: fastButton() ______________________________________.
   * Creates a JToggleButton for speeding up the animation.
   */
  private void fastButton() {
    fasterButton = new JButton("Faster");
    setItemFields(fasterButton, 1, 9, 1, 1,
            GridBagConstraints.BOTH, 1, 0, GridBagConstraints.NORTH);

    fasterButton.setActionCommand(Actions.FAST.name());
    playbackAnimationFrame.add(fasterButton);
  }

  /**
   * _______________________________ METHOD: setFastButtonListener() ______________________________.
   * Creates a listener for the "faster" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setFastButtonListener(ActionListener actionEvent) {
    fasterButton.addActionListener(actionEvent);
  }

  /**
   * __________________________________ METHOD: slowButton() ______________________________________.
   * Creates a JToggleButton for slowing down the animation.
   */
  private void slowButton() {
    slowerButton = new JButton("Slower");
    setItemFields(slowerButton, 1, 10, 1, 1,
            GridBagConstraints.BOTH, 1, 0, GridBagConstraints.NORTH);

    slowerButton.setActionCommand(Actions.SLOW.name());
    playbackAnimationFrame.add(slowerButton);
  }

  /**
   * ______________________________ METHOD: setSlowButtonListener() _______________________________.
   * Creates a listener for the "slower" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSlowButtonListener(ActionListener actionEvent) {
    slowerButton.addActionListener(actionEvent);
  }

  /**
   * ____________________________________ METHOD: speedArea() _____________________________________.
   * This is the text area in the animation frame where the speed is displayed and updated.
   */
  private void speedArea() {
    int updatedSpeed = this.speed;
    //System.out.println("The updated speed is: " + updatedSpeed);

    // The speed displayed in the animation window should update:
    speedLabel.setText("Current Speed:");
    speedNumber.setText(String.valueOf(updatedSpeed));

    setItemFields(speedNumber, 1, 8, 1, 1,
            GridBagConstraints.BOTH, 1, 0, GridBagConstraints.SOUTH);

    setItemFields(speedLabel, 1, 7, 1, 1,
            GridBagConstraints.HORIZONTAL, 1, 1, GridBagConstraints.SOUTH);
  }

  /**
   * ___________________________________ METHOD: loopCheckbox() ___________________________________.
   * This is the checkbox in the animation frame where the user selects whether the animation should
   * be automatically replayed.
   */
  private void loopCheckbox() {
    loopCheckbox = new JCheckBox("  Loop?");

    setItemFields(loopCheckbox, 1, 14, 1, 1,
            GridBagConstraints.BOTH, 1, 0, GridBagConstraints.NORTH);

    loopCheckbox.setActionCommand(Actions.LOOP.name());
    playbackAnimationFrame.add(loopCheckbox);
  }

  /**
   * _________________________________ METHOD: getLoopCheckbox() __________________________________.
   * This getter method returns the checkbox in the animation frame.
   *
   * @return the loopCheckbox, a JCheckBox
   */
  public JCheckBox getLoopCheckbox() {
    return loopCheckbox;
  }


  /**
   * ______________________________ METHOD: setLoopButtonListener() _______________________________.
   * Creates a listener for the "loop" checkbox.
   *
   * @param itemEvent the event, an ItemListener
   */
  public void setLoopButtonListener(ItemListener itemEvent) {
    loopCheckbox.addItemListener(itemEvent);
  }

  /**
   * __________________________________ METHOD: loadButton() ______________________________________.
   * Creates a JToggleButton for loading a .txt file that would be build a new animation to play.
   */
  private void loadButton() {
    loadFile = new JButton("Load file");
    setItemFields(loadFile, 11, 17, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);

    loadFile.setActionCommand(Actions.LOAD.name());
    playbackAnimationFrame.add(loadFile);
  }

  /**
   * ______________________________ METHOD: setLoadButtonListener() _______________________________.
   * Creates a listener for the "load" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setLoadButtonListener(ActionListener actionEvent) {
    loadFile.addActionListener(actionEvent);
  }

  /**
   * ________________________________ METHOD: saveTextButton() ____________________________________.
   * Creates a JToggleButton for saving an animation file as a text file.
   */
  private void saveTextButton() {
    saveAsText = new JButton("Save as text");
    setItemFields(saveAsText, 14, 17, 1, 1,
            GridBagConstraints.BOTH, 1, 0, GridBagConstraints.NORTH);

    saveAsText.setActionCommand(Actions.SAVETEXT.name());
    playbackAnimationFrame.add(saveAsText);
  }

  /**
   * ____________________________ METHOD: setSaveTextButtonListener() _____________________________.
   * Creates a listener for the "save as text" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSaveTextButtonListener(ActionListener actionEvent) {
    saveAsText.addActionListener(actionEvent);
  }

  /**
   * _________________________________ METHOD: saveSVGButton() ____________________________________.
   * Creates a JToggleButton for saving an animation file as an svg file.
   */
  private void saveSVGButton() {
    saveAsSVG = new JButton("Save as SVG");
    setItemFields(saveAsSVG, 17, 17, 1, 1, GridBagConstraints.BOTH,
            1, 0, GridBagConstraints.NORTH);

    saveAsSVG.setActionCommand(Actions.SAVESVG.name());
    playbackAnimationFrame.add(saveAsSVG);
  }

  /**
   * _____________________________ METHOD: setSaveSVGButtonListener() _____________________________.
   * Creates a listener for the "save as svg" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSaveSVGButtonListener(ActionListener actionEvent) {
    saveAsSVG.addActionListener(actionEvent);
  }

  /**
   * __________________________________ METHOD: setItemFields() ___________________________________.
   * This is the setItemFields() method. It uses the GridBagConstraints object to set the animation
   * constraints.
   *
   * @param item       an instance of JComponent, which is a base class for both standard and custom
   *                   components that use the Swing architecture
   * @param gridX      an int that specifies the cell containing the leading edge of the component's
   *                   display area, where the first cell in a row has gridx=0. The leading edge of
   *                   a component's display area is its left edge for a horizontal, left-to-right
   *                   container and its right edge for a horizontal, right-to-left container.
   * @param gridY      an int that specifies the cell at the top of the component's display area,
   *                   where the topmost cell has gridy=0.
   * @param gridWidth  an int that specifies the number of cells in a row for the component's
   *                   display area.
   * @param gridHeight an int that specifies the number of cells in a column for the component's
   *                   display area.
   * @param fill       an int that determines whether to and how to resize the component's display
   *                   area, when the component's display area is larger than the component's
   *                   requested size.
   * @param weightX    an int that specifies how to distribute extra horizontal space, where the
   *                   default value of this field is 0. weightx should be a non-negative value.
   * @param weightY    an int that specifies how to distribute extra vertical space. The default
   *                   value of this field is 0. weighty should be a non-negative value.
   * @param anchor     an int that determines where, within the display area, to place the component
   *                   when the component is smaller than its display area.
   */
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

  /**
   * _____________________________________ METHOD: setSpeed() _____________________________________.
   * Sets the speed of the animation.
   *
   * @param speed the speed to set the animation to, an int
   */
  @Override
  public void setSpeed(int speed) {
    this.speed = speed;

    // Update the speed
    speedArea();
  }

  /**
   * _____________________________________ METHOD: getSpeed() _____________________________________.
   * Gets the speed of the animation.
   *
   * @return the speed of the animation, an int
   */
  @Override
  public int getSpeed() {
    return this.speed;
  }

  /**
   * _____________________________ METHOD OVERRIDE: actionPerformed() _____________________________.
   * Repaints the animation to play the animation.
   *
   * @param e the event, an ActionEvent
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    animationPanel.setTick(tick);
    animationPanel.repaint();
    //System.out.println("draw frame " + tick);
    tick++;
    //noLoop();
    AnimationCommand loop = new Loop();
    try {
      loop.go(model, this);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  /**
   * ____________________________________ METHOD OVERRIDE: loop() _________________________________.
   * Loops the animation to play the animation.
   */
  @Override
  public void loop() {
    int lastShapeTime = model.getEndTick();
    if (tick > lastShapeTime) {
      tick = 0;
      timer.restart();
    }
  }

  /**
   * __________________________________ METHOD OVERRIDE: noLoop() _________________________________.
   * Does not loop the animation after the last tick.
   */
  @Override
  public void noLoop() {
    int lastShapeTime = model.getEndTick();
    if (tick > lastShapeTime) {
      timer.stop();
    }
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
    this.model.clearShapeMap();
  }
}
