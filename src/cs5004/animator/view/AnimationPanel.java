package cs5004.animator.view;

import java.awt.*;

import javax.swing.*;

public class AnimationPanel extends JPanel {

  // I think the Panel is supposed to be a separate class -- as seen in the Turtle code from mod10
  // create a JPanel -- the window in which the animation happens
  // https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html

  // create scroll panes so everything in the animation can be seen
  // https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html
  public AnimationPanel() {
    super();
  }

  @Override
  protected void paintComponent(Graphics g) {
    // idk if we need this but it seems like it could be important
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // draw something
    g.drawRect(230,80,10000,10000);
    g.setColor(Color.RED);
    g.fillRect(230,80,10000,10000);
  }
}
