package cs3500.hw05.control;

import cs3500.hw05.model.fileprocessing.ImageUtils;
import cs3500.hw05.model.fileprocessing.JPGUtils;
import cs3500.hw05.model.fileprocessing.MultiLayerUtil;
import cs3500.hw05.model.fileprocessing.PNGUtils;
import cs3500.hw05.model.fileprocessing.PPMUtils;
import cs3500.hw05.model.image.IImage;
import cs3500.hw05.model.image.IMultiLayeredImage;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents an abstract implementation of IImageController. It contains an
 * implementation of the method used to process commands and apply them to an image.
 */
public abstract class AbstractController implements IImageController {
  protected IMultiLayeredImage image;

  @Override
  public String processCommand(String command) throws IllegalArgumentException {
    Scanner s = new Scanner(command);
    StringBuilder output = new StringBuilder();
    while (s.hasNext()) {
      String in = s.next();
      try {
        switch (in) {
          case "create":
            this.image.newLayer(s.next());
            output.append("Created new layer.\n");
            break;
          case "current":
            this.image.setCurrent(s.next());
            output.append("Set current layer.\n");
            break;
          case "invisible":
            this.image.setOpacity(0);
            output.append("Made current layer invisible.\n");
            break;
          case "visible":
            this.image.setOpacity(100);
            output.append("Made current layer visible.\n");
            break;
          case "blur":
            this.image.blur();
            output.append("Applied blur to current layer.\n");
            break;
          case "sepia":
            this.image.sepia();
            output.append("Applied sepia to current layer.\n");
            break;
          case "sharpen":
            this.image.sharpen();
            output.append("Applied sharpen to current layer.\n");
            break;
          case "monochrome":
            this.image.monochrome();
            output.append("Applied monochrome to current layer.\n");
            break;
          case "load":
            String type = s.next();
            String filename = this.getFilename(s.next());
            if (type.equals("layer")) {
              this.image.loadLayer(getUtils(filename).readFile(filename));
              output.append("Loaded image to current layer.\n");
            } else if (type.equals("project")) {
              this.image = new MultiLayerUtil().readFile(filename);
              output.append("Loaded project.\n");
            } else {
              throw new IllegalArgumentException("Invalid command");
            }
            break;
          case "save":
            type = s.next();
            filename = this.getFilename(s.next());
            if (type.equals("image")) {
              getUtils(filename).writeToFile(this.image.getSaveImage(), filename);
              output.append("Saved project to image.\n");
            } else if (type.equals("project")) {
              new MultiLayerUtil().writeToFile(image, filename);
              output.append("Saved project.\n");
            } else {
              throw new IllegalArgumentException("Invalid command.");
            }
            break;
          default:
            throw new IllegalArgumentException("Invalid command.");
        }
      }
      catch (IOException | NoSuchElementException e) {
        throw new IllegalArgumentException("Failed to process " + in + ".\n");
      }
    }
    return output.toString();
  }

  /**
   * Gets a filepath from the given filename.
   * This method is necessary because GUI provides an absolute path while
   * text and script files only provide the name of a file in the resources folder.
   * @param filename the name of the file.
   * @return the filepath to the file.
   */
  protected abstract String getFilename(String filename);

  /**
   * Returns the appropriate ImageUtils object to handle a filename.
   * @param filename the name of the file for which an ImageUtils object will be generated.
   * @return an appropriate ImageUtils object.
   * @throws IllegalArgumentException if the filename is invalid.
   */
  private static ImageUtils<IImage> getUtils(String filename) throws IllegalArgumentException {
    if (filename.length() < 5) {
      throw new IllegalArgumentException("Invalid filename");
    }
    switch (filename.substring(filename.length() - 4)) {
      case ".ppm":
        return new PPMUtils();
      case ".jpg":
        return new JPGUtils();
      case ".png":
        return new PNGUtils();
      default:
        throw new IllegalArgumentException("Invalid filename or format.");
    }
  }
}
