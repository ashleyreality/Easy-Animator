package cs5004.animator.util;

import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;
import cs5004.animator.model.MoveShape;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ScaleShape;

//

// fixme -- intelliJ will not accept the right identifier??
public class Builder<T> implements AnimationBuilder<IAnimationModel> {

  // We need to implement this class so that it works with IAnimationModel. --AB
  cs5004.animator.model.IAnimationModel model;

  public Builder(IAnimationModel model) {
    this.model = model;
  }

  @Override
  public IAnimationModel build() {
    //System.out.println(model.toString());
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

  // It's bad design to have a function that takes in so many parameters
  // This does move from point A to point B with each call
  // What we want is to have the shape move multiple locations for the moving logic
  // It's not enough to just make one move at a time

  // Instead, create a keypoint, which says this is the state of my shape at time t
  // We pass in time, x, y, h, w, r, g, b
  // Each object needs a life span (when is it born & when does it die)
  // We interpolate between KeyPoints, which defines a path of motion
  // We want to interpolate for each type of change/property
  // Each shape is going to have its own animation defined by 3 sets of keypoints
  // the color --> here's how the color is changing
  // the scale/size
  // the move
  // We need to store the keypoint to some variable in order to access them to interpolate
  // them

  // These could be helpers for addMotion

//  void addColorKeyPoint(String shapeName, int time, int r, int g, int b) {}
//
//  void addScaleKeyPoint(String shapeName, int time, int h, int w) {}
//
//  void addMoveKeyPoint(String shapeName, int time, int x, int y) {}

  // Question: should we account for more than one change per row that would be passed into addMotion?
  // Question: For testing, should we create txt files for specific cases?

  @Override
  public AnimationBuilder<IAnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                     int h1, int r1, int g1, int b1, int t2, int x2,
                                                     int y2, int w2, int h2, int r2, int g2, int b2)
  {
    // get shape from name
    IShape shape = model.getShape(name); // update shape with t1, so the shape gets mutated

    // Should be all ifs instead of else ifs because what if the same shape has multiple changes
    // at once

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
      shape.setAppear(t1); // This will likely cause a bug because this should only occur if this is
        // the FIRST animation to appear on the view. Because this would scrap all previous animations.
        // I'm only alive at the beginning of the last animation because t1 is overriden with each animation

      shape.setLocation(x1, y1);
      shape.setHeight(h1);
      shape.setWidth(w1);
      shape.setColor(r1, g1, b1);
    }

    return this;
  }
}
