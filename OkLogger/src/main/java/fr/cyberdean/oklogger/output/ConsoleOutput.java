package fr.cyberdean.oklogger.output;

/**
 * ConsoleOutput, display logs in standard console sdtout.
 * @author Dean79000
 */
public class ConsoleOutput implements Output {
  @Override
  public void append(final String str) {
    System.out.println(str);
  }
}