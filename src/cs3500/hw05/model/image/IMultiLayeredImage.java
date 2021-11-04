package cs3500.hw05.model.image;

import cs3500.hw05.model.image.IImage;
import cs3500.hw05.model.image.ILayer;

import java.util.ArrayList;

/**
 * This interface represents operations onto a multilayered image.
 */
public interface IMultiLayeredImage {
  /**
   * Creates a new topmost layer to the image. Default visible. Changes current to this new layer.
   * @param name is the name of the layer.
   * @throws IllegalArgumentException if the new layer name is null or already exists.
   */
  public void newLayer(String name) throws IllegalArgumentException;

  /**
   * Changes the current layer that the user is modifying.
   * @param name is the name of the new current layer.
   * @throws IllegalArgumentException if the name doesn't match to a layer.
   */
  public void setCurrent(String name) throws IllegalArgumentException;

  /**
   * Loads the given image into the current layer.
   * @param image is the image to be added.
   * @throws IllegalArgumentException if the image is null.
   */
  public void loadLayer(IImage image) throws IllegalArgumentException;

  /**
   * Returns the multilayered image as a single IImage.
   * @return the multi layered image as a single IImage.
   * @throws IllegalStateException if there are no visible layers or no layer
   *     has a loaded image.
   */
  public IImage getSaveImage() throws IllegalStateException;

  /**
   * Sets the current layer to the desired opacity.
   * @param opacity the desired opacity.
   * @throws IllegalArgumentException if the opacity is invalid.
   */
  public void setOpacity(int opacity) throws IllegalArgumentException;

  /**
   * Blurs the current layer of the image.
   * @throws IllegalArgumentException if no image has been loaded to the layer.
   */
  public void blur() throws IllegalArgumentException;

  /**
   * Sharpens the current layer of the image.
   * @throws IllegalArgumentException if no image has been loaded to the layer.
   */
  public void sharpen() throws IllegalArgumentException;

  /**
   * Converts the current layer to sepia.
   * @throws IllegalArgumentException if no image has been loaded to the layer.
   */
  public void sepia() throws IllegalArgumentException;

  /**
   * Converts the current layer to monochrome.
   * @throws IllegalArgumentException if no image has been loaded to the layer.
   */
  public void monochrome() throws IllegalArgumentException;

  /**
   * Retrieves the list of layer names for this image.
   * @return the list of layer names.
   */
  public ArrayList<String> getNames();

  /**
   * Retrieves the layer for this name.
   * @param name the name of the layer.
   * @return the layer for the name.
   * @throws IllegalArgumentException if the name is invalid.
   */
  public ILayer getLayer(String name) throws IllegalArgumentException;

  /**
   * Retrieves the current layer name.
   * @return the current layer name.
   */
  public String getCurrent();
}
