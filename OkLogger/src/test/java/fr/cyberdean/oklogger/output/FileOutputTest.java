package fr.cyberdean.oklogger.output;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileOutputTest {

  @Test
  public void finalRenameTest() throws IOException {
    final File dest = new File("test");
    Assert.assertEquals("test", dest.getName());
    Assert.assertTrue(dest.createNewFile());
    final File target = new File(dest.getAbsolutePath() + ".1");
    Assert.assertTrue(dest.renameTo(target));
    Assert.assertTrue(target.exists());
    Assert.assertFalse(dest.exists());
    Assert.assertEquals("test", dest.getName() );
    Assert.assertEquals("test.1", target.getName() );
    Assert.assertTrue(dest.createNewFile());

    Assert.assertTrue(dest.delete());
    Assert.assertTrue(target.delete());
  }

}
