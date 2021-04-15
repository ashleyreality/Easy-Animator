package cs5004.animator;

import java.awt.*;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.util.ViewFactory;
import cs5004.animator.view.VisualView;


public class EasyAnimator {


  public static void main(String[] args) throws IOException {

    // FixMe - If the output set is not specified and the view needs it, the default should be
    //  System.out. If the speed is not specified and the view needs it, the default is 1 tick per second.

    // The controller/main uses a Readable object to
    // read one "token" at a time (where each token is a String separated by a space)
    // from the input (like a text file input)
    // and it decides how/what will be output for the view's use

    // Return the Appendable object for the View to use
    // The View (such as TextView) uses an Appendable object to transmit all outputs (like a text file output)

    // ----------------------------------------------------------------------------------------
    // setup the *model* using an instance of AnimationModelImpl()
    //    AnimatorHelper model = new AnimatorHelper();
    IAnimationModel model = new AnimationModelImpl();
    //AnimatorHelper helper = new AnimatorHelper();

    // ----------------------------------------------------------------------------------------
    // setup the *frame* using an instance of JFrame()
    JFrame frame = AnimatorHelper.jframeStart();

    // ----------------------------------------------------------------------------------------
    // setup the *string builder* using an instance of StringBuilder()
    // convert the input to main "args" from an array of Strings to tokens of Strings
    // with a space in between each token
    StringBuilder sb = AnimatorHelper.stringBuilder(args);

    // ----------------------------------------------------------------------------------------
    // setup the *input* using an instance of Scanner()
    // the Scanner reads through the Strings of tokens in the string builder
    // and looks for the String "-in" and assigns the String right after it to variable "inResult"
    // which represents the name of the .txt input file
    String inputName = AnimatorHelper.nameScanner(sb);

    // ----------------------------------------------------------------------------------------
    // setup the *input file reader* using an instance of FileReader()
    // the FileReader takes in the input file
    Readable file = AnimatorHelper.fileExceptions(inputName, frame);

    // ----------------------------------------------------------------------------------------
    // setup the *build* using an instance of AnimationBuilder()
    // the AnimationBuilder takes in the model
    // which is then cast from an AnimationReader type to an IAnimationModel type
    // and is then parsed using the input file using the instance of AnimationBuilder()
    AnimationBuilder build = new Builder(model);
    model = (IAnimationModel) AnimationReader.parseFile(file, build);

    // ----------------------------------------------------------------------------------------
    // setup the *view* using an instance of Scanner()
    // the Scanner reads through the Strings of tokens in the string builder
    // and looks for the String "-view" and assigns the String right after it to variable "outputView"
    // which represents the view type of the output
    String outputView = AnimatorHelper.viewScanner(sb);

    // if the view type is wrong, pop up an error message
    AnimatorHelper.viewExceptions(outputView, frame);

    // ----------------------------------------------------------------------------------------
    // setup the *out* using an instance of Scanner()
    // the Scanner reads through the Strings of tokens in the string builder
    // and looks for the String "-out" and assigns the String right after it to variable "outputName"
    // which represents the name of the output (any type)
    String outputName = AnimatorHelper.outScanner(sb);

    // fixme - what to do if outputSpeed isn't given and also isn't needed, like for text view?
    // only throw an error if it's required for the given view. if outputSpeed is req'd and not given,
    // it's ok to ignore. If you need the outputSpeed and don't get it, you can either default to a given
    // outputSpeed or you can throw an error. Defaulting preferred -- be sure to document it!

    // ----------------------------------------------------------------------------------------
    // setup the *outputSpeed* using an instance of Scanner()
    // the Scanner reads through the Strings of tokens in the string builder
    // and looks for the String "-speed" and assigns the String right after it to variable "outputSpeed"
    // which represents the outputSpeed within the output (any type)
    String outputSpeed = AnimatorHelper.speedScanner(sb);

    // if the outputSpeed is less than the integer value of 1, pop up an error message
    AnimatorHelper.speedExceptions(outputSpeed, frame);

    // ----------------------------------------------------------------------------------------
    // setup the *view* using an instance of ViewFactory()
    // the ViewFactory takes in:
    // 1) the view type of the output,
    // 2) the model,
    // 3) the name of the output,
    // 4) and the outputSpeed of the output
    ViewFactory newView = new ViewFactory(outputView, model, outputName, outputSpeed);
    newView.create();

    // ----------------------------------------------------------------------------------------
    // JFrame finishing up
    frame.pack();
    //System.exit(0);

    // Visual View ScrollPane Confusion - aya

    // Visual view
    // invokeLater is used as a wrapper around the JFrame code to ensure that
    // Swing code is executed on the Event Dispatch Thread (EDT)
    // Why? Because there is a JSwing rule: "only modify Swing (GUI) elements from the EDT "
    //SwingUtilities.invokeLater(new Runnable() {

     /* @Override
      public void run() {

        IAnimationModel anotherModel = new AnimationModelImpl();

        // Create the visual video "visual"
        JFrame window = AnimatorHelper.jframeStart();

        // Access the xml/svg file & parse the XML
        // The XML creates a JFrame object by using java.beans.XMLEncoder?
        // The file extension ".svg" is an XML namespace
        XMLDecoder xmlDecoder = null;
        try {
          xmlDecoder = new XMLDecoder(new FileInputStream("simple-example.svg"));
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        Object frame = xmlDecoder.readObject();
        xmlDecoder.close();

        //JFrame window = new JFrame();
//        ViewFactory newView = new VisualView(anotherModel, outputSpeed);
//        newView.create();

        // Add some visuals to it
        // FixMe

        // Create a scrollpane
        JScrollPane scrollPane = new JScrollPane(window);

        // Add scrollpane to jframe's content pane placing it in center of border layout
        JFrame newFrame = new JFrame("Test");
        newFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // make it easy to close the application
        // this sets up the program/application so that a user can press the "close" button on the
        // window they see and the program will end
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the frame size (call frame.pack())
        newFrame.pack();
        //frame.setSize(new Dimension(500, 500));

        // center the frame
        newFrame.setLocationRelativeTo(null);

        // make it visible to the user
        newFrame.setVisible(true);
*/

    //  }
   // });
  }
}