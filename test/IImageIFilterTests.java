import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import cs3500.hw05.model.filter.Blur;
import cs3500.hw05.model.filter.Monochrome;
import cs3500.hw05.model.filter.Sepia;
import cs3500.hw05.model.filter.Sharpen;
import cs3500.hw05.model.image.CheckerboardImage;
import cs3500.hw05.model.image.IImage;
import cs3500.hw05.model.image.Image1D;
import cs3500.hw05.model.pixel.IPixel;
import cs3500.hw05.model.pixel.SimplePixel;
import java.awt.Color;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the various functions of IImage and IFilter.
 */
public class IImageIFilterTests {

  IPixel black;
  IPixel white;
  IPixel pink;
  IImage checkerboardImage;
  IImage blurredCheckerboardImage;
  IImage monochromeCheckerboardImage;
  IImage sepiaCheckerboardImage;
  IImage sharpenCheckerboardImage;

  @Before
  public void setup() {
    black = new SimplePixel(0, 0, 0);
    white = new SimplePixel(255, 255, 255);
    pink = new SimplePixel(255, 175, 174);
    checkerboardImage =
        new CheckerboardImage(1, 2, 2, Color.WHITE, Color.BLACK);
    IPixel[] blurredImagePixels = {
      new SimplePixel(79, 79, 79),
      new SimplePixel(41, 41, 41),
      new SimplePixel(44, 44, 44),
      new SimplePixel(79, 79, 79)
    };
    blurredCheckerboardImage = new Image1D(blurredImagePixels, 2, 2);
    IPixel[] monochromeImagePixels = {
      new SimplePixel(254, 254, 254),
      new SimplePixel(0, 0, 0),
      new SimplePixel(0, 0, 0),
      new SimplePixel(254, 254, 254)
    };
    monochromeCheckerboardImage = new Image1D(monochromeImagePixels, 2, 2);
    IPixel[] sepiaImagePixels = {
      new SimplePixel(255, 255, 238),
      new SimplePixel(0, 0, 0),
      new SimplePixel(0, 0, 0),
      new SimplePixel(255, 255, 238)
    };
    sepiaCheckerboardImage = new Image1D(sepiaImagePixels, 2, 2);
    IPixel[] sharpenImagePixels = {
      new SimplePixel(255, 255, 255),
      new SimplePixel(127, 127, 127),
      new SimplePixel(159, 159, 159),
      new SimplePixel(255, 255, 255)
    };
    sharpenCheckerboardImage = new Image1D(sharpenImagePixels, 2, 2);
  }

  @Test
  public void createCheckerboardWorksCorrectly() {
    IPixel [] checkerboardPixels = {black, black, pink, pink, black, black, pink, pink,
        pink, pink, black, black, pink, pink, black, black};
    IImage checkerBoardExpected =
        new Image1D(checkerboardPixels, 4, 4);
    IImage checkerBoardActual =
        new CheckerboardImage(2, 2, 2, Color.BLACK, Color.PINK);
    assertEquals(checkerBoardExpected, checkerBoardActual);
  }

