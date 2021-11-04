package cs3500.hw05.control;

import cs3500.hw05.view.GUIView;
import cs3500.hw05.view.TextBasedView;

/**
 * This class represents an interaction to apply filters and transformations onto a
 * multilayered image.
 */
public class Main {
  /**
   * Runs an image processing program.
   * @param args indicate the output for the program
   */
  public static void main(String[] args) {
    IImageController controller;
    if (args.length == 0) {
      throw new IllegalArgumentException("No command line arguments provided.");
    }
    switch (args[0]) {
      case "-script":
        if (args.length != 2) {
          throw new IllegalArgumentException("script must be followed by filename.");
        }
        controller = new TextImageController(new TextBasedView(args[1]));
        break;
      case "-text":
        controller = new TextImageController(new TextBasedView());
        break;
      case "-interactive":
        controller = new GUIImageController(new GUIView());
        break;
      default:
        throw new IllegalArgumentException("Invalid command line argument");
    }
    controller.start();
  }
}
