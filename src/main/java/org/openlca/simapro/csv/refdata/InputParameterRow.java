package org.openlca.simapro.csv.refdata;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;
import org.openlca.simapro.csv.UncertaintyRecord;

public class InputParameterRow implements CsvRecord {

  private String name;
  private double value;
  private UncertaintyRecord uncertainty;
  private boolean isHidden;
  private String comment;

  public String name() {
    return name;
  }

  public InputParameterRow name(String name) {
    this.name = name;
    return this;
  }

  public String comment() {
    return comment;
  }

  public InputParameterRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  public UncertaintyRecord uncertainty() {
    return uncertainty;
  }

  public InputParameterRow uncertainty(UncertaintyRecord uncertainty) {
    this.uncertainty = uncertainty;
    return this;
  }

  public double value() {
    return value;
  }

  public InputParameterRow value(double value) {
    this.value = value;
    return this;
  }

  public boolean isHidden() {
    return isHidden;
  }

  public InputParameterRow isHidden(boolean hidden) {
    isHidden = hidden;
    return this;
  }

  public static InputParameterRow read(CsvLine line) {
    return new InputParameterRow()
      .name(line.getString(0))
      .value(line.getDouble(1))
      .uncertainty(UncertaintyRecord.read(line, 2))
      .isHidden(line.getBoolean(6))
      .comment(line.getString(7));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putDouble(value);

    var u = uncertainty != null
      ? uncertainty
      : UncertaintyRecord.undefined();
    u.write(buffer);

    buffer.putBoolean(isHidden)
      .putString(comment)
      .writeln();
  }
}
