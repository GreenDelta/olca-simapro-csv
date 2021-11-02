package org.openlca.simapro.csv.method;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class DamageFactorRow implements CsvRecord {

  private String impactCategory;
  private double factor;

  public String impactCategory() {
    return impactCategory;
  }

  public DamageFactorRow impactCategory(String impactCategory) {
    this.impactCategory = impactCategory;
    return this;
  }

  public double factor() {
    return factor;
  }

  public DamageFactorRow factor(double factor) {
    this.factor = factor;
    return this;
  }

  public static DamageFactorRow read(CsvLine line) {
    return new DamageFactorRow()
      .impactCategory(line.getString(0))
      .factor(line.getDouble(1));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(impactCategory())
      .putDouble(factor())
      .writeln();
  }

}
