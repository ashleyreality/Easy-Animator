package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IShape;

/**
 * This is the AnimationPanel class. It extends
 */
public class AnimationPanel extends JPanel { // FixMe removed for now: implements ActionListener, ItemListener
  private int someTick;
  private IAnimationModel model;

  // I think the Panel is supposed to be a separate class -- as seen in the Turtle code from mod10
  // create a JPanel -- the window in which the animation happens
  // https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html

  // create scroll panes so everything in the animation can be seen
  // https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html

  // should the constructor take in:
  // IAnimationController myController, IAnimationModel myModel ?

  public AnimationPanel(IAnimationModel model, int someTick) {
    super();
    this.model = model;
    this.someTick = someTick;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Create a list of shapes at the given tick
    List<IShape> listOfShapesAtSomeTick = model.getShapesAtTick(someTick);
    // Iterate through the list of shapes at the given tick
    for (IShape eachShapeAtTick : listOfShapesAtSomeTick) {
      //System.out.println("\n\n\nLIST OF SHAPES AT " + someTick);
      //System.out.println(eachShapeAtTick);
      // Check if it's a rectangle
      if (eachShapeAtTick.getType().equalsIgnoreCase("Rectangle")) { // FixMe to reliably check the shape type
        int currentXLocation = (int) eachShapeAtTick.getLocation().getX();
        int currentYLocation = (int) eachShapeAtTick.getLocation().getY();
        int currentWidth = (int) eachShapeAtTick.getWidth();
        int currentHeight = (int) eachShapeAtTick.getHeight();
        int currentR = eachShapeAtTick.getColor().getRed();
        int currentG = eachShapeAtTick.getColor().getGreen();
        int currentB = eachShapeAtTick.getColor().getBlue();
        // Draw the shape
        g2d.drawRect(currentXLocation, currentYLocation, currentWidth, currentHeight);
        g2d.setColor(new Color(currentR, currentG, currentB));
        g2d.fillRect(currentXLocation, currentYLocation, currentWidth, currentHeight);

        System.out.println("Tick: " + someTick + "  currentX: " + currentXLocation);
      }
      //AYA TO DO: else if getType.equalsIgnoreCXase("Ellipse")
      // {
      //
      // }
    }
  }


  public void setModel(IAnimationModel model) {
    this.model = model;
  }

  public void setTick(int someTick) {
    this.someTick = someTick;
  }

}
