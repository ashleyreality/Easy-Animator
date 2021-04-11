package cs5004.animator;

import java.io.FileNotFoundException;

import javax.swing.JFrame;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.ViewFactory;


public class EasyAnimator {


  public static void main(String[] args) throws FileNotFoundException {

    // fixme - what to do if outputSpeed isn't given and also isn't needed, like for text view?
    // only throw an error if it's required for the given view. if outputSpeed is req'd and not given,
    // it's ok to ignore. If you need the outputSpeed and don't get it, you can either default to a given
    // outputSpeed or you can throw an error. Defaulting preferred -- be sure to document it!

    // The controller/main uses a Readable object to
    // read one "token" at a time (where each token is a String separated by a space)
    // from the input (like a text file input)
    // and it decides how/what will be output for the view's use

    // Return the Appendable object for the View to use
    // The View (such as TextView) uses an Appendable object to transmit all outputs (like a text
    // file output)

    // ___________________________________ Invoke modelSetup() ____________________________________.
    // setup the *model* using an instance of AnimationModelImpl()
    IAnimationModel model = AnimatorHelper.modelSetup();

    // __________________________________ Invoke jframeStart() ____________________________________.
    // setup the *frame* using an instance of JFrame()
    JFrame frame = AnimatorHelper.jframeStart();

    // _________________________________ Invoke stringBuilder() ___________________________________.
    // setup the *string builder* using an instance of StringBuilder()
    StringBuilder sb = AnimatorHelper.stringBuilder(args);

    // _________________________________ Invoke nameScanner() _____________________________________.
    // setup the *input* using an instance of Scanner()
    String inputName = AnimatorHelper.nameScanner(sb);

    // ___________________________________ Invoke setFile() _______________________________________.
    // setup the *input file reader* using an instance of FileReader()
    Readable file = AnimatorHelper.setFile(inputName);
    AnimatorHelper.fileExceptions(file, inputName, frame);

    // __________________________________ Invoke buildSetup() _____________________________________.
    // setup the *build* using an instance of AnimationBuilder()
    AnimationBuilder build = AnimatorHelper.buildSetup();
    AnimatorHelper.buildToModel(file, build);

    // _________________________________ Invoke viewScanner() _____________________________________.
    // setup the *view* using an instance of Scanner()
    String outputView = AnimatorHelper.viewScanner(sb);
    AnimatorHelper.viewExceptions(outputView, frame);

    // __________________________________ Invoke outScanner() _____________________________________.
    // setup the *out* using an instance of Scanner()

    String outputName = AnimatorHelper.outScanner(sb);

    // _________________________________ Invoke speedScanner() ____________________________________.
    // setup the *outputSpeed* using an instance of Scanner()
    String outputSpeed = AnimatorHelper.speedScanner(sb);
    AnimatorHelper.speedExceptions(outputSpeed, frame);

    // _______________________________ Instantiate ViewFactory() __________________________________.
    // setup the *view* using an instance of ViewFactory()
    ViewFactory newView = new ViewFactory(outputView, model, outputName, outputSpeed);
    newView.create();

    // ________________________________ End the JFrame Instance ___________________________________.
    frame.pack();
    System.exit(0);
  }
}