package cs3500.hw05.model.image;

import cs3500.hw05.model.filter.IFilter;
import cs3500.hw05.model.image.IImage;

import java.util.Optional;

/**
 * This interface represents a layer of a multilayered image.
 * It houses operations to modify the layer.
 */
public interface ILayer {
  /**
   * Sets the opacity of this layer to the given value.
   * @param opacity the desired opacity of the layer.
   * @throws IllegalArgumentException if the opacity is invalid.
   */
  public void setOpacity(int opacity) throws IllegalArgumentException;

  /**
   * Retrieves the layer opacity.
   * @return the opacity of the layer.
   */
  public int getOpacity();

  /**
   * Returns true if the current layer is savable, eg. has a loaded image and is at 100 opacity.
   * @return whether the image is savable.
   */
  public boolean savable();

  /**
   * Applies the given filter to the image.
   * @param filter the filter to be applied.
   * @throws IllegalArgumentException if the given filter is null.
   * @throws IllegalStateException if no image has been loaded.
   */
  public void applyFilter(IFilter filter) throws IllegalArgumentException, IllegalStateException;

  /**
   * returns a copy of this layer's image, or false if there is no image.
   * @throws IllegalStateException if there is no loaded image for this layer.
   */
  public Optional<IImage> getImage() throws IllegalStateException;

  /**
   * Sets the image for this layer to the given image, overriding the current image if
   * necessary.
   * @param image is the image to be put in the layer
   * @throws IllegalArgumentException if the given image is null.
   */
  public void setImage(IImage image) throws IllegalArgumentException;
}
