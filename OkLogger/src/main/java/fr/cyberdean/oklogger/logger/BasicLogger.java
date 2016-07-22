package fr.cyberdean.oklogger.logger;

import fr.cyberdean.oklogger.Configuration;
import fr.cyberdean.oklogger.Level;
import fr.cyberdean.oklogger.Utils;
import fr.cyberdean.oklogger.output.Output;

/**
 * Basic Logger, send messages to configured outputs, if level is greater or equal than configured level.
 * Also define message pattern like "[Log level] [ClassName] [message]"
 * @see Configuration
 * @see Output
 * @author Dean79000
 */
public class BasicLogger implements Logger {
  private Configuration mConfiguration;

  public BasicLogger(final Configuration configuration) {
    mConfiguration = configuration;
  }

  private void write(final Level level, final String msg) {
    final StackTraceElement[] ste = new Exception().getStackTrace();
    String className = "";
    for (int i=0; i<ste.length;i++) {
      if (!getClass().getName().equals(ste[i].getClassName())) {
        className = ste[i].getClassName();
        break;
      }
    }

    if (mConfiguration.getOutputs().isEmpty()) System.err.println("[" + getClass().getName() + "] No outputs configured !");
    if (getLevel().intVal <= level.intVal) {
      final String m = level.name() + " " + className + " " + msg;
      for (final Output out : mConfiguration.getOutputs()) {
        out.append(m);
      }
    }
  }

  @Override
  public Level getLevel() {
    return mConfiguration.getLevel();
  }

  @Override
  public void debug(final Throwable t) {
    debug(Utils.stacktraceToString(t));
  }

  @Override
  public void debug(final String msg) {
    write(Level.DEBUG, msg);
  }

  @Override
  public void info(final Throwable t) {
    info(Utils.stacktraceToString(t));
  }

  @Override
  public void info(final String msg) {
    write(Level.INFO, msg);
  }

  @Override
  public void warn(final Throwable t) {
    warn(Utils.stacktraceToString(t));
  }

  @Override
  public void warn(final String msg) {
    write(Level.WARN, msg);
  }

  @Override
  public void error(final Throwable t) {
    error(Utils.stacktraceToString(t));
  }

  @Override
  public void error(final String msg) {
    write(Level.ERROR, msg);
  }

  @Override
  public void fatal(final Throwable t) {
    fatal(Utils.stacktraceToString(t));
  }

  @Override
  public void fatal(final String msg) {
    write(Level.FATAL, msg);
  }
}