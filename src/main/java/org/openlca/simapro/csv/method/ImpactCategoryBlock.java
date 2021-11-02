package org.openlca.simapro.csv.method;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvRecord;

public class ImpactCategoryBlock implements CsvRecord {

  private ImpactCategoryRow info;
  private final List<ImpactFactorRow> factors = new ArrayList<>();

  public ImpactCategoryRow info() {
    if (info == null) {
      info = new ImpactCategoryRow();
    }
    return info;
  }

  public ImpactCategoryBlock info(ImpactCategoryRow info) {
    this.info = info;
    return this;
  }

  public List<ImpactFactorRow> factors() {
    return factors;
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString("Impact category").writeln()
      .putRecord(info())
      .writeln()
      .putString("Substances").writeln()
      .putRecords(factors())
      .writeln();
  }
}
