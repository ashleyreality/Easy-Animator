package cs5004.animator;

import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.*;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.util.ViewFactory;


public class EasyAnimator {
  private static IAnimationModel model;

  public static void main(String[] args) throws FileNotFoundException {
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
      JOptionPane.showMessageDialog(frame, "Input file not found", "Input file error",
              JOptionPane.ERROR_MESSAGE);
      System.out.println("The input file was not found.");
      e.printStackTrace();
    }
    AnimationBuilder build = new Builder(model);
    model = (IAnimationModel) AnimationReader.parseFile(file, build);

    // Get the view type
    Scanner view = new Scanner(sb.toString());
    view.findInLine("-view");
    String viewResult = view.next();

    // Get the output file name
    Scanner out = new Scanner(sb.toString());
    out.findInLine("-out");
    String outResult = out.next();
    System.out.println(outResult);

    // Get the speed
    Scanner speed = new Scanner(sb.toString());
    speed.findInLine("-speed");
    String speedResult = speed.next();
    System.out.println(speedResult);

    // what to do if speed isn't given and also isn't needed, like for text view?

    // If command line arguments aren't valid, this should pop up an error message and then exit.
    // see this link for error message info: https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#features

    // make a new ViewFactory object
    ViewFactory newView = new ViewFactory(viewResult, model, outResult, speedResult);
    // this should create the view
    newView.create();

    // JFrame nonsense I think is required?
    frame.pack();
    frame.setVisible(true);

  }
}
