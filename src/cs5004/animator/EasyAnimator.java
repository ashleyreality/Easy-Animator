package cs5004.animator;

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
  private Readable in;
  private Appendable out;

  public static void main(String[] args) throws FileNotFoundException {

    // The controller uses a Readable object to read one "token" at a time from the input (like a text file input)
    //	The view uses an Appendable object to transmit all outputs (like a text file output)


    // make the appendable here & pass it in to TextView (for example)
    // and decide here how it will be output (command line, file, or multiple places, etc.)
    // because the command line
    // gives the info to the controller

    // setup the *model* using an instance of AnimationModelImpl()
    model = new AnimationModelImpl();

    // setup the *frame* using an instance of JFrame()
    JFrame frame = new JFrame("Frame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // setup the *string builder* using an instance of StringBuilder()
    // convert the input to main "args" from an array of Strings to tokens of Strings
    // with a space in between each token
    StringBuilder sb = new StringBuilder();
    for (String arg : args) {
      sb.append(arg);
      sb.append(" ");
    }

    // setup the *input* using an instance of Scanner()
    // the Scanner reads through the Strings of tokens in the string builder
    // and looks for the String "-in" and assigns the String right after it to variable "inResult"
    // which represents the name of the .txt input file
    Scanner in = new Scanner(sb.toString());
    in.findInLine("-in");
    String inResult = in.next();

    // setup the *input file reader* using an instance of FileReader()
    // the FileReader takes in the input file
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

    // setup the *build* using an instance of AnimationBuilder()
    // the AnimationBuilder takes in the model
    // which is then cast from an IAnimationModel type to a AnimationReader type
    // and is then parsed using the input file using the instance of AnimationBuilder()
    AnimationBuilder build = new Builder(model);
    model = (IAnimationModel) AnimationReader.parseFile(file, build);

    // setup the *view* using an instance of Scanner()
    // the Scanner reads through the Strings of tokens in the string builder
    // and looks for the String "-view" and assigns the String right after it to variable "viewResult"
    // which represents the view type of the output
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

    // setup the *out* using an instance of Scanner()
    // the Scanner reads through the Strings of tokens in the string builder
    // and looks for the String "-out" and assigns the String right after it to variable "outResult"
    // which represents the name of the output (any type)
    Scanner out = new Scanner(sb.toString());
    out.findInLine("-out");
    String outResult = out.next();
    System.out.println(outResult);

    // fixme - what to do if speed isn't given and also isn't needed, like for text view?
    // only throw an error if it's required for the given view. if speed is req'd and not given,
    // it's ok to ignore. If you need the speed and don't get it, you can either default to a given
    // speed or you can throw an error. Defaulting preferred -- be sure to document it!

    // setup the *speed* using an instance of Scanner()
    // the Scanner reads through the Strings of tokens in the string builder
    // and looks for the String "-speed" and assigns the String right after it to variable "speedResult"
    // which represents the speed within the output (any type)
    Scanner speed = new Scanner(sb.toString());
    speed.findInLine("-speed");
    String speedResult = speed.next();

    // if the speed is less than the integer value of 1, pop up an error message
    try {
      int speedInt = Integer.parseInt(speedResult);
      if (Integer.parseInt(speedResult) < 1) throw new NumberFormatException();
    } catch (NumberFormatException n) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "Speed must be a positive integer",
              "Invalid speed", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }

    // setup the *view* using an instance of ViewFactory()
    // the ViewFactory takes in:
    // 1) the view type of the output,
    // 2) the model,
    // 3) the name of the output,
    // 4) and the speed of the output
    ViewFactory newView = new ViewFactory(viewResult, model, outResult, speedResult);
    newView.create();

    // JFrame finishing up
    frame.pack();
    System.exit(0);
  }

  private StringBuilder appendableHelper(String[] args) {
    StringBuilder sb = new StringBuilder();
    for (String arg : args) {
      sb.append(arg);
      sb.append(" ");
    }
  }

}
