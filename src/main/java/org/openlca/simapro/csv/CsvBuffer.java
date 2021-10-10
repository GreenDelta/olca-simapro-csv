package org.openlca.simapro.csv;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVPrinter;

public class CsvBuffer {

  private final CSVPrinter printer;
  private final char decimalSeparator;
  private final List<String> buffer = new ArrayList<>();

  public CsvBuffer(Writer writer, CsvHeader header) {
    this.decimalSeparator = header.decimalSeparator();
    var format = SimaProCsv.formatOf(header.csvSeparator());
    try {
      printer = new CSVPrinter(writer, format);
    } catch (IOException e) {
      throw new RuntimeException("failed to create CSVPrinter", e);
    }
  }

  public CsvBuffer writeln() {
    try {
      printer.printRecord(buffer);
    } catch (IOException e) {
      throw new RuntimeException("failed to print record", e);
    }
    buffer.clear();
    return this;
  }

  public CsvBuffer putString(String s) {
    if (s == null) {
      buffer.add("");
      return this;
    }
    buffer.add(s.replaceAll("\\r?\\n", Character.toString((char) 127)));
    return this;
  }

  public CsvBuffer putDouble(double d) {
    var s = Double.toString(d);
    return putString(decimalPoint(s));
  }

  public CsvBuffer putNumeric(Numeric n) {
    if (n == null) {
      putDouble(0);
    } else if (n.hasFormula()) {
      putString(decimalPoint(n.formula()));
    } else {
      putDouble(n.value());
    }
    return this;
  }

  public CsvBuffer putBoolean(boolean b) {
    return putString(b ? "Yes" : "No");
  }

  private String decimalPoint(String s) {
    if (s == null || s.length() == 0)
      return "";
    if (decimalSeparator == '.')
      return s;
    // TODO: handle ',' parameter separators etc.
    return s.replace('.', decimalSeparator);
  }

}
