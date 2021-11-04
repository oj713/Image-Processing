package cs3500.hw05.model.fileprocessing;

import java.io.IOException;

/**
 * This interface represents methods that import and export image objects to files.
 * It's parametrized over the type of image object to be imported/exported.
 */
public interface ImageUtils<T> {
  /**
   * Reads a file and interprets it to an object of type T.
   * @param filename the name of the file within the resources/ folder.
   * @return an object object parsed from file data.
   * @throws IOException if reading to the file fails.
   */
  public T readFile(String filename) throws IOException;

  /**
   * Converts image to the given file, creating a new file if necessary.
   * @param image represents the image that is being filtered
   * @param filename is the name of the image file.
   * @throws IOException if writing to the file fails.
   */
  public void writeToFile(T image, String filename) throws IOException;
}
