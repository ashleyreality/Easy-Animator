package cs5004.animator;

import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.util.ViewFactory;


public class EasyAnimator {
  private static IAnimationModel model;

  public static void main(String[] args) throws FileNotFoundException {
    // This is the entry point for our program. We will pass in command line arguments
    // which are put into a String array (args).

    // the command line argument looks something like this:
    // -in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go"
    // -speed "integer-ticks-per-second"
    // See section 5 of assignment for more info

    model = new AnimationModelImpl();

    // convert array to string
    StringBuilder sb = new StringBuilder();
    for (String arg : args) {
      sb.append(arg);
      sb.append(" ");
    }

    // I think we are meant to use a Scanner to parse the String and then do stuff with it.
    Scanner in = new Scanner(sb.toString());
    in.findInLine("-in");
    String inResult = in.next();

    // open this file and send it to Builder?
    Readable file = null;
    try {
      file = new FileReader(inResult);
    } catch (FileNotFoundException e) {
      System.out.println("The input file was not found.");
      e.printStackTrace();
    }
    AnimationBuilder build = new Builder(model);
    model = (IAnimationModel) AnimationReader.parseFile(file, build);

    Scanner view = new Scanner(sb.toString());
    view.findInLine("-view");
    String viewResult = view.next(); // result from reading the file using the key word "-view" (in this case for text view)
    //System.out.println(viewResult);
    // something like ViewFactory(viewResult, model, outResult, speedResult)
    // this is the view type we want -- we will need to send it to something that knows what to do with it
    // then ViewFactory will send out what we asked for

    Scanner out = new Scanner(sb.toString());
    out.findInLine("-out");
    String outResult = out.next();
    System.out.println(outResult);
    // this is the output file

    Scanner speed = new Scanner(sb.toString());
    speed.findInLine("-speed");
    String speedResult = speed.next();
    System.out.println(speedResult);
    // this is the speed

    // what to do if speed isn't given and also isn't needed, like for text view?

    // If command line arguments aren't valid, this should pop up an error message and then exit.
    // see this link for error message info: https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#features

    // make a new ViewFactory object
    ViewFactory newView = new ViewFactory(viewResult, model, outResult, speedResult);
    // this should create the view
    newView.create(viewResult, model);

  }
}
