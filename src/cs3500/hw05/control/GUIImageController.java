package cs3500.hw05.control;

import cs3500.hw05.model.fileprocessing.PNGUtils;
import cs3500.hw05.model.image.SimpleMultiLayer;
import cs3500.hw05.view.IGUIView;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class represents a controller that handles and transmits image processing information
 * to a GUI view. It represents a controller as a GUI view and an image.
 */
public class GUIImageController extends AbstractController
    implements IImageController, ViewObserver {
  private final IGUIView view;

  /**
   * Constructs an {@code GUIImageController} object.
   * @param view is the view to be used for the controller.
   * @throws IllegalArgumentException if the provided view is null.
   */
  public GUIImageController(IGUIView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("Cannot use null view.");
    }
    this.view = view;
    this.image = new SimpleMultiLayer();
  }

  @Override
  public void start() {
    this.view.setObserver(this);
    this.view.makeVisible();
  }

  @Override
  public void update() {
    String command = view.getImageCommand();
    try {
      String status = this.processCommand(command);
      view.showMessage(status);
    } catch (Exception ex) {
      view.showErrorMessage(ex.getMessage());
    }
    try {
      new PNGUtils().writeToFile(image.getSaveImage(),
          Paths.get("resources\\gui.png").toAbsolutePath().toString());
      view.setImageVisibility(true);
    } catch (IOException | IllegalStateException ex) {
      view.setImageVisibility(false);
    }
    ArrayList<String> names = image.getNames();
    ArrayList<String> reversedNames = new ArrayList<>();
    for (int i = names.size() - 1; i >= 0; i = i - 1) {
      reversedNames.add(names.get(i));
    }
    view.setNames(reversedNames);
    view.setCurrent(image.getCurrent());
    boolean currentVisible;
    try {
      currentVisible = image.getLayer(image.getCurrent()).getOpacity() == 100;
    } catch (IllegalArgumentException e) {
      currentVisible = false;
      view.showErrorMessage(e.getMessage());
    }
    view.setCurrentVisibility(currentVisible);
    view.refresh();
  }

  @Override
  protected String getFilename(String filename) {
    return filename;
  }
}
