package cs5004.animator.controller.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;

import static cs5004.animator.controller.AnimatorController.newBuild;

/**
 * ___________________________________ CLASS: LoadFile {} _________________________________________.
 * This is the LoadFile class which implements the AnimationCommand interface, which contains
 * methods common to all animation commands. The LoadFile receives the command to load a file per
 * the user button click in the animation playback window.
 */
public class LoadFile implements AnimationCommand, ActionListener {

  /**
   * _________________________________ METHOD OVERRIDE: go() ______________________________________.
   * This is an override of the go() method from the AnimationCommand interface. It performs the
   * command, being loading a file.
   *
   * @param model the IAnimationModel instance containing the animation data.
   * @param view  the type of view specified, an IView
   */
  @Override
  public void go(IAnimationModel model, IView view) throws FileNotFoundException {
    // do something
    System.out.println("Load file command received");
    view.getTimer().stop();

    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      // Get the file name
      String filename = selectedFile.getName();

      System.out.println(model.getShapeMap().toString());

      // To load the file...
      // 1) Clear the model to remove all events and shapes from a model, so the model is
      // essentially empty again
      model.clearShapeMap();
      view.clearModel();
      System.out.println(model.getShapeMap().toString());


      // 2) Do what the controller does to add all the shapes and events to the model.
      //  public void loadFile(String inputFile) {
    // Takes in file name


    AnimationBuilder build = newBuild(model);

    Readable readableFile = new FileReader(filename);
    // Step 7) Fill the model
    fillTheModel(readableFile, build);

    System.out.println(selectedFile.getName());
    }
  }

  /**
   * ___________________________ METHOD OVERRIDE: actionPerformed() _______________________________.
   * This is an override of the actionPerformed() method override from the ActionListener interface,
   * which is invoked when an action event occurs whenever an action is performed by the user. An
   * example of an action is when a user clicks a button. In this case, an action performed occurs
   * when the user clicks on the SpeedUp button a button to increase the speed of the animation.
   *
   * @param e the item event, an ActionEvent
   */
  @Override
  public void actionPerformed(ActionEvent e) {
  }


  /**
   * ____________________________ CONTROLLER STEP 7: fillTheModel() _______________________________.
   * Parse the file so the model is filled, and set the model variable to the filled model.
   *
   * @param file  the file being input, a Readable
   * @param build the build of the model, an AnimationBuilder
   */
  public static void fillTheModel(Readable file, AnimationBuilder build) {
    AnimationReader.parseFile(file, build);
  }

  public static Readable checkInputFile(String inputName, JFrame frame) {
    return fileExceptions(inputName, frame);
  }

  /**
   * _____________________________ HELPER METHOD: fileExceptions() ________________________________.
   * Checks for problems with input files and throws exceptions if problems exist.
   *
   * @param inputName the name of the in file
   * @param frame     the JFrame used for exception-throwing
   * @return a FileReader object with the in file specified
   */
  public static Readable fileExceptions(String inputName, JFrame frame) {
    try {
      return new FileReader(inputName);
    } catch (FileNotFoundException e) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "Input file not found", "Input file error",
              JOptionPane.ERROR_MESSAGE);
      System.out.println("The input file was not found.");
      e.printStackTrace();
    }
    return null;
  }
}
