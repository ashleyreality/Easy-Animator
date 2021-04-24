package cs5004.animator.controller.commands;

import java.io.IOException;

import javax.swing.text.TabExpander;

import cs5004.animator.controller.AnimationCommand;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.TextView;

public class SaveAsText implements AnimationCommand {
  @Override
  public void go(IAnimationModel model, IView view) throws IOException {
    // do something
    System.out.println("Save as text file command received");
    new TextView(model, "text.txt");
  }
}
