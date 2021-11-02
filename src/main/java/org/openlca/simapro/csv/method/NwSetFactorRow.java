package org.openlca.simapro.csv.method;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class NwSetFactorRow implements CsvRecord {

  private String impactCategory;
  private double factor;

  public String impactCategory() {
    return impactCategory;
  }

  public NwSetFactorRow impactCategory(String impactCategory) {
    this.impactCategory = impactCategory;
    return this;
  }

  public double factor() {
    return factor;
  }

  public NwSetFactorRow factor(double factor) {
    this.factor = factor;
    return this;
  }

  public static NwSetFactorRow read(CsvLine line) {
    return new NwSetFactorRow()
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
