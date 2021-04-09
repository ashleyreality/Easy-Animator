package cs5004.animator.util;

public class Builder<IAnimationModel> implements AnimationBuilder<IAnimationModel> {

  // We need to implement this class so that it works with IAnimationModel. --AB
  IAnimationModel model;

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
    System.out.println("Shape " + name + " will be declared as " + type);
    return this;
  }

  @Override
  public AnimationBuilder<IAnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                     int h1, int r1, int g1, int b1, int t2, int x2,
                                                     int y2, int w2, int h2, int r2, int g2, int b2)
  {
    // add events to model
    System.out.println("Event " + name + " seen by Builder");
    return this;
  }
}
