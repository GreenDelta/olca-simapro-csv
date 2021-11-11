package org.openlca.simapro.csv;

import org.junit.Test;
import org.openlca.simapro.csv.refdata.QuantityRow;

import static org.junit.Assert.*;

public class QuantityBlockTest {

  @Test
  public void testQuantities() {
    var ds = Tests.testDataSet("process.csv");
    assertEquals(3, ds.quantities().size());

    assertContains(ds, new QuantityRow()
      .name("Mass")
      .hasDimension(true));

    assertContains(ds, new QuantityRow()
      .name("Energy")
      .hasDimension(true));

    assertContains(ds, new QuantityRow()
      .name("Length")
      .hasDimension(true));
  }

  private void assertContains(CsvDataSet ds, QuantityRow expected) {
    boolean found = false;
    for (var quantity : ds.quantities()) {
      if (quantity.name().equals(expected.name())) {
        found = true;
        assertEquals(expected.hasDimension(), quantity.hasDimension());
      }
    }
    assertTrue(found);
  }

}
