package cs5004.animator.view;

import cs5004.animator.model.IAnimationModel;

public class VisualView implements IView {
  public VisualView(IAnimationModel model, String speed) {

    // specify the speed

    // I think the Panel is supposed to be a separate class -- as seen in the Turtle code from mod10
    // create a JPanel -- the window in which the animation happens
    // https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html

    // create scroll panes so everything in the animation can be seen
    // https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html

    // create shapes
    // https://docs.oracle.com/javase/8/docs/api/java/awt/geom/Rectangle2D.Double.html
    // https://docs.oracle.com/javase/8/docs/api/java/awt/geom/Ellipse2D.Double.html
    // https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html#fill-java.awt.Shape-
    // or: fill(new Rectangle(x, y, w, h);


    // add components to the panel
  }
}
