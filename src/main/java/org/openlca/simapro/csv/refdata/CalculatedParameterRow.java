package org.openlca.simapro.csv.refdata;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class CalculatedParameterRow implements CsvRecord {

  private String name;
  private String expression;
  private String comment;

  public String name() {
    return name;
  }

  public CalculatedParameterRow name(String name) {
    this.name = name;
    return this;
  }

  public String expression() {
    return expression;
  }

  public CalculatedParameterRow expression(String expression) {
    this.expression = expression;
    return this;
  }

  public String comment() {
    return comment;
  }

  public CalculatedParameterRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  public static CalculatedParameterRow read(CsvLine line) {
    return new CalculatedParameterRow()
      .name(line.getString(0))
      .expression(line.getFormula(1))
      .comment(line.getString(2));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(name)
      .putString(expression)
      .putString(comment)
      .writeln();
  }
}
