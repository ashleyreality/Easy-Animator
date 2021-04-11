package cs5004.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.*;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;

public class AnimatorHelper {
  private static IAnimationModel model;

  /**
   * ______________________________ HELPER METHOD: modelSetup() ___________________________________.
   * @return
   */
  public static IAnimationModel modelSetup() {
    model = new AnimationModelImpl();
    return model;
  }

  /**
   * ______________________________ HELPER METHOD: buildSetup() ___________________________________.
   * The AnimationBuilder takes in the model which is then cast from an AnimationReader type to an
   * IAnimationModel type and is then parsed using the input file using the instance of
   * AnimationBuilder().
   * @return the build, an AnimationBuilder
   */
  public static AnimationBuilder buildSetup() {
    AnimationBuilder build = new Builder(model);
    return build;
  }

  /**
   * _____________________________ HELPER METHOD: buildToModel() __________________________________.
   * @param file the input file, a Readable
   * @param build the build with the shapes and their events, an AnimationBuilder
   * @return the model, an IAnimationModel
   */
  public static IAnimationModel buildToModel(Readable file, AnimationBuilder build) {
    model = (IAnimationModel) AnimationReader.parseFile(file, build);
    return model;
  }

  /**
   * _____________________________ HELPER METHOD: jframeStart() ___________________________________.
   * @return the frame of the model, a JFrame
   */
  public static JFrame jframeStart() {
    JFrame frame = new JFrame("Frame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    return frame;
  }

  /**
   * _____________________________ HELPER METHOD: stringBuilder() _________________________________.
   * Convert the input to main "args" from an array of Strings to tokens of Strings with a space in
   * between each token.
   * @param args the array of Strings
   * @return the string builder, a StringBuilder
   */
  public static StringBuilder stringBuilder(String[] args) {
    StringBuilder sb = new StringBuilder();
    for (String arg : args) {
      sb.append(arg);
      sb.append(" ");
    }
    return sb;
  }

  /**
   * _____________________________ HELPER METHOD: nameScanner() ___________________________________.
   * The Scanner reads through the Strings of tokens in the string builder and looks for the String
   * "-in" and assigns the String right after it to variable "inResult" which represents the name of
   * the .txt input file.
   * @param sb the string builder, a StringBuilder
   * @return the next String after "-in", a String
   */
  public static String nameScanner(StringBuilder sb) {
    Scanner in = new Scanner(sb.toString());
    in.findInLine("-in");
    return in.next();
  }

  /**
   * _______________________________ HELPER METHOD: setFile() _____________________________________.
   * The FileReader takes in the input file.
   * @param inputName the name of the input file, a String
   * @return the file, a Readable
   * @throws FileNotFoundException if the file is not found
   */
  public static Readable setFile(String inputName) throws FileNotFoundException {
    Readable file = new FileReader(inputName);
    return file;
  }

  /**
   * _____________________________ HELPER METHOD: fileExceptions() ________________________________.
   * @param file the input file, a Readable
   * @param inputName the name of the input file, a String
   * @param frame the frame of the model, a JFrame
   */
  public static void fileExceptions(Readable file, String inputName, JFrame frame) {
    try {
      file = new FileReader(inputName);
    } catch (FileNotFoundException e) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "Input file not found", "Input file error",
              JOptionPane.ERROR_MESSAGE);
      System.out.println("The input file was not found.");
      e.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * _____________________________ HELPER METHOD: viewScanner() ___________________________________.
   * The Scanner reads through the Strings of tokens in the string builder and looks for the String
   * "-view" and assigns the String right after it to variable "outputView" which represents the
   * view type of the output.
   * @param sb the string builder, a StringBuilder
   * @return the next String after "-view", a String
   */
  public static String viewScanner(StringBuilder sb) {
    Scanner view = new Scanner(sb.toString());
    view.findInLine("-view");
    return view.next();
  }

  /**
   * ___________________________ HELPER METHOD: viewExceptions() __________________________________.
   * If the view type is wrong, pop up an error message.
   * @param outputView the view type being output
   * @param frame the frame of the view, a JFrame
   */
  public static void viewExceptions(String outputView, JFrame frame) {
    if (!outputView.equalsIgnoreCase("text")
            && !outputView.equalsIgnoreCase("svg")
            && !outputView.equalsIgnoreCase("visual")) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "The view type must be text, svg, or visual",
              "Invalid view", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
  }

  /**
   * ______________________________ HELPER METHOD: outScanner() ___________________________________.
   * The Scanner reads through the Strings of tokens in the string builder and looks for the String
   * "-out" and assigns the String right after it to variable "outputName" which represents the name
   * of the output (any type).
   * @param sb the string builder, a StringBuilder
   * @return the next String after "-out", a String
   */
  public static String outScanner(StringBuilder sb) {
    Scanner out = new Scanner(sb.toString());
    out.findInLine("-out");
    return out.next();
  }

  /**
   * _____________________________ HELPER METHOD: speedScanner() __________________________________.
   * The Scanner reads through the Strings of tokens in the string builder and looks for the String
   * "-speed" and assigns the String right after it to variable "outputSpeed" which represents the
   * outputSpeed within the output (any type).
   * @param sb the string builder, a StringBuilder
   * @return the next String after "-speed", a String
   */
  public static String speedScanner(StringBuilder sb) {
    Scanner outputSpeed = new Scanner(sb.toString());
    outputSpeed.findInLine("-speed");
    return outputSpeed.next();
  }

  /**
   * ___________________________ HELPER METHOD: speedExceptions() _________________________________.
   * If the outputSpeed is less than the integer value of 1, pop up an error message.
   * @param outputSpeed the output speed of the animation, a String
   * @param frame the frame of the view, a JFrame
   */
  public static void speedExceptions(String outputSpeed, JFrame frame) {
    try {
      int speedInt = Integer.parseInt(outputSpeed);
      if (Integer.parseInt(outputSpeed) < 1) throw new NumberFormatException();
    } catch (NumberFormatException n) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "Speed must be a positive integer",
              "Invalid speed", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
  }

  }
