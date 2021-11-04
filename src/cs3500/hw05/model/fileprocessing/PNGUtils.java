package cs3500.hw05.model.fileprocessing;

import cs3500.hw05.model.image.IImage;

import java.io.IOException;

/**
 * This class contains utility methods to read a PNG image from file and convert to IImage,
 * and also to write an IImage to a PNG file.
 */
public class PNGUtils extends AbstractImageIO implements ImageUtils<IImage> {
  @Override
  public void writeToFile(IImage image, String filename) throws IOException {
    writeToFileHelp(image, filename, "png");
  }
}
