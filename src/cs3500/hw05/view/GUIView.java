package cs3500.hw05.view;

import cs3500.hw05.control.ViewObserver;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

/**
 * This class represents the GUI view. 
 */
public class GUIView extends JFrame implements IGUIView, ActionListener {
  //records input from GUI
  private String input;

  // observer that is notified if input changes
  private ViewObserver observer;

  //variables to record the state of the project.
  private ArrayList<String> names;
  private String current;
  private boolean currentVisible;
  private String message;
  private boolean imageVisible;

  //panels that will change when refreshing
  ButtonGroup visibilityGroup;
  JRadioButtonMenuItem visible;
  JRadioButtonMenuItem invisible;
  JPanel mainPanel;

  /**
   * Constructs the GUI.
   */
  public GUIView() {
    super();
    this.setTitle("Image Processing View");
    this.setSize(600, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.input = "";
    this.observer = null;
    this.names = new ArrayList<>();
    this.current = "";
    this.currentVisible = true;
    this.message = "Loaded new project.";
    this.imageVisible = false;

    this.setJMenuBar(this.makeMenuBar());

    this.mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    mainPanel.add(this.makeTopPane());
    mainPanel.add(this.makeFooter());

    this.add(mainPanel);
    pack();
  }

  /**
   * Makes the top panel, which consists of the image and layers.
   * @return the top panel.
   */
  private JPanel makeTopPane() {
    JPanel topPane = new JPanel();
    topPane.setLayout(new FlowLayout());
    topPane.add(this.makeImagePane());
    topPane.add(this.makeLayersPane());
    return topPane;
  }

  /**
   * Constructs the footer pane for the application displaying confirmation messages.
   * @return the footer pane.
   */
  private JPanel makeFooter() {
    JPanel footer = new JPanel();
    JLabel footerMessage = new JLabel(this.message);
    footer.add(footerMessage);
    return footer;
  }

  /**
   * Constructs the image pane for the application.
   * @return the image pane for the application.
   */
  private JScrollPane makeImagePane() {
    JPanel imagePanel = new JPanel();
    if (imageVisible) {
      try {
        String imageFile = Paths.get("resources\\gui.png").toAbsolutePath().toString();
        BufferedImage image = ImageIO.read(new File(imageFile));
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imagePanel.add(imageLabel);
      } catch (IOException e) {
        this.showErrorMessage("Loading image went wrong.");
      }
    }

    JScrollPane imageScrollPane = new JScrollPane(imagePanel);
    imageScrollPane.setBorder(BorderFactory.createTitledBorder("Topmost Visible Layer"));
    imageScrollPane.setPreferredSize(new Dimension(600, 600));
    return imageScrollPane;
  }

  /**
   * Constructs the panel displaying the layers to the image.
   * @return a panel representing the layers of the image.
   */
  private JPanel makeLayersPane() {
    JPanel layers = new JPanel();
    layers.setLayout(new BoxLayout(layers, BoxLayout.PAGE_AXIS));
    layers.add(new JLabel("Layer select: "));
    ButtonGroup group = new ButtonGroup();

    for (String name : this.names) {
      JRadioButton button =
          this.makeButton(name, "current " + name);
      group.add(button);
      if (name.equals(current)) {
        button.setSelected(true);
      }
      layers.add(button);
    }
    JRadioButton createNew = this.makeButton("Create new", "create ");
    group.add(createNew);
    layers.add(createNew);
    return layers;
  }

  /**
   * Constructs a JRadioButton object and links it to the action listener.
   * @param name the name of the button
   * @param command the action command for the button
   * @return a new JRadioButton.
   */
  private JRadioButton makeButton(String name, String command) {
    JRadioButton button = new JRadioButton(name);
    button.addActionListener(this);
    button.setActionCommand(command);
    return button;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setObserver(ViewObserver observer) {
    this.observer = observer;
  }

  @Override
  public String getImageCommand() {
    String command = input;
    this.input = "";
    return command;
  }

  @Override
  public void showMessage(String msg) {
    this.message = msg;
  }

  @Override
  public void showErrorMessage(String msg) {
    JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setNames(ArrayList<String> names) {
    this.names = names;
  }

  @Override
  public void setCurrent(String current) {
    this.current = current;
  }

  @Override
  public void setCurrentVisibility(boolean visible) {
    this.currentVisible = visible;
  }

  @Override
  public void setImageVisibility(boolean visible) {
    this.imageVisible = visible;
  }

  @Override
  public void refresh() {
    mainPanel.removeAll();
    mainPanel.add(this.makeTopPane());
    mainPanel.add(this.makeFooter());
    mainPanel.revalidate();
    visible.setSelected(currentVisible);
    invisible.setSelected(!currentVisible);
    mainPanel.repaint();
    visible.repaint();
    invisible.repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    String inputText = "";
    switch (command) {
      case "create ":
        String layerName = (String) JOptionPane.showInputDialog("Enter new layer name:");
        inputText = command + layerName;
        break;
      case "load project ":
      case "load layer ":
        JFileChooser jfc = new JFileChooser();
        jfc.showDialog(null,"Please select file to load:");
        String filename = jfc.getSelectedFile().getAbsolutePath();
        inputText = command + filename;
        break;
      case "save project ":
      case "save image ":
        JFileChooser jfc2 = new JFileChooser();
        jfc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc2.showDialog(null,"Please select file directory:");
        String directory = jfc2.getSelectedFile().getAbsolutePath();
        String name = (String) JOptionPane.showInputDialog("Enter name under which to save file:");
        inputText = command + directory + "/" + name;
        break;
      default:
        inputText = command;
    }
    if (!inputText.equals("")) {
      this.input = inputText;
      observer.update();
    }
  }

  /**
   * Constructs the menu bar for the GUI.
   * @return a new menu bar for this GUI.
   */
  private JMenuBar makeMenuBar() {
    final JMenuBar menuBar = new JMenuBar();

    //project menu -- allows for loading and saving a project.
    JMenu projectMenu = this.makeMenu("Project",
        this.makeMenuItem("Load Project", "load project "),
        this.makeMenuItem("Save Project", "save project "));
    menuBar.add(projectMenu);

    //image menu -- allows for loading and saving images
    JMenu imageMenu = this.makeMenu("Image",
        this.makeMenuItem("Load layer image", "load layer "),
        this.makeMenuItem("Save project image", "save image "));
    menuBar.add(imageMenu);

    //filter menu -- allows for applying a filter
    JMenu filterMenu = this.makeMenu("Filters",
        this.makeMenuItem("Blur", "blur"),
        this.makeMenuItem("Sharpen", "sharpen"),
        this.makeMenuItem("Sepia", "sepia"),
        this.makeMenuItem("Monochrome", "monochrome"));
    menuBar.add(filterMenu);

    // visibility menu -- allows for control of whether this layer is visible/invisible
    visibilityGroup = new ButtonGroup();
    visible =
        this.makeRadioMenuItem("Layer Visible", "visible", visibilityGroup);
    invisible =
        this.makeRadioMenuItem("Layer Invisible", "invisible", visibilityGroup);
    visible.setSelected(currentVisible);
    JMenu visibilityMenu = this.makeMenu("Visibility", visible, invisible);
    menuBar.add(visibilityMenu);

    return menuBar;
  }

  /**
   * Constructs a menu.
   * @param name the name of the menu.
   * @param items all the menu items for the menu
   * @return a new menu.
   */
  private JMenu makeMenu(String name, JMenuItem... items) {
    JMenu menu = new JMenu(name);
    for (JMenuItem item : items) {
      menu.add(item);
    }
    return menu;
  }

  /**
   * Constructs a radio button menu item.
   * @param name the name of the button.
   * @param command the command for the button.
   * @param group the group for the button.
   * @return a new radio button item.
   */
  private JRadioButtonMenuItem makeRadioMenuItem(String name, String command, ButtonGroup group) {
    JRadioButtonMenuItem item = new JRadioButtonMenuItem(name);
    item.addActionListener(this);
    item.setActionCommand(command);
    group.add(item);
    return item;
  }

  /**
   * Constructs a menu item.
   * @param name the name of the menu item
   * @param command the action command for the menu item.
   * @return a new menu item.
   */
  private JMenuItem makeMenuItem(String name, String command) {
    JMenuItem item = new JMenuItem(name);
    item.addActionListener(this);
    item.setActionCommand(command);
    return item;
  }
}