  @Test
  public void testApplySimpleKernel() {
    IPixel[] imagePixels = {black};
    IImage image = new Image1D(imagePixels, 1, 1);
    double[][] simpleKernel = {{0.0}};
    image.applyKernel(simpleKernel);
    assertEquals(black, (IPixel)image.getPixels()[0]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBad2by2Kernel() {
    IPixel[] imagePixels = {black};
    IImage image = new Image1D(imagePixels, 1, 1);
    double [][] kernel = {{1, 0}, {0, 1}};
    image.applyKernel(kernel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullKernel() {
    IPixel[] imagePixels = {black};
    IImage image = new Image1D(imagePixels, 1, 1);
    double [][] kernel = null;
    image.applyKernel(kernel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnevenKernel() {
    IPixel[] imagePixels = {black};
    IImage image = new Image1D(imagePixels, 1, 1);
    double [][] kernel = {{1}, {1}, {1}};
    image.applyKernel(kernel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadLCT() {
    IPixel[] imagePixels = {black};
    IImage image = new Image1D(imagePixels, 1, 1);
    double [][] lct = {{1, 0}, {0, 1}};
    image.applyLCT(lct);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullLCT() {
    IPixel[] imagePixels = {black};
    IImage image = new Image1D(imagePixels, 1, 1);
    double [][] lct = null;
    image.applyLCT(lct);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnevenLCT() {
    IPixel[] imagePixels = {black};
    IImage image = new Image1D(imagePixels, 1, 1);
    double [][] lct = {{1}, {1}, {1}};
    image.applyLCT(lct);
  }


  // TESTS FILTERS

  @Test
  public void testApplyBlurToBlack() {
    IPixel[] imagePixels = {black, black, black,black, black, black,black, black, black};
    IImage simpleBlackImage = new Image1D(imagePixels, 3, 3);
    Blur filter = new Blur();
    filter.apply(simpleBlackImage);
    assertTrue(Arrays.equals(imagePixels,simpleBlackImage.getPixels()));
  }

  @Test
  public void testApplyMonochromeToBlack() {
    IPixel[] imagePixels = {black, black, black,black, black, black,black, black, black};
    IImage simpleBlackImage = new Image1D(imagePixels, 3, 3);
    Monochrome filter = new Monochrome();
    filter.apply(simpleBlackImage);
    assertTrue(Arrays.equals(imagePixels,simpleBlackImage.getPixels()));

  }

  @Test
  public void testApplySepiaToBlack() {

    IPixel[] imagePixels = {black, black, black,black, black, black,black, black, black};
    IImage simpleBlackImage = new Image1D(imagePixels, 3, 3);
    Sepia filter = new Sepia();
    filter.apply(simpleBlackImage);
    assertTrue(Arrays.equals(imagePixels,simpleBlackImage.getPixels()));

  }

  @Test
  public void testApplySharpenToBlack() {
    IPixel[] imagePixels = {black, black, black,black, black, black,black, black, black};
    IImage simpleBlackImage = new Image1D(imagePixels, 3, 3);
    Sharpen filter = new Sharpen();
    filter.apply(simpleBlackImage);
    assertTrue(Arrays.equals(imagePixels,simpleBlackImage.getPixels()));
  }

  @Test
  public void testBlurredCheckerboardImage() {
    Blur filter = new Blur();
    filter.apply(checkerboardImage);
    assertEquals(checkerboardImage, blurredCheckerboardImage);
  }

  @Test
  public void testMonochromeCheckerboardImage() {
    Monochrome filter = new Monochrome();
    filter.apply(checkerboardImage);
    assertEquals(checkerboardImage, monochromeCheckerboardImage);

  }

  @Test
  public void testSepiaCheckerboardImage() {
    Sepia filter = new Sepia();
    filter.apply(checkerboardImage);
    assertEquals(checkerboardImage, sepiaCheckerboardImage);
  }

  @Test
  public void testSharpenCheckerboardImage() {
    Sharpen filter = new Sharpen();
    filter.apply(checkerboardImage);
    assertEquals(checkerboardImage, sharpenCheckerboardImage);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImageHeight() {
    IPixel[] imagePixels = {black};
    IImage image = new Image1D(imagePixels, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImageWidth() {
    IPixel[] imagePixels = {black};
    IImage image = new Image1D(imagePixels, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNumofPixels() {
    IPixel[] imagePixels = null;
    IImage image = new Image1D(imagePixels,0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPixelLengthTooSmall() {
    IPixel[] imagePixels = {black};
    IImage image = new Image1D(imagePixels, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLengthTooBig() {
    IPixel[] imagePixels = {black,white};
    IImage image = new Image1D(imagePixels, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPixelLengthNotEven() {
    IPixel[] imagePixels = {black, white, pink};
    IImage image = new Image1D(imagePixels, 4, 1);

  }
}

