package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JCheckBox;
import javax.swing.Timer;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;

/**
 * This class represents a text view. The text view outputs a text description of the animation to
 * an appendable.
 */
public class TextView implements IView {
  private IAnimationModel model;
  private final PrintStream out;
  private final StringBuilder sb;

  /**
   * Constructs a text view, given a model and a string representing the file to be written to.
   *
   * @param model     the AnimationModel instance you want to create a view for
   * @param outString name of the file to write to
   * @throws FileNotFoundException if the out is not found
   */
  public TextView(IAnimationModel model, String outString) throws IOException {
    this.model = model;
    this.sb = new StringBuilder();

    if (outString.equals("System.out")) {
      this.out = new PrintStream(System.out);
    } else {
      this.out = new PrintStream(outString);
    }
  }


  /**
   * _______________________________ METHOD: buildTheTextString() _________________________________.
   * Build the TextView String.
   */
  private void buildTheTextString() {
    List<IShape> shapeList = new ArrayList<>(model.getShapeMap().keySet());

    // For each shape, add its IShape createString() to the shapeList ArrayList
    for (IShape shape : shapeList) {
      if (shape.getColor() == null || shape.getLocation() == null || shape.getHeight() == 0
              || shape.getWidth() == 0) {
        throw new IllegalArgumentException("Shape parameters not set.");
      }
      sb.append(shape.createString());
      sb.append("\n");
    }
    sb.append("\n");

    // For each shape, add its appear/disappear string the shapeList ArrayList
    for (IShape shape : shapeList) {
      sb.append(shape.appearString());
      sb.append("\n");
    }
    sb.append("\n");
    // Sort the events in terms of begin time
    Comparator<IEvent> sortByEventBegin = Comparator.comparingInt(IEvent::getEventBegin);

    // Create a list of sorted events
    List<IEvent> eventList = model.getShapeMap().values().stream().flatMap(Collection::stream)
            .sorted(sortByEventBegin).collect(Collectors.toList());

    // Add the changes/events strings to out
    for (IEvent event : eventList) {
      sb.append(event.toString());
      sb.append("\n");
    }
  }

  /**
   * Creates a text view.
   */
  public void createView() {
    printView();
    System.exit(0);
  }

  /**
   * Prints the current view to an out file.
   */
  public void printView() {
    buildTheTextString();
    out.print(sb.toString());
    out.close();
  }

  /**
   * Gets the state of the view.
   *
   * @return a string representation of the current state of the view
   */
  public String getViewState() {
    buildTheTextString();
    return sb.toString();
  }

  /**
   * _____________________________ METHOD: setStartButtonListener() _______________________________.
   * Creates a listener for the start button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setStartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * ______________________________ METHOD: setStopButtonListener() _______________________________.
   * Creates a listener for the stop button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setStopButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * ____________________________ METHOD: setRestartButtonListener() ______________________________.
   * Creates a listener for the restart button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setRestartButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * _______________________________ METHOD: setFastButtonListener() ______________________________.
   * Creates a listener for the "faster" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setFastButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * ______________________________ METHOD: setSlowButtonListener() _______________________________.
   * Creates a listener for the "slower" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSlowButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * ______________________________ METHOD: setLoopButtonListener() _______________________________.
   * Creates a listener for the "loop" checkbox.
   *
   * @param itemEvent the event, an ItemListener
   */
  @Override
  public void setLoopButtonListener(ItemListener itemEvent) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * ______________________________ METHOD: setLoadButtonListener() _______________________________.
   * Creates a listener for the "load" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setLoadButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * ____________________________ METHOD: setSaveTextButtonListener() _____________________________.
   * Creates a listener for the "save as text" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSaveTextButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * _____________________________ METHOD: setSaveSVGButtonListener() _____________________________.
   * Creates a listener for the "save as svg" button.
   *
   * @param actionEvent an event, an ActionListener
   */
  @Override
  public void setSaveSVGButtonListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * Draws the shapes at a given tick.
   *
   * @param fromTick the current tick
   */
  @Override
  public void drawShapes(int fromTick) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * Gets the timer of the animation.
   * @return the timer, a Timer
   */
  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * Sets the tick in the animation Timer.
   * @param tick a tick/frame rate, an int
   */
  @Override
  public void setTick(int tick) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * Gets the loop checkbox from the aniimation.
   * @return a loop checkbox, a JCheckBox
   */
  @Override
  public JCheckBox getLoopCheckbox() {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * ____________________________________ METHOD OVERRIDE: loop() _________________________________.
   * Loops the animation to play the animation.
   */
  @Override
  public void loop() {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * __________________________________ METHOD OVERRIDE: noLoop() _________________________________.
   * Does not loop the animation after the last tick.
   */
  @Override
  public void noLoop() {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * _____________________________________ METHOD: setSpeed() _____________________________________.
   * Sets the speed of the animation.
   *
   * @param speed the speed to set the animation to, an int
   */
  @Override
  public void setSpeed(int speed) {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * _____________________________________ METHOD: getSpeed() _____________________________________.
   * Gets the speed of the animation.
   *
   * @return the speed of the animation, an int
   */
  @Override
  public int getSpeed() {
    throw new UnsupportedOperationException("The text view does not use this.");
  }

  /**
   * _________________________________ METHOD OVERRIDE: play() ____________________________________.
   * This is the play() method override from the IView interface. It instantiates a new Timer object
   * with a time delay defined by the user's speed selection and the view being the listener.
   */
  @Override
  public void play() {
    throw new UnsupportedOperationException("The text view does not use this.");
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


