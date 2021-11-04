import cs3500.hw05.model.pixel.Channel;
import cs3500.hw05.model.pixel.IPixel;
import cs3500.hw05.model.pixel.SimplePixel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the various functions of IPixel.
 */
public class IPixelTests {
  IPixel black;
  IPixel white;
  IPixel pink;

  @Before
  public void setup() {
    black = new SimplePixel(0, 0, 0);
    white = new SimplePixel(255, 255, 255);
    pink = new SimplePixel(255,175,174);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullIPixelBreaksConstructor() {
    new SimplePixel(null);
  }

  @Test
  public void cloneConstructorWorks() {
    assertEquals(new SimplePixel(pink), pink);
    assertEquals(new SimplePixel(black), black);
  }

  @Test(expected = IllegalArgumentException.class)
  public void applyLCTBreaksForBadLCT() {
    double [][] badLCT = {{6, 5}, {4, 3}, {3, 2}};
    black.applyLCT(badLCT);
  }

  @Test(expected = IllegalArgumentException.class)
  public void applyLCTBreaksForBadLCT2() {
    double [][] badLCT = {{6, 5, 4}};
    black.applyLCT(badLCT);
  }

  @Test
  public void applyLCTWorks() {
    double [][] zeroLCT = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    double [][] noChangeLCT = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    double [][] sepiaLCT = {{.393, .769, .189}, {.349, .686, .168}, {.272, .534, .131}};
    white.applyLCT(noChangeLCT);
    assertEquals(white, new SimplePixel(255, 255, 255));
    white.applyLCT(zeroLCT);
    assertEquals(white, black);
    pink.applyLCT(sepiaLCT);
    assertEquals(pink, new SimplePixel(255, 238, 185));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getChannelBreaksForNullChannel() {
    pink.getChannel(null);
  }

  @Test
  public void getChannelWorks() {
    assertEquals(pink.getChannel(Channel.RED), 255);
    assertEquals(pink.getChannel(Channel.GREEN), 175);
    assertEquals(pink.getChannel(Channel.BLUE), 174);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setChannelBreaksForNullChannel() {
    pink.setChannel(5, null);
  }

  @Test
  public void setChannelWorks() {
    pink.setChannel(0, Channel.RED);
    pink.setChannel(4, Channel.GREEN);
    pink.setChannel(17, Channel.BLUE);
    assertEquals(pink, new SimplePixel(0, 4, 17));
  }

  @Test
  public void toStringWorks() {
    assertEquals(white.toString(), "255 255 255");
  }

  @Test
  public void equalsWorks() {
    assertEquals(black.equals(new SimplePixel(0, 0, 0)), true);
    assertEquals(white.equals(new SimplePixel(0, 0, 0)), false);
  }
}
