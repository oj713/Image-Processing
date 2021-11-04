package cs3500.hw05.model.fileprocessing;

import cs3500.hw05.model.image.IImage;
import cs3500.hw05.model.image.Image1D;
import cs3500.hw05.model.pixel.Channel;
import cs3500.hw05.model.pixel.IPixel;
import cs3500.hw05.model.pixel.SimplePixel;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class represents abstract operations to assist with reading
 * and writing image files using the ImageIO class.
 */
public abstract class AbstractImageIO implements ImageUtils<IImage> {
  @Override
  public IImage readFile(String filename) throws IOException {
    Path inputFile = Paths.get(filename);
    BufferedImage image = ImageIO.read(inputFile.toFile());

    return bufferedToIImage(image);
  }

  /**
   * Helper method for writing an IImage to a file.
   * @param image the image to be written to a file.
   * @param filename the name of the file to which the image will be written.
   * @param format the format of the file - png, jpg
   * @throws IOException if writing to the file fails.
   */
  protected void writeToFileHelp(IImage image, String filename, String format) throws IOException {
    File outputFile = new File(filename);
    outputFile.createNewFile();
    ImageIO.write(iImageToBuffered(image), format, outputFile);
  }

  /**
   * Converts a BufferedImage to an IImage.
   * @param image the image to be converted to IPixels.
   * @return the image as an IImage.
   * @throws IllegalArgumentException if the given image is null.
   */
  private static IImage bufferedToIImage(BufferedImage image) {
    if (image == null) {
      throw new IllegalArgumentException("null buffered image.");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    IPixel[] pixels = new SimplePixel[height * width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Color pixelColor = new Color(image.getRGB(col, row));
        pixels[row * width + col] = new SimplePixel(pixelColor.getRed(),
            pixelColor.getGreen(),
            pixelColor.getBlue());
      }
    }

    return new Image1D(pixels, width, height);
  }

  /**
   * Converts an IImage to a buffered image.
   * @param image the image to be converted.
   * @return the image as a buffered image.
   * @throws IllegalArgumentException if the given image is null
   */
  private static BufferedImage iImageToBuffered(IImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Cannot convert a null IImage to buffered.");
    }

    int width = image.getWidth();
    int height = image.getHeight();

    IPixel[] pixels = image.getPixels();

    int[] bufferedPixels = new int[height * width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int index = row * width + col;
        IPixel pixel =  pixels[index];
        bufferedPixels[index] = new Color(
            pixel.getChannel(Channel.RED),
            pixel.getChannel(Channel.GREEN),
            pixel.getChannel(Channel.BLUE)).getRGB();
      }
    }

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    bufferedImage.setRGB(0,0, width, height, bufferedPixels, 0, width);

    return bufferedImage;
  }
}
