package cs3500.hw05.model.fileprocessing;

import cs3500.hw05.model.image.IImage;
import cs3500.hw05.model.image.Image1D;
import cs3500.hw05.model.image.ILayer;
import cs3500.hw05.model.image.IMultiLayeredImage;
import cs3500.hw05.model.image.SimpleMultiLayer;
import cs3500.hw05.model.pixel.IPixel;
import cs3500.hw05.model.pixel.SimplePixel;

import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents operations to read and write a MultiLayeredImage to/from a text file.
 */
public class MultiLayerUtil implements ImageUtils<IMultiLayeredImage> {

  @Override
  public IMultiLayeredImage readFile(String filename) throws IOException {
    IMultiLayeredImage image = new SimpleMultiLayer();
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      throw new IOException("File " + filename + " not found!");
    }
    int numLayers = sc.nextInt();
    for (int i = 0; i < numLayers; i++) {
      image.newLayer(sc.next());
      image.setOpacity(sc.nextInt());
      int width = sc.nextInt();
      if (width != -1) {
        IImage layerImage = this.parseIImage(sc, width);
        image.loadLayer(layerImage);
      }
    }
    image.setCurrent(sc.next());
    return image;
  }

  /**
   * parses the scanner for an IImage object.
   * @param sc the scanner to be used
   * @param width the width of the IImage
   * @return an IImage parsed from the scanner
   * @throws IOException if reading from the scanner fails.
   */
  private IImage parseIImage(Scanner sc, int width) throws IOException {
    int height = sc.nextInt();
    sc.nextInt();

    int length = width * height;
    IPixel[] imagePixels = new SimplePixel[length];
    for (int i = 0; i < length; i++) {
      imagePixels[i] = new SimplePixel(sc.nextInt(), sc.nextInt(), sc.nextInt());
    }
    return new Image1D(imagePixels, width, height);
  }

  @Override
  public void writeToFile(IMultiLayeredImage image, String filename) throws IOException {
    File file = new File(filename);
    file.createNewFile();
    FileWriter writer = new FileWriter(file.toString());
    writer.write(image.getNames().size() + "\n");
    for (String name : image.getNames()) {
      writer.write(name + "\n");
      this.writeLayer(writer, image.getLayer(name));
    }
    writer.write(image.getCurrent() + "\n");
    writer.close();
  }

  /**
   * Writes the information for a layer using the given file writer.
   * @param writer the file writer writing info.
   * @param layer the layer being transcribed.
   * @throws IOException if something fails during transcribing.
   */
  private void writeLayer(FileWriter writer, ILayer layer) throws IOException {
    writer.write(layer.getOpacity() + "\n");
    if (layer.getImage().isEmpty()) {
      writer.write("-1\n");
    } else {
      IImage image = layer.getImage().get();
      writer.write(image.getWidth() + " " + image.getHeight() + " 256\n");
      for (IPixel pixel : image.getPixels()) {
        writer.write(pixel.toString() + " ");
      }
    }
  }
}
