package cs5004.animator.controller.commands;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.controller.AnimatorController;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;

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
    // do something
    System.out.println("Load file command received");

    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      //try {
        // Get the file name
        String filename = selectedFile.getName();
        // Load the file


        // First, we need the AnimatorController
        // AnimationController can be found in...
        //view.getModel();
        //IAnimationModel model = new AnimationModelImpl();
        //view.setModel(model);

        // 1) Clear your model. It's given to you as the model variable
        // So you'll call model.clear()
        // The clear() function is supposed remove all events and shapes from a model, so the model is essentially empty again

        // 2), Then, you'll figure out how your controller is adding all the shapes and stuff to the model.
        // You'll do the same thing here.
        //
      //} catch (IOException ioException) {
      //  ioException.printStackTrace();
      //}
      System.out.println(selectedFile.getName());
    }

    //JFrame.setDefaultLookAndFeelDecorated(true);
    //JDialog.setDefaultLookAndFeelDecorated(true);
    //JFrame frame = new JFrame("JComboBox Test");
    //frame.setLayout(new FlowLayout());
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   //JButton button = new JButton("Select File");
//    button.addActionListener(new ActionListener() {


//      public void actionPerformed(ActionEvent e) {
//        JFileChooser fileChooser = new JFileChooser();
//        int returnValue = fileChooser.showOpenDialog(null);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//          File selectedFile = fileChooser.getSelectedFile();
//          try {
//            Desktop.getDesktop().open(selectedFile);
//          } catch (IOException ioException) {
//            ioException.printStackTrace();
//          }
//          System.out.println(selectedFile.getName());
//        }
//      }
//    });

  }

  /**
   * ___________________________ METHOD OVERRIDE: actionPerformed() _______________________________.
   * This is an override of the actionPerformed() method override from the ActionListener interface,
   * which is invoked when an action event occurs whenever an action is performed by the user. An
   * example of an action is when a user clicks a button. In this case, an action performed occurs
   * when the user clicks on the SpeedUp button a button to increase the speed of the animation.
   *
   *
   * @param e the item event, an ActionEvent
   */
  @Override
  public void actionPerformed(ActionEvent e) {
//    JFileChooser fileChooser = new JFileChooser();
//    int returnValue = fileChooser.showOpenDialog(null);
//    if (returnValue == JFileChooser.APPROVE_OPTION) {
//      File selectedFile = fileChooser.getSelectedFile();
//      try {
//        Desktop.getDesktop().open(selectedFile);
//      } catch (IOException ioException) {
//        ioException.printStackTrace();
//      }
//      System.out.println(selectedFile.getName());
//    }
  }
}
