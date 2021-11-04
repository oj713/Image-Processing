package cs3500.hw05.model.image;

import cs3500.hw05.model.pixel.Channel;
import cs3500.hw05.model.pixel.IPixel;
import cs3500.hw05.model.pixel.SimplePixel;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a simple implementation of IImage. It represents an image as a width,
 * height, and a list of all the pixels in the image from left to right, top to bottom.
 */
public class Image1D implements IImage {
  private final int width;
  private final int height;
  private final IPixel[] imagePixels;

  /**
   * Constructs a {@code Image1DIPixel} object.
   * @param imagePixels an array of all the pixels in the image.
   * @param imageWidth the width of the image in pixels.
   * @param imageHeight the height of the image in pixels.
   * @throws IllegalArgumentException if any argument is invalid.
   */
  public Image1D(IPixel[] imagePixels, int imageWidth, int imageHeight) {
    if (imagePixels == null || imageWidth <= 0 || imageHeight <= 0
        || imagePixels.length != imageHeight * imageWidth) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
    IPixel[] pixels = new IPixel[imagePixels.length];
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = new SimplePixel(imagePixels[i]);
    }
    this.imagePixels = pixels;
    this.width = imageWidth;
    this.height = imageHeight;
  }

  /**
   * Constructs a {@code Image1D} object that's a copy of the given Image.
   * @param image is the image to be copied.
   */
  public Image1D(IImage image) throws IllegalArgumentException {
    this(image.getPixels(), image.getWidth(), image.getHeight());
  }

  @Override
  public void applyKernel(double[][] kernel) {
    if (kernel == null) {
      throw new IllegalArgumentException("Cannot apply null kernel");
    }
    if (kernel.length % 2 != 1 || kernel.length != kernel[0].length) {
      throw new IllegalArgumentException("Illegal kernel.");
    }

    int kleg = kernel.length / 2;

    for (Channel c : Channel.values()) {
      for (int central = 0; central < imagePixels.length; central++) {
        double newValue = 0;
        for (int ky = 0; ky < kernel.length; ky++) {
          for (int kx = 0; kx < kernel.length; kx++) {

            int row = central / width + ky - kleg;
            int column = central % width + kx - kleg;

            if (row >= 0 && row < height && column >= 0 && column < width) {
              newValue += kernel[ky][kx] * imagePixels[row * width + column].getChannel(c);
            }
          }
        }
        imagePixels[central].setChannel((int) newValue, c);
      }
    }
  }

  @Override
  public void applyLCT(double[][] lct) {
    if (lct == null) {
      throw new IllegalArgumentException("Cannot apply null lct");
    }
    if (lct.length % 3 != 0 || lct.length != lct[0].length) {
      throw new IllegalArgumentException("Illegal lct.");
    }
    for (IPixel pixel : imagePixels) {
      pixel.applyLCT(lct);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Image1D that = (Image1D) o;
    return width == that.width && height == that.height
        && Arrays.equals(imagePixels, that.imagePixels);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(width, height);
    result = 31 * result + Arrays.hashCode(imagePixels);
    return result;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public IPixel[] getPixels() {
    IPixel [] pixels = new IPixel[this.height * this.width];
    for (int i = 0; i < this.height * this.width; i++) {
      pixels[i] = new SimplePixel(this.imagePixels[i]);
    }
    return pixels;
  }

  @Override
  public String toString() {
    String finalString = "";
    for (IPixel pixel : imagePixels) {
      finalString += pixel.toString() + "\n";
    }
    return finalString;
  }
}
