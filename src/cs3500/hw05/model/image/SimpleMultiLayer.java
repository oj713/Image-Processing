package cs3500.hw05.model.image;

import cs3500.hw05.model.filter.Blur;
import cs3500.hw05.model.filter.Monochrome;
import cs3500.hw05.model.filter.Sepia;
import cs3500.hw05.model.filter.Sharpen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a simple implementation of IMultiLayeredImage. It represents a
 * multi layered image as a list of layer names, the current layer, and a map of names
 * and layers.
 */
public class SimpleMultiLayer implements IMultiLayeredImage {
  private final ArrayList<String> names;
  private final Map<String, ILayer> layers;
  private String current;

  /**
   * Constructs an {@code SimpleMultiLayer} object.
   */
  public SimpleMultiLayer() {
    names = new ArrayList<>();
    layers = new HashMap<>();
    current = "";
  }

  @Override
  public void newLayer(String name) {
    if (name == null || name.equals("") || names.contains(name)) {
      throw new IllegalArgumentException("Invalid name");
    }
    this.current = name;
    names.add(name);
    layers.put(name, new Layer1D());
  }

  @Override
  public void setCurrent(String name) throws IllegalArgumentException {
    if (!names.contains(name)) {
      throw new IllegalArgumentException("given layer does not exist.");
    }
    this.current = name;
  }

  @Override
  public void loadLayer(IImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Cannot load from null image");
    }
    this.layers.get(this.current).setImage(image);
  }

  @Override
  public IImage getSaveImage() throws IllegalStateException {
    for (int i = names.size() - 1; i >= 0; i -= 1) {
      ILayer layer = layers.get(names.get(i));
      if (layer.savable()) {
        return layer.getImage().get();
      }
    }
    throw new IllegalStateException("No layers in the image could be saved.");
  }

  @Override
  public void setOpacity(int opacity) throws IllegalArgumentException {
    if (opacity < 0 || opacity > 100) {
      throw new IllegalArgumentException("Invalid opacity.");
    }
    this.currLayer().setOpacity(opacity);
  }

  @Override
  public void blur() throws IllegalArgumentException {
    this.currLayer().applyFilter(new Blur());
  }

  @Override
  public void sharpen() throws IllegalArgumentException {
    this.currLayer().applyFilter(new Sharpen());
  }

  @Override
  public void sepia() throws IllegalArgumentException {
    this.currLayer().applyFilter(new Sepia());
  }

  @Override
  public void monochrome() throws IllegalArgumentException {
    this.currLayer().applyFilter(new Monochrome());
  }

  @Override
  public ArrayList<String> getNames() {
    return new ArrayList<String>(names);
  }

  @Override
  public ILayer getLayer(String name) throws IllegalArgumentException {
    if (!names.contains(name)) {
      throw new IllegalArgumentException("given layer does not exist.");
    }
    ILayer layer = layers.get(name);
    ILayer layerCopy = new Layer1D();
    if (layer.getImage().isPresent()) {
      layerCopy.setImage(layer.getImage().get());
    }
    layerCopy.setOpacity(layer.getOpacity());
    return layerCopy;
  }

  @Override
  public String getCurrent() {
    return this.current;
  }

  /**
   * Returns the current layer.
   * @return the current layer.
   */
  private ILayer currLayer() {
    return this.layers.get(this.current);
  }
}
