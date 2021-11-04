package cs3500.hw05.model.filter;

import cs3500.hw05.model.image.IImage;

/**
 * This class represents a filter that will blur an image.
 */
public class Blur implements IFilter {
  private final double[][] blurKernel;

  /**
   * Constructs an {@code Blur} object.
   */
  public Blur() {
    this.blurKernel = new double[][]
        {{.0625, .125, .0625}, {.125, .25, .125}, {.0625, .125, .0625}};
  }

  /**
   * Applies blur filter onto given image.
   * @param image is the image to be filtered.
   */
  @Override
  public void apply(IImage image) {
    image.applyKernel(this.blurKernel);
  }
}
