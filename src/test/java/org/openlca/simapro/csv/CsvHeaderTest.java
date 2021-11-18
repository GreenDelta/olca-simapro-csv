package org.openlca.simapro.csv;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

import org.junit.Test;

public class CsvHeaderTest {

  @Test
  public void testDefaults() {
    var header = new CsvHeader();
    assertEquals(';', header.csvSeparator());
    assertEquals('.', header.decimalSeparator());
    assertEquals('.', header.dateSeparator());
  }

  @Test
  public void testParseHeader() throws Exception {
    var stream = getClass().getResourceAsStream("header.csv");
    assertNotNull(stream);
    try (var reader = SimaProCsv.readerOf(stream, StandardCharsets.UTF_8)) {
      var header = CsvHeader.readFrom(reader);

      assertEquals("8.5.0.0", header.version());
      assertEquals("2019-10-24", header.date());
      assertEquals("18:35:10", header.time());
      assertEquals("Methods", header.project());
      assertEquals("8.0.5", header.formatVersion());
      assertEquals(';', header.csvSeparator());
      assertEquals('.', header.decimalSeparator());
      assertEquals('-', header.dateSeparator());
      assertEquals("yyyy-MM-dd", header.shortDateFormat().toPattern());
    }
  }
}
