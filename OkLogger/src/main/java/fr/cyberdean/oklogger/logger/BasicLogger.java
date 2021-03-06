package fr.cyberdean.oklogger.logger;

import fr.cyberdean.oklogger.Configuration;
import fr.cyberdean.oklogger.Level;
import fr.cyberdean.oklogger.Utils;
import fr.cyberdean.oklogger.output.Output;

import java.sql.Timestamp;

/**
 * Basic Logger, send messages to configured outputs, if level is greater or equal than configured level.
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
    String className = "UnknownClass";
    for (final StackTraceElement aSte : ste) {
      if (!getClass().getName().equals(aSte.getClassName())) {
        className = aSte.getClassName();
        break;
      }
    }

    if (mConfiguration.getOutputs().isEmpty()) System.err.println("[" + getClass().getName() + "] No outputs configured !");
    if (getLevel().intVal <= level.intVal) {
      final String m = mConfiguration.getMessagePattern()
          .replace("{level}", level.name())
          .replace("{thread}", Thread.currentThread().getName())
          .replace("{timestamp}", String.valueOf(new Timestamp(System.currentTimeMillis()).getTime()))
          .replace("{className}", className)
          .replace("{message}", msg);

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