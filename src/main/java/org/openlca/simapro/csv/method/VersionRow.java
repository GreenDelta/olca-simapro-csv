package org.openlca.simapro.csv.method;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class VersionRow implements CsvRecord {

  private int major;
  private int minor;

  public int major() {
    return major;
  }

  public VersionRow major(int major) {
    this.major = major;
    return this;
  }

  public int minor() {
    return minor;
  }

  public VersionRow minor(int minor) {
    this.minor = minor;
    return this;
  }

  public static VersionRow read(CsvLine line) {
    return new VersionRow()
      .major(line.getInt(0))
      .minor(line.getInt(1));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putInt(major)
      .putInt(minor)
      .writeln();
  }
}
