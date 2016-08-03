package fr.cyberdean.oklogger.output;

import fr.cyberdean.oklogger.Configuration;
import fr.cyberdean.oklogger.Level;
import fr.cyberdean.oklogger.logger.BasicLogger;
import static org.junit.Assert.*;

import okio.BufferedSource;
import okio.Okio;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileOutput test
 * @author Dean79000
 */
public class FileOutputTest {
  @Test
  public void finalRenameTest() throws IOException {
    final File dest = new File("test");
    assertEquals("test", dest.getName());
    assertTrue(dest.createNewFile());
    final File target = new File(dest.getAbsolutePath() + ".1");
    assertTrue(dest.renameTo(target));
    assertTrue(target.exists());
    assertFalse(dest.exists());
    assertEquals("test", dest.getName() );
    assertEquals("test.1", target.getName() );
    assertTrue(dest.createNewFile());

    assertTrue(dest.delete());
    assertTrue(target.delete());
  }

  @Test
  public void testMaxFiles() throws IOException {
    final File f = new File("testMaxFiles.log");
    final FileOutput fo = new FileOutput(f);
    assertEquals(2, fo.getMaxFiles());

    fo.setMaxFiles((byte)6);
    assertEquals(6, fo.getMaxFiles());
    assertTrue(f.delete());
  }

  @Test
  public void testMaxSizeFiles() throws IOException {
    final File f = new File("testMaxSizeFiles.log");
    final FileOutput fo = new FileOutput(f);
    assertEquals(10*1024*1024, fo.getMaxFileSize());

    fo.setMaxFileSize(42);
    assertEquals(42, fo.getMaxFileSize());
    assertTrue(f.delete());
  }

  @Test
  public void rollingDefaultTest() throws IOException {
    final File f = new File("rollingTest.log");
    final FileOutput fo = new FileOutput(f);
    assertEquals(2, fo.getMaxFiles());

    fo.setMaxFileSize(100);
    assertEquals(100, fo.getMaxFileSize());
    assertTrue(f.exists());

    final Configuration conf = new Configuration(Level.DEBUG, "{level} {className} {message}");
    conf.addOutput(fo);
    final BasicLogger logger = new BasicLogger(conf);

    final File f1 = new File("rollingTest.log.1");
    assertFalse(f1.exists());
    logger.info("Log an info message 1");
    logger.warn("Log an warn message 1");
    waiting(300);
    assertEquals("WARN fr.cyberdean.oklogger.output.FileOutputTest Log an warn message 1\r\n", readFile(f));
    assertEquals("INFO fr.cyberdean.oklogger.output.FileOutputTest Log an info message 1\r\n", readFile(f1));

    logger.info("Log an info message 2");
    waiting(300);
    assertEquals("INFO fr.cyberdean.oklogger.output.FileOutputTest Log an info message 2\r\n", readFile(f));
    assertEquals("WARN fr.cyberdean.oklogger.output.FileOutputTest Log an warn message 1\r\n", readFile(f1));

    logger.warn("Log an warn message 2");
    waiting(300);
    assertEquals("WARN fr.cyberdean.oklogger.output.FileOutputTest Log an warn message 2\r\n", readFile(f));
    assertEquals("INFO fr.cyberdean.oklogger.output.FileOutputTest Log an info message 2\r\n", readFile(f1));

    logger.info("Log an info message 3");
    logger.warn("Log an warn message 3");
    waiting(300);
    assertEquals("INFO fr.cyberdean.oklogger.output.FileOutputTest Log an info message 3\r\n", readFile(f1));
    assertEquals("WARN fr.cyberdean.oklogger.output.FileOutputTest Log an warn message 3\r\n", readFile(f));

    assertTrue(f.delete());
    assertTrue(f1.delete());
  }

  @Test
  public void threadedTest() throws IOException {
    final File f = new File("threadedTest.log");
    final FileOutput fo = new FileOutput(f);
    final Configuration conf = new Configuration(Level.DEBUG);
    conf.addOutput(fo);
    final BasicLogger logger = new BasicLogger(conf);

    final Thread one = new Thread(new Runnable() {
      @Override
      public void run() {
        for(int i = 100; i<200; i++) {
          logger.info(String.valueOf(i));
        }
      }
    });
    final Thread two = new Thread(new Runnable() {
      @Override
      public void run() {
        for(int i = 200; i<300; i++) {
          logger.info(String.valueOf(i));
        }
      }
    });
    final Thread three = new Thread(new Runnable() {
      @Override
      public void run() {
        for(int i = 300; i<400; i++) {
          logger.info(String.valueOf(i));
        }
      }
    });

    three.start();
    one.start();
    two.start();

    try {
      three.join();
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
    try {
      two.join();
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
    try {
      one.join();
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }

    waiting(1000);

    final BufferedSource src = Okio.buffer(Okio.source(f));
    String line;
    List<Integer> readNum = new ArrayList<>();
    while ((line = src.readUtf8Line()) != null) {
      int index = line.lastIndexOf(" ");
      assertTrue(index > -1);
      int num = Integer.parseInt(line.substring(index + 1, index + 4));
      assertFalse(readNum.contains(num));
      readNum.add(num);
    }
    assertEquals(300, readNum.size());

    //check list content (if not missing and duplicated)
    for (int i=100; i<400; i++) {
      assertTrue(readNum.contains(i));
      readNum.remove(readNum.indexOf(i));
    }
    assertEquals(0, readNum.size());
    assertTrue(f.delete());
  }

  private String readFile(final File f) throws IOException {
    return Okio.buffer(Okio.source(f)).readUtf8();
  }

  private void waiting(final long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ignore) {}
  }
}