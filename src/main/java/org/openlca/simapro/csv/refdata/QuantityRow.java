package org.openlca.simapro.csv.refdata;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class QuantityRow implements CsvRecord {

  private String name;
  private boolean hasDimension = true;

  public String name() {
    return name;
  }

  public QuantityRow name(String name) {
    this.name = name;
    return this;
  }

  public boolean hasDimension() {
    return hasDimension;
  }

  public QuantityRow hasDimension(boolean hasDimension) {
    this.hasDimension = hasDimension;
    return this;
  }

  public static QuantityRow read(CsvLine line) {
    return new QuantityRow()
      .name(line.getString(0))
      .hasDimension(line.getBoolean(1));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putBoolean(hasDimension)
      .writeln();
  }
}
