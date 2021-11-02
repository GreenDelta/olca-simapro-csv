package org.openlca.simapro.csv.method;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class ImpactCategoryRow implements CsvRecord {

  private String name;
  private String unit;

  public String name() {
    return name;
  }

  public ImpactCategoryRow name(String name) {
    this.name = name;
    return this;
  }

  public String unit() {
    return unit;
  }

  public ImpactCategoryRow unit(String unit) {
    this.unit = unit;
    return this;
  }

  public static ImpactCategoryRow read(CsvLine line) {
    return new ImpactCategoryRow()
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
