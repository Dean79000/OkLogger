package fr.cyberdean.oklogger;

import org.junit.Assert;
import org.junit.Test;

public class LevelTest {

  @Test
  public void values() {
    Assert.assertEquals(Level.DEBUG.intVal, 0);
    Assert.assertEquals(Level.INFO.intVal, 1);
    Assert.assertEquals(Level.WARN.intVal, 2);
    Assert.assertEquals(Level.ERROR.intVal, 3);
    Assert.assertEquals(Level.FATAL.intVal, 4);
  }

  @Test
  public void incremental() {
    Assert.assertTrue(Level.DEBUG.intVal < Level.INFO.intVal);
    Assert.assertTrue(Level.INFO.intVal < Level.WARN.intVal);
    Assert.assertTrue(Level.WARN.intVal < Level.ERROR.intVal);
    Assert.assertTrue(Level.ERROR.intVal < Level.FATAL.intVal);
  }
}
