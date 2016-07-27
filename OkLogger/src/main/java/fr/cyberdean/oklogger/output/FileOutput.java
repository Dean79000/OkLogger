package fr.cyberdean.oklogger.output;

import okio.BufferedSink;
import okio.Okio;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * FileOutput, write logs in limited number of files and backup them when they reach a certain size.
 * This class is Thread-Safe and fast (without synchronized).
 * @author Dean79000
 */
public class FileOutput implements Output {
  private BlockingQueue<String> mQueue = new ArrayBlockingQueue<>(512);
  private BufferedSink mSink;
  private File mFile;
  private Thread mThread;
  protected long mCount = 0;

  //todo add FilePattern name

  /**
   The default maximum file size is 10MB.
   */
  private long mMaxFileSize = 10485760;

  /**
   There is two backup file by default.
   */
  private byte mMaxFilesIndex = 1;

  /**
   * Constructor init buffer.
   * @param file Base destination file (Eg. if 'test.log' with maxFiles = 2, you can have 'test.log' and 'test.log.1' files
   * @throws IOException If file not exist, and fail to create it
   */
  public FileOutput(final File file) throws IOException {
    mFile = file;
    if (!file.exists()) {
      file.createNewFile(); //Ignore result because we want the file exists
    }
    mSink = Okio.buffer(Okio.sink(file));
    mCount = file.length();
    mThread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (!mThread.isInterrupted()) {
          try {
            final String str = mQueue.take();
            if (str != null) {
              if (mCount + str.length() + 2 >= mMaxFileSize) {
                boolean canContinue = true;
                final String path = mFile.getAbsolutePath();
                try {
                  File file = new File(path + '.' + mMaxFilesIndex);
                  if (file.exists()) {
                    canContinue = file.delete(); //Delete old file
                  }

                  // Map {(maxBackupIndex - 1), ..., 2, 1} to {maxBackupIndex, ..., 3, 2}
                  File target;
                  for (int i = mMaxFilesIndex - 1; i >= 1 && canContinue; i--) {
                    file = new File(path + '.' + i);
                    if (file.exists()) {
                      target = new File(path + '.' + (i + 1));
                      canContinue = file.renameTo(target);
                    }
                  }

                  if (canContinue) { //rename actual file to .1  and update sink
                    target = new File(path + ".1");
                    if (mFile.renameTo(target)) {
                      if (mFile.createNewFile()) {
                        mSink = Okio.buffer(Okio.sink(mFile));
                        mCount = 0;
                      }
                    }
                  }
                }
                catch (final Exception e) {
                  e.printStackTrace();
                }
              }
              mSink.writeUtf8(str);
              mSink.writeUtf8("\r\n");
              mSink.flush();
              mCount += str.length() + 2;
            }
          }
          catch (final InterruptedException ignore) {}
          catch (final IOException e) {
            e.printStackTrace();
          }
        }
      }
    });
    mThread.setPriority(Thread.NORM_PRIORITY + 1);
    mThread.start();
  }

  /**
   * Add message to writing queue, can block the thread if max capacity has reached.
   * @param str Message to append
   */
  @Override
  public void append(final String str) {
    try {
      mQueue.put(str);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get maximum files size.
   * @return size in bytes
   */
  public long getMaxFileSize() {
    return mMaxFileSize;
  }

  /**
   * Set maximum files size.
   * @param maxFileSize size in bytes
   */
  public void setMaxFileSize(final long maxFileSize) {
    mMaxFileSize = maxFileSize;
  }

  /**
   * Get maximum number of files used.
   * @return number of files
   */
  public byte getMaxFiles() {
    return (byte)(mMaxFilesIndex+1);
  }

  /**
   * Set maximum number of files used.
   * @param maxFiles number of files
   */
  public void setMaxFiles(final byte maxFiles) {
    mMaxFilesIndex = (byte)(maxFiles-1);
  }

  @Override
  protected void finalize() throws Throwable {
    mSink.close();
    mThread.interrupt();
    super.finalize();
  }
}
