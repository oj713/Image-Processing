package cs3500.hw05.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class represents a view that receives and outputs text information. Text information is
 * outputted to the system, and input can either by inputted from the system or loaded
 * from a text file.
 */
public class TextBasedView implements IView {
  private final Scanner sc;
  private final Appendable out = System.out;

  /**
   * Constructs a TextBasedView object that gets input from the system.
   */
  public TextBasedView() {
    this.sc = new Scanner(System.in);
  }

  /**
   * Constructs a TextBasedView object that gets input from a script file located in resources.
   * @param filename the name of the file with commands within the resources folder.
   * @throws IllegalArgumentException if reading from the file fails.
   */
  public TextBasedView(String filename) throws IllegalArgumentException {
    try {
      Path inputFile = Paths.get(filename).toAbsolutePath();
      this.sc = new Scanner(new FileInputStream(inputFile.toString()));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("No such file found.");
    }
  }

  @Override
  public String getImageCommand() {
    if (sc.hasNext()) {
      return sc.nextLine();
    } else {
      return "quit";
    }
  }

  @Override
  public void showMessage(String msg) {
    try {
      out.append(msg);
    } catch (IOException e) {
      System.out.println("Couldn't append message.");
    }
  }

  @Override
  public void showErrorMessage(String msg) {
    this.showMessage(msg);
  }
}
