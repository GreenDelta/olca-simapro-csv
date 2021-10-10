package org.openlca.simapro.csv;

import java.io.IOException;

import org.apache.commons.csv.CSVParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CsvLineTest {

  @Test
  public void testGetString() {
    var line = lineOf(" a ;\" b \";c");
    for (int i = -1; i < 5; i++) {
      var s = line.getString(i);
      switch (i) {
        case 0:
          assertEquals("a", s);
          break;
        case 1:
          assertEquals("b", s);
          break;
        case 2:
          assertEquals("c", s);
          break;
        default:
          assertEquals("", s);
      }
    }
  }

  private CsvLine lineOf(String line) {
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
