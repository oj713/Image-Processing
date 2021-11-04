package cs3500.hw05.model.fileprocessing;

import cs3500.hw05.model.image.IImage;

import java.io.IOException;

/**
 * This class contains utility methods to read a JPEG image from file and convert to IImage,
 * and also to write an IImage to a JPEG file.
 */
public class JPGUtils extends AbstractImageIO implements ImageUtils<IImage> {
  @Override
  public void writeToFile(IImage image, String filename) throws IOException {
    writeToFileHelp(image, filename, "jpg");
  }
}
