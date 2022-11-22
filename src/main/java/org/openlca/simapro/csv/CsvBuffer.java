package org.openlca.simapro.csv;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVPrinter;

public class CsvBuffer {

  private final CSVPrinter printer;
  private final char decimalSeparator;
  private final SimpleDateFormat dateFormat;
  private final List<String> buffer = new ArrayList<>();

  public CsvBuffer(Writer writer, CsvHeader header) {
    this.decimalSeparator = header.decimalSeparator();
    this.dateFormat = header.shortDateFormat();
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
    if (d == (int) d) {
      // reflect SimaPro behaviour when there is no fractional part
      return putString(Integer.toString((int) d));
    }
    var s = Double.toString(d);
    return putString(decimalPoint(s));
  }

  public CsvBuffer putInt(int i) {
    var s = Integer.toString(i);
    return putString(s);
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

  public CsvBuffer putDate(Date date) {
    if (date == null)
      return putString("");
    try {
      var s = dateFormat.format(date);
      return putString(s);
    } catch (Exception e) {
      return putString("");
    }
  }

  public CsvBuffer putRecord(CsvRecord record) {
    if (record != null) {
      record.write(this);
    }
    return this;
  }

  public CsvBuffer putRecords(Iterable<? extends CsvRecord> records) {
    for (var record : records) {
      putRecord(record);
    }
    return this;
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
