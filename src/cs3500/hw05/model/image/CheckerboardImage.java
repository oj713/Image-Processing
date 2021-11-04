package cs3500.hw05.model.image;

import cs3500.hw05.model.pixel.IPixel;
import cs3500.hw05.model.pixel.SimplePixel;

import java.awt.Color;
import java.util.Objects;

/**
 * This class represents a checkerboard image. It represents a checkerboardImage as an
 * Image1D object.
 */
public class CheckerboardImage implements IImage {
  private final IImage image;

  /**
   * Constructs an {@code CheckerboardImage} object.
   * @param squareSize is the size of each square in pixels.
   * @param widthInSquares is the width of the image in squares
   * @param heightInSquares is the height of the image in squares
   * @param color1 is the first color for the checkerboard.
   * @param color2 is the second color for the checkerboard.
   * @throws IllegalArgumentException if any argument is invalid.
   */
  public CheckerboardImage(int squareSize, int widthInSquares, int heightInSquares,
                           Color color1, Color color2) throws IllegalArgumentException {
    if (color1 == null || color2 == null
        || squareSize < 1 || widthInSquares < 0 || heightInSquares < 0) {
      throw new IllegalArgumentException("Cannot create an image from these arguments");
    }
    int imageWidth = squareSize * widthInSquares;
    int imageHeight = squareSize * heightInSquares;
    IPixel[] pixels = new IPixel[imageWidth * imageHeight];

    for (int row = 0; row < imageHeight; row ++) {
      for (int column = 0; column < imageWidth; column++) {
        Color color;
        if ((row / squareSize + column / squareSize) % 2 == 1) {
          color = color2;
        } else {
          color = color1;
        }
        pixels[row * imageWidth + column] =
            new SimplePixel(color.getRed(), color.getGreen(), color.getBlue());
      }
    }

    this.image = new Image1D(pixels, imageWidth, imageHeight);
  }

  @Override
  public void applyKernel(double[][] kernel) {
    image.applyKernel(kernel);
  }

  @Override
  public void applyLCT(double[][] lct) {
    image.applyLCT(lct);
  }

  @Override
  public boolean equals(Object o) {
    return this.image.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image);
  }

  @Override
  public int getWidth() {
    return image.getWidth();
  }

  @Override
  public int getHeight() {
    return image.getHeight();
  }

  @Override
  public IPixel[] getPixels() {
    return image.getPixels();
  }

  @Override
  public String toString() {
    return this.image.toString();
  }

}
