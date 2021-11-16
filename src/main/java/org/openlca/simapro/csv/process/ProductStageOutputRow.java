package org.openlca.simapro.csv.process;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;
import org.openlca.simapro.csv.Numeric;

public class ProductStageOutputRow implements CsvRecord, RefExchangeRow {

  private String name;
  private String unit;
  private Numeric amount;
  private String category;
  private String comment;
  private String platformId;

  @Override
  public String name() {
    return name;
  }

  public ProductStageOutputRow name(String name) {
    this.name = name;
    return this;
  }

  @Override
  public String unit() {
    return unit;
  }

  public ProductStageOutputRow unit(String unit) {
    this.unit = unit;
    return this;
  }

  @Override
  public Numeric amount() {
    return amount;
  }

  public ProductStageOutputRow amount(Numeric amount) {
    this.amount = amount;
    return this;
  }

  @Override
  public String category() {
    return category;
  }

  public ProductStageOutputRow category(String category) {
    this.category = category;
    return this;
  }

  public String comment() {
    return comment;
  }

  public ProductStageOutputRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  @Override
  public String platformId() { return platformId; }

  public ProductStageOutputRow platformId(String platformId) {
    this.platformId = platformId;
    return this;
  }

  public static ProductStageOutputRow read(CsvLine line) {
    return new ProductStageOutputRow()
      .name(line.getString(0))
      .unit(line.getString(1))
      .amount(line.getNumeric(2))
      .category(line.getString(3))
      .comment(line.getString(4))
      .platformId(line.getString(5));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putString(unit)
      .putNumeric(amount)
      .putString(category)
      .putString(comment)
      .putString(platformId)
      .writeln();
  }
}
