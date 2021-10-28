package org.openlca.simapro.csv;

import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.openlca.simapro.csv.refdata.UnitBlock;

import static org.junit.Assert.*;

public class UnitBlockTest {

  @Test
  public void testRead() throws Exception {
    CsvHeader header;
    try (var reader = readExampleFile()) {
      header = CsvHeader.readFrom(reader);
    }

    UnitBlock origin;
    try (var reader = readExampleFile()) {
      origin = readBlock(reader, header);
      assertNotNull(origin);
    }
    assertEquals(34, origin.units().size());


    var csv = Tests.toCsv(origin);
    var copy = readBlock(new StringReader(csv), header);
    assertNotNull(copy);

    for (int i = 0; i < origin.units().size(); i++) {
      var u1 = origin.units().get(i);
      var u2 = origin.units().get(i);
      assertEquals(u1.name(), u2.name());
      assertEquals(u1.quantity(), u2.quantity());
      assertEquals(u1.referenceUnit(), u2.referenceUnit());
      assertEquals(u1.conversionFactor(), u2.conversionFactor(), 1e-10);
    }
  }

  private UnitBlock readBlock(Reader reader, CsvHeader header) {
    var lines = CsvLine.iter(header, reader);
    for (var line : lines) {
      if (line.first().equals("Units")) {
        return UnitBlock.read(lines);
      }
    }
    fail();
    return null;
  }

  private Reader readExampleFile() {
    var stream = getClass().getResourceAsStream("process.csv");
    assertNotNull(stream);
    return Csv.readerOf(stream, StandardCharsets.UTF_8);
  }

  @Test
  public void testUnits() {
    var dataSet = Tests.testFile("process.csv");
    assertEquals(34, dataSet.units().size());

  }


}
