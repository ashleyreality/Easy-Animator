package cs5004.animator.controller.commands;

import java.io.IOException;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;

/**
 * Saves the current file as SVG.
 */
public class SaveAsSVG implements AnimationCommand {

  /**
   /**
   * _____________________________ METHOD OVERRIDE: go() __________________________________________.
   * This is an override of the go() method from the AnimationCommand interface. It performs the
   * command, being saving the animation as am SVG file.
   *
   * @param model the IAnimationModel instance containing the animation data.
   * @param view  the type of view specified, an IView
   * @throws IOException if there is a problem saving the file
   */
  @Override
  public void go(IAnimationModel model, IView view) throws IOException {
    IView svg = new SVGView(model, "svg.svg", view.getSpeed());
    svg.printView();
  }
}
