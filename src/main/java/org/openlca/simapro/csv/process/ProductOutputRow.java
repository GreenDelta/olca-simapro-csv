package org.openlca.simapro.csv.process;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;
import org.openlca.simapro.csv.Numeric;

public class ProductOutputRow implements CsvRecord {

  private String name;
  private String unit;
  private Numeric amount;
  private String allocation;
  private String wasteType;
  private String category;
  private String comment;

  public String name() {
    return name;
  }

  public ProductOutputRow name(String name) {
    this.name = name;
    return this;
  }

  public String unit() {
    return unit;
  }

  public ProductOutputRow unit(String unit) {
    this.unit = unit;
    return this;
  }

  public Numeric amount() {
    return amount;
  }

  public ProductOutputRow amount(Numeric amount) {
    this.amount = amount;
    return this;
  }

  public String allocation() {
    return allocation;
  }

  public ProductOutputRow allocation(String allocation) {
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

  public String category() {
    return category;
  }

  public ProductOutputRow category(String category) {
    this.category = category;
    return this;
  }

  public String comment() {
    return comment;
  }

  public ProductOutputRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  public static ProductOutputRow read(CsvLine line) {
    return new ProductOutputRow()
      .name(line.getString(0))
      .unit(line.getString(1))
      .amount(line.getNumeric(2))
      .allocation(line.getString(3))
      .wasteType(line.getString(4))
      .category(line.getString(5))
      .comment(line.getString(6));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putString(unit)
      .putNumeric(amount)
      .putString(allocation)
      .putString(wasteType)
      .putString(category)
      .putString(comment)
      .writeln();
  }
}
