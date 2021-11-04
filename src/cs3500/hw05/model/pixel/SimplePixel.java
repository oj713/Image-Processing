package cs3500.hw05.model.pixel;

import java.util.Objects;

/**
 * This class represents a simple pixel for an image. It represents a pixel as its red,
 * green, and blue values, where the maximum allowed value for a pixel is 255.
 */
public class SimplePixel implements IPixel {
  private int r;
  private int g;
  private int b;
  private final int max = 255;

  /**
   * Constructs an {@code SimplePixel} object.
   * @param r is the red value of the pixel.
   * @param g is the green value of the pixel.
   * @param b is the blue value of the pixel.
   */
  public SimplePixel(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
    this.clamp();
  }

  /**
   * Constructs an {@code SimplePixel} object copying another object.
   * @param pixel the pixel to be copied.
   * @throws IllegalArgumentException if the pixel is null.
   */
  public SimplePixel(IPixel pixel) {
    if (pixel == null) {
      throw new IllegalArgumentException("Cannot copy null pixel.");
    }
    this.r = pixel.getChannel(Channel.RED);
    this.g = pixel.getChannel(Channel.GREEN);
    this.b = pixel.getChannel(Channel.BLUE);
  }

  @Override
  public void applyLCT(double[][] lct) throws IllegalArgumentException {
    if (lct.length != 3 || lct[0].length != 3) {
      throw new IllegalArgumentException("Invalid linear color transformation.");
    }
    int oldR = this.r;
    int oldG = this.g;
    int oldB = this.b;
    this.r = (int) (lct[0][0] * oldR + lct[0][1] * oldG + lct[0][2] * oldB);
    this.g = (int) (lct[1][0] * oldR + lct[1][1] * oldG + lct[1][2] * oldB);
    this.b = (int) (lct[2][0] * oldR + lct[2][1] * oldG + lct[2][2] * oldB);

    this.clamp();
  }

  @Override
  public int getChannel(Channel channel) throws IllegalArgumentException {
    if (channel == null) {
      throw new IllegalArgumentException("null channel.");
    }
    switch (channel) {
      case RED:
        return r;
      case GREEN:
        return g;
      case BLUE:
        return b;
      default:
        throw new IllegalArgumentException("Should not get here.");
    }
  }

  @Override
  public void setChannel(int value, Channel channel) {
    if (channel == null) {
      throw new IllegalArgumentException("null channel.");
    }
    switch (channel) {
      case RED:
        r = value;
        break;
      case GREEN:
        g = value;
        break;
      case BLUE:
        b = value;
        break;
      default:
        throw new IllegalArgumentException("Should not get here.");
    }
    this.clamp();
  }

  /**
   * Clamps the value of this pixel to between 0 and 255.
   */
  private void clamp() {
    r = (int)Math.min(Math.max(r, 0), max);
    g = (int)Math.min(Math.max(g, 0), max);
    b = (int)Math.min(Math.max(b, 0), max);
  }

  @Override
  public String toString() {
    return r + " " + g + " " + b;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimplePixel pixel = (SimplePixel) o;
    return r == pixel.r && g == pixel.g && b == pixel.b;
  }

  @Override
  public int hashCode() {
    return Objects.hash(r, g, b);
  }
}
