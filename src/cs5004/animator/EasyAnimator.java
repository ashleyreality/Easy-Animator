package cs5004.animator;

import java.io.FileNotFoundException;

import javax.swing.JFrame;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.util.ViewFactory;


public class EasyAnimator {


  public static void main(String[] args) throws FileNotFoundException {

    // The controller/main uses a Readable object to
    // read one "token" at a time (where each token is a String separated by a space)
    // from the input (like a text file input)
    // and it decides how/what will be output for the view's use

    // Return the Appendable object for the View to use
    // The View (such as TextView) uses an Appendable object to transmit all outputs (like a text file output)

    // ----------------------------------------------------------------------------------------
    // setup the *model* using an instance of AnimationModelImpl()
    //AnimatorHelper model = new AnimatorHelper();
    IAnimationModel model = AnimatorHelper.modelSetup();
    //IAnimationModel model = new AnimationModelImpl();
    //AnimatorHelper helper = new AnimatorHelper(model);

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
    //Readable file = null;
    Readable file = AnimatorHelper.setFile(inputName);
    //AnimatorHelper.setFile(file, inputName);
    AnimatorHelper.fileExceptions(file, inputName, frame);

    // ----------------------------------------------------------------------------------------
    // setup the *build* using an instance of AnimationBuilder()
    // the AnimationBuilder takes in the model
    // which is then cast from an AnimationReader type to an IAnimationModel type
    // and is then parsed using the input file using the instance of AnimationBuilder()
    AnimationBuilder build = AnimatorHelper.buildSetup();
    //AnimationBuilder build = new Builder(model);
    AnimatorHelper.buildToModel(file, build);
    //model = (IAnimationModel) AnimationReader.parseFile(file, build);

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
    System.exit(0);
  }


}