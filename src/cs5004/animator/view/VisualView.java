package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs5004.animator.model.IAnimationModel;

/**
 * This class represents a visual view. The visual view outputs a visual display to the end-user of
 * the animation.
 */
public class VisualView extends JFrame implements IView, ActionListener {
  private AnimationPanel animationPanel;
  private IAnimationModel model;

  public VisualView(IAnimationModel model, String speed) {
    super();
    Integer intSpeed = Integer.parseInt(speed);
    this.model = model;

    this.setLayout(new BorderLayout());
    animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(model.getBoundsWidth(),
            model.getBoundsHeight()));
    this.add(animationPanel, BorderLayout.CENTER);

    // specify the speed
    // create a timer & specify the action listener to be used
    // actionPerformed in the listener is getShapesAtTick, then draw shapes

    Timer timer = new Timer(intSpeed * 100, this);
    timer.start();

    // create shapes
    // https://docs.oracle.com/javase/8/docs/api/java/awt/geom/Rectangle2D.Double.html
    // https://docs.oracle.com/javase/8/docs/api/java/awt/geom/Ellipse2D.Double.html
    // https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html#fill-java.awt.Shape-
    // or: fill(new Rectangle(x, y, w, h);


    // add components to the panel

    // finish up JFrame
    this.pack();
  }

  public void refresh() { this.repaint(); }

  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // getShapesAtTick, then draw shapes
    //List shapesAtTick = model.getShapesAtTick;
    //drawShapes(shapesAtTick);
    refresh();
  }
}
