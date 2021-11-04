Please see the USEME for information on how to use the GUI.
We did not attempt any extra credit.

Screenshot of working GUI is named exampleGUI.png

Working script files for the jar are JarCommands1.txt and JarCommands2.txt

** DESIGN CHANGES **
* HW 6's controller had the view hardcoded into the program. For this homework, 
  we decoupled the view and controller by creating the IView interface and using delegation. 
  This design change is necessary to allow for flexibility in choosing a view for the program. 
  To implement this change, we split IImageController's processCommands() method into two separate
  methods - start() and processCommand(String command). This design change was necessary in order
  to properly decouple processing commands from getting them (which is the view's job).
* Creation of IGuiView, GUIView, and GUIImageController. We created GUIImageController 
  and IGUIView because the GUI required view information that a text based view did not.
  This design change also necessitated the creation of AbstractController, as the processing
  command code was common across both controllers. 
* The file processing classes were modified to accept an absolute path rather than a file
  assumed to be in the resources/ folder. This design change was necessary because the GUI view
  did not require files to be located in the resources folder, and it actually  
  simplified the complexity of the file processing methods.
* All other classes remained unchanged.   

**VIEW**
* IView
    * this interface represents general operations necessary for a view in java. 
    These operations include getting commands and showing messages. 
* TextBasedView
    * This interface represents a text-based view for an image processing application. 
      Input can either by programmatically entered by the user or loaded from a script, and
      output is printed to the system.
    * this class assumes that all images/projects to be saved and loaded are contained
      in the resources folder. 
* IGUIView
    * this interface extends IView and represents view operations specific to a GUI View. 
      These operations mainly consist of setters that let the GUI to display image information.
* GUIView
   * this interface represents a GUI view of an image processing application. It lets
    the user modify an image through various menus and creates popups prompting for 
     extra information when necessary. 

**CONTROLLER**
* Main
  * runs the program. 
* IImageController
    * this interface represents basic operations for an image processing controller. This includes
    starting an image processing application and processing a command.
* AbstractController
   * generalizes implementation of the code to process image commands.
* TextImageController
    * represents a controller for a text based application. represents a controller as 
    a model and a view. 
* ViewObserver
  * this interface represents operations to update itself. It is designed so that a view
    can notify a controller when an action occurs.
* GUIImageController
    * represents a controller for a GUI application. Represents a controller as a model
    and a GUI view.

**FILTER**
* IFilter interface
  * This interface applies filters onto images.
* Blur class
  * This class blurs an image.
* Monochrome class
  * This class creates a monochrome image.
* Sepia class
  * This class creates a sepia image.
* Sharpen class
  * This class sharpens an image.

**IMAGE**
* IImage interface
  * This interface represents the different operations done onto images like: 
      * applyKernel - applies a 2D array of numbers onto the pixels of an image to filter it. 
      * applyLCT - applies a color transformation onto the pixels by linearly combining rgb values.
      * Equals - used to aid with testing.
      * Hashcode - used to aid with testing.
      * getWidth - retrieves the width of an image.
      * getHeight - retrieves the height of an image.
      * getPixels - retrieves the number of pixels in an image. 
      * getMaxValue - represents the maximum value of a pixel.
* CheckerboardImage class
  * This class allows for programattic construction of checkerboard images.
* Image1D class
  * Implements the methods listed above and performs the necessary actions. 

**PIXEL**
* IPixel interface
  * This interface represents the operations done onto pixels to filter it. 
* Channel enumeration
  * This class represents the three values of colors applied onto each pixel: red, green, and blue.
* SimplePixel class
  * This class represents a simple representation of a pixel through its 3 values: 
    red, green, and blue.
    
**LAYERED IMAGE**
 * ILayer Interface
  * This interface represents the operations done to a layer of an image.
 * IMultiLayeredImage
  * this interface represents operations done onto a multilayered image.
 * Layer1D
  * This class is represents a layer of an image as an optional contained image and a visibility.
 * SimpleMultiLayer
  * this class represents a multi-layered image as a list of layer names, the current layer
    name, and a mapping of layer names to layers. 
 
**FILE PROCESSING**
 * ImageUtils<T> Interface
  * This interface represents methods that import and export image objects to files.
 * Abstract ImageIO Class
   * abstract class that implements reading and writing functionality for jpg and png
    pictures using ImageIO. 
 * JPG Utils Class
   * This class contains utility methods to read a JPEG image from file and convert to IImage,
     and to write an IImage to a JPEG file.
 * MultiLayerUtil Class
   * This class represents methods to read and write a MultiLayeredImage to/from a text file.
 * PNG Utils Class
   * This class contains utility methods to read a PNG image from file and convert to IImage, and 
     to write an IImage to a PNG file.
 * PPM Utils Class
   * This class contains utility methods to read a PPM image from file and convert to IImage, 
     and to write an IImage to a PPM file.

**CITATIONS OF IMAGES**
* Flower Image: https://pixabay.com/photos/marguerite-daisy-flower-white-729510/
* Car Image: https://pixabay.com/vectors/volkswagen-car-bus-mobile-home-158463/