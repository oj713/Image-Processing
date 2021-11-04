package cs3500.hw05.model.filter;

import cs3500.hw05.model.image.IImage;

/**
 * Represents a filter that converts an image to monochrome.
 */
public class Monochrome implements IFilter {
  private final double[][] monochromeLCT;

  /**
   * Constructs an {@code Monochrome} object.
   */
  public Monochrome() {
    this.monochromeLCT = new double[][]
        {{.2126, .7152, .0722}, {.2126, .7152, .0722}, {.2126, .7152, .0722}};
  }

  /**
   * Applies the monochrome filter onto given image.
   * @param image is the image to be filtered.
   */
  @Override
  public void apply(IImage image) {
    image.applyLCT(this.monochromeLCT);
  }
}
