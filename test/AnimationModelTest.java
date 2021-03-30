import org.junit.Test;

import java.awt.*;

public class AnimationModelTest {
  private AnimationModel testAnimation;

  @Test
  public void TextTest() {
    // create new shapes first
    // give shape a point2D
    point2D rLocation = new point2D(200.0,200.0);
    IShape R = new Rectangle(Color.red, rLocation,50.0, 100.0);
    point2D cLocation = new point2D(500.0,100.0);
    IShape C = new Oval(Color.blue, cLocation,60.0,30.0);
    // make a new movement

    testAnimation = new AnimationModelImpl();
    // add the shapes to a shape list in the animation model
    // decide when they appear and disappear here
    // rectangle appears at 1, disappears at 100
    testAnimation.addShape(R,1,100);
    testAnimation.addShape(C,6,100);




  }
}
