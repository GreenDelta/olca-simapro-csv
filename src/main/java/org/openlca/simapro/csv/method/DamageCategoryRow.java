package org.openlca.simapro.csv.method;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class DamageCategoryRow implements CsvRecord {

  private String name;
  private String unit;

  public String name() {
    return name;
  }

  public DamageCategoryRow name(String name) {
    this.name = name;
    return this;
  }

  public String unit() {
    return unit;
  }

  public DamageCategoryRow unit(String unit) {
    this.unit = unit;
    return this;
  }

  public static DamageCategoryRow read(CsvLine line) {
    return new DamageCategoryRow()
      .name(line.getString(0))
      .unit(line.getString(1));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name())
      .putString(unit())
      .writeln();
  }

}
