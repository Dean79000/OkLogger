package fr.cyberdean.oklogger;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {
  private Utils() {
  }

  public static String stacktraceToString(final Throwable t) {
    final StringWriter sw = new StringWriter();
    t.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }

}
