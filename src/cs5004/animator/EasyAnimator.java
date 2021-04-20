package cs5004.animator;


import cs5004.animator.controller.AnimatorController;

/**
 * Easy Animator allows a user to create an animation from a formatted text file.
 */
public class EasyAnimator {

  /**
   * The main class runs the animation.
   *
   * @param args command line or other arguments for in file, out file, speed, and view type. If
   *             speed is not given, default is 1. If out file is not given, default is System.out.
   */
  public static void main(String[] args) {
    AnimatorController controller = new AnimatorController(args);
    controller.start();
  }
}