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

  // I think the Panel is supposed to be a separate class -- as seen in the Turtle code from mod10
  // create a JPanel -- the window in which the animation happens
  // https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html

  // create scroll panes so everything in the animation can be seen
  // https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html

  // should the constructor take in:
  // IAnimationController myController, IAnimationModel myModel ?

  public AnimationPanel(int someTick) {
    super();
  }

  @Override
  protected void paintComponent(Graphics g) {
    // idk if we need this but it seems like it could be important
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // draw something
    //    g.drawRect(230, 80, 10000, 10000);
    //    g.setColor(Color.RED);
    //    g.fillRect(230, 80, 10000, 10000);

    // Instantiate a new AnimationModelImpl() in order to access the method getShapesAtTick()
    IAnimationModel model = new AnimationModelImpl();
    // Create a list of shapes at the given tick
    List<IShape> listOfShapesAtSomeTick = model.getShapesAtTick(someTick);
    // Iterate through the list of shapes at the given tick
    for (IShape eachShapeAtTick : listOfShapesAtSomeTick) {
      // Check if it's a rectangle
      if (eachShapeAtTick.getType().equals("Rectangle")) { // FixMe to reliably check the shape type
        int currentXLocation = (int) eachShapeAtTick.getLocation().getX();
        int currentYLocation = (int) eachShapeAtTick.getLocation().getY();
        int curentWidth = (int) eachShapeAtTick.getWidth();
        int currentHeight = (int) eachShapeAtTick.getHeight();
        int currentR = eachShapeAtTick.getColor().getRed();
        int currentG = eachShapeAtTick.getColor().getGreen();
        int currentB = eachShapeAtTick.getColor().getBlue();
        // Draw the shape
        g.drawRect(currentXLocation, currentYLocation, curentWidth, currentHeight);
        g.setColor(new Color(currentR, currentG, currentB));
        g.fillRect(currentXLocation, currentYLocation, curentWidth, currentHeight);
      }
    }
  }
}
