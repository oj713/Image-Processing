Input method is determined at runtime by command line arguments: 
* -script FILENAME : loads and runs the text commands stored in script at given file location
* -text : loads text-based view
* -interactive : loads GUI view 

**SUPPORTED COMMANDS**

Note: All caps denotes user input. See sub-bullets for information on how to activate
  this command with the GUI.

* load project FILENAME - loads a project from the given file to work with.
    * the file must exist and be a text file with valid project data. 
    * GUI: click on the "Project" menu and select "Load Project." A file selector popup
      will appear -- navigate to the correct file and confirm.
* save project FILENAME - saves a multi layered project to a file. Either overwrites
  an existing file or creates a new one.
  * filetype must be a text file
  * GUI: click on the "Project" menu and select "Save Project." The resulting file selector
    pop up will prompt you to select the desired destination directory. Then, 
    a text pop up will ask you to enter the name of the file to be saved. 
* create NAME - creates a new layer for a multilayered image with the given name.
  new layer is designated the topmost layer, and current reference changes to it.
    * the name should be unique.
    * GUI: Click on "create new" in the sidebar and enter the desired layer name when prompted.
* quit - quits the program.
    * GUI: click on the "x" on the top right of the screen.

NOTE: all of the below commands require that at least one layer has been created.
* load layer FILENAME - loads an image into the working layer from the given file.
  * Supported filetypes: png, jpg, ppm
  * the file must exist for the command to work correctly.
  * GUI: click on the "Image" menu and select "Load Layer Image." A file selector popup
    will appear -- navigate to the correct file and confirm.
* current NAME - sets the working layer to the layer with the given name.
    * the name must match the name of an existing layer.
    * GUI: Click on the desired current layer on the sidebar. 
* invisible / visible - sets the visibility of the working layer to be invisible/visible.
  An invisible layer cannot be seen when the project is exported to an image.
    * GUI: Click on the "Visibility" dropdown and select the desired visibility from
      the dropdown. The current visibility of the layer will already be selected.
* blur / sepia / monochrome / sharpen - applies the designated image filter onto
  the working layer.
    * requires that an image has been loaded to the layer.
    * GUI: Click on the "Filters" menu and select the desired filter from the dropdown.
* save image FILENAME - saves the project as a single image to the designated file location.
  A saved project image will consist of the topmost loaded, visible layer to the image.
  Overrides the data in the given file or creates a new one.
  * Supported filetypes: png, jpg, ppm
  * GUI: click on the "Image" menu and select "Save Project image." The resulting file selector
    pop up will prompt you to select the desired destination directory. Then,
    a text pop up will ask you to enter the name of the file to be saved. 
