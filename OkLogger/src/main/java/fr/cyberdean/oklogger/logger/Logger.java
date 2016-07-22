package fr.cyberdean.oklogger.logger;

import fr.cyberdean.oklogger.Level;

/**
 * Logging operations are done through this interface.
 * @author Dean79000
 */
public interface Logger {
  /**
   * Gets the Level associated with the Logger.
   *
   * @return the Level associate with the Logger.
   */
  Level getLevel();


  /**
   * Logs Throwable at the {@link Level#DEBUG DEBUG} level.
   *
   * @param t A Throwable
   */
  void debug(Throwable t);

  /**
   * Logs message at the {@link Level#DEBUG DEBUG} level.
   *
   * @param msg message
   */
  void debug(String msg);



  /**
   * Logs Throwable at the {@link Level#INFO INFO} level.
   *
   * @param t A Throwable
   */
  void info(Throwable t);

  /**
   * Logs message at the {@link Level#INFO INFO} level.
   *
   * @param msg message
   */
  void info(String msg);




  /**
   * Logs Throwable at the {@link Level#WARN WARN} level.
   *
   * @param t A Throwable
   */
  void warn(Throwable t);

  /**
   * Logs message at the {@link Level#WARN WARN} level.
   *
   * @param msg message
   */
  void warn(String msg);



  /**
   * Logs Throwable at the {@link Level#ERROR ERROR} level.
   *
   * @param t A Throwable
   */
  void error(Throwable t);

  /**
   * Logs message at the {@link Level#ERROR ERROR} level.
   *
   * @param msg message
   */
  void error(String msg);



  /**
   * Logs Throwable at the {@link Level#FATAL FATAL} level.
   *
   * @param t A Throwable
   */
  void fatal(Throwable t);

  /**
   * Logs message at the {@link Level#FATAL FATAL} level.
   *
   * @param msg message
   */
  void fatal(String msg);
}