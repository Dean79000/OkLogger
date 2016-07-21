package fr.cyberdean.oklogger.output;

public class ConsoleOutput implements Output {
  @Override
  public void append(final String str) {
    System.out.println(str);
  }
}