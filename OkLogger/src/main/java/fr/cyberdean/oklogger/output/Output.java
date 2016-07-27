package fr.cyberdean.oklogger.output;

/**
 * Output, interface called by {@link fr.cyberdean.oklogger.logger.Logger} to write log message.
 * @see fr.cyberdean.oklogger.logger.Logger
 * @author Dean79000
 */
public interface Output {
  /**
   * Append message to output
   * @param str message to append
   */
  public void append(final String str);
}
