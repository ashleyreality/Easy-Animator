package cs5004.animator.controller.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.controller.AnimatorController;
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
  public void go(IAnimationModel model, IView view) {
    System.out.println("Load file command received");
    if (view.getTimer() != null) {
      view.getTimer().stop();
    }

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose an animation file to load.");

    // Specify allowable file types that can be loaded
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setAcceptAllFileFilterUsed(false);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("XML and TXT files",
            "xml", "txt");
    fileChooser.addChoosableFileFilter(filter);

    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();

      // Clear the model to remove all events and shapes from the model
      model.clearShapeMap();
      view.clearModel();

      // Do what the controller does to add all the shapes and events to the model

      // New build
      AnimationBuilder build = newBuild(model);

      // New readable
      Readable readableFile = null;
      try {
        readableFile = new FileReader(selectedFile);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }

      // Fill the model with the readable and build
      AnimatorController.fillTheModel(readableFile, build);
    }
  }

  /**
   * ___________________________ METHOD OVERRIDE: actionPerformed() _______________________________.
   * This is an override of the actionPerformed() method override from the ActionListener interface,
   * which is invoked when an action event occurs whenever an action is performed by the user. An
   * example of an action is when a user clicks a button. In this case, an action performed occurs
   * when the user clicks on the Load button, a button to load file for a new animation.
   *
   * @param e the item event, an ActionEvent
   */
  @Override
  public void actionPerformed(ActionEvent e) {
  }
}
