package fr.cyberdean.oklogger;

import fr.cyberdean.oklogger.output.Output;

import java.util.HashSet;
import java.util.Set;

/**
 * Loggers configuration
 * @author Dean79000
 */
public class Configuration {
  private Level mLevel;
  private Set<Output> mOutputs;
  private String mMessagePattern;

  /**
   * Default constructor, use "{level} {thread} {timestamp} {className} {message}" as messages pattern
   * @param level Log Level
   * @see Level
   */
  public Configuration(final Level level) {
    this(level, "{level} {thread} {timestamp} {className} {message}");
  }

  /**
   * Constructor
   * @param level Log Level
   * @param messagesPattern Messages pattern
   * @see Level
   */
  public Configuration(final Level level, final String messagesPattern) {
    mLevel = level;
    mOutputs = new HashSet<>();
    setMessagePattern(messagesPattern);
  }

  /**
   * Get current log level
   * @return Level enum member
   * @see Level
   */
  public Level getLevel() {
    return mLevel;
  }

  /**
   * Set current log level (Logs with lower level are ignored)
   * @param level Level enum member
   * @see Level
   */
  public void setLevel(final Level level) {
    mLevel = level;
  }

  /**
   * Get all outputs
   * @return outputs
   * @see Output
   */
  public Set<Output> getOutputs() {
    return mOutputs;
  }

  /**
   * Add new output
   * @param output output to add (if already added, it just ignored)
   * @see Output
   */
  public void addOutput(final Output output) {
    if (output == null) {
     System.err.println("[" + getClass().getName() + "] Output cannot be null !");
    }
    else {
      mOutputs.add(output);
    }
  }

  /**
   * Get current messages pattern. Default pattern is "{level} {thread} {timestamp} {className} {message}"
   * @return Current pattern
   */
  public String getMessagePattern() {
    return mMessagePattern;
  }

  /**
   * Define new messages pattern.
   * @param messagePattern Default pattern is "{level} {thread} {timestamp} {className} {message}"
   */
  public void setMessagePattern(final String messagePattern) {
    if (messagePattern == null || messagePattern.isEmpty()) {
      mMessagePattern = "{level} {thread} {timestamp} {className} {message}";
    }
    else {
      mMessagePattern = messagePattern;
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Configuration that = (Configuration) o;
    if (mLevel != that.mLevel) return false;
    if (mOutputs != null ? !mOutputs.equals(that.mOutputs) : that.mOutputs != null) return false;
    return mMessagePattern != null ? mMessagePattern.equals(that.mMessagePattern) : that.mMessagePattern == null;
  }

  @Override
  public int hashCode() {
    int result = mLevel != null ? mLevel.hashCode() : 0;
    result = 31 * result + (mOutputs != null ? mOutputs.hashCode() : 0);
    result = 31 * result + (mMessagePattern != null ? mMessagePattern.hashCode() : 0);
    return result;
  }
}
