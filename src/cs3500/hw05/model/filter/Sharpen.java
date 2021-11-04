package cs3500.hw05.model.filter;

import cs3500.hw05.model.image.IImage;

/**
 * This class represents a filter that will sharpen an image.
 */
public class Sharpen implements IFilter {
  private final double[][] sharpenKernel;

  /**
   * Constructs an {@code Sharpen} object.
   */
  public Sharpen() {
    this.sharpenKernel = new double[][]{
        {-.125, -.125, -.125, -.125, -.125},
        {-.125, .25, .25, .25, -.125},
        {-.125, .25, 1, .25, -.125},
        {-.125, .25, .25, .25, -.125},
        {-.125, -.125, -.125, -.125, -.125}
    };
  }

  /**
   * Applies sharpen filter onto given image.
   * @param image is the image to be filtered.
   */
  @Override
  public void apply(IImage image) {
    image.applyKernel(this.sharpenKernel);
  }
}
