package cs3500.hw05.view;

//import cs3500.hw05.model.image.IImage;

//import java.awt.event.ActionListener;
import cs3500.hw05.control.ViewObserver;

import java.util.ArrayList;
//import java.util.Observer;

/**
 * This interface represents a view for a GUI. It has methods necessary for transmitting
 * and receiving necessary information to/from a GUI.
 */
public interface IGUIView extends IView {
  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();

  /**
   * Provide the views with an observer for the controller.
   * The observer allows the controller class to be notified
   * when a new input command is provided.
   *
   * @param observer is the observer to be used.
   */
  void setObserver(ViewObserver observer);

  /**
   * Sets the names of all the layers available for the image.
   * @param names is a list of all the names of layers
   */
  void setNames(ArrayList<String> names);

  /**
   * Sets the current layer of the image.
   * @param current is the current layer.
   */
  void setCurrent(String current);

  /**
   * Sets the visibility of the current layer.
   * @param visible is true if the current layer is visible.
   */
  void setCurrentVisibility(boolean visible);

  /**
   * Sets the visibility of a saveable image.
   * @param visible is true if a saveable image is visible.
   */
  void setImageVisibility(boolean visible);

  /**
   * Signal the view to draw itself.
   */
  void refresh();
}
