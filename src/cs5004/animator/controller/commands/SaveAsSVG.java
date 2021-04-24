package cs5004.animator.controller.commands;

import java.io.IOException;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;

public class SaveAsSVG implements AnimationCommand {
  @Override
  public void go(IAnimationModel model, IView view) throws IOException {
    // do something
    System.out.println("Save as SVG command received");
    IView svg = new SVGView(model, "svg.svg", view.getSpeed());
    svg.printView();
  }
}
