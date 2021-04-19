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
 * The AnimationPanel class extends JPanel. It is a container that can store an organized group of
 * components.
 */
public class AnimationPanel extends JPanel {
  private int someTick;
  private IAnimationModel model;

  /**
   * Constructs an animation panel.
   *
   * @param model    the animation model being viewed
   * @param someTick the tick at which shapes are being drawn
   */
  public AnimationPanel(IAnimationModel model, int someTick) {
    super();
    this.model = model;
    this.someTick = someTick;
  }

  /**
   * Paint a component in the Animation Panel.
   *
   * @param g the graphics2d object drawing the shape
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Create a list of rectangles at the given tick
    List<IShape> listOfRectanglesAtSomeTick = model.getShapesAtTick(someTick);
    // Iterate through the list of shapes at the given tick
    for (IShape eachShapeAtTick : listOfRectanglesAtSomeTick) {
      // Check if it's a rectangle
      if (eachShapeAtTick.getType() == ShapeType.RECTANGLE) {
        // Draw the rectangle(s)
        //drawRectangle(eachShapeAtTick, g2d);
        setColor(eachShapeAtTick, g2d);
        fillRectangleWithColor(eachShapeAtTick, g2d);
      }
      // Check if it's an oval/ellipse
      if (eachShapeAtTick.getType() == ShapeType.ELLIPSE) {
        // Draw the oval(s)
        //drawEllipse(eachShapeAtTick, g2d);
        setColor(eachShapeAtTick, g2d);
        fillOvalWithColor(eachShapeAtTick, g2d);
      }
    }
  }

  /**
   * Sets a shape's color.
   *
   * @param eachShapeAtTick the shape to be changed
   * @param g2d             the graphics2d object drawing the shape
   */
  private void setColor(IShape eachShapeAtTick, Graphics2D g2d) {
    int currentR = eachShapeAtTick.getColor().getRed();
    int currentG = eachShapeAtTick.getColor().getGreen();
    int currentB = eachShapeAtTick.getColor().getBlue();
    g2d.setColor(new Color(currentR, currentG, currentB));
  }

  /**
   * Fills a rectangle with color.
   *
   * @param eachShapeAtTick the shape to be changed
   * @param g2d             the graphics2d object drawing the shape
   */
  private void fillRectangleWithColor(IShape eachShapeAtTick, Graphics2D g2d) {
    int x = currentXLocation(eachShapeAtTick);
    int y = currentYLocation(eachShapeAtTick);
    int w = currentWidth(eachShapeAtTick);
    int h = currentHeight(eachShapeAtTick);
    g2d.fillRect(x, y, w, h);
  }

  /**
   * Fills an oval with color.
   *
   * @param eachShapeAtTick the shape to be changed
   * @param g2d             the graphics2d object drawing the shape
   */
  private void fillOvalWithColor(IShape eachShapeAtTick, Graphics2D g2d) {
    int x = currentXLocation(eachShapeAtTick);
    int y = currentYLocation(eachShapeAtTick);
    int w = currentWidth(eachShapeAtTick);
    int h = currentHeight(eachShapeAtTick);
    g2d.fillOval(x, y, w, h);
  }

  /**
   * Draws a rectangle.
   *
   * @param eachShapeAtTick the shape to be drawn
   * @param g2d             the graphics2d object drawing the shape
   */
  private void drawRectangle(IShape eachShapeAtTick, Graphics2D g2d) {
    int x = currentXLocation(eachShapeAtTick);
    int y = currentYLocation(eachShapeAtTick);
    int w = currentWidth(eachShapeAtTick);
    int h = currentHeight(eachShapeAtTick);
    g2d.drawRect(x, y, w, h);
  }

  /**
   * Draws an ellipse.
   *
   * @param eachShapeAtTick the shape to be drawn
   * @param g2d             the graphics2d object drawing the shape
   */
  private void drawEllipse(IShape eachShapeAtTick, Graphics2D g2d) {
    int x = currentXLocation(eachShapeAtTick);
    int y = currentYLocation(eachShapeAtTick);
    int w = currentWidth(eachShapeAtTick);
    int h = currentHeight(eachShapeAtTick);
    g2d.drawOval(x, y, w, h);
  }

  /**
   * Gets a shape's X location.
   *
   * @param eachShapeAtTick the shape to be located
   * @return the shape's location at X
   */
  private int currentXLocation(IShape eachShapeAtTick) {
    return (int) eachShapeAtTick.getLocation().getX();
  }

  /**
   * Gets a shape's Y location.
   *
   * @param eachShapeAtTick the shape to be located
   * @return the shape's location at Y
   */
  private int currentYLocation(IShape eachShapeAtTick) {
    return (int) eachShapeAtTick.getLocation().getY();
  }

  /**
   * Gets a shape's width.
   *
   * @param eachShapeAtTick the shape you want data about
   * @return the shape's width
   */
  private int currentWidth(IShape eachShapeAtTick) {
    return (int) eachShapeAtTick.getWidth();
  }

  /**
   * Gets a shape's height.
   *
   * @param eachShapeAtTick the shape you want data about
   * @return the shape's height
   */
  private int currentHeight(IShape eachShapeAtTick) {
    return (int) eachShapeAtTick.getHeight();
  }

  /**
   * Sets the animation model.
   *
   * @param model the model you want to set this.model to
   */
  public void setModel(IAnimationModel model) {
    this.model = model;
  }

  /**
   * Set the current tick.
   *
   * @param someTick the tick you'd like to set the current tick to
   */
  public void setTick(int someTick) {
    this.someTick = someTick;
  }

}
