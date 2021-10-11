package org.openlca.simapro.csv;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.csv.CSVParser;

class Tests {

  static CsvLine lineOf(String line) {
    try {
      var r = CSVParser.parse(line, Csv.formatOf(';'))
        .iterator()
        .next();
      var h = new CsvHeader()
        .csvSeparator(';')
        .decimalSeparator(',');
      return CsvLine.of(r, h);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  static String toCsv(CsvRecord record) {
    if (record == null)
      return "";
    var writer = new StringWriter();
    var buffer = new CsvBuffer(writer, new CsvHeader());
    record.write(buffer);
    buffer.writeln();
    writer.flush();
    return writer.toString().trim();
  }

}
