package org.openlca.simapro.csv.process;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;
import org.openlca.simapro.csv.Numeric;

public class ProductOutputRow implements CsvRecord, RefExchangeRow {

  private String name;
  private String unit;
  private Numeric amount;
  private Numeric allocation;
  private String wasteType;
  private String category;
  private String comment;
  private String platformId;

  @Override
  public String name() {
    return name;
  }

  public ProductOutputRow name(String name) {
    this.name = name;
    return this;
  }

  @Override
  public String unit() {
    return unit;
  }

  public ProductOutputRow unit(String unit) {
    this.unit = unit;
    return this;
  }

  @Override
  public Numeric amount() {
    return amount == null
      ? Numeric.zero()
      : amount;
  }

  public ProductOutputRow amount(Numeric amount) {
    this.amount = amount;
    return this;
  }

  public Numeric allocation() {
    return allocation == null
      ? Numeric.zero()
      : allocation;
  }

  public ProductOutputRow allocation(Numeric allocation) {
    this.allocation = allocation;
    return this;
  }

  public String wasteType() {
    return wasteType;
  }

  public ProductOutputRow wasteType(String wasteType) {
    this.wasteType = wasteType;
    return this;
  }

  @Override
  public String category() {
    return category;
  }

  public ProductOutputRow category(String category) {
    this.category = category;
    return this;
  }

  @Override
  public String comment() {
    return comment;
  }

  public ProductOutputRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  @Override
  public String platformId() { return platformId; }

  public ProductOutputRow platformId(String platformId) {
    this.platformId = platformId;
    return this;
  }

  public static ProductOutputRow read(CsvLine line) {
    return new ProductOutputRow()
      .name(line.getString(0))
      .unit(line.getString(1))
      .amount(line.getNumeric(2))
      .allocation(line.getNumeric(3))
      .wasteType(line.getString(4))
      .category(line.getString(5))
      .comment(line.getString(6))
      .platformId(line.getString(7));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putString(unit)
      .putNumeric(amount)
      .putNumeric(allocation)
      .putString(wasteType)
      .putString(category)
      .putString(comment)
      .putString(platformId)
      .writeln();
  }
}
