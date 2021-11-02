package org.openlca.simapro.csv.method;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvRecord;

public class ImpactCategoryBlock implements CsvRecord {

  private String name;
  private final List<ImpactFactorRow> factors = new ArrayList<>();

  public String name() {
    return name;
  }

  public ImpactCategoryBlock name(String name) {
    this.name = name;
    return this;
  }

  public List<ImpactFactorRow> factors() {
    return factors;
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString("Impact category").writeln()
      .putString(name()).writeln()
      .writeln()
      .putString("Substances").writeln()
      .putRecords(factors())
      .writeln();
  }
}
