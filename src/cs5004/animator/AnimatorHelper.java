package cs5004.animator;

import javax.swing.*;

class AnimatorHelper {

  public static JFrame jframeStart() {
    JFrame frame = new JFrame("Frame");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    return frame;
  }

  public static StringBuilder stringBuilder(String[] args) {
    StringBuilder sb = new StringBuilder();
    for (String arg : args) {
      sb.append(arg);
      sb.append(" ");
    }
    return sb;
  }

}
