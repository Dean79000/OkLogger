package fr.cyberdean.oklogger.output;

import okio.BufferedSink;
import okio.Okio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileOutput implements Output {
  private BufferedSink mSink;
  private File mFile;

  //todo add FilePattern name
  private long mMaxFileSize = 50000;
  private byte mMaxFiles = 3;

  public FileOutput(final File file) throws FileNotFoundException {
    mFile = file;
    mSink = Okio.buffer(Okio.sink(file));
  }

  @Override
  public void append(final String str) {
    try {
      mSink.writeUtf8(str);
      mSink.flush();

      if (mFile.length() >= mMaxFileSize) {
        //todo prepare new file, and update mSink
        //todo clear old file if necessary
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
