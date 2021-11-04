package cs3500.hw05.control;

//import cs3500.hw05.model.image.IMultiLayeredImage;
import cs3500.hw05.model.image.SimpleMultiLayer;
import cs3500.hw05.view.IView;

import java.nio.file.Paths;

/**
 * This class represents the image controller which processes input from the script and produces
 * the necessary layering. It represents a controller as a view and an image.
 */
public class TextImageController extends AbstractController implements IImageController {
  private final IView view;

  /**
   * Constructs an {@code SimpleImageController} object.
   * @param view the view for the program.
   * @throws IllegalArgumentException if the given view is null.
   */
  public TextImageController(IView view) {
    if (view == null) {
      throw new IllegalArgumentException("Cannot process with null view.");
    }
    this.view = view;
    this.image = new SimpleMultiLayer();
  }

  @Override
  public void start() {
    view.showMessage("Beginning image processing application.\n");
    while (true) {
      String cmd = view.getImageCommand();
      if (cmd.equals("quit")) {
        return;
      }
      try {
        String status = this.processCommand(cmd);
        view.showMessage(status);
      } catch (IllegalArgumentException e) {
        view.showErrorMessage("Error: " + e.getMessage() + "\n");
      }
    }
  }

  @Override
  protected String getFilename(String filename) {
    return Paths.get(filename).toAbsolutePath().toString();
  }
}
