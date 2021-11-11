package org.openlca.simapro.csv;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimaProCsvTest {

  @Test
  public void testDefaultCharset() {
    var charset = SimaProCsv.defaultCharset();
    assertNotNull(charset);
    assertEquals("windows-1252", charset.name());
  }

}
