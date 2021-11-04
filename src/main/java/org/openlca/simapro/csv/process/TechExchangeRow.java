package org.openlca.simapro.csv.process;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;
import org.openlca.simapro.csv.Numeric;
import org.openlca.simapro.csv.UncertaintyRecord;

public class TechExchangeRow implements CsvRecord, ExchangeRow {

  private String name;
  private String unit;
  private Numeric amount;
  private UncertaintyRecord uncertainty;
  private String comment;
  private String pedigree;

  @Override
  public String name() {
    return name;
  }

  public TechExchangeRow name(String name) {
    this.name = name;
    return this;
  }

  @Override
  public String unit() {
    return unit;
  }

  public TechExchangeRow unit(String unit) {
    this.unit = unit;
    return this;
  }

  @Override
  public Numeric amount() {
    return amount;
  }

  public TechExchangeRow amount(Numeric amount) {
    this.amount = amount;
    return this;
  }

  public UncertaintyRecord uncertainty() {
    return uncertainty;
  }

  public TechExchangeRow uncertainty(UncertaintyRecord uncertainty) {
    this.uncertainty = uncertainty;
    return this;
  }

  @Override
  public String comment() {
    return comment;
  }

  public TechExchangeRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  public String pedigree() {
    return pedigree;
  }

  public TechExchangeRow pedigree(String pedigree) {
    this.pedigree = pedigree;
    return this;
  }

  public static TechExchangeRow read(CsvLine line) {
    var row = new TechExchangeRow()
      .name(line.getString(0))
      .unit(line.getString(1))
      .amount(line.getNumeric(2))
      .uncertainty(UncertaintyRecord.read(line, 3));

    var comment = line.getString(7);
    row.comment(comment);
    var pedigree = PedigreeMatcher.match(comment);
    row.pedigree(pedigree);
    return row;
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putString(unit)
      .putNumeric(amount);

    var u = uncertainty != null
      ? uncertainty
      : UncertaintyRecord.undefined();
    u.write(buffer);

    String c;
    if (comment != null) {
      c = pedigree != null
        ? pedigree + "\n" + comment
        : comment;
    } else {
      c = pedigree != null
        ? pedigree
        : "";
    }
    buffer.putString(c);
    buffer.writeln();
  }
}
