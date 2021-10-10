package org.openlca.simapro.csv;

import java.io.IOException;

import org.apache.commons.csv.CSVParser;

class Tests {

  static CsvLine lineOf(String line) {
    try {
      var r = CSVParser.parse(line, SimaProCsv.formatOf(';'))
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

}
