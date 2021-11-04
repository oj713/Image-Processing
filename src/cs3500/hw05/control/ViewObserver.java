package cs3500.hw05.control;

/**
 * This interface represents a simple observer to be used on a view. It has one
 * function that when called, will update the observer class.
 */
public interface ViewObserver {
  /**
   * Updates the observer class.
   */
  void update();
}
