package fr.cyberdean.oklogger;

import fr.cyberdean.oklogger.output.Output;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Dean79000
 */
public class Configuration {
  private Level mLevel;
  private Set<Output> mOutputs;

  //todo add outputPattern message. Eg. {level} {thread} {yyyy-MM-DD HH:mm:SS} {className} {message}

  public Configuration(final Level level) {
    mLevel = level;
    mOutputs = new HashSet<>();
  }

  public Level getLevel() {
    return mLevel;
  }

  public void setLevel(final Level level) {
    mLevel = level;
  }

  public Set<Output> getOutputs() {
    return mOutputs;
  }

  public void addOutput(final Output output) {
    if (output == null) {
     System.err.println("[" + getClass().getName() + "] Output cannot be null !");
    }
    else {
      mOutputs.add(output);
    }
  }
}
