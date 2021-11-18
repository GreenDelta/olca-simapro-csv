package org.openlca.simapro.csv.process;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;
import org.openlca.simapro.csv.Numeric;
import org.openlca.simapro.csv.UncertaintyRecord;

public class ElementaryExchangeRow implements CsvRecord, ExchangeRow {

  private String name;
  private String subCompartment;
  private String unit;
  private Numeric amount;
  private UncertaintyRecord uncertainty;
  private String comment;
  private String platformId;

  @Override
  public String name() {
    return name;
  }

  public ElementaryExchangeRow name(String name) {
    this.name = name;
    return this;
  }

  public String subCompartment() {
    return subCompartment;
  }

  public ElementaryExchangeRow subCompartment(String subCompartment) {
    this.subCompartment = subCompartment;
    return this;
  }

  @Override
  public String unit() {
    return unit;
  }

  public ElementaryExchangeRow unit(String unit) {
    this.unit = unit;
    return this;
  }

  @Override
  public Numeric amount() {
    return amount;
  }

  public ElementaryExchangeRow amount(Numeric amount) {
    this.amount = amount;
    return this;
  }

  public UncertaintyRecord uncertainty() {
    return uncertainty;
  }

  public ElementaryExchangeRow uncertainty(UncertaintyRecord uncertainty) {
    this.uncertainty = uncertainty;
    return this;
  }

  @Override
  public String comment() {
    return comment;
  }

  public ElementaryExchangeRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  @Override
  public String platformId() { return platformId; }

  public ElementaryExchangeRow platformId(String platformId) {
    this.platformId = platformId;
    return this;
  }

  public static ElementaryExchangeRow read(CsvLine line) {
    return new ElementaryExchangeRow()
      .name(line.getString(0))
      .subCompartment(line.getString(1))
      .unit(line.getString(2))
      .amount(line.getNumeric(3))
      .uncertainty(UncertaintyRecord.read(line, 4))
      .comment(line.getString(8))
      .platformId(line.getString(9));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putString(subCompartment)
      .putString(unit)
      .putNumeric(amount);

    var u = uncertainty != null
      ? uncertainty
      : UncertaintyRecord.undefined();
    u.write(buffer);

    buffer.putString(comment)
      .putString(platformId);
    buffer.writeln();
  }
}
