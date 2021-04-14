package cs5004.animator.view;

import java.awt.*;

import javax.swing.*;

import cs5004.animator.model.IAnimationModel;

public class VisualView extends JFrame implements IView {
  private AnimationPanel animationPanel;

  public VisualView(IAnimationModel model, String speed) {
    super();

    this.setLayout(new BorderLayout());
    animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(model.getBoundsWidth(),
            model.getBoundsHeight()));
    this.add(animationPanel, BorderLayout.CENTER);

    // specify the speed



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

}
