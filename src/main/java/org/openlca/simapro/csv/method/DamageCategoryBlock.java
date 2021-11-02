package org.openlca.simapro.csv.method;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvRecord;

public class DamageCategoryBlock implements CsvRecord {

  private DamageCategoryRow info;
  private final List<DamageFactorRow> factors = new ArrayList<>();

  public DamageCategoryRow info() {
    if (info == null) {
      info = new DamageCategoryRow();
    }
    return info;
  }

  public DamageCategoryBlock info(DamageCategoryRow info) {
    this.info = info;
    return this;
  }

  public List<DamageFactorRow> factors() {
    return factors;
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString("Damage category").writeln()
      .putRecord(info())
      .writeln()
      .putString("Impact categories").writeln()
      .putRecords(factors())
      .writeln();
  }
}
