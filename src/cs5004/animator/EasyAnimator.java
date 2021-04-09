package cs5004.animator;

public class EasyAnimator {
  public static void main(String[] args) {
    // This is the entry point for our program. We will pass in command line arguments
    // which are put into a String array (args).
    for (String s : args) {
      System.out.println(s);
    }

    // the command line argument looks something like this:
    // -in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go"
    // -speed "integer-ticks-per-second"
    // See section 5 of assignment for more info

    // I think we are meant to use a Scanner (+ regex ugh) to parse the String and then do stuff
    // with it.

    // If command line arguments aren't valid, this should pop up an error message and then exit.
    // see this link for error message info: https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#features
  }
}
