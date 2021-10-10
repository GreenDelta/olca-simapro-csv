package org.openlca.simapro.csv.process;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;
import org.openlca.simapro.csv.Numeric;

public class WasteTreatmentRow implements CsvRecord {

  private String name;
  private String unit;
  private Numeric amount;
  private String wasteType;
  private String category;
  private String comment;

  public String name() {
    return name;
  }

  public WasteTreatmentRow name(String name) {
    this.name = name;
    return this;
  }

  public String unit() {
    return unit;
  }

  public WasteTreatmentRow unit(String unit) {
    this.unit = unit;
    return this;
  }

  public Numeric amount() {
    return amount;
  }

  public WasteTreatmentRow amount(Numeric amount) {
    this.amount = amount;
    return this;
  }

  public String wasteType() {
    return wasteType;
  }

  public WasteTreatmentRow wasteType(String wasteType) {
    this.wasteType = wasteType;
    return this;
  }

  public String category() {
    return category;
  }

  public WasteTreatmentRow category(String category) {
    this.category = category;
    return this;
  }

  public String comment() {
    return comment;
  }

  public WasteTreatmentRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  public static WasteTreatmentRow read(CsvLine line) {
    return new WasteTreatmentRow()
      .name(line.getString(0))
      .unit(line.getString(1))
      .amount(line.getNumeric(2))
      .wasteType(line.getString(3))
      .category(line.getString(4))
      .comment(line.getString(5));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putString(unit)
      .putNumeric(amount)
      .putString(wasteType)
      .putString(category)
      .putString(comment)
      .writeln();
  }
}
