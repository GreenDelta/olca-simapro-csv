package org.openlca.simapro.csv;

import java.io.StringWriter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CsvBufferTest {

  @Test
  public void testMultiLineStrings() {
    var writer = new StringWriter();
    var buffer = new CsvBuffer(writer, new CsvHeader());
    buffer.putString(" no line breaks ");
    buffer.putString(" unix\n line\n breaks\n ");
    buffer.putString(" windows\r\n line\r\n breaks\r\n ");
    buffer.writeln();

    var line = writer.toString().trim();
    var expected = "no line breaks;" +
                   "unix\u007F line\u007F breaks\u007F;" +
                   "windows\u007F line\u007F breaks\u007F";
    assertEquals(expected, line);
  }

}
