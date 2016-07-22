package fr.cyberdean.oklogger.output;

import okio.BufferedSink;
import okio.Okio;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileOutput, write logs in limited number of files and backup them when they reach a certain size.
 * @author Dean79000
 */
public class FileOutput implements Output {
  private BufferedSink mSink;
  private File mFile;
  private final Lock mRollOverLock = new ReentrantLock();
  protected long mCount = 0;

  //todo add FilePattern name

  /**
   The default maximum file size is 10MB.
   */
  private long mMaxFileSize = 10485760;

  /**
   There is two backup file by default.
   */
  private byte mMaxFiles = 2; //todo -1 or change nbMax to maxIndex

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
  }

  @Override
  public void append(final String str) {
    try {
      mSink.writeUtf8(str);
      mSink.flush();

      mCount += str.length();
      if (mCount >= mMaxFileSize && mRollOverLock.tryLock()) {
        boolean canContinue = true;
        final String path = mFile.getAbsolutePath();
        try {
          File file = new File(path + '.' + mMaxFiles); //todo -1 or change nbMax to maxIndex
          if (file.exists()) {
            canContinue = file.delete(); //Delete old file
          }

          // Map {(maxBackupIndex - 1), ..., 2, 1} to {maxBackupIndex, ..., 3, 2}
          File target;
          for (int i = mMaxFiles - 1; i >= 1 && canContinue; i--) {   //todo -2 or change nbMax to maxIndex
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
        mRollOverLock.unlock();
      }
    }
    catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public long getMaxFileSize() {
    return mMaxFileSize;
  }

  public void setMaxFileSize(final long maxFileSize) {
    mMaxFileSize = maxFileSize;
  }

  public byte getMaxFiles() {
    return mMaxFiles;
  }

  public void setMaxFiles(final byte maxFiles) {
    mMaxFiles = maxFiles;
  }

  @Override
  protected void finalize() throws Throwable {
    mSink.close();
    super.finalize();
  }
}
