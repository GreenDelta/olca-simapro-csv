package org.openlca.simapro.csv.process;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class WasteFractionRow implements CsvRecord {

  private String wasteTreatment;
  private String wasteType;
  private double fraction;
  private String comment;

  public String wasteTreatment() {
    return wasteTreatment;
  }

  public WasteFractionRow wasteTreatment(String wasteTreatment) {
    this.wasteTreatment = wasteTreatment;
    return this;
  }

  public String wasteType() {
    return wasteType;
  }

  public WasteFractionRow wasteType(String wasteType) {
    this.wasteType = wasteType;
    return this;
  }

  public double fraction() {
    return fraction;
  }

  public WasteFractionRow fraction(double fraction) {
    this.fraction = fraction;
    return this;
  }

  public String comment() {
    return comment;
  }

  public WasteFractionRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  public static WasteFractionRow read(CsvLine line) {
    return new WasteFractionRow()
      .wasteTreatment(line.getString(0))
      .wasteType(line.getString(1))
      .fraction(line.getDouble(2))
      .comment(line.getString(3));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(wasteTreatment)
      .putString(wasteType)
      .putDouble(fraction)
      .putString(comment)
      .writeln();
  }
}
