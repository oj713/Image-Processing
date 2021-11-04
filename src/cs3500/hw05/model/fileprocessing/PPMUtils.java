package cs3500.hw05.model.fileprocessing;

import cs3500.hw05.model.image.IImage;
import cs3500.hw05.model.image.Image1D;
import cs3500.hw05.model.pixel.IPixel;
import cs3500.hw05.model.pixel.SimplePixel;

import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class contains utility methods to read a PPM image from file and convert to IImage,
 * and also to write an IImage to a PPM file.
 */
public class PPMUtils implements ImageUtils<IImage> {
  @Override
  public void writeToFile(IImage image, String filename) throws IOException {
    File file = new File(filename);
    file.createNewFile();
    FileWriter writer = new FileWriter(file.toString());
    writer.write("P3 " + image.getWidth() + " " + image.getHeight() + " 255");
    for (IPixel pixel : image.getPixels()) {
      writer.write(" " + pixel.toString());
    }
    writer.close();
  }

  @Override
  public IImage readFile(String filename) throws IOException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      throw new IOException("File " + filename + " not found!");
    }
    sc.next();
    int width = sc.nextInt();
    int height = sc.nextInt();
    sc.nextInt();

    IPixel[] imagePixels = new IPixel[width * height];
    for (int i = 0; i < width * height; i++) {
      imagePixels[i] = new SimplePixel(sc.nextInt(), sc.nextInt(), sc.nextInt());
    }
    return new Image1D(imagePixels, width, height);
  }
}

