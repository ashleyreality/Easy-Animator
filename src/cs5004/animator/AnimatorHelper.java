package cs5004.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.*;

/**
 *
 */
public class AnimatorHelper {

  /**
   * _____________________________ CONSTRUCTOR: AnimatorHelper() __________________________________.
   */
  public AnimatorHelper() { // removed param: IAnimationModel model
  }

  /**
   * _____________________________ HELPER METHOD: jframeStart() ___________________________________.
   * @return
   */
  public static JFrame jFrameStart() {
    JFrame frame = new JFrame("Frame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    return frame;
  }

  public static StringBuilder stringBuilder(String[] args) {
    StringBuilder sb = new StringBuilder();
    for (String arg : args) {
      sb.append(arg);
      sb.append(" ");
    }
    return sb;
  }

  public static String nameScanner(StringBuilder sb) {
    Scanner in = new Scanner(sb.toString());
    in.findInLine("-in");
    return in.next();
  }

  public static Readable fileExceptions(String inputName, JFrame frame) {
    try {
      return new FileReader(inputName);
    } catch (FileNotFoundException e) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "Input file not found", "Input file error",
              JOptionPane.ERROR_MESSAGE);
      System.out.println("The input file was not found.");
      e.printStackTrace();
       //System.exit(1);
    }
    return null;
  }

  public static String viewScanner(StringBuilder sb) {
    Scanner view = new Scanner(sb.toString());
    view.findInLine("-view");
    return view.next();
  }

  public static void viewExceptions(String outputView, JFrame frame) {
    if (!outputView.equalsIgnoreCase("text")
            && !outputView.equalsIgnoreCase("svg")
            && !outputView.equalsIgnoreCase("visual")) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "The view type must be text, svg, or visual",
              "Invalid view", JOptionPane.ERROR_MESSAGE);
      //System.exit(1);
    }
  }

  public static String outScanner(StringBuilder sb) {
    Scanner out = new Scanner(sb.toString());
    out.findInLine("-out");
    return out.next();
  }

  public static String speedScanner(StringBuilder sb) {
    Scanner outputSpeed = new Scanner(sb.toString());
    outputSpeed.findInLine("-speed");
    return outputSpeed.next();
  }

  public static void speedExceptions(String outputSpeed, JFrame frame) {
    try {
      int speedInt = Integer.parseInt(outputSpeed);
      if (Integer.parseInt(outputSpeed) < 1) throw new NumberFormatException();
    } catch (NumberFormatException n) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "Speed must be a positive integer",
              "Invalid speed", JOptionPane.ERROR_MESSAGE);
      //System.exit(1);
    }
  }

  }
