package cs5004.animator.util;

import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.IAnimationModel;
import cs5004.animator.model.IEvent;
import cs5004.animator.model.IShape;
import cs5004.animator.model.MoveShape;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ScaleShape;

/**
 * ___________________________ IMPLEMENTATION CLASS: Builder {} ___________________________________.
 * The Builder class implements AnimationBuilder<IAnimationModel>. It is used to build the animation
 * model from a file.
 */
public class Builder implements AnimationBuilder<IAnimationModel> {
  IAnimationModel model;

  /**
   * _________________________________ CONSTRUCTOR: Builder() _____________________________________.
   * Constructs a Builder instance.
   *
   * @param model the model of the animation, an IAnimationModel
   */
  public Builder(IAnimationModel model) {
    this.model = model;
  }

  /**
   * ___________________________________ METHOD: Build() ______________________________________.
   * Builds the final animation model.
   *
   * @return this instance of the animation model
   */
  @Override
  public IAnimationModel build() {
    return this.model;
  }

  /**
   * ___________________________________ METHOD: setBounds() ______________________________________.
   * Sets the boundaries of the animation.
   *
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the bounding box
   * @param height The height of the bounding box
   * @return
   */
  @Override
  public AnimationBuilder<IAnimationModel> setBounds(int x, int y, int width, int height) {

    model.addBounds(x, y, width, height);
    return this;
  }

  /**
   * _________________________________ METHOD: declareShape() _____________________________________.
   * Adds a shape to this animation model.
   *
   * @param name The unique name of the shape to be added. No shape with this name should already
   *             exist.
   * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
   *             shapes is unspecified, but should include "ellipse" and "rectangle" at a minimum.
   * @return
   */
  @Override
  public AnimationBuilder<IAnimationModel> declareShape(String name, String type) {

    // add shapes to model
    if (type.equalsIgnoreCase("ellipse")) {
      IShape shape = new Ellipse(name, type);
      model.addShape(shape);
    } else if (type.equalsIgnoreCase("rectangle")) {
      IShape shape = new Rectangle(name, type);
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

  /**
   * ___________________________________ METHOD: addMotion() ______________________________________.
   *
   * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
   * @param t1   The start time of this transformation
   * @param x1   The initial x-position of the shape
   * @param y1   The initial y-position of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-position of the shape
   * @param y2   The final y-position of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   * @return this instance of the animation model
   */
  @Override
  public AnimationBuilder<IAnimationModel> addMotion(String name,
                                                     int t1,
                                                     int x1, int y1,
                                                     int w1, int h1,
                                                     int r1, int g1, int b1,
                                                     int t2,
                                                     int x2, int y2,
                                                     int w2, int h2,
                                                     int r2, int g2, int b2) {

    // get the desired shape from the model using the shape's name
    IShape shape = model.getShape(name);

    // using a default value (-1) that would flag whether the  appear time has not yet been set
    // set appear time (the start of when the changes to the shape occur)
    if (shape.getAppear() == -1) {
      shape.setAppear(t1);
    }

    // using a default value (null) that would flag whether the shape color has not yet been set
    // set initial color
    if (shape.getColor() == null) {
      shape.setColor(r1, g1, b1);
    }

    // using a default value (null) that would flag whether the shape location has not yet been set
    // set initial location
    if (shape.getLocation() == null) {
      shape.setLocation(x1, y1);
    }

    // using a default value (0) that would flag whether the shape's width has not yet been set
    // set initial width
    if (shape.getWidth() == 0) {
      shape.setWidth(w1);
    }

    // using a default value (0) that would flag whether the shape's height has not yet been set
    // set initial height
    if (shape.getHeight() == 0) {
      shape.setHeight(h1);
    }

    // create event
    // if the position of the shape has changed on either the x-axis or y-axis
    // then set the disappearance time for the change to end
    if (x1 != x2 || y1 != y2) {
      // set unrelated attributes
      shape.setDisappear(t2);
      //shape.setWidth(w1);
      //shape.setHeight(h1);
      //shape.setColor(r1, g1, b1);

      // create event and add it to the model
      IEvent event = new MoveShape(shape, x1, y1, x2, y2);
      model.addEvent(shape, event, t1, t2);
    } else if (w1 != w2 || h1 != h2) {
      // set unrelated attributes
      shape.setDisappear(t2);
      //shape.setColor(r1, g1, b1);
      //shape.setLocation(x1, y1);

      // create event and add it to the model
      IEvent event = new ScaleShape(shape, w1, h1, w2, h2);
      model.addEvent(shape, event, t1, t2);
    } else if (r1 != r2 || b1 != b2 || g1 != g2) {
      // set unrelated attributes
      shape.setDisappear(t2);
      //shape.setWidth(w1);
      //shape.setHeight(h1);
      //shape.setLocation(x1, y1);

      // create event and add it to the model
      IEvent event = new ChangeColor(shape, r1, g1, b1, r2, g2, b2);
      model.addEvent(shape, event, t1, t2);
    } else {
      // just set disappear for now, but I think we need a doNothing event
      shape.setDisappear(t2);
    }

    return this;
  }
}
