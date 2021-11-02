package org.openlca.simapro.csv.method;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvRecord;

public class NwSetBlock implements CsvRecord {

  private String name;
  private final List<NwSetFactorRow> normalizationFactors = new ArrayList<>();
  private final List<NwSetFactorRow> weightingFactors = new ArrayList<>();

  public String name() {
    return name;
  }

  public NwSetBlock name(String name) {
    this.name = name;
    return this;
  }

  public List<NwSetFactorRow> normalizationFactors() {
    return normalizationFactors;
  }

  public List<NwSetFactorRow> weightingFactors() {
    return weightingFactors;
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString("Normalization-Weighting set").writeln()
      .putString(name).writeln()
      .writeln()
      .putString("Normalization").writeln()
      .putRecords(normalizationFactors())
      .writeln()
      .putString("Weighting").writeln()
      .putRecords(weightingFactors())
      .writeln();
  }
}
