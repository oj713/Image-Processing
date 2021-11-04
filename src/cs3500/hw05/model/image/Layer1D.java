package cs3500.hw05.model.image;

import cs3500.hw05.model.filter.IFilter;

import java.util.Objects;
import java.util.Optional;

/**
 * This class represents a simple implementation of a layer. It represents a layer
 * as an optional image object and an opacity.
 */
public class Layer1D implements ILayer {
  private Optional<IImage> image;
  private int opacity;

  /**
   * Constructs an {@code Layer1D} object.
   */
  public Layer1D() {
    this.opacity = 100;
    this.image = Optional.empty();
  }

  @Override
  public void setOpacity(int opacity) throws IllegalArgumentException {
    if (opacity < 0 || opacity > 100) {
      throw new IllegalArgumentException("Invalid opacity");
    }
    this.opacity = opacity;
  }

  @Override
  public int getOpacity() {
    return this.opacity;
  }

  @Override
  public boolean savable() {
    return image.isPresent() && opacity == 100;
  }

  @Override
  public void applyFilter(IFilter filter) throws IllegalArgumentException {
    if (image.isEmpty()) {
      throw new IllegalStateException("No loaded image to blur in layer.");
    }
    if (filter == null) {
      throw new IllegalArgumentException("Cannot apply null filter.");
    }
    filter.apply(this.image.get());
  }

  @Override
  public Optional<IImage> getImage() {
    if (image.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(new Image1D(this.image.get()));
  }

  @Override
  public void setImage(IImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Cannot add null image");
    }
    this.image = Optional.of(image);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Layer1D layer1D = (Layer1D) o;
    return opacity == layer1D.opacity && Objects.equals(image, layer1D.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image, opacity);
  }
}
