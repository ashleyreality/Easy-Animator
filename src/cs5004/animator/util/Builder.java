package cs5004.animator.util;

import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;
import cs5004.animator.model.MoveShape;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ScaleShape;

// fixme -- intelliJ will not accept the right identifier??
public class Builder<T> implements AnimationBuilder<IAnimationModel> {

  // We need to implement this class so that it works with IAnimationModel. --AB
  cs5004.animator.model.IAnimationModel model;

  public Builder(IAnimationModel model) {
    this.model = model;
  }

  @Override
  public IAnimationModel build() {
    System.out.println(model.toString());
    return this.model;
  }

  @Override
  public AnimationBuilder<IAnimationModel> setBounds(int x, int y, int width, int height) {
    // send bounds to view? seems impossible
    // should we hold the bounds in the model?
    System.out.println("Bounds will be set to: " + x + " " + y + " " + width + " " + height);
    return this;
  }

  @Override
  public AnimationBuilder<IAnimationModel> declareShape(String name, String type) {

    // add shapes to model
    if (type.equalsIgnoreCase("ellipse")) {
      IShape shape = new Ellipse(name);
      model.addShape(shape);
    } else if (type.equalsIgnoreCase("rectangle")) {
      IShape shape = new Rectangle(name);
      model.addShape(shape);
    } else {
      throw new IllegalArgumentException("Invalid shape type!");
    }
    return this;
  }

  @Override
  public AnimationBuilder<IAnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                     int h1, int r1, int g1, int b1, int t2, int x2,
                                                     int y2, int w2, int h2, int r2, int g2, int b2)
  {
    // get shape from name
    IShape shape = model.getShape(name);

    // create event
    // fixme -- need to figure out how to set disappear (t2 of final motion)
    // fixme -- figure out radii for ellipses
    if (x1 != x2 || y1 != y2) {
      // set unrelated attributes
      shape.setDisappear(t2 + 1);
      shape.setWidth(w1);
      shape.setHeight(h1);
      shape.setColor(r1, g1, b1);

      // create event and add it to the model
      IEvent event = new MoveShape(shape, x1, y1, x2, y2);
      model.addEvent(shape, event, t1, t2);
    } else if (w1 != w2 || h1 != h2) {
      // set unrelated attributes
      shape.setDisappear(t2 + 1);
      shape.setColor(r1, g1, b1);
      shape.setLocation(x1, y1);

      // create event and add it to the model
      shape.setColor(r1, g1, b1);
      IEvent event = new ScaleShape(shape, w1, h1, w2, h2);
      model.addEvent(shape, event, t1, t2);
    } else if (r1 != r2 || b1 != b2 || g1 != g2) {
      // set unrelated attributes
      shape.setDisappear(t2 + 1);
      shape.setWidth(w1);
      shape.setHeight(h1);
      shape.setLocation(x1, y1);

      // create event and add it to the model
      IEvent event = new ChangeColor(shape, r1, g1, b1, r2, g2, b2);
      model.addEvent(shape, event, t1, t2);
    } else {
      // set unrelated attributes
      shape.setDisappear(t2 + 1);
      shape.setAppear(t1);
      shape.setLocation(x1, y1);
      shape.setHeight(h1);
      shape.setWidth(w1);
      shape.setColor(r1, g1, b1);
    }

    return this;
  }
}
