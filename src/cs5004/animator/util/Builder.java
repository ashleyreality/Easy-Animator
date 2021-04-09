package cs5004.animator.util;

public class Builder<IAnimationModel> implements AnimationBuilder<IAnimationModel> {

  public Builder() {
  }

  @Override
  public IAnimationModel build() {
    return null;
  }

  @Override
  public AnimationBuilder<IAnimationModel> setBounds(int x, int y, int width, int height) {
    return null;
  }

  @Override
  public AnimationBuilder<IAnimationModel> declareShape(String name, String type) {
    return null;
  }

  @Override
  public AnimationBuilder<IAnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                     int h1, int r1, int g1, int b1, int t2, int x2,
                                                     int y2, int w2, int h2, int r2, int g2, int b2)
  {
    return null;
  }
}
