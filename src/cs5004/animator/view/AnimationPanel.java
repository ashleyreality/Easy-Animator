package cs5004.animator.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;

import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;

/**
 * This is the AnimationPanel class. It extends the JPanel which is a container that can store a
 * and organize group of components and provides access to override its paintComponent() method,
 * which takes Graphics objects as parameters, and draws something on the JPanel using the super
 * declaration.
 */
public class AnimationPanel extends JPanel {
  private int someTick;
  private IAnimationModel model;

  // create a JPanel -- the window in which the animation happens
  // https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html

  // create scroll panes so everything in the animation can be seen
  // https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html

  public AnimationPanel(IAnimationModel model, int someTick) {
    super();
    this.model = model;
    this.someTick = someTick;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Create a list of rectangles at the given tick
    List<IShape> listOfRectanglesAtSomeTick = model.getShapesAtTick(someTick);
    // Iterate through the list of shapes at the given tick
    for (IShape eachShapeAtTick : listOfRectanglesAtSomeTick) {
      // Check if it's a rectangle
      if (eachShapeAtTick.getType() == ShapeType.RECTANGLE) { // FixMe to reliably check the shape type
        // Draw the rectangle(s)
        drawRectangle(eachShapeAtTick, g2d);
        setColor(eachShapeAtTick, g2d);
        fillRectangleWithColor(eachShapeAtTick, g2d);
      }
      // Check if it's an oval/ellipse
      if (eachShapeAtTick.getType()  == ShapeType.ELLIPSE) {
        // Draw the oval(s)
        drawEllipse(eachShapeAtTick, g2d);
        setColor(eachShapeAtTick, g2d);
        fillOvalWithColor(eachShapeAtTick, g2d);
      }
    }
  }

  private void setColor(IShape eachShapeAtTick, Graphics2D g2d) {
    int currentR = eachShapeAtTick.getColor().getRed();
    int currentG = eachShapeAtTick.getColor().getGreen();
    int currentB = eachShapeAtTick.getColor().getBlue();
    g2d.setColor(new Color(currentR, currentG, currentB));
  }

  private void fillRectangleWithColor(IShape eachShapeAtTick, Graphics2D g2d) {
    int x = currentXLocation(eachShapeAtTick);
    int y = currentYLocation(eachShapeAtTick);
    int w = currentWidth(eachShapeAtTick);
    int h = currentHeight(eachShapeAtTick);
    g2d.fillRect(x, y, w, h);
  }

  private void fillOvalWithColor(IShape eachShapeAtTick, Graphics2D g2d) {
    int x = currentXLocation(eachShapeAtTick);
    int y = currentYLocation(eachShapeAtTick);
    int w = currentWidth(eachShapeAtTick);
    int h = currentHeight(eachShapeAtTick);
    g2d.fillOval(x, y, w, h);
  }

  private void drawRectangle(IShape eachShapeAtTick, Graphics2D g2d) {
    int x = currentXLocation(eachShapeAtTick);
    int y = currentYLocation(eachShapeAtTick);
    int w = currentWidth(eachShapeAtTick);
    int h = currentHeight(eachShapeAtTick);
    g2d.drawRect(x, y, w, h);
  }

  private void drawEllipse(IShape eachShapeAtTick, Graphics2D g2d) {
    int x = currentXLocation(eachShapeAtTick);
    int y = currentYLocation(eachShapeAtTick);
    int w = currentWidth(eachShapeAtTick);
    int h = currentHeight(eachShapeAtTick);
    g2d.drawOval(x, y, w, h);
  }

  private int currentXLocation(IShape eachShapeAtTick) {
    return (int) eachShapeAtTick.getLocation().getX();
  }

  private int currentYLocation(IShape eachShapeAtTick) {
    return (int) eachShapeAtTick.getLocation().getY();
  }

  private int currentWidth(IShape eachShapeAtTick) {
    return (int) eachShapeAtTick.getWidth();
  }

  private int currentHeight(IShape eachShapeAtTick) {
    return (int) eachShapeAtTick.getHeight();
  }

  public void setModel(IAnimationModel model) {
    this.model = model;
  }

  public void setTick(int someTick) {
    this.someTick = someTick;
  }

}
