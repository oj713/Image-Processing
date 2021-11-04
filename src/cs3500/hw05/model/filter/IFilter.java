package cs3500.hw05.model.filter;

import cs3500.hw05.model.image.IImage;

/**
 * This interface represents a filter that modifies an image.
 */
public interface IFilter {
  /**
   * Applies this filter onto an image.
   * @param image is the image to be filtered.
   */
  public void apply(IImage image);
}
