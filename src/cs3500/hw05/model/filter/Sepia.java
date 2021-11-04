package cs3500.hw05.model.filter;

import cs3500.hw05.model.image.IImage;

/**
 * Represents a filter that converts an image to sepia.
 */
public class Sepia implements IFilter {
  private final double[][] sepiaLCT;

  /**
   * Constructs an {@code Sepia} object.
   */
  public Sepia() {
    this.sepiaLCT = new double[][]
        {{.393, .769, .189}, {.349, .686, .168}, {.272, .534, .131}};
  }

  /**
   * Applies sepia filter onto the given image.
   * @param image is the image to be filtered.
   */
  @Override
  public void apply(IImage image) {
    image.applyLCT(this.sepiaLCT);
  }
}
