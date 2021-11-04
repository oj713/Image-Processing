package cs3500.hw05.view;

import java.io.IOException;

/**
 * This interface represents basic operations necessary for a view in an image processing
 * application. It includes functionality to access commands and show messages.
 */
public interface IView {
  /**
   * Gets the next image command from the user.
   * @return the next image command.
   */
  String getImageCommand();

  /**
   * Shows the given message.
   * @param msg the message to be shown.
   * @throws IOException if showing the message fails.
   */
  void showMessage(String msg);

  /**
   * Shows the given error message.
   * @param msg the message to be shown.
   * @throws IOException if showing the message fails.
   */
  void showErrorMessage(String msg);
}
