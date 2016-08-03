package fr.cyberdean.oklogger;

import static org.junit.Assert.*;

import fr.cyberdean.oklogger.output.ConsoleOutput;
import org.junit.Test;

public class ConfigurationTest {

  @Test
  public void defaultConstructorTest() {
    final Configuration c = new Configuration(Level.DEBUG);
    assertEquals(Level.DEBUG, c.getLevel());
    assertEquals("{level} {thread} {timestamp} {className} {message}", c.getMessagePattern());

    assertEquals(Level.INFO, new Configuration(Level.INFO).getLevel());
    assertEquals(Level.WARN, new Configuration(Level.WARN).getLevel());
    assertEquals(Level.ERROR, new Configuration(Level.ERROR).getLevel());
    assertEquals(Level.FATAL, new Configuration(Level.FATAL).getLevel());
  }

  @Test
  public void constructorTest() {
    final Configuration c = new Configuration(Level.DEBUG, "{level} {className} {message}");
    assertEquals(Level.DEBUG, c.getLevel());
    assertEquals("{level} {className} {message}", c.getMessagePattern());
  }

  @Test
  public void levelTest() {
    final Configuration c = new Configuration(Level.DEBUG);
    assertEquals(Level.DEBUG, c.getLevel());

    c.setLevel(Level.WARN);
    assertEquals(Level.WARN, c.getLevel());
  }

  @Test
  public void messagesPatternTest() {
    final Configuration c = new Configuration(Level.DEBUG);
    assertEquals("{level} {thread} {timestamp} {className} {message}", c.getMessagePattern());

    c.setMessagePattern(null);
    assertEquals("{level} {thread} {timestamp} {className} {message}", c.getMessagePattern());

    c.setMessagePattern("");
    assertEquals("{level} {thread} {timestamp} {className} {message}", c.getMessagePattern());

    c.setMessagePattern("{level} {className} {message}");
    assertEquals("{level} {className} {message}", c.getMessagePattern());
  }

  @Test
  public void equalsHashCodeTest() {
    final Configuration c = new Configuration(Level.DEBUG);
    assertEquals(c, new Configuration(Level.DEBUG));
    assertEquals(c.hashCode(), new Configuration(Level.DEBUG).hashCode());
  }

  @Test
  public void testOutputs() {
    final Configuration c = new Configuration(Level.DEBUG);
    assertNotNull(c.getOutputs());
    assertEquals(0, c.getOutputs().size());

    c.addOutput(null);
    assertEquals(0, c.getOutputs().size());

    final ConsoleOutput consoleOutput = new ConsoleOutput();
    c.addOutput(consoleOutput);
    assertEquals(1, c.getOutputs().size());

    c.addOutput(consoleOutput);
    assertEquals(1, c.getOutputs().size());

    c.getOutputs().clear();
    assertEquals(0, c.getOutputs().size());
  }

}
