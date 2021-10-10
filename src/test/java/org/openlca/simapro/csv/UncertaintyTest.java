package org.openlca.simapro.csv;

import java.io.StringWriter;

import org.apache.commons.csv.CSVPrinter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UncertaintyTest {

  @Test
  public void testReadUncertainty() {

    var line1 = Tests.lineOf(" ; ; Lognormal ; 42 ; 0 ; 0 ; ; ");
    var u1 = UncertaintyRecord.read(line1, 2);
    assertTrue(u1.isLogNormal());
    assertEquals(42.0, u1.getAsLogNormal().xsd(), 1e-10);

    var line2 = Tests.lineOf("normal;42");
    var u2 = UncertaintyRecord.read(line2, 0);
    assertTrue(u2.isNormal());
    assertEquals(42.0, u2.getAsNormal().xsd(), 1e-10);

    var line3 = Tests.lineOf(";;;Triangle;;1;2;;");
    var u3 = UncertaintyRecord.read(line3, 3);
    assertTrue(u3.isTriangle());
    var triangle = u3.getAsTriangle();
    assertEquals(1.0, triangle.min(), 1e-10);
    assertEquals(2.0, triangle.max(), 1e-10);

    var line4 = Tests.lineOf(";;;uniform;;1;2;;");
    var u4 = UncertaintyRecord.read(line4, 3);
    assertTrue(u4.isUniform());
    var uniform = u4.getAsUniform();
    assertEquals(1.0, uniform.min(), 1e-10);
    assertEquals(2.0, uniform.max(), 1e-10);

    var line5 = Tests.lineOf(";");
    var u5 = UncertaintyRecord.read(line5, 1);
    assertTrue(u5.isUndefined());
  }

  @Test
  public void testWriteUncertainty() throws Exception {
    var buffer = new StringWriter();
    var printer = new CSVPrinter(buffer, SimaProCsv.formatOf(';'));

  }

}
