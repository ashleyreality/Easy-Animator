package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs5004.animator.model.IAnimationModel;


// FixMe - Make sure to test different output types for the visual view


/**
 * This class represents a visual view. The visual view outputs a visual display to the end-user of
 * the animation.
 */
public class VisualView extends JFrame implements IView, ActionListener {
  private AnimationPanel animationPanel;
  private IAnimationModel model;
  private int someTick;

  public VisualView(IAnimationModel model, int speed) {
    super();
    this.model = model;

    // Get the ticks in the file by the file's created AnimationModel which has its own shapeMap
    // IEvent has getEventBegin()
    // IShape has getAppear()

    // create the layout and animation panel; set the size; make it visible
    this.setLayout(new BorderLayout());
    // I could create a for loop or for each that updates the "someTick" argument for AnimationPanel--
    // I want the Visual to get each tick somehow
    animationPanel = new AnimationPanel(model, 0); // FixMe to make the tick change
    animationPanel.setPreferredSize(new Dimension(model.getBoundsWidth(),
            model.getBoundsHeight()));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(animationPanel, BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);

    // Create a scrollpane
    JTextArea textArea = new JTextArea(5, 30);
    JScrollPane scrollPane = new JScrollPane(textArea);

    // Add scrollpane to jframe's content pane placing it in center of border layout
    this.add(scrollPane, BorderLayout.CENTER);



    // specify the speed
    // create a timer & specify the action listener to be used
    // actionPerformed in the listener is getShapesAtTick, then draw shapes

    Timer timer = new Timer(speed * 100, this);
    timer.start();

    // create shapes
    // https://docs.oracle.com/javase/8/docs/api/java/awt/geom/Rectangle2D.Double.html
    // https://docs.oracle.com/javase/8/docs/api/java/awt/geom/Ellipse2D.Double.html
    // https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html#fill-java.awt.Shape-
    // or: fill(new Rectangle(x, y, w, h);


    // add components to the panel

    // finish up JFrame


  }

  public void refresh() { this.repaint(); }

  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // getShapesAtTick, then draw shapes
    // List shapesAtTick = model.getShapesAtTick;
    //drawShapes(shapesAtTick);
    refresh();
  }
}
