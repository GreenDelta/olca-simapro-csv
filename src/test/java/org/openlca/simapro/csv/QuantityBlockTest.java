package org.openlca.simapro.csv;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityBlockTest {

  @Test
  public void testQuantities() {
    var ds = Tests.testFile("process.csv");
    assertEquals(3, ds.quantities().size());
    // TODO: @mg: assertContains as in UnitBlockTest
  }

}
