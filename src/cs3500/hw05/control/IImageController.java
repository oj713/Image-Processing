package cs3500.hw05.control;

/**
 * This interface represents functions for a simple controller for an image processing application.
 * The controller can process commands and apply them to a multilayered image.
 */
public interface IImageController {
  /**
   * Processes a command and applies it to a multilayered image.
   * @param command the command to be executed
   * @return a string representing the status or error message.
   */
  public String processCommand(String command);

  /**
   * Starts the program.
   */
  public void start();
}
