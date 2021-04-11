package cs5004.animator;

import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.util.ViewFactory;


public class EasyAnimator {
  private static IAnimationModel model;

  public static void main(String[] args) throws FileNotFoundException {

    // make the appendable here & pass it in to TextView (for example)
    // and decide here how it will be output (command line, file, or multiple places, etc.)
    // because the command line
    // gives the info to the controller

    model = new AnimationModelImpl();
    JFrame frame = new JFrame("Frame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // convert args array to string
    StringBuilder sb = new StringBuilder();
    for (String arg : args) {
      sb.append(arg);
      sb.append(" ");
    }

    // Get the in file name
    Scanner in = new Scanner(sb.toString());
    in.findInLine("-in");
    String inResult = in.next();

    // Open the in file and send it to Builder
    Readable file = null;
    try {
      file = new FileReader(inResult);
    } catch (FileNotFoundException e) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "Input file not found", "Input file error",
              JOptionPane.ERROR_MESSAGE);
      System.out.println("The input file was not found.");
      e.printStackTrace();
      System.exit(1);
    }
    AnimationBuilder build = new Builder(model);
    model = (IAnimationModel) AnimationReader.parseFile(file, build);

    // Get the view type
    Scanner view = new Scanner(sb.toString());
    view.findInLine("-view");
    String viewResult = view.next();

    // if the view type is wrong, pop up an error message
    if (!viewResult.equalsIgnoreCase("text")
            && !viewResult.equalsIgnoreCase("svg")
            && !viewResult.equalsIgnoreCase("visual")) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "The view type must be text, svg, or visual",
              "Invalid view", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }

    // Get the output file name
    Scanner out = new Scanner(sb.toString());
    out.findInLine("-out");
    String outResult = out.next();
    System.out.println(outResult);

    // Get the speed
    // fixme - what to do if speed isn't given and also isn't needed, like for text view?
    Scanner speed = new Scanner(sb.toString());
    speed.findInLine("-speed");
    String speedResult = speed.next();
    try {
      int speedInt = Integer.parseInt(speedResult);
      if (Integer.parseInt(speedResult) < 1) throw new NumberFormatException();
    } catch (NumberFormatException n) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "Speed must be a positive integer",
              "Invalid speed", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }

    // Make a new ViewFactory object
    ViewFactory newView = new ViewFactory(viewResult, model, outResult, speedResult);
    // Create the view
    newView.create();

    // JFrame finishing up
    frame.pack();
    System.exit(0);
  }
}
