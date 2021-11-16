package org.openlca.simapro.csv.refdata;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class ElementaryFlowRow implements CsvRecord {

  private String name;
  private String unit;
  private String cas;
  private String comment;
  private String platformId;

  public String name() {
    return name;
  }

  public ElementaryFlowRow name(String name) {
    this.name = name;
    return this;
  }

  public String unit() {
    return unit;
  }

  public ElementaryFlowRow unit(String unit) {
    this.unit = unit;
    return this;
  }

  public String cas() {
    return cas;
  }

  public ElementaryFlowRow cas(String cas) {
    this.cas = cas;
    return this;
  }

  public String comment() {
    return comment;
  }

  public ElementaryFlowRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  public String platformId() { return platformId; }

  public ElementaryFlowRow platformId(String platformId) {
    this.platformId = platformId;
    return this;
  }

  public static ElementaryFlowRow read(CsvLine line) {
    return new ElementaryFlowRow()
      .name(line.getString(0))
      .unit(line.getString(1))
      .cas(line.getString(2))
      .comment(line.getString(3))
      .platformId(line.getString(4));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putString(unit)
      .putString(cas)
      .putString(comment)
      .putString(platformId)
      .writeln();
  }
}
