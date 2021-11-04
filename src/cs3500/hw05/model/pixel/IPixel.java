package cs3500.hw05.model.pixel;

/**
 * This interface represents simple operations onto a pixel in an image.
 */
public interface IPixel {

  /**
   * Applies the given linear color transformation onto this pixel.
   * @param lct is the linear color transformation to be applied.
   * @throws IllegalArgumentException if the given LCT does not have 3 x 3 dimensions
   */
  public void applyLCT(double[][] lct) throws IllegalArgumentException;

  /**
   * Retrieves the value of the given channel.
   * @param channel the channel for which a value will be retrieved
   * @return the value of the channel.
   * @throws IllegalArgumentException if the channel is null
   */
  public int getChannel(Channel channel) throws IllegalArgumentException;

  /**
   * Sets a channel to the given value.
   * @param value the value to which a channel will be set.
   * @param channel the channel to be multiplied.
   * @throws IllegalArgumentException if the channel is null
   */
  public void setChannel(int value, Channel channel) throws IllegalArgumentException;
}
