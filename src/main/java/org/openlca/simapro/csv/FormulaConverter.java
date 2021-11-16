package org.openlca.simapro.csv;

import java.util.Objects;

public class FormulaConverter {

  private final CsvHeader header;
  private char decimalSeparator;
  private char parameterSeparator;

  private FormulaConverter(CsvHeader header) {
    this.header = Objects.requireNonNull(header);
    this.decimalSeparator = header.decimalSeparator();
    this.parameterSeparator = decimalSeparator == ','
      ? ';'
      : ',';
  }

  public static FormulaConverter of(CsvHeader header) {
    return new FormulaConverter(header);
  }

  public FormulaConverter decimalSeparator(char c) {
    this.decimalSeparator = c;
    return this;
  }

  public FormulaConverter parameterSeparator(char parameterSeparator) {
    this.parameterSeparator = parameterSeparator;
    return this;
  }

  public String convert(String formula) {
    if (formula == null)
      return null;
    var buffer = new StringBuilder();
    for (var i = 0; i < formula.length(); i++) {
      var c = formula.charAt(i);
      if (c == header.decimalSeparator()) {
        buffer.append(decimalSeparator);
      } else if (c == ',' || c == ';') {
        buffer.append(parameterSeparator);
      } else {
        buffer.append(c);
      }
    }
    return buffer.toString();
  }
}
