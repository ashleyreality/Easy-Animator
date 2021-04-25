package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs5004.animator.controller.commands.LoadFile;
import cs5004.animator.controller.commands.Loop;
import cs5004.animator.controller.commands.RestartAnimation;
import cs5004.animator.controller.commands.SaveAsSVG;
import cs5004.animator.controller.commands.SaveAsText;
import cs5004.animator.controller.commands.SlowDown;
import cs5004.animator.controller.commands.SpeedUp;
import cs5004.animator.controller.commands.StartAnimation;
import cs5004.animator.controller.commands.StopAnimation;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.util.ViewFactory;
import cs5004.animator.view.IView;


/**
 * The AnimatorController class controls animations. The start/main uses a
 * Readable object to read one "token" at a time (where each token is a String separated by a space)
 * from the input (like a text file input) and it decides how/what will be output for the view's
 * use. The View (such as TextView) uses an Appendable object for the View to use to transmit all
 * outputs (like a text file output).
 */
public class AnimatorController implements ActionListener, ItemListener {
  private String[] args;
  private IAnimationModel model;
  private IView view;

  /**
   * _________________________ CONSTRUCTOR: AnimatorController() __________________________________.
   * Constructs an AnimatorController instance, which contains the start functionalities.
   *
   * @param args the arguments from the command line being parsed, an array of Strings
   */
  public AnimatorController(String[] args) {
    this.args = args;
  }

  /**
   * _______________________________ PRIMARY CONTROLLER METHOD ____________________________________.
   */
  public void start() {
    // Step 1) Create the model
    model = newAnimation();

    // Step 2) Create the frame
    JFrame frame = newFrame();

    // Step 3) Parse the command-line arguments
    StringBuilder parsedString = parsedCommandLine(args);

    // Step 4) Find the input file
    String inputFile = inputFile(parsedString);

    // Step 5) Check input file for exceptions
    Readable file = checkInputFile(inputFile, frame);

    // Step 6) Create a new build
    AnimationBuilder build = newBuild(model);

    // Step 7) Fill the model
    fillTheModel(file, build);

    // Step 8) Find the view type
    String viewType = findViewType(parsedString);

    // Step 9) Check the view type for exceptions
    checkViewType(viewType, frame);

    // Step 10) Find the output file's name
    String outputName = findOutputFileName(parsedString);

    // Step 11) Find the output file's speed
    int outputSpeed = findOutputSpeed(parsedString);

    // Step 12) Check the speed for exceptions
    checkSpeed(outputSpeed, frame);

    // Step 13) Send commandline arguments to the ViewFactory
    ViewFactory factory = newViewFactory(viewType, model, outputName, outputSpeed);

    // Step 14) Construct the view
    this.view = newView(factory);

    // Step 15) Create the view
    createTheView(this.view, viewType);

    // Step 16) Pack the frame and then exit once animation has completed running
    packFrameAndExit(frame);
  }

  /**
   * ___________________________ CONTROLLER STEP 1: newAnimation() ________________________________.
   * Create an empty model.
   *
   * @return the model animation, an IAnimationModel
   */
  public static IAnimationModel newAnimation() {
    return new AnimationModelImpl();
  }


  /**
   * _____________________________ CONTROLLER STEP 2: newFrame() __________________________________.
   * Create the frame used for error messages.
   *
   * @return the frame of the animation, a JFrame
   */
  public static JFrame newFrame() {
    return jFrameStart();
  }


  /**
   * _________________________ CONTROLLER STEP 3: parsedCommandLine() _____________________________.
   * Create a StringBuilder which will be used to parse the command-line arguments.
   *
   * @param args the passed in arguments being parsed
   * @return the string after parsing, a StringBuilder
   */
  public static StringBuilder parsedCommandLine(String[] args) {
    return stringBuilder(args);
  }


  /**
   * ______________________________ CONTROLLER STEP 4: inputFile() ________________________________.
   * Find the input file.
   *
   * @param sb the parsed command line, the StringBuilder
   * @return the name of the input file, a String
   */
  public static String inputFile(StringBuilder sb) {
    return inScanner(sb);
  }

  /**
   * ___________________________ CONTROLLER STEP 5: checkInputFile() ______________________________.
   * Set up the input file as a Readable object and check it for exceptions.
   *
   * @param inputName the name of the final being considered, a String
   * @param frame     the frame of the animation, a JFrame
   * @return the file, a Readable
   */
  public static Readable checkInputFile(String inputName, JFrame frame) {
    return fileExceptions(inputName, frame);
  }


