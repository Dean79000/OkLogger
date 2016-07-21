package fr.cyberdean.oklogger;

public enum Level {
  DEBUG((byte)0), INFO((byte)1), WARN((byte)2), ERROR((byte)3), FATAL((byte)4);

  public byte intVal;
  Level(final byte intVal) {
    this.intVal = intVal;
  }
}
