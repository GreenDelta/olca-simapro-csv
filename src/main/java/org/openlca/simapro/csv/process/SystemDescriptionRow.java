package org.openlca.simapro.csv.process;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class SystemDescriptionRow implements CsvRecord {

  private String name;
  private String comment;

  public String name() {
    return name;
  }

  public SystemDescriptionRow name(String name) {
    this.name = name;
    return this;
  }

  public String comment() {
    return comment;
  }

  public SystemDescriptionRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  public static SystemDescriptionRow read(CsvLine line) {
    return new SystemDescriptionRow()
      .name(line.getString(0))
      .comment(line.getString(1));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putString(comment)
      .writeln();
  }
}
