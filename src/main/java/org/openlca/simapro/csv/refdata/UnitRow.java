package org.openlca.simapro.csv.refdata;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class UnitRow implements CsvRecord {

  private double conversionFactor = 1;
  private String name = "";
  private String referenceUnit = "";
  private String quantity = "";

  public double conversionFactor() {
    return conversionFactor;
  }

  public UnitRow conversionFactor(double conversionFactor) {
    this.conversionFactor = conversionFactor;
    return this;
  }

  public String name() {
    return name;
  }

  public UnitRow name(String name) {
    this.name = name != null ? name : "";
    return this;
  }

  public String referenceUnit() {
    return referenceUnit;
  }

  public UnitRow referenceUnit(String referenceUnit) {
    this.referenceUnit = referenceUnit != null ? referenceUnit : "";
    return this;
  }

  public String quantity() {
    return quantity;
  }

  public UnitRow quantity(String quantity) {
    this.quantity = quantity != null ? quantity : "";
    return this;
  }

  public static UnitRow read(CsvLine line) {
    return new UnitRow()
      .name(line.getString(0))
      .quantity(line.getString(1))
      .conversionFactor(line.getDouble(2))
      .referenceUnit(line.getString(3));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putString(quantity)
      .putDouble(conversionFactor)
      .putString(referenceUnit)
      .writeln();
  }
}
