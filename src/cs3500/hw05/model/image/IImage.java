package cs3500.hw05.model.image;

import cs3500.hw05.model.pixel.IPixel;

/**
 * This interface represents some basic operations onto an image in Java.
 */
public interface IImage {
  /**
   * Applies the given kernel onto an image.
   * @param kernel is the kernel to be applied.
   * @throws IllegalArgumentException if the given kernel does not have odd, equal dimensions.
   */
  public void applyKernel(double[][] kernel) throws IllegalArgumentException;

  /**
   * Applies the given linear color transformation onto the image.
   * @param lct is the linear color transformation to be applied.
   */
  public void applyLCT(double[][] lct);

  /**
   * Gets the width of the image.
   * @return int representing the width of the image.
   */
  public int getWidth();

  /**
   * Gets the height of the image.
   * @return int representing the height of the image.
   */
  public int getHeight();

  /**
   * Gets the array of IPixels.
   * @return IPixel Array
   */
  public IPixel[] getPixels();
}