  /**
   * ______________________________ CONTROLLER STEP 6: newBuild() _________________________________.
   * Send the model to the Builder.
   *
   * @param model the model being used for the animation, an IAnimationModel
   * @return the build of the model, an AnimationBuilder
   */
  public static AnimationBuilder newBuild(IAnimationModel model) {
    return new Builder(model);
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


  /**
   * ____________________________ CONTROLLER STEP 8: findViewType() _______________________________.
   * Find the view type.
   *
   * @param sb the String being reviewed, a StringBuilder
   * @return the view type to be output, a String
   */
  public static String findViewType(StringBuilder sb) {
    return viewScanner(sb);
  }


  /**
   * ____________________________ CONTROLLER STEP 9: checkViewType() ______________________________.
   * Check the view type for exceptions.
   *
   * @param outputView the view to be output, a String
   * @param frame      the frame of the animation, a JFrame
   */
  public static void checkViewType(String outputView, JFrame frame) {
    viewExceptions(outputView, frame);
  }


  /**
   * ________________________ CONTROLLER STEP 10: findOutputFileName() ____________________________.
   * Find the output file name.
   *
   * @param sb the String being reviewed, a StringBuilder
   * @return the output name of the file, a String
   */
  public static String findOutputFileName(StringBuilder sb) {
    return outScanner(sb);
  }


  /**
   * __________________________ CONTROLLER STEP 11: findOutputSpeed() _____________________________.
   * Find the requested speed.
   *
   * @param sb the String being reviewed, a StringBuilder
   * @return the output speed of teh file, an int
   */
  public static int findOutputSpeed(StringBuilder sb) {
    return speedScanner(sb);
  }


  /**
   * _____________________________ CONTROLLER STEP 12: checkSpeed() _______________________________.
   * Check the speed for exceptions.
   *
   * @param outputSpeed the speed being output, an int
   * @param frame       the frame of the animation, a JFrame
   */
  public static void checkSpeed(int outputSpeed, JFrame frame) {
    speedExceptions(outputSpeed, frame);
  }


  /**
   * ____________________________ CONTROLLER STEP 13: newViewFactory() ____________________________.
   * Send command line arguments to ViewFactory.
   *
   * @param outputView  the view type to be output, a String
   * @param model       the model being used for the animation, an IAnimationModel
   * @param outputName  the name of the output file, a String
   * @param outputSpeed the speed of the output animation, an int
   * @return the factory construction of what a view will contain, a ViewFactory
   */
  public static ViewFactory newViewFactory(String outputView, IAnimationModel model,
                                           String outputName, int outputSpeed) {
    return new ViewFactory(outputView, model, outputName, outputSpeed);
  }


  /**
   * _______________________________ CONTROLLER STEP 14: newView() ________________________________.
   * Construct the view using the factory.
   *
   * @param factory the factory construction of what a view will contain, a ViewFactory
   * @return the view that has been requested per the command line, an IView
   */
  public static IView newView(ViewFactory factory) {
    IView view = null;
    try {
      view = factory.create(); ///////////
    } catch (IOException e) {
      e.printStackTrace();
    }
    return view;
  }

  /**
   * ___________________________ CONTROLLER STEP 15: createTheView() ______________________________.
   * Create the view.
   *
   * @param view the view that has been requested per the command line, an IView
   */
  public void createTheView(IView view, String viewType) {

    view.createView();

    if (viewType.equalsIgnoreCase("playback")) {

      view.setStartButtonListener(this);
      view.setStopButtonListener(this);
      view.setRestartButtonListener(this);
      view.setSlowButtonListener(this);
      view.setFastButtonListener(this);
      view.setLoopButtonListener(this);
      view.setLoadButtonListener(this);
      view.setSaveTextButtonListener(this);
      view.setSaveSVGButtonListener(this);
    }
  }


  /**
   * __________________________ CONTROLLER STEP 16: packFrameAndExit() ____________________________.
   * JFrame finishing up
   *
   * @param frame the frame of the animation, a JFrame
   */
  public static void packFrameAndExit(JFrame frame) {
    frame.pack();
    //System.exit(0);
    // commented this out because different views have different exits --
    // we need to exit in each view -- AB
  }


  /**
   * _____________________________ HELPER METHOD: jFrameStart() ___________________________________.
   * Starts a JFrame.
   *
   * @return a JFrame object
   */
  public static JFrame jFrameStart() {
    JFrame frame = new JFrame("Frame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    return frame;
  }

  /**
   * _____________________________ HELPER METHOD: stringBuilder() _________________________________.
   * Generates a StringBuilder with arguments from the command line, where it converts the input as
   * an array of Strings to tokens of Strings with a space in between each token.
   *
   * @param args arguments passed in from the command line
   * @return a StringBuilder
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
   * _______________________________ HELPER METHOD: inScanner() ___________________________________.
   * Scans arguments for an in-file. The Scanner reads through the Strings of tokens in the
   * StringBuilder and looks for the String "-in" and get the String right after it, which
   * represents the name of the file being read.
   *
   * @param sb the StringBuilder to be scanned
   * @return the name of the in file
   */
  public static String inScanner(StringBuilder sb) {
    Scanner in = new Scanner(sb.toString());
    in.findInLine("-in");
    return in.next();
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

  /**
   * _______________________________ HELPER METHOD: viewScanner() _________________________________.
   * Scans command-line arguments for the view type. The Scanner reads through the Strings of tokens
   * in the StringBuilder and looks for the String "-view" and get the String right after it, which
   * represents the view type to be used.
   *
   * @param sb the StringBuilder to scan
   * @return the view type as a string
   */
  public static String viewScanner(StringBuilder sb) {
    Scanner view = new Scanner(sb.toString());
    view.findInLine("-view");
    return view.next();
  }

  /**
   * ______________________________ HELPER METHOD: viewExceptions() _______________________________.
   * Checks for problems with the view type and throws exceptions if problems exist.
   *
   * @param outputView the view type, a String
   * @param frame      the frame used to throw exceptions
   */
  public static void viewExceptions(String outputView, JFrame frame) {
    if (!outputView.equalsIgnoreCase("text")
            && !outputView.equalsIgnoreCase("svg")
            && !outputView.equalsIgnoreCase("visual")
            && !outputView.equalsIgnoreCase("playback")) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "The view type must be text, svg,"
                      + " visual, or playback.",
              "Invalid view type", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * ________________________________ HELPER METHOD: outScanner() _________________________________.
   * Scans command-line arguments for the out file. The Scanner reads through the Strings of tokens
   * in the StringBuilder and looks for the String "-out" and get the String right after it, which
   * represents the name to be used for the output.
   *
   * @param sb the StringBuilder to be scanned
   * @return the name of the out file as a string
   */
  public static String outScanner(StringBuilder sb) {
    Scanner out = new Scanner(sb.toString());
    String outFile = out.findInLine("-out");
    if (outFile == null || outFile.equals("")) {
      return "System.out";
    } else {
      return out.next();
    }
  }

  /**
   * ______________________________ HELPER METHOD: speedScanner() _________________________________.
   * Scans command-line arguments for the requested speed. The Scanner reads through the Strings of
   * tokens in the StringBuilder and looks for the String "-speed" and get the String right after
   * it, which represents the speed to be used.
   *
   * @param sb sb the StringBuilder to be scanned
   * @return the speed as a String
   */
  public static int speedScanner(StringBuilder sb) {
    Scanner outputSpeed = new Scanner(sb.toString());
    String stringSpeed = outputSpeed.findInLine("-speed");
    if (stringSpeed == null) {
      return 1;
    } else {
      return Integer.parseInt(outputSpeed.next());
    }
  }

  /**
   * ______________________________ HELPER METHOD: speedExceptions() ______________________________.
   * Checks for problems with the speed and throws exceptions if problems exist.
   *
   * @param outputSpeed the speed, as a string
   * @param frame       the frame used to throw exceptions
   */
  public static void speedExceptions(int outputSpeed, JFrame frame) {
    try {
      if (outputSpeed < 1) {
        throw new NumberFormatException();
      }
    } catch (NumberFormatException n) {
      frame.setVisible(true);
      JOptionPane.showMessageDialog(frame, "Speed must be a positive integer",
              "Invalid speed", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * _____________________________ METHOD OVERRIDE: itemStateChanged() ____________________________.
   * This is an override of the itemStateChanged() from the ItemListener Interface, which is called
   * just after a state change in the listened-to component event. The override creates an Item
   * Listener for the AnimationCommand interface which instantiates a Loop() object for the
   * JCheckBox getLoopCheckbox(), an implementation of a check box -- an item that can be selected
   * or deselected, and which displays its state to the user.
   *
   * @param e the item event, an ActionEvent
   */
  @Override
  public void itemStateChanged(ItemEvent e) {
    AnimationCommand cmd = new Loop();
    try {
      cmd.start(model, view);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }


  /**
   * _____________________________ METHOD OVERRIDE: actionPerformed() _____________________________.
   * This is an override of the actionPerformed() method override from the ActionListener interface,
   * which is invoked when an action event occurs whenever an action is performed by the user. An
   * example of an action is when a user clicks a button. In this case, an action performed occurs
   * when the user selection a button to start, stop, speed up, slow down, and restart the
   * animation, as well as loading an animation file, and saving an animation as a text or svg file.
   *
   * @param e the item event, an ActionEvent
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    AnimationCommand cmd = null;

    switch (e.getActionCommand()) {
      case "START":
        cmd = new StartAnimation();
        break;
      case "STOP":
        cmd = new StopAnimation();
        break;
      case "FAST":
        cmd = new SpeedUp(view);
        break;
      case "SLOW":
        cmd = new SlowDown(view);
        break;
      case "RESTART":
        cmd = new RestartAnimation();
        break;
      case "LOAD":
        cmd = new LoadFile();
        break;
      case "SAVETEXT":
        cmd = new SaveAsText();
        break;
      case "SAVESVG":
        cmd = new SaveAsSVG();
        break;
      default:
        cmd = null;
        break;
    }


    if (cmd != null) {
      try {
        cmd.start(model, view);
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
